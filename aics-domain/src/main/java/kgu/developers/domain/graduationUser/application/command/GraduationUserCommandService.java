package kgu.developers.domain.graduationUser.application.command;

import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.domain.GraduationUserRepository;
import kgu.developers.domain.graduationUser.exception.GraduationUserIdDuplicateException;
import kgu.developers.domain.thesis.domain.ThesisType;
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

    public void updateCertificate(GraduationUser graduationUser, Long certificateId) {
        graduationUser.updateCertificate(certificateId);
        graduationUserRepository.save(graduationUser);
    }

    public void updateThesis(GraduationUser graduationUser, Long thesisId, ThesisType thesisType) {
        switch (thesisType) {
            case MID_THESIS -> graduationUser.updateMidThesisId(thesisId);
            case FINAL_THESIS -> graduationUser.updateFinalThesisId(thesisId);
        }
    }
}
