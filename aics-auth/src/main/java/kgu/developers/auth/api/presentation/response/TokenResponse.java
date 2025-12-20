package kgu.developers.auth.api.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record TokenResponse(
	@Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYzNzQwNjQwMH0.7J", requiredMode = REQUIRED)
	String accessToken,

	@Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsILJdhDYTYzNzQwNjQwMH0.7J", requiredMode = REQUIRED)
	String refreshToken,

	@Schema(description = "권한", example = "ADMIN", requiredMode = REQUIRED)
	String role,

	@Schema(description = "졸업타입", example = "THESIS", requiredMode =NOT_REQUIRED)
	GraduationType graduationType

) {
	public static TokenResponse of(String accessToken, String refreshToken, String role, GraduationType graduationType) {
		return TokenResponse.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.role(role)
			.graduationType(graduationType)
			.build();
	}
}
