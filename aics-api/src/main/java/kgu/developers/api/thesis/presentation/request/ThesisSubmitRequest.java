package kgu.developers.api.thesis.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import kgu.developers.domain.schedule.domain.SubmissionType;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record ThesisSubmitRequest(
	@Schema(description = "졸업 논문 유형", example = "MIDTHESIS", requiredMode = REQUIRED)
	@NotNull(message = "졸업 논문 유형은 필수입니다.")
	SubmissionType type
) {
}
