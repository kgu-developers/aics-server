package kgu.developers.domain.schedule.application.command;

import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.schedule.domain.ScheduleRepository;
import kgu.developers.domain.schedule.domain.SubmissionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public Long createScheduleManagement(SubmissionType submissionType, String title,String content ,LocalDateTime startDate, LocalDateTime endDate) {
        Schedule schedule = Schedule.create(submissionType,title,content,startDate,endDate);

        return scheduleRepository.save(schedule).getId();
    }
    @Transactional
    public void updateScheduleManagement(Schedule schedule, SubmissionType submissionType , String title,String content, LocalDateTime startDate, LocalDateTime endDate) {
        schedule.updateSubmissionType(submissionType);
        schedule.updateTitle(title);
        schedule.updateContent(content);
        schedule.updateStartDate(startDate);
        schedule.updateEndDate(endDate);

        scheduleRepository.save(schedule);
    }
    public void deleteScheduleManagement(Long id) {
        scheduleRepository.deleteById(id);
    }

}
