package kgu.developers.api.thesis.presentation.request;

import jakarta.validation.constraints.NotNull;

public record ThesisSubmitRequest(
	@NotNull(message = "졸업 논문 일정 id는 필수입니다.")
	Long scheduleId
) {
}
