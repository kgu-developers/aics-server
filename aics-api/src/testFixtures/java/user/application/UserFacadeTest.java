package user.application;

import static kgu.developers.domain.user.domain.Major.CSE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kgu.developers.api.user.application.UserFacade;
import kgu.developers.api.user.presentation.request.UserCreateRequest;
import kgu.developers.api.user.presentation.request.UserUpdateRequest;
import kgu.developers.api.user.presentation.response.UserPersistResponse;
import kgu.developers.domain.user.application.command.UserCommandService;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.application.response.UserDetailResponse;
import kgu.developers.domain.user.domain.User;
import mock.repository.FakeUserRepository;

public class UserFacadeTest {
	private UserFacade userFacade;

	@BeforeEach
	public void init() {
		FakeUserRepository fakeUserRepository = new FakeUserRepository();
		UserQueryService userQueryService = new UserQueryService(fakeUserRepository);
		userFacade = new UserFacade(
			userQueryService,
			new UserCommandService(new BCryptPasswordEncoder(), fakeUserRepository)
		);

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
	@DisplayName("createUser는 유저를 생성한다")
	public void createUser_Success() {
		// given
		UserCreateRequest request = UserCreateRequest.builder()
			.userId("202411346")
			.password("password1234")
			.name("홍길순")
			.email("test@kgu.ac.kr")
			.phone("010-5678-1234")
			.major(CSE)
			.build();

		// when
		UserPersistResponse result = userFacade.createUser(request);

		// then
		assertEquals("202411346", result.id());
	}

	@Test
	@DisplayName("updateUser는 유저의 정보를 수정한다")
	public void updateUser_Success() {
		// given
		UserUpdateRequest request = UserUpdateRequest.builder()
			.email("update@kyonggi.ac.kr")
			.phone("010-1234-5679")
			.build();

		// when
		userFacade.updateUser(request);

		// then
		UserDetailResponse result = userFacade.getUserDetail();
		assertEquals("update@kyonggi.ac.kr", result.email());
		assertEquals("010-1234-5679", result.phone());
	}

	@Test
	@DisplayName("getUserDetail은 현재 로그인 유저의 정보를 반환한다")
	public void getUserDetail_Success() {

		//when
		UserDetailResponse result = userFacade.getUserDetail();

		//then
		assertEquals("202411345", result.id());
		assertEquals("홍길동", result.name());
		assertEquals("test@kyonggi.ac.kr", result.email());
		assertEquals("010-1234-5678", result.phone());
		assertEquals(CSE, result.major());
	}
}
