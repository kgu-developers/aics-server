package kgu.developers.api.schedule.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.schedule.domain.Schedule;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record ScheduleTypeContentResponse(
    @Schema(description = "제출 유형", example = "MIDTHESIS", requiredMode = REQUIRED)
    String submissionType,

    @Schema(description = "일정 본문 내용", example = "매학기 개강 후 2주 이내에 신청서를 작성하여 접수해야 합니다.",requiredMode = REQUIRED)
    String content
) {
    public static ScheduleTypeContentResponse from(Schedule schedule) {
        return ScheduleTypeContentResponse.builder()
                .submissionType(schedule.getSubmissionType().name())
                .content(schedule.getContent())
                .build();
    }
}
