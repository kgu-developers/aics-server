package kgu.developers.admin.graduationUser.application;

import kgu.developers.admin.graduationUser.presentation.request.GraduationUserBulkDeleteRequest;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserCreateRequest;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserBulkDeleteResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserDetailResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserPersistResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserSummaryPageResponse;
import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    public GraduationUserSummaryPageResponse getGraduationUsersByNameAndGraduationType(PageRequest pageable, String name, GraduationType graduationType) {
        PaginatedListResponse<GraduationUser> response = graduationUserQueryService.getUsersByNameAndGraduationType(pageable,name,graduationType);
        return GraduationUserSummaryPageResponse.of(response.contents(), response.pageable());
    }

    public void deleteGraduationUser(Long id) {
        GraduationUser graduationUser = graduationUserQueryService.getById(id);
        graduationUserCommandService.deleteGraduationUser(graduationUser);
    }

    public GraduationUserDetailResponse getGrduationUserById(Long graduationUserId) {
        return GraduationUserDetailResponse.from(graduationUserQueryService.getById(graduationUserId));
    }

    public GraduationUserBulkDeleteResponse deleteGraduationUsers(GraduationUserBulkDeleteRequest request) {
        List<GraduationUser> users = request.ids().stream()
            .map(graduationUserQueryService::getById)
            .toList();

        List<Long> deletedUsers = users.stream()
            .map(graduationUserCommandService::deleteGraduationUser)
            .toList();

        return GraduationUserBulkDeleteResponse.from(deletedUsers);
    }
}
