package kgu.developers.apis.api.auth.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kgu.developers.apis.api.auth.application.AuthService;
import kgu.developers.apis.api.auth.presentation.request.LoginRequest;
import kgu.developers.apis.api.auth.presentation.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "로그인 API")
public class AuthController {
	private final AuthService authService;

	@Operation(summary = "로그인 API", description = """
			- Description : 이 API는 jwt 토큰 기반 로그인을 처리합니다.
			- Assignee : 이한음
		""")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = TokenResponse.class)))
	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(
		@Valid @RequestBody LoginRequest request
	) {
		TokenResponse response = authService.login(request);
		return ResponseEntity.ok(response);
	}
}
