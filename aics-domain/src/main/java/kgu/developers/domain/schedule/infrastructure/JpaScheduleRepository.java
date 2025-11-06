package kgu.developers.domain.schedule.infrastructure;

import kgu.developers.domain.schedule.domain.SubmissionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaScheduleRepository extends JpaRepository<ScheduleJpaEntity, Long> {
        Optional<ScheduleJpaEntity> findBySubmissionType(SubmissionType submissionType);

}
