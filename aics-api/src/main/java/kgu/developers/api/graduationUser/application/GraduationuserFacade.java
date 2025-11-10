package kgu.developers.api.graduationUser.application;

import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class GraduationuserFacade {
    private final GraduationUserQueryService graduationUserQueryService;
    private final GraduationUserCommandService graduationUserCommandService;

    public void selectGraduationType(Long graduationUserId, GraduationType type) {
        //TODO: 어떤 계정으로 로그인 중인지 알아야 함
        GraduationUser graduationUser = graduationUserQueryService.getById(graduationUserId);
        graduationUserCommandService.selectGraduationType(graduationUser,type);

    }
}
