package kgu.developers.admin.graduationUser.application;

import kgu.developers.admin.graduationUser.presentation.dto.GraduationUserExcelFileDto;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserBatchCreateRequest;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserBatchDeleteRequest;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserCreateRequest;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserBatchCreateResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserBatchDeleteResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserDetailResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserPersistResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserSummaryPageResponse;
import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class GraduationUserAdminFacade {

    private final GraduationUserCommandService graduationUserCommandService;
    private final GraduationUserQueryService graduationUserQueryService;

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
}
