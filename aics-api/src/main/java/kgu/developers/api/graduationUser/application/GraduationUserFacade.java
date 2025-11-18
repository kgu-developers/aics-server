package kgu.developers.api.graduationUser.application;

import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.user.application.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class GraduationUserFacade {
    private final GraduationUserQueryService graduationUserQueryService;
    private final GraduationUserCommandService graduationUserCommandService;
    private final UserQueryService userQueryService;

    public void updateGraduationType(Long graduationUserId, GraduationType type) {
        GraduationUser graduationUser = graduationUserQueryService.getById(graduationUserId);
        graduationUser.validateAccessPermission(userQueryService.me().getId());
        graduationUserCommandService.updateGraduationType(graduationUser,type);
    }

    public void updateGraduationUserEmail(Long graduationUserId, String email) {
        GraduationUser graduationUser = graduationUserQueryService.getById(graduationUserId);
        graduationUser.validateAccessPermission(userQueryService.me().getId());
        graduationUserCommandService.updateGraduationUserEmail(graduationUser,email);
    }
}
