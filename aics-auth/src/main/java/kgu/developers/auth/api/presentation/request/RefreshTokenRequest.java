package kgu.developers.auth.api.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record RefreshTokenRequest(
	@Schema(description = "리프레시 토큰",
		example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsILJdhDYTYzNzQwNjQwMH0.7J",
		requiredMode = REQUIRED)
	@NotNull
	String refreshToken
) {
}
