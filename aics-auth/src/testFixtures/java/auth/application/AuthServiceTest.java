package auth.application;

import kgu.developers.auth.api.application.AuthService;
import kgu.developers.domain.user.exception.AlreadyDeletedUserException;
import kgu.developers.auth.api.presentation.exception.TokenNotFoundException;
import kgu.developers.auth.api.presentation.request.LoginRequest;
import kgu.developers.auth.api.presentation.request.RefreshTokenRequest;
import kgu.developers.common.auth.jwt.JwtProperties;
import kgu.developers.common.auth.jwt.TokenProvider;
import kgu.developers.domain.refreshtoken.domain.RefreshToken;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.exception.InvalidPasswordException;
import mock.repository.FakeRefreshTokenRepository;
import mock.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static kgu.developers.common.domain.BaseRole.USER;
import static kgu.developers.domain.user.domain.Major.CSE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class AuthServiceTest {
	private AuthService authService;
	private User user;

	@BeforeEach
	public void init() {
		FakeUserRepository fakeUserRepository = new FakeUserRepository();
		FakeRefreshTokenRepository fakeRefreshTokenRepository = new FakeRefreshTokenRepository();

		authService = new AuthService(
			new UserQueryService(fakeUserRepository),
			new BCryptPasswordEncoder(),
			TokenProvider.builder()
				.jwtProperties(new JwtProperties("testIssuer", "testSecretKey"))
				.build(),
			fakeRefreshTokenRepository
		);

		user = fakeUserRepository.save(User.builder()
			.id("202411345")
			.password("$2a$10$ViIAGtB9Y/9cE//3WY6i4e6RQVHbJhQQDWshsFlElNnyz88.8EOu2")
			.name("홍길동")
			.email("test@kyonggi.ac.kr")
			.phone("010-1234-5678")
			.major(CSE)
			.role(USER)
			.build());

		fakeRefreshTokenRepository.save(
			RefreshToken.of("202411345", "test")
		);
	}

	@Test
	@DisplayName("login은 토큰을 발급할 수 있다")
	public void login_Success() {
		// given
		String userId = "202411345";
		String password = "password1234";

		// when
		// then
		assertThatCode(() -> authService.login(
			LoginRequest.builder()
				.userId(userId)
				.password(password)
				.build())
		).doesNotThrowAnyException();
	}

	@Test
	@DisplayName("login은 비밀번호가 틀리면 InvalidPasswordException을 발생시킨다")
	public void login_InvalidPassword_ThrowsException() {
		// given
		String userId = "202411345";
		String password = "wrongPassword";

		// when
		// then
		assertThatThrownBy(() -> authService.login(
			LoginRequest.builder()
				.userId(userId)
				.password(password)
				.build()
		)).isInstanceOf(InvalidPasswordException.class);
	}

	@Test
	@DisplayName("탈퇴한 회원이 로그인 요청을 하면 AlreadyDeletedUserException 발생시킨다")
	public void login_alreadyDeleted_ThrowsException() {
		// given
		String userId = "202411345";
		String password = "password1234";
		LoginRequest request = LoginRequest.builder()
			.userId(userId)
			.password(password)
			.build();

		// when
		user.delete();

		// then
		assertThatThrownBy(() -> authService.login(request))
			.isInstanceOf(AlreadyDeletedUserException.class);
	}

	@Test
	@DisplayName("reissue는 토큰을 재발급할 수 있다")
	public void reissue_Success() {
		// when
		// then
		assertThatCode(() -> authService.reissue(
			RefreshTokenRequest.builder()
				.refreshToken("test")
				.build())
		).doesNotThrowAnyException();
	}

	@Test
	@DisplayName("reissue는 RefreshToken이 틀리면 TokenNotFoundException을 발생시킨다")
	public void reissue_TokenNotFoundException_ThrowsException() {
		// given
		String refreshToken = "eyJNOTREALTOKEN.eyJzdWIiOiJhZG1pbiIsILJdhDYTYzNzQwNjQwMH0.7J";

		// when
		// then
		assertThatThrownBy(() -> authService.reissue(
			RefreshTokenRequest.builder()
				.refreshToken(refreshToken)
				.build()
		)).isInstanceOf(TokenNotFoundException.class);
	}
}
