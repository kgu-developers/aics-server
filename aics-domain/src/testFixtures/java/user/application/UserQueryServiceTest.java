package user.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.exception.UserNotFoundException;
import mock.TestContainer;

public class UserQueryServiceTest {
	private UserQueryService userQueryService;

	@BeforeEach
	public void init() {
		TestContainer testContainer = new TestContainer();
		userQueryService = testContainer.userQueryService;
	}

	@Test
	@DisplayName("getUserById는 유저를 찾아올 수 있다")
	public void getUserById_Success() {
		// given
		String id = "202411345";

		// when
		User result = userQueryService.getUserById(id);

		// then
		assertEquals("홍길동", result.getName());
	}

	@Test
	@DisplayName("getUserById는 존재하지 않는 유저를 찾아올 경우 UserNotFoundException을 발생시킨다")
	public void getUserById_NotFound_ThrowsException() {
		// given
		String id = "202411348";

		// when
		// then
		assertThatThrownBy(() ->
			userQueryService.getUserById(id)
		).isInstanceOf(UserNotFoundException.class);
	}

	@Test
	@DisplayName("me는 현재 로그인 되어있는 사용자의 정보를 가져온다.")
	public void me_Success() {
		// when
		User user = userQueryService.me();

		// then
		assertEquals("202411345", user.getId());
		assertEquals("홍길동", user.getName());
	}
}
