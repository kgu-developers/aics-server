package kgu.developers.domain.graduationUser.application.command;

import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.domain.GraduationUserRepository;
import kgu.developers.domain.graduationUser.exception.GraduationUserIdDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GraduationUserCommandService {
    private final GraduationUserRepository graduationUserRepository;

    public Long createGraduationUser(String studentId, String name, String advisor, Boolean capstoneCompletion, String department, LocalDate graduationDate) {
        validateDuplicateId(studentId);
        GraduationUser graduationUser = GraduationUser.create(studentId,name,advisor,capstoneCompletion,department,graduationDate);
        return graduationUserRepository.save(graduationUser).getId();
    }

    private void validateDuplicateId(String id) {
        if (graduationUserRepository.findByUserIdAndDeletedAtIsNull(id).isPresent())
            throw new GraduationUserIdDuplicateException();
    }

    public void updateGraduationType(GraduationUser graduationUser, GraduationType type) {
        graduationUser.updateGraduationType(type);
        graduationUserRepository.save(graduationUser);
    }

    public Long deleteGraduationUser(GraduationUser graduationUser) {
        graduationUser.delete();
        return graduationUserRepository.save(graduationUser).getId();
    }

    public void updateGraduationUserEmail(GraduationUser graduationUser, String email) {
        graduationUser.updateEmail(email);
        graduationUserRepository.save(graduationUser);
    }
}
