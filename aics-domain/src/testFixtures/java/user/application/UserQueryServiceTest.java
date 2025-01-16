package user.application;

import static kgu.developers.domain.user.domain.Major.CSE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.exception.UserNotFoundException;
import mock.repository.FakeUserRepository;

public class UserQueryServiceTest {
	private UserQueryService userQueryService;

	@BeforeEach
	public void init() {
		FakeUserRepository fakeUserRepository = new FakeUserRepository();
		userQueryService = new UserQueryService(fakeUserRepository);

		fakeUserRepository.save(User.builder()
			.id("202411345")
			.password("password1234")
			.name("홍길동")
			.email("test@kyonggi.ac.kr")
			.phone("010-1234-5678")
			.major(CSE)
			.build());

		UserDetails user = userQueryService.getUserById("202411345");
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(
			new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities())
		);
	}

	@Test
	@DisplayName("getUserById는 유저를 찾아올 수 있다")
	public void getUserById_Success() {
		// given
		String id = "202411345";

		// when
		User result = userQueryService.getUserById(id);

		// then
		assertEquals(id, result.getId());
		assertEquals("홍길동", result.getName());
		assertEquals("test@kyonggi.ac.kr", result.getEmail());
		assertEquals("010-1234-5678", result.getPhone());
		assertEquals(CSE, result.getMajor());
	}

	@Test
	@DisplayName("getUserById는 존재하지 않는 유저를 찾아올 경우 UserNotFoundException을 발생시킨다")
	public void getUserById_NotFound_ThrowsException() {
		// given
		String id = "202411348";

		// when
		// then
		assertThatThrownBy(() -> userQueryService.getUserById(id))
			.isInstanceOf(UserNotFoundException.class);
	}

	@Test
	@DisplayName("me는 현재 로그인 되어있는 사용자의 정보를 가져온다.")
	public void me_Success() {
		// when
		User result = userQueryService.me();

		// then
		assertEquals("202411345", result.getId());
		assertEquals("홍길동", result.getName());
		assertEquals("test@kyonggi.ac.kr", result.getEmail());
		assertEquals("010-1234-5678", result.getPhone());
		assertEquals(CSE, result.getMajor());
	}
}
