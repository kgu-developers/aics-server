package kgu.developers.api.graduationUser.application;

import kgu.developers.api.graduationUser.presentation.response.MyGraduationUserResponse;
import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GraduationUserFacade {
    private final GraduationUserQueryService graduationUserQueryService;
    private final GraduationUserCommandService graduationUserCommandService;

    public void updateGraduationType(GraduationType type) {
        GraduationUser graduationUser = graduationUserQueryService.me();
        graduationUserCommandService.updateGraduationType(graduationUser,type);
    }

    public void updateGraduationUserEmail(String email) {
        GraduationUser graduationUser = graduationUserQueryService.me();
        graduationUserCommandService.updateGraduationUserEmail(graduationUser,email);
    }

    public MyGraduationUserResponse getMyGraduationUser() {
        GraduationUser graduationUser = graduationUserQueryService.me();
        return MyGraduationUserResponse.from(graduationUser);
    }
}
