package kgu.developers.api.graduationUser.application;

import kgu.developers.api.graduationUser.presentation.response.MyGraduationUserResponse;
import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.user.application.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GraduationUserFacade {
    private final GraduationUserQueryService graduationUserQueryService;
    private final GraduationUserCommandService graduationUserCommandService;
    private final UserQueryService userQueryService;

    public void updateGraduationType(GraduationType type) {
        String userId = userQueryService.getMyId();
        GraduationUser graduationUser = graduationUserQueryService.getByUserId(userId);
        graduationUser.validateAccessPermission(userQueryService.getMyId());
        graduationUserCommandService.updateGraduationType(graduationUser,type);
    }

    public void updateGraduationUserEmail(String email) {
        String userId = userQueryService.getMyId();
        GraduationUser graduationUser = graduationUserQueryService.getByUserId(userId);
        graduationUser.validateAccessPermission(userQueryService.getMyId());
        graduationUserCommandService.updateGraduationUserEmail(graduationUser,email);
    }

    public MyGraduationUserResponse getMyGraduationUser() {
        String userId = userQueryService.getMyId();
        GraduationUser graduationUser = graduationUserQueryService.getByUserId(userId);
        return MyGraduationUserResponse.from(graduationUser);
    }
}
