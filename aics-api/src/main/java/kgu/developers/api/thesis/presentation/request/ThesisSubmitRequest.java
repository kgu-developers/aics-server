package kgu.developers.api.thesis.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import kgu.developers.domain.thesis.domain.ThesisType;

public record ThesisSubmitRequest(
	@NotNull(message = "졸업 논문 일정 id는 필수입니다.")
	Long scheduleId,

	@Schema(description = "논문 타입", example = "MID_THESIS")
	ThesisType thesisType
) {
}
