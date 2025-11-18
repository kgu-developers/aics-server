package kgu.developers.admin.schedule.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record ScheduleContentUpdateRequest(
    @Schema(description = "일정 내용", example = "매학기 개강 후 2주 이내에 신청서를 작성하여 접수해야합니다.",requiredMode = REQUIRED)
    @NotBlank
    String content
) {

}
