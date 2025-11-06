package kgu.developers.admin.schedule.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import kgu.developers.domain.schedule.domain.SubmissionType;

import java.time.LocalDateTime;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record ScheduleUpdateRequest(
        @Schema(description = "제출 유형", example = "SUBMITTED", requiredMode = REQUIRED)
        @NotNull
        SubmissionType submissionType,

        @Schema(description = "일정 제목", example = "중간논문 제출 안내",requiredMode = REQUIRED)
        @NotBlank @Size(max=50)
        String title,


        @Schema(description = "시작 일시", example = "2025-04-15T00:00:00", requiredMode = REQUIRED)
        @NotNull @FutureOrPresent
        LocalDateTime startDate,

        @Schema(description = "종료 일시", example = "2025-12-31T23:59:59", requiredMode = REQUIRED)
        @NotNull @FutureOrPresent
        LocalDateTime endDate
) {
        @AssertTrue(message = "종료 일시는 시작 일시 이후여야 합니다.")
        public boolean isValidDateRange() {
                if (startDate ==null || endDate == null) {
                        return true;
                }
                return !endDate.isBefore(startDate);
        }
}
