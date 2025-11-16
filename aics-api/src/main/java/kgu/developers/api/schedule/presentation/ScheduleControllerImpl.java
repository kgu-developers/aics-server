package kgu.developers.api.schedule.presentation;

import kgu.developers.api.schedule.application.ScheduleFacade;
import kgu.developers.api.schedule.presentation.response.ScheduleListResponse;
import kgu.developers.api.schedule.presentation.response.ScheduleSummaryResponse;
import kgu.developers.api.schedule.presentation.response.ScheduleTypeContentResponse;
import kgu.developers.domain.schedule.domain.SubmissionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedules")
public class ScheduleControllerImpl implements ScheduleController {
    private final ScheduleFacade scheduleFacade;

    @Override
    @GetMapping
    public ResponseEntity<ScheduleListResponse> getScheduleList() {
        LocalDateTime referenceTime = LocalDateTime.now();
        return ResponseEntity.ok(ScheduleListResponse.from(scheduleFacade.findAll(),referenceTime));
    }

    @Override
    @GetMapping("/type/{type}")
    public ResponseEntity<ScheduleTypeContentResponse> getSchedulesByType(@PathVariable SubmissionType type){
        return ResponseEntity.ok(ScheduleTypeContentResponse.from(scheduleFacade.findBySubmissionType(type)));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleSummaryResponse> getScheduleById(@PathVariable Long id){
        LocalDateTime referenceTime = LocalDateTime.now();
        return ResponseEntity.ok(ScheduleSummaryResponse.from(scheduleFacade.findById(id), referenceTime ));
    }
}
