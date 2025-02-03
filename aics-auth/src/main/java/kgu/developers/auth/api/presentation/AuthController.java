package kgu.developers.auth.api.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kgu.developers.auth.api.presentation.request.LoginRequest;
import kgu.developers.auth.api.presentation.request.RefreshTokenRequest;
import kgu.developers.auth.api.presentation.response.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth", description = "로그인 API")
public interface AuthController {

	@Operation(summary = "로그인 API", description = """
			- Description : 이 API는 jwt 토큰 기반 로그인을 처리합니다.
			- Assignee : 이한음
		""")
	@ApiResponse(
		responseCode = "200",
		content = @Content(schema = @Schema(implementation = TokenResponse.class)))
	ResponseEntity<TokenResponse> login(
		@Parameter(
			description = "로그인 request 객체 입니다.",
			required = true
		) @Valid @RequestBody LoginRequest request
	);

	@Operation(summary = "AT 재발행 API", description = """
			- Description : 이 API는 RereshToken을 입력 받아 AccessToken을 재발급 처리합니다.
			- Assignee : 이신행
		""")
	@ApiResponse(
		responseCode = "200",
		content = @Content(schema = @Schema(implementation = TokenResponse.class)))
	ResponseEntity<TokenResponse> reissue(
		@Parameter(
			description = "Refresh Token을 request 객체에 담으면 Access Token이 재발급 됩니다.",
			required = true
		) @Valid @RequestBody RefreshTokenRequest request
	);
}
