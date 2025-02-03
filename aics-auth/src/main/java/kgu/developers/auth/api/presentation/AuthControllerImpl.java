package kgu.developers.auth.api.presentation;

import jakarta.validation.Valid;
import kgu.developers.auth.api.application.AuthService;
import kgu.developers.auth.api.presentation.request.LoginRequest;
import kgu.developers.auth.api.presentation.request.RefreshTokenRequest;
import kgu.developers.auth.api.presentation.response.TokenResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Builder
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthControllerImpl implements AuthController {
	private final AuthService authService;

	@Override
	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(
		@Valid @RequestBody LoginRequest request
	) {
		TokenResponse response = authService.login(request);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping("/reissue")
	public ResponseEntity<TokenResponse> reissue(
		@Valid @RequestBody RefreshTokenRequest request
	) {
		TokenResponse response = authService.reissue(request);
		return ResponseEntity.ok(response);
	}
}
