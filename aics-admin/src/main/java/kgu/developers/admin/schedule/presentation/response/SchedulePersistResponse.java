package kgu.developers.admin.schedule.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record SchedulePersistResponse(
    @Schema(description = "일정 id", example = "1", requiredMode = REQUIRED)
    Long scheduleId
) {
    public static SchedulePersistResponse from(Long scheduleId) {
        return SchedulePersistResponse.builder()
                .scheduleId(scheduleId)
                .build();
    }
}
