package kgu.developers.domain.graduationUser.application.command;

import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.domain.GraduationUserRepository;
import kgu.developers.domain.graduationUser.exception.GraduationTypeSubmissionPeriodClosedException;
import kgu.developers.domain.graduationUser.exception.GraduationUserIdDuplicateException;
import kgu.developers.domain.schedule.application.query.ScheduleQueryService;
import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.schedule.domain.SubmissionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;

@Service
@Transactional
@RequiredArgsConstructor
public class GraduationUserCommandService {
    private final GraduationUserRepository graduationUserRepository;
    private final ScheduleQueryService scheduleQueryService;

    public Long createGraduationUser(String studentId, String name, String advisor, Boolean capstoneCompletion, String department, YearMonth graduationDate) {
        validateId(studentId);
        GraduationUser graduationUser = GraduationUser.create(studentId,name,advisor,capstoneCompletion,department,graduationDate.atDay(1));
        return graduationUserRepository.save(graduationUser).getId();
    }

    private void validateId(String id) {
        if (graduationUserRepository.findByUserIdAndDeletedAtIsNull(id).isPresent())
            throw new GraduationUserIdDuplicateException();
    }

    public void updateGraduationType(GraduationUser graduationUser, GraduationType type) {
        Schedule schedule = scheduleQueryService.getBySubmissionType(SubmissionType.SUBMITTED);
        LocalDateTime referenceTime = LocalDateTime.now();
        if(!schedule.isInProgress(referenceTime)) {
            throw new GraduationTypeSubmissionPeriodClosedException();
        }

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

    public void updateThesis(GraduationUser graduationUser, Long thesisId, SubmissionType thesisType) {
        switch (thesisType) {
            case MIDTHESIS -> graduationUser.updateMidThesisId(thesisId);
            case FINALTHESIS -> graduationUser.updateFinalThesisId(thesisId);
        }
        graduationUserRepository.save(graduationUser);
    }
}
