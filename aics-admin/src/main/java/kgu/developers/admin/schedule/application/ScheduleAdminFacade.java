package kgu.developers.admin.schedule.application;

import kgu.developers.admin.schedule.presentation.request.ScheduleCreateRequest;
import kgu.developers.admin.schedule.presentation.request.ScheduleUpdateRequest;
import kgu.developers.admin.schedule.presentation.response.SchedulePersistResponse;
import kgu.developers.domain.schedule.application.command.ScheduleService;
import kgu.developers.domain.schedule.application.query.ScheduleQueryService;
import kgu.developers.domain.schedule.domain.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ScheduleAdminFacade {
    private final ScheduleService scheduleService;
    private final ScheduleQueryService scheduleQueryService;

    public SchedulePersistResponse createSchedule(ScheduleCreateRequest request) {
        Long id = scheduleService.createSchedule(
                request.submissionType(),
                request.title(),
                request.content(),
                request.startDate(),
                request.endDate()
        );
        return SchedulePersistResponse.from(id);
    }

    @Transactional
    public void updateSchedule(Long scheduleId, ScheduleUpdateRequest request) {
        Schedule schedule = scheduleQueryService.getScheduleManagement(scheduleId);
        scheduleService.updateSchedule(
                schedule,
                request.submissionType(),
                request.title(),
                request.content(),
                request.startDate(),
                request.endDate()
        );
    }

    public void deleteSchedule(Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
    }
}
