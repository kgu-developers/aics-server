package kgu.developers.domain.graduationUser.application.command;

import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.domain.GraduationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GraduationUserCommandService {
    private final GraduationUserRepository graduationUserRepository;

    public void selectGraduationType(GraduationUser graduationUser, GraduationType type) {
        graduationUser.updateGraduationType(type);
        graduationUserRepository.save(graduationUser);
    }

    public Long createGraduationUser(String studentId, String name, String advisor, Boolean capstoneCompletion, String department, LocalDate graduationDate) {
        GraduationUser graduationUser = GraduationUser.create(studentId,name,advisor,capstoneCompletion,department,graduationDate);
        return graduationUserRepository.save(graduationUser).getId();
    }

    public Long deleteGraduationUser(GraduationUser graduationUser) {
        graduationUser.delete();
        return graduationUserRepository.save(graduationUser).getId();
    }
}
