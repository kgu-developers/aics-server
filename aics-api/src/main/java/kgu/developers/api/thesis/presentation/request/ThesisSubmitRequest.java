package kgu.developers.api.thesis.presentation.request;

import jakarta.validation.constraints.NotNull;
import kgu.developers.domain.schedule.domain.SubmissionType;

public record ThesisSubmitRequest(
	@NotNull(message = "졸업 논문 유형은 필수입니다.")
	SubmissionType type
) {
}
