package kgu.developers.domain.schedule.infrastructure.repository;

import kgu.developers.domain.schedule.domain.SubmissionType;
import kgu.developers.domain.schedule.infrastructure.entity.ScheduleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaScheduleRepository extends JpaRepository<ScheduleJpaEntity, Long> {
        Optional<ScheduleJpaEntity> findBySubmissionType(SubmissionType submissionType);

}
