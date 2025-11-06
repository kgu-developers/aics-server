package kgu.developers.admin.schedule.presentation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.admin.schedule.application.ScheduleAdminFacade;
import kgu.developers.admin.schedule.presentation.request.ScheduleContentUpdateRequest;
import kgu.developers.admin.schedule.presentation.request.ScheduleCreateRequest;
import kgu.developers.admin.schedule.presentation.request.ScheduleUpdateRequest;
import kgu.developers.admin.schedule.presentation.response.SchedulePersistResponse;
import kgu.developers.domain.schedule.domain.SubmissionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/schedules")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ScheduleAdminControllerImpl implements ScheduleAdminController {
    private final ScheduleAdminFacade scheduleAdminFacade;

    @Override
    @PostMapping
    public ResponseEntity<SchedulePersistResponse> createSchedule(
            @Valid @RequestBody ScheduleCreateRequest request
    ){
        SchedulePersistResponse response = scheduleAdminFacade.createSchedule(request);
        return ResponseEntity.status(CREATED).body(response);
    }

    @Override
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<Void> updateSchedule(
            @Positive @PathVariable Long scheduleId,
            @Valid @RequestBody ScheduleUpdateRequest request
    ){
        scheduleAdminFacade.updateSchedule(scheduleId, request);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/type/{submissionType}/content")
    public ResponseEntity<Void> updateScheduleContent(
            @PathVariable SubmissionType submissionType,
            @Valid @RequestBody ScheduleContentUpdateRequest request
            ){
        scheduleAdminFacade.updateScheduleContent(submissionType, request);
        return ResponseEntity.noContent().build();
    }
    @Override
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @Positive @PathVariable Long scheduleId
    ){
        scheduleAdminFacade.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }
}
