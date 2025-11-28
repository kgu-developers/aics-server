package kgu.developers.admin.graduationUser.application;

import kgu.developers.admin.graduationUser.presentation.dto.GraduationUserExcelFileDto;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserBatchApproveRequest;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserBatchCreateRequest;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserBatchDeleteRequest;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserCreateRequest;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserBatchApproveResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserBatchCreateResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserBatchDeleteResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserDetailResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserPersistResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserStatusResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserSummaryPageResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserSummaryResponse;
import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.certificate.application.command.CertificateCommandService;
import kgu.developers.domain.certificate.application.query.CertificateQueryService;
import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.thesis.application.command.ThesisCommandService;
import kgu.developers.domain.thesis.application.query.ThesisQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GraduationUserAdminFacade {

    private final GraduationUserCommandService graduationUserCommandService;
    private final GraduationUserQueryService graduationUserQueryService;
    private final ThesisCommandService thesisCommandService;
    private final ThesisQueryService thesisQueryService;
    private final CertificateCommandService certificateCommandService;
    private final CertificateQueryService certificateQueryService;

    public GraduationUserPersistResponse createGraduationUser(GraduationUserCreateRequest request) {
        Long id = graduationUserCommandService.createGraduationUser(request.studentId(), request.name(), request.advisorProfessor(), request.capstoneCompletion(), request.department(), request.graduationDate());
        return GraduationUserPersistResponse.of(id);
    }

    public GraduationUserBatchCreateResponse createGraduationUsers(GraduationUserBatchCreateRequest request) {

        List<Long> ids = request.graduationUsers().stream()
            .map(graduationUser -> graduationUserCommandService.createGraduationUser(
                graduationUser.studentId(),
                graduationUser.name(),
                graduationUser.advisorProfessor(),
                graduationUser.capstoneCompletion(),
                graduationUser.department(),
                graduationUser.graduationDate()
            ))
            .toList();

        return GraduationUserBatchCreateResponse.from(ids);
    }

    public GraduationUserSummaryPageResponse getGraduationUsersByNameAndGraduationType(Pageable pageable, String name, GraduationType graduationType) {
        PaginatedListResponse<GraduationUser> response = graduationUserQueryService.getGraduationUsersByNameAndGraduationType(pageable,name,graduationType);

        List<GraduationUserSummaryResponse> graduationUserSummaryResponses = response.contents().stream()
                .map(this::createSummaryResponse)
                .toList();

        return GraduationUserSummaryPageResponse.of(graduationUserSummaryResponses, response.pageable());
    }

    private GraduationUserSummaryResponse createSummaryResponse(GraduationUser user) {
        GraduationUserStatusResponse status = buildSubmissionStatus(user);
        return GraduationUserSummaryResponse.of(user, status);
    }

    private GraduationUserStatusResponse buildSubmissionStatus(GraduationUser user) {
        if (user.getGraduationType() == null) {
            return null;
        }

        return switch (user.getGraduationType()) {
            case CERTIFICATE -> buildCertificateStatus(user.getCertificateId());
            case THESIS -> buildThesisStatus(user.getMidThesisId(), user.getFinalThesisId());
        };
    }

    private GraduationUserStatusResponse.Certificate buildCertificateStatus(Long certificateId) {
        boolean submitted = certificateId != null;
        boolean approval = false;
        if(submitted) {
            approval = certificateQueryService.isApproved(certificateId);
        }

        return new GraduationUserStatusResponse.Certificate("CERTIFICATE", submitted, approval);
    }

    private GraduationUserStatusResponse.Thesis buildThesisStatus(Long middleThesisId, Long finalThesisId) {

        boolean midThesisSubmitted = middleThesisId != null;
        boolean midThesisapproval = false;
        if(midThesisSubmitted) {
            midThesisapproval = thesisQueryService.isApproved(middleThesisId);
        }

        GraduationUserStatusResponse.Thesis.Middle midStatus =
                new GraduationUserStatusResponse.Thesis.Middle(midThesisSubmitted,midThesisapproval);

        boolean finalThesisSubmitted = finalThesisId != null;
        boolean finalThesisapproval = false;
        if(finalThesisSubmitted) {
            finalThesisapproval = thesisQueryService.isApproved(finalThesisId);
        }

        GraduationUserStatusResponse.Thesis.Final finalStatus =
                new GraduationUserStatusResponse.Thesis.Final(finalThesisSubmitted,finalThesisapproval);

        return new GraduationUserStatusResponse.Thesis("THESIS", midStatus, finalStatus);
    }

    public void deleteGraduationUser(Long id) {
        GraduationUser graduationUser = graduationUserQueryService.getById(id);
        graduationUserCommandService.deleteGraduationUser(graduationUser);
    }

    public GraduationUserDetailResponse getGraduationUserById(Long graduationUserId) {
        return GraduationUserDetailResponse.from(graduationUserQueryService.getById(graduationUserId));
    }

    public GraduationUserBatchDeleteResponse deleteGraduationUsers(GraduationUserBatchDeleteRequest request) {
        List<GraduationUser> users = request.ids().stream()
            .map(graduationUserQueryService::getById)
            .toList();

        List<Long> deletedUsersIds = users.stream()
            .map(graduationUserCommandService::deleteGraduationUser)
            .toList();

        return GraduationUserBatchDeleteResponse.from(deletedUsersIds);
    }

    public GraduationUserExcelFileDto getGraduateUsersExcelByGraduationType(GraduationType graduationType) {

        byte[] content = graduationUserQueryService.getGraduationUsersExcelByGraduationType(graduationType);

        String filename = String.format("graduate_users_%s_%s.xlsx",
            graduationType != null ? graduationType.name().toLowerCase() : "all",
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")));

        return GraduationUserExcelFileDto.from(content, filename);
    }

    public GraduationUserBatchApproveResponse approveGraduationUsers(GraduationUserBatchApproveRequest request) {
        List<GraduationUser> users = request.ids().stream()
                .map(graduationUserQueryService::getById)
                .toList();

        List<Long> approvedUserIds = new ArrayList<>();

        for(GraduationUser user: users) {
            if(user.getGraduationType() == GraduationType.CERTIFICATE) {
                if(user.getCertificateId() == null) continue;
                boolean approved = certificateCommandService.approve(user.getCertificateId());
                if(approved) approvedUserIds.add(user.getId());
            } else if(user.getGraduationType() == GraduationType.THESIS) {
                boolean midThesisapproved = false;
                boolean finalThesisapproved = false;

                if(user.getMidThesisId() != null) {
                    midThesisapproved = thesisCommandService.approve(user.getMidThesisId());
                }

                if(user.getFinalThesisId() != null) {
                    finalThesisapproved = thesisCommandService.approve(user.getFinalThesisId());
                }

                if(midThesisapproved || finalThesisapproved)
                    approvedUserIds.add(user.getId());
            }
        }

        return GraduationUserBatchApproveResponse.from(approvedUserIds);
    }
}
