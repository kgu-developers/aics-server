package user.application;

import static kgu.developers.domain.user.domain.Major.CSE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import kgu.developers.common.response.PageableResponse;
import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.application.response.UserDetailResponse;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.exception.UserNotAuthenticatedException;
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

		fakeUserRepository.save(User.builder()
			.id("202411346")
			.password("password1234")
			.name("박민준")
			.email("test1@kyonggi.ac.kr")
			.phone("010-1234-5679")
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
	@DisplayName("me는 현재 로그인 되어있는 사용자의 객체를 가져온다.")
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

	@Test
	@DisplayName("me는 현재 로그인이 안되어있는 상태에서 호출 시 UserNotAuthenticatiedException을 발생시킨다")
	public void me_Throws_NotAuthenticated_ThrowsException() {
		// given
		SecurityContextHolder.clearContext();

		// when
		// then
		assertThatThrownBy(() -> userQueryService.me())
			.isInstanceOf(UserNotAuthenticatedException.class);
	}

	@Test
	@DisplayName("getUserDetail은 현재 로그인 되어있는 사용자의 상세정보를 가져온다")
	public void getUserDetail_Success() {

		// when
		UserDetailResponse result = userQueryService.getUserDetail();

		// then
		assertEquals("202411345", result.id());
		assertEquals("홍길동", result.name());
		assertEquals("test@kyonggi.ac.kr", result.email());
		assertEquals("010-1234-5678", result.phone());
		assertEquals(CSE, result.major());
	}

	@Test
	@DisplayName("getUsersByName은 유저 리스트를 이름을 검색하여 페이징 조회할 수 있다")
	public void getUsersByAll_Success() {
		// given
		int page = 0;
		int size = 10;
		String name = "길동";

		// when
		PaginatedListResponse<User> result = userQueryService.getUsersByName(
			PageRequest.of(page, size), name
		);

		// then
		List<User> resultData = result.contents();
		User resultUser = resultData.get(0);
		PageableResponse resultPageable = result.pageable();

		assertEquals(1, resultData.size());
		assertEquals("202411345", resultUser.getId());
		assertEquals("홍길동", resultUser.getName());
		assertEquals("test@kyonggi.ac.kr", resultUser.getEmail());
		assertEquals("010-1234-5678", resultUser.getPhone());
		assertEquals(CSE, resultUser.getMajor());

		assertEquals(page, resultPageable.page());
		assertEquals(size, resultPageable.size());
	}

	@Test
	@DisplayName("getAllUsersByIds은 아이디 리스트로 유저 리스트를 조회한다")
	public void getAllUsersByIds_Success() {
		// given
		List<String>  ids = Arrays.asList("202411345", "202411346");

		// when
		List<User> users = userQueryService.getAllUsersByIds(ids);

		// then
		assertEquals(ids.size(), users.size());
		assertEquals("202411345", users.get(0).getId());
		assertEquals("202411346", users.get(1).getId());
	}
}
