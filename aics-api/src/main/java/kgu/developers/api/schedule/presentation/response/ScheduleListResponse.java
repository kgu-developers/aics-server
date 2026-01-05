package kgu.developers.api.schedule.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.schedule.domain.Schedule;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ScheduleListResponse(
    @Schema(description = "일정 리스트",
                example = """
        [{
            "id": 1,
            "submissionType": "중간논문",
            "startDate": "2025-05-01",
            "endDate": "2025-12-31",
            "status": "진행 중"
        }]
        """,
                requiredMode = REQUIRED)
    List<ScheduleSummaryResponse> contents
) {
    public static ScheduleListResponse from(List<Schedule> schedules,LocalDateTime referenceTime) {
        return ScheduleListResponse.builder()
                .contents(schedules.stream()
                        .map(schedule -> ScheduleSummaryResponse.from(schedule,referenceTime))
                        .toList())
                .build();
    }
}
