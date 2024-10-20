package kgu.developers.apis.api.user.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import kgu.developers.core.domain.user.domain.Gender;
import kgu.developers.core.domain.user.domain.Grade;

public record UserCreateRequest(

	@Schema(description = "학번", example = "202412345", requiredMode = REQUIRED)
	@NotNull
	String personalId,

	@NotNull
	String password,

	@NotNull
	String name,

	@NotNull
	String birth,

	@NotNull
	Gender gender,

	@NotNull
	Grade grade,

	@NotNull
	String majorName
) {
}
