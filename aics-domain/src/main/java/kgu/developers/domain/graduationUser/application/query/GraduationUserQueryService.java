package kgu.developers.domain.graduationUser.application.query;

import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.domain.GraduationUserExcel;
import kgu.developers.domain.graduationUser.domain.GraduationUserRepository;
import kgu.developers.domain.graduationUser.exception.GraduationUserNotFoundException;
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
    private final GraduationUserExcel graduationUserExcel;

    public GraduationUser getById(Long graduationUserId) {
        return graduationUserRepository.findByIdAndDeletedAtIsNull(graduationUserId)
            .orElseThrow(GraduationUserNotFoundException::new);
    }

    public PaginatedListResponse<GraduationUser> getGraduationUsersByNameAndGraduationType(Pageable pageable, String name, GraduationType graduationType) {
        return graduationUserRepository.findAllByNameAndGraduationTypeOrderByIdAsc(pageable,name, graduationType);
    }

    public byte[] getGraduationUsersExcelByGraduationType(GraduationType graduationType) {
        List<GraduationUser> graduationUsers = graduationUserRepository.findAllByGraduationTypeOrderByIdAsc(graduationType);
        return graduationUserExcel.generate(graduationUsers); //TODO: 추후 Approval 여부를 함께 넘겨주어야 함
    }
}
