package kgu.developers.admin.graduationUser.application;

import kgu.developers.admin.graduationUser.presentation.dto.GraduationUserExcelFileDto;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserBatchApproveRequest;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserBatchCreateRequest;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserBatchDeleteRequest;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserCreateRequest;
import kgu.developers.admin.graduationUser.presentation.response.*;
import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.certificate.application.command.CertificateCommandService;
import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.thesis.application.command.ThesisCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class GraduationUserAdminFacade {

    private final GraduationUserCommandService graduationUserCommandService;
    private final GraduationUserQueryService graduationUserQueryService;
    private final ThesisCommandService thesisCommandService;
    private final CertificateCommandService certificateCommandService;

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
        return GraduationUserSummaryPageResponse.of(response.contents(), response.pageable());
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

        List<Long> approvedUserIds = null;

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
