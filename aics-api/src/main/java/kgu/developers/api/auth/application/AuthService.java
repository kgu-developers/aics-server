package kgu.developers.api.auth.application;

import java.time.Duration;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.api.auth.presentation.exception.TokenNotFoundException;
import kgu.developers.api.auth.presentation.request.LoginRequest;
import kgu.developers.api.auth.presentation.request.RefreshTokenRequest;
import kgu.developers.api.auth.presentation.response.TokenResponse;
import kgu.developers.common.auth.jwt.TokenProvider;
import kgu.developers.domain.refreshtoken.domain.RefreshToken;
import kgu.developers.domain.refreshtoken.domain.RefreshTokenRepository;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Service
@Builder
@RequiredArgsConstructor
public class AuthService {
	private final UserQueryService userQueryService;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	private final RefreshTokenRepository refreshTokenRepository;

	@Transactional(readOnly = true)
	public TokenResponse login(LoginRequest request) {
		String userId = request.userId();
		String password = request.password();

		User user = userQueryService.getUserById(userId);
		user.isPasswordMatching(password, passwordEncoder);

		String refreshToken = tokenProvider.generateToken(user.getId(), Duration.ofDays(7));
		String accessToken = tokenProvider.generateToken(user.getId(), Duration.ofHours(2));

		refreshTokenRepository.save(RefreshToken.of(userId, refreshToken));
		return TokenResponse.of(accessToken, refreshToken);
	}

	public TokenResponse reissue(RefreshTokenRequest request) {
		String requestToken = request.refreshToken();
		RefreshToken refreshTokenEntity = refreshTokenRepository.findByRefreshToken(requestToken)
			.orElseThrow(TokenNotFoundException::new);

		refreshTokenRepository.delete(refreshTokenEntity);

		String userId = refreshTokenEntity.getUserId();
		String refreshToken = tokenProvider.generateToken(userId, Duration.ofDays(7));
		String accessToken = tokenProvider.generateToken(userId, Duration.ofHours(2));
		refreshTokenRepository.save(RefreshToken.of(userId, refreshToken));
		return TokenResponse.of(accessToken, refreshToken);
	}
}
