package kgu.developers.domain.schedulemanagement.domain;

import java.util.List;
import java.util.Optional;

public interface ScheduleManagementRepository {
    ScheduleManagement save(ScheduleManagement scheduleManagement);
    void deleteById(Long id);
    Optional<ScheduleManagement> findById(Long id);
    List<ScheduleManagement> findAll();
    List<ScheduleManagement> findBySubmissionType(SubmissionType submissionType);
}
