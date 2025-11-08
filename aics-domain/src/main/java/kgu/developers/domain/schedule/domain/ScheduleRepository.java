package kgu.developers.domain.schedule.domain;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Schedule save(Schedule schedule);
    void deleteById(Long id);
    Optional<Schedule> findById(Long id);
    List<Schedule> findAll();
    Optional<Schedule> findBySubmissionType(SubmissionType submissionType);
}
