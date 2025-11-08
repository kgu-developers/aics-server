package kgu.developers.domain.schedule.infrastructure.repository;

import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.schedule.domain.ScheduleRepository;
import kgu.developers.domain.schedule.domain.SubmissionType;
import kgu.developers.domain.schedule.infrastructure.entity.ScheduleJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final JpaScheduleRepository jpaScheduleRepository;

    @Override
    public Schedule save(Schedule schedule){
        return jpaScheduleRepository.save(
                ScheduleJpaEntity.toEntity(schedule)
        ).toDomain();

    }
    @Override
    public void deleteById(Long id){
        jpaScheduleRepository.deleteById(id);}

    @Override
    public Optional<Schedule> findById(Long id){
        Optional<ScheduleJpaEntity> optionalEntity = jpaScheduleRepository.findById(id);
        return optionalEntity.map(ScheduleJpaEntity::toDomain);
    }

    @Override
    public List<Schedule> findAll() {
        return jpaScheduleRepository.findAll().stream()
                .map(ScheduleJpaEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Schedule> findBySubmissionType(SubmissionType submissionType) {
        return jpaScheduleRepository.findBySubmissionType(submissionType)
                .map(ScheduleJpaEntity::toDomain);
    }

}
