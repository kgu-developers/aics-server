package kgu.developers.domain.graduationUser.application.command;

import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.domain.GraduationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GraduationUserCommandService {
    private final GraduationUserRepository graduationUserRepository;

    public void selectGraduationType(GraduationUser graduationUser, GraduationType type) {
        graduationUser.updateGraduationType(type);
        graduationUserRepository.save(graduationUser);

    }
}
