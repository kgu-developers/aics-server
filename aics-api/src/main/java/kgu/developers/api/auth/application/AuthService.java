package kgu.developers.api.auth.application;

import java.time.Duration;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.api.auth.presentation.request.LoginRequest;
import kgu.developers.api.auth.presentation.response.TokenResponse;
import kgu.developers.api.user.application.UserService;
import kgu.developers.common.auth.jwt.TokenProvider;
import kgu.developers.domain.user.domain.User;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Service
@Builder
@RequiredArgsConstructor
public class AuthService {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;

	@Transactional(readOnly = true)
	public TokenResponse login(LoginRequest request) {
		String userId = request.userId();
		String password = request.password();

		User user = userService.getUserById(userId);
		user.isPasswordMatching(password, passwordEncoder);

		String refreshToken = tokenProvider.generateToken(user.getId(), Duration.ofDays(7));
		String accessToken = tokenProvider.generateToken(user.getId(), Duration.ofHours(2));
		// TODO: refresh token 저장

		return TokenResponse.of(accessToken, refreshToken);
	}
}
