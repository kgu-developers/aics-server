package kgu.developers.domain.schedulemanagement.application.command;

import kgu.developers.domain.schedulemanagement.domain.ScheduleManagement;
import kgu.developers.domain.schedulemanagement.domain.ScheduleManagementRepository;
import kgu.developers.domain.schedulemanagement.domain.SubmissionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScheduleManagementService {
    private final ScheduleManagementRepository scheduleManagementRepository;

    public Long createScheduleManagement(SubmissionType submissionType, String title, LocalDateTime startDate, LocalDateTime endDate) {
        ScheduleManagement scheduleManagement =ScheduleManagement.create(submissionType,title,startDate,endDate);

        return scheduleManagementRepository.save(scheduleManagement).getId();
    }
    @Transactional
    public void updateScheduleManagement(ScheduleManagement scheduleManagement,SubmissionType submissionType ,String title, LocalDateTime startDate, LocalDateTime endDate) {
        scheduleManagement.updateSubmissionType(submissionType);
        scheduleManagement.updateTitle(title);
        scheduleManagement.updateStartDate(startDate);
        scheduleManagement.updateEndDate(endDate);

        scheduleManagementRepository.save(scheduleManagement);
    }
    public void deleteScheduleManagement(Long id) {
        scheduleManagementRepository.deleteById(id);
    }

}
