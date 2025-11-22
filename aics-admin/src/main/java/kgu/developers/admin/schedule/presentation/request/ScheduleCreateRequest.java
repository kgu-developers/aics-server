package kgu.developers.admin.schedule.presentation.request;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import kgu.developers.domain.schedule.domain.SubmissionType;
import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record ScheduleCreateRequest(
        @Schema(description = "제출 유형", example = "SUBMITTED", requiredMode = REQUIRED)
        @NotNull SubmissionType submissionType,

        @Schema(description = "일정 제목", example = "중간논문 제출 안내",requiredMode = REQUIRED)
        @NotBlank @Size(max=100,message = "일정 제목은 100자 이내여야 합니다.") String title,

        @Schema(description = "일정 내용", example = "매학기 개강 후 2주 이내에 신청서를 작성하여 접수해야합니다.",requiredMode = REQUIRED)
        @NotBlank String content,

        @Schema(description = "시작 일시", example = "2025-04-15T00:00:00", requiredMode = REQUIRED)
        @NotNull LocalDateTime startDate,

        @Schema(description = "종료 일시", example = "2025-12-31T23:59:59", requiredMode = REQUIRED)
        @NotNull LocalDateTime endDate
) {
        @AssertTrue(message = "종료 일시는 시작 일시 이후여야 합니다.")
        @Schema(hidden = true)
        public boolean isValidDateRange() {
                if (startDate ==null || endDate == null) {
                        return true;
                }
                return !endDate.isBefore(startDate);
        }
}
