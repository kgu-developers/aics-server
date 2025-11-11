package kgu.developers.api.graduationUser.application;

import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class GraduationuserFacade {
    private final GraduationUserQueryService graduationUserQueryService;
    private final GraduationUserCommandService graduationUserCommandService;
    private final UserQueryService userQueryService;

    public void selectGraduationType(Long graduationUserId, GraduationType type) {
        GraduationUser graduationUser = graduationUserQueryService.getById(graduationUserId);
        System.out.println(graduationUser.getUserId());
        //graduationUser.validateAccessPermission(userQueryService.me().getId());
        graduationUserCommandService.selectGraduationType(graduationUser,type);
    }
}
