package kgu.developers.api.schedule.application;

import kgu.developers.domain.schedule.application.query.ScheduleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleFacade {
    private final ScheduleQueryService scheduleQueryService;


}
