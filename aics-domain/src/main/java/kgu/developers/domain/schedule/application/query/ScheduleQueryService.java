package kgu.developers.domain.schedule.application.query;

import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.schedule.domain.ScheduleRepository;
import kgu.developers.domain.schedule.domain.SubmissionType;
import kgu.developers.domain.schedule.exception.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static kgu.developers.domain.schedule.domain.SubmissionType.*;

@Service
@RequiredArgsConstructor
public class ScheduleQueryService {
    private final ScheduleRepository scheduleRepository;
    private static final List<SubmissionType> ORDER = List.of(
            SUBMITTED,
            MIDTHESIS,
            FINALTHESIS,
            CERTIFICATE,
            APPROVED,
            OTHER
    );
    public List<Schedule> getAllScheduleManagements() {

        return scheduleRepository.findAll().stream()
                .sorted(Comparator.comparing(s -> ORDER.indexOf(s.getSubmissionType())))
                .toList();
    }

    public Schedule getScheduleManagement(Long id) {
        return scheduleRepository
                .findById(id)
                .orElseThrow(ScheduleNotFoundException::new);
    }
    public Schedule getBySubmissionType(SubmissionType submissionType) {
        return scheduleRepository.findBySubmissionType(submissionType)
                .orElseThrow(ScheduleNotFoundException::new);
    }

    public void checkExistsOrThrow(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new ScheduleNotFoundException();
        }
    }
}
