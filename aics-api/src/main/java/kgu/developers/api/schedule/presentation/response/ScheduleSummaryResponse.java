package kgu.developers.api.schedule.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.schedule.domain.Schedule;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record ScheduleSummaryResponse(
    @Schema(description = "일정 id", example = "1", requiredMode = REQUIRED)
    Long id,

    @Schema(description = "제출 유형", example = "중간논문", requiredMode = REQUIRED)
    String submissionType,

    @Schema(description = "시작일", example = "2025-05-01", requiredMode = REQUIRED)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    String startDate,

    @Schema(description = "종료일", example = "2025-12-31", requiredMode = REQUIRED)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    String endDate,

    @Schema(description = "상태(대기/진행/마감)", example = "진행 중", requiredMode = REQUIRED)
    String status
) {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static ScheduleSummaryResponse from(Schedule schedule, LocalDateTime referenceTime) {
        return ScheduleSummaryResponse.builder()
                .id(schedule.getId())
                .submissionType(schedule.getSubmissionType().getLabel())
                .startDate(schedule.getStartDate().format(DATE_FORMATTER))
                .endDate(schedule.getEndDate().format(DATE_FORMATTER))
                .status(schedule.determineStatusAt(referenceTime).getLabel())
                .build();
    }
}