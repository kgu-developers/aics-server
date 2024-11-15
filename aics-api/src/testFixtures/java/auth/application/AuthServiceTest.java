package auth.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kgu.developers.api.auth.application.AuthService;
import kgu.developers.api.auth.presentation.exception.InvalidPasswordException;
import kgu.developers.api.auth.presentation.request.LoginRequest;
import kgu.developers.api.user.application.UserService;
import kgu.developers.common.auth.jwt.JwtProperties;
import kgu.developers.common.auth.jwt.TokenProvider;
import kgu.developers.domain.user.domain.Major;
import kgu.developers.domain.user.domain.User;
import mock.FakeUserRepository;

public class AuthServiceTest {
	private AuthService authService;

	@BeforeEach
	public void init() {
		FakeUserRepository fakeUserRepository = new FakeUserRepository();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		this.authService = AuthService.builder()
			.userService(
				UserService.builder()
					.userRepository(fakeUserRepository)
					.bCryptPasswordEncoder(bCryptPasswordEncoder)
					.build()
			)
			.passwordEncoder(bCryptPasswordEncoder)
			.tokenProvider(
				TokenProvider.builder()
					.jwtProperties(new JwtProperties("testIssuer", "testSecretKey"))
					.build()
			)
			.build();

		fakeUserRepository.save(User.builder()
			.id("202411345")
			.password(bCryptPasswordEncoder.encode("password1234"))
			.name("홍길동")
			.email("test@kyonggi.ac.kr")
			.phone("010-1234-5678")
			.major(Major.CSE)
			.build());
	}

	@Test
	@DisplayName("login은 토큰을 발급할 수 있다")
	public void login_Success() {
		// given
		String userId = "202411345";
		String password = "password1234";

		// when
		// then
		assertThatCode(() -> {
			authService.login(LoginRequest.builder()
				.userId(userId)
				.password(password)
				.build()
			);
		}).doesNotThrowAnyException();
	}

	@Test
	@DisplayName("login은 비밀번호가 틀리면 InvalidPasswordException을 발생시킨다")
	public void login_InvalidPassword_ThrowsException() {
		// given
		String userId = "202411345";
		String password = "wrongPassword";

		// when
		// then
		assertThatThrownBy(() -> {
			authService.login(LoginRequest.builder()
				.userId(userId)
				.password(password)
				.build()
			);
		}).isInstanceOf(InvalidPasswordException.class);
	}
}
