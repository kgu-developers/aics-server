package kgu.developers.apis.api.auth.application;

import kgu.developers.apis.api.auth.presentation.exception.InvalidPasswordException;
import kgu.developers.apis.api.auth.presentation.request.LoginRequest;
import kgu.developers.apis.api.auth.presentation.response.TokenResponse;
import kgu.developers.apis.api.user.application.UserService;
import kgu.developers.core.common.auth.jwt.TokenProvider;
import kgu.developers.core.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;

	@Transactional
	public TokenResponse login(LoginRequest request) {
		String userId = request.userId();
		String password = request.password();

		User user = userService.getUserByUserId(userId);
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new InvalidPasswordException();
		}

		String refreshToken = tokenProvider.generateToken(user.getUserId(), Duration.ofDays(7));
		String accessToken = tokenProvider.generateToken(user.getUserId(), Duration.ofHours(2));
		// TODO: refresh token 저장

		return TokenResponse.of(accessToken, refreshToken);
	}
}
