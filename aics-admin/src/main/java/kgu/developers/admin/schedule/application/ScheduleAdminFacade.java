package kgu.developers.admin.schedule.application;

import kgu.developers.admin.schedule.presentation.request.ScheduleContentUpdateRequest;
import kgu.developers.admin.schedule.presentation.request.ScheduleCreateRequest;
import kgu.developers.admin.schedule.presentation.request.ScheduleUpdateRequest;
import kgu.developers.admin.schedule.presentation.response.SchedulePersistResponse;
import kgu.developers.domain.schedule.application.command.ScheduleCommandService;
import kgu.developers.domain.schedule.application.query.ScheduleQueryService;
import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.schedule.domain.SubmissionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ScheduleAdminFacade {
    private final ScheduleCommandService scheduleCommandService;
    private final ScheduleQueryService scheduleQueryService;

    public SchedulePersistResponse createSchedule(ScheduleCreateRequest request) {
        Long id = scheduleCommandService.createSchedule(
                request.submissionType(),
                request.content(),
                request.startDate(),
                request.endDate()
        );
        return SchedulePersistResponse.from(id);
    }

    public void updateSchedule(Long scheduleId, ScheduleUpdateRequest request) {
        Schedule schedule = scheduleQueryService.getScheduleManagement(scheduleId);
        scheduleCommandService.updateSchedule(
                schedule,
                request.startDate(),
                request.endDate()
        );
    }

    public void updateScheduleContent(SubmissionType submissionType, ScheduleContentUpdateRequest request) {
        Schedule schedule =scheduleQueryService.getBySubmissionType(submissionType);
        scheduleCommandService.updateScheduleContent(schedule,request.content());
    }

    public void deleteSchedule(Long scheduleId) {
        scheduleCommandService.deleteSchedule(scheduleId);
    }
}
