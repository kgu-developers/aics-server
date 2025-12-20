package kgu.developers.domain.graduationUser.application.query;

import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.certificate.domain.CertificateRepository;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.domain.GraduationUserExcel;
import kgu.developers.domain.graduationUser.domain.GraduationUserRepository;
import kgu.developers.domain.graduationUser.exception.GraduationUserNotFoundException;
import kgu.developers.domain.graduationUser.infrastructure.excel.GraduationUserExcelRow;
import kgu.developers.domain.thesis.domain.ThesisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GraduationUserQueryService {
    private final GraduationUserRepository graduationUserRepository;
    private final ThesisRepository thesisRepository;
    private final CertificateRepository certificateRepository;
    private final GraduationUserExcel graduationUserExcel;

    public GraduationUser getById(Long graduationUserId) {
        return graduationUserRepository.findByIdAndDeletedAtIsNull(graduationUserId)
            .orElseThrow(GraduationUserNotFoundException::new);
    }
    public GraduationType getGraduationTypeByUserId(String graduationUserId) {
        return graduationUserRepository.findByUserIdAndDeletedAtIsNull(graduationUserId)
                .map(GraduationUser::getGraduationType)
                .orElse(null);
    }
    public PaginatedListResponse<GraduationUser> getGraduationUsersByNameAndGraduationType(Pageable pageable, String name, GraduationType graduationType) {
        return graduationUserRepository.findAllByNameAndGraduationTypeOrderByIdAsc(pageable,name, graduationType);
    }

    public byte[] getGraduationUsersExcelByGraduationType(GraduationType graduationType) {
        List<GraduationUser> graduationUsers = graduationUserRepository.findAllByGraduationTypeOrderByIdAsc(graduationType);

        List<GraduationUserExcelRow> graduationUserExcelRows = graduationUsers.stream()
            .map(this::getGraduationUserExcelRow)
            .toList();

        return graduationUserExcel.generate(graduationUserExcelRows);
    }

    private GraduationUserExcelRow getGraduationUserExcelRow(GraduationUser graduationUser) {
        return GraduationUserExcelRow.from(graduationUser, determineStage(graduationUser), determineStatus(graduationUser));
    }

    private String determineStage(GraduationUser user) {
        if (user.getGraduationType() == null) {
            return "졸업 유형 미제출";
        }

        if (user.getAdvisorProfessor() == null) {
            return "지도교수 미배정";
        }

        if (user.getGraduationType() == GraduationType.THESIS) {
            if (user.getMidThesisId() == null) {
                return "중간 논문 미제출";
            }
            if (user.getFinalThesisId() == null) {
                return "최종 논문 미제출";
            }
            return "최종 논문 제출";
        }
        else if (user.getGraduationType() == GraduationType.CERTIFICATE) {
            if (user.getCertificateId() == null) {
                return "자격증 미제출";
            }
            return "자격증 제출";
        }

        return "졸업 요건 충족";
    }

    private String determineStatus(GraduationUser user) {
        if (user.getGraduationType() == GraduationType.THESIS) {
            if (user.getFinalThesisId() != null) {
                return thesisRepository.findApprovalByIdAndDeletedAtIsNull(user.getFinalThesisId())
                    .map(approved -> approved ? "승인" : "미승인")
                    .orElse("미승인");
            }
            if (user.getMidThesisId() != null) {
                return thesisRepository.findApprovalByIdAndDeletedAtIsNull(user.getMidThesisId())
                    .map(approved -> approved ? "승인" : "미승인")
                    .orElse("미승인");
            }
        }
        else if (user.getGraduationType() == GraduationType.CERTIFICATE) {
            if (user.getCertificateId() != null) {
                return certificateRepository.findApprovalByIdAndDeletedAtIsNull(user.getCertificateId())
                    .map(approved -> approved ? "승인" : "미승인")
                    .orElse("미승인");
            }
        }

        return "대기";
    }

    public GraduationUser getByUserId(String userId) {
        return graduationUserRepository.findByUserIdAndDeletedAtIsNull(userId)
            .orElseThrow(GraduationUserNotFoundException::new);
    }
}
