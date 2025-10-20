package kgu.developers.domain.schedulemanagement.infrastructure;

import kgu.developers.domain.schedulemanagement.domain.ScheduleManagement;
import kgu.developers.domain.schedulemanagement.domain.ScheduleManagementRepository;
import kgu.developers.domain.schedulemanagement.domain.SubmissionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ScheduleManagementRepositoryImpl implements ScheduleManagementRepository {
    private final JpaScheduleManagementRepository jpaScheduleManagementRepository;

    @Override
    public ScheduleManagement save(ScheduleManagement scheduleManagement){
        return jpaScheduleManagementRepository.save(
                ScheduleManagementJpaEntity.toEntity(scheduleManagement)
        ).toDomain();

    }
    @Override
    public void deleteById(Long id){jpaScheduleManagementRepository.deleteById(id);}

    @Override
    public Optional<ScheduleManagement> findById(Long id){
        Optional<ScheduleManagementJpaEntity> optionalEntity = jpaScheduleManagementRepository.findById(id);
        return optionalEntity.map(ScheduleManagementJpaEntity::toDomain);
    }

    @Override
    public List<ScheduleManagement> findAll() {
        return jpaScheduleManagementRepository.findAll().stream()
                .map(ScheduleManagementJpaEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleManagement> findBySubmissionType(SubmissionType submissionType){
        return jpaScheduleManagementRepository.findBySubmissionType(submissionType)
                .stream()
                .map(ScheduleManagementJpaEntity::toDomain)
                .collect(Collectors.toList());
    }

}
