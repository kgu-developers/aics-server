package kgu.developers.domain.schedulemanagement.infrastructure;

import kgu.developers.domain.schedulemanagement.domain.SubmissionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaScheduleManagementRepository extends JpaRepository<ScheduleManagementJpaEntity, Long> {
        List<ScheduleManagementJpaEntity> findBySubmissionType(SubmissionType submissionType);
}
