package kgu.developers.apis.api.auth.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
	@Schema(description = "학번", example = "202412345", requiredMode = REQUIRED)
	@NotNull
	String personalId,

	@Schema(description = "비밀번호", example = "password1234", requiredMode = REQUIRED)
	@NotNull
	String password
) {
}
