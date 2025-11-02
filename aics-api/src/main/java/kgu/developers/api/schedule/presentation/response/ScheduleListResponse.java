package kgu.developers.api.schedule.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.schedule.domain.Schedule;
import lombok.Builder;

import java.util.List;

@Builder
public record ScheduleListResponse(
        @Schema(description = "일정 리스트",
                example = """
        [{
            "id": 1,
            "submissionType": "MIDTHESIS",
            "title": "중간논문 제출 안내",
            "content": "매학기 개강 후 2주 이내에 신청서를 작성하여 접수해야합니다.",
            "startDate": "2025-05-01",
            "endDate": "2025-12-31"
        }]
        """,
                requiredMode = REQUIRED)
        List<ScheduleDetailResponse> contents
) {
    public static ScheduleListResponse from(List<Schedule> schedules) {
        return ScheduleListResponse.builder()
                .contents(schedules.stream()
                        .map(ScheduleDetailResponse::from)
                        .toList())
                .build();
    }
}
