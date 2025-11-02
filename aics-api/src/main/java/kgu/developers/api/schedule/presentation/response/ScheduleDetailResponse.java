package kgu.developers.api.schedule.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.schedule.domain.Schedule;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.format.DateTimeFormatter;

@Builder
public record ScheduleDetailResponse(
        @Schema(description = "일정 id", example = "1", requiredMode = REQUIRED)
        Long id,

        @Schema(description = "제출 유형", example = "MIDTHESIS", requiredMode = REQUIRED)
        String submissionType,

        @Schema(description = "일정 제목", example = "중간논문 제출 안내", requiredMode = REQUIRED)
        String title,

        @Schema(description = "일정 내용", example = "매학기 개강 후 2주 이내에 신청서를 작성하여 접수해야합니다.", requiredMode = REQUIRED)
        String content,

        @Schema(description = "시작일", example = "2025-05-01", requiredMode = REQUIRED)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        String startDate,

        @Schema(description = "종료일", example = "2025-12-31", requiredMode = REQUIRED)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        String endDate
) {
    public static ScheduleDetailResponse from(Schedule schedule) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return ScheduleDetailResponse.builder()
                .id(schedule.getId())
                .submissionType(schedule.getSubmissionType().name())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .startDate(schedule.getStartDate().format(formatter))
                .endDate(schedule.getEndDate().format(formatter))
                .build();
    }
}
