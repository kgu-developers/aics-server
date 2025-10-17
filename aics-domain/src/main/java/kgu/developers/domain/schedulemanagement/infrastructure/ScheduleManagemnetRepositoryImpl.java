package kgu.developers.domain.schedulemanagement.infrastructure;

import kgu.developers.domain.schedulemanagement.domain.ScheduleManagement;
import kgu.developers.domain.schedulemanagement.domain.ScheduleManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleManagemnetRepositoryImpl implements ScheduleManagementRepository {
    ScheduleManagement save(ScheduleManagement scheduleManagement){
        ScheduleManagementJpaEntity entity = ScheduleManagementJpaEntity.toEntity(scheduleManagement);


    }
    void deleteById(Long id);
    Optional<ScheduleManagement> findById(Long id);
    List<ScheduleManagement> findAll();

}
