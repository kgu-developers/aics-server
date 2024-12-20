package kgu.developers.api.auth.application;

import kgu.developers.api.auth.presentation.exception.TokenNotFoundException;
import kgu.developers.api.auth.presentation.request.LoginRequest;
import kgu.developers.api.auth.presentation.request.RefreshTokenRequest;
import kgu.developers.api.auth.presentation.response.AccessTokenResponse;
import kgu.developers.api.auth.presentation.response.TokenResponse;
import kgu.developers.api.user.application.UserService;
import kgu.developers.common.auth.jwt.TokenProvider;
import kgu.developers.domain.refreshtoken.domain.RefreshToken;
import kgu.developers.domain.refreshtoken.domain.RefreshTokenRepository;
import kgu.developers.domain.user.domain.User;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@Builder
@RequiredArgsConstructor
public class AuthService {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	private final RefreshTokenRepository refreshTokenRepository;

	@Transactional(readOnly = true)
	public TokenResponse login(LoginRequest request) {
		String userId = request.userId();
		String password = request.password();

		User user = userService.getUserById(userId);
		user.isPasswordMatching(password, passwordEncoder);

		String refreshToken = tokenProvider.generateToken(user.getId(), Duration.ofDays(7));
		String accessToken = tokenProvider.generateToken(user.getId(), Duration.ofHours(2));

		refreshTokenRepository.save(RefreshToken.of(refreshToken, userId));
		return TokenResponse.of(accessToken, refreshToken);
	}

	public AccessTokenResponse reissue(RefreshTokenRequest request) {
		String requestToken = request.refreshToken();
		RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(requestToken)
			.orElseThrow(TokenNotFoundException::new);

		String userId = refreshToken.getUserId();
		String accessToken = tokenProvider.generateToken(userId, Duration.ofHours(2));
		return AccessTokenResponse.of(accessToken);
	}
}
