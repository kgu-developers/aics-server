package kgu.developers.domain.schedule.application.command;


import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.schedule.domain.ScheduleRepository;
import kgu.developers.domain.schedule.domain.SubmissionType;
import kgu.developers.domain.schedule.exception.DuplicateScheduleTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public Long createSchedule(SubmissionType submissionType, String title, String content , LocalDateTime startDate, LocalDateTime endDate) {
        scheduleRepository.findBySubmissionType(submissionType).ifPresent(existing -> {
            throw new DuplicateScheduleTypeException();
        });
        Schedule schedule = Schedule.create(submissionType,title,content,startDate,endDate);

        return scheduleRepository.save(schedule).getId();
    }
    @Transactional
    public void updateSchedule(Schedule schedule, SubmissionType submissionType , String title, LocalDateTime startDate, LocalDateTime endDate) {
        schedule.updateSubmissionType(submissionType);
        schedule.updateTitle(title);
        schedule.updateStartDate(startDate);
        schedule.updateEndDate(endDate);

        scheduleRepository.save(schedule);
    }
    @Transactional
    public void updateScheduleContent(Schedule schedule, String content) {
        schedule.updateContent(content);
        scheduleRepository.save(schedule);
    }
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

}
