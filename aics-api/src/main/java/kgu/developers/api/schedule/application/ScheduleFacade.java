package kgu.developers.api.schedule.application;

import kgu.developers.domain.schedule.application.query.ScheduleQueryService;
import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.schedule.domain.SubmissionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleFacade {
    private final ScheduleQueryService scheduleQueryService;

    public List<Schedule> findAll() {
        return scheduleQueryService.getAllScheduleManagements();
    }
    public Schedule findBySubmissionType(SubmissionType submissionType) {
        return scheduleQueryService.getBySubmissionType(submissionType);
    }

    public Schedule findById(Long id) {
        return scheduleQueryService.getScheduleManagement(id);
    }



}
