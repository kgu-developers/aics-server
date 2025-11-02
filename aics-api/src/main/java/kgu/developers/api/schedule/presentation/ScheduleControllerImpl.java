package kgu.developers.api.schedule.presentation;

import kgu.developers.api.schedule.application.ScheduleFacade;
import kgu.developers.api.schedule.presentation.response.ScheduleDetailResponse;
import kgu.developers.api.schedule.presentation.response.ScheduleListResponse;
import kgu.developers.domain.schedule.domain.SubmissionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedules")
public class ScheduleControllerImpl implements ScheduleController {
    private final ScheduleFacade scheduleFacade;

    @Override
    @GetMapping
    public ResponseEntity<ScheduleListResponse> getScheduleList() {
        return ResponseEntity.ok(ScheduleListResponse.from(scheduleFacade.findAll()));
    }

    @Override
    @GetMapping("/type/{type}")
    public ResponseEntity<ScheduleListResponse> getSchedulesByType(@PathVariable SubmissionType type){
        return ResponseEntity.ok(ScheduleListResponse.from(scheduleFacade.findBySubmissionType(type)));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDetailResponse> getScheduleById(@PathVariable Long id){
        return ResponseEntity.ok(ScheduleDetailResponse.from(scheduleFacade.findById(id)));
    }
}
