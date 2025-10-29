package kgu.developers.domain.schedule.infrastructure;

import kgu.developers.domain.schedule.domain.SubmissionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaScheduleRepository extends JpaRepository<ScheduleJpaEntity, Long> {
        List<ScheduleJpaEntity> findBySubmissionType(SubmissionType submissionType);
}
