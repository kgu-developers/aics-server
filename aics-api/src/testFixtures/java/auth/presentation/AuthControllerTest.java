package auth.presentation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import kgu.developers.api.auth.presentation.request.LoginRequest;
import kgu.developers.api.auth.presentation.response.TokenResponse;
import kgu.developers.api.user.presentation.request.UserCreateRequest;
import kgu.developers.domain.user.domain.Major;
import mock.TestContainer;
/*
 * 추후 Controller 테스트는 medium test로 전환할 예정입니다.
 * medium test는 Controller / Service / Repository 계층을 함께 테스트합니다.
 */
/*
public class AuthControllerTest {

	@Test
	@DisplayName("로그인 성공 후 200 상태 코드와 토큰을 정상적으로 발급받는다")
	public void login_Success() {
		// given
		TestContainer testContainer = new TestContainer();
		testContainer.userFacade.createUser(UserCreateRequest.builder()
			.userId("202411345")
			.password("password0000")
			.name("김철수")
			.email("kim@kyonggi.ac.kr")
			.phone("010-0000-0000")
			.major(Major.CSE)
			.build());

		// when
		ResponseEntity<TokenResponse> result = testContainer.authController.login(LoginRequest.builder()
			.userId("202411345")
			.password("password0000")
			.build());

		// then
		assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
		assertThat(Objects.requireNonNull(result.getBody()).accessToken()).isNotNull();
		assertThat(Objects.requireNonNull(result.getBody()).refreshToken()).isNotNull();
	}
}
*/