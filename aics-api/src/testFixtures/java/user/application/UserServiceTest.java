package user.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kgu.developers.api.user.application.UserFacade;
import kgu.developers.domain.user.exception.UserIdDuplicateException;
import kgu.developers.api.user.presentation.request.UserCreateRequest;
import kgu.developers.api.user.presentation.response.UserPersistResponse;
import kgu.developers.domain.user.domain.Major;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.exception.UserNotFoundException;
import mock.FakeUserRepository;
/*
public class UserServiceTest {
	private UserFacade userFacade;

	@BeforeEach
	public void init() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		FakeUserRepository fakeUserRepository = new FakeUserRepository();

		this.userFacade = UserFacade.builder()
			.userRepository(fakeUserRepository)
			.bCryptPasswordEncoder(bCryptPasswordEncoder)
			.build();

		fakeUserRepository.save(User.builder()
			.id("202411345")
			.password(bCryptPasswordEncoder.encode("password1234"))
			.name("홍길동")
			.email("test@kyonggi.ac.kr")
			.phone("010-1234-5678")
			.major(Major.CSE)
			.build());

		fakeUserRepository.save(User.builder()
			.id("202411346")
			.password(bCryptPasswordEncoder.encode("password5678"))
			.name("신짱구")
			.email("shin@kyonggi.ac.kr")
			.phone("010-5678-1234")
			.major(Major.AIT)
			.build());
	}

	@Test
	@DisplayName("createUser는 유저를 생성할 수 있다")
	public void createUser_Success() {
		// given
		UserCreateRequest request = UserCreateRequest.builder()
			.userId("202411347")
			.password("password0000")
			.name("김철수")
			.email("kim@kyonggi.ac.kr")
			.phone("010-0000-0000")
			.major(Major.CSE)
			.build();

		// when
		UserPersistResponse result = userFacade.createUser(request);

		// then
		assertEquals("202411347", result.id());
	}

	@Test
	@DisplayName("createUser는 중복된 아이디로 유저를 생성할 경우 UserIdDuplicateException을 발생시킨다")
	public void createUser_DuplicateId_ThrowsException() {
		// given
		UserCreateRequest request = UserCreateRequest.builder()
			.userId("202411345")
			.password("password0000")
			.name("김철수")
			.email("kim@kyonggi.ac.kr")
			.phone("010-0000-0000")
			.major(Major.CSE)
			.build();

		// when
		// then
		assertThatThrownBy(() -> {
			userFacade.createUser(request);
		}).isInstanceOf(UserIdDuplicateException.class);
	}

	@Test
	@DisplayName("getUserById는 유저를 찾아올 수 있다")
	public void getUserById_Success() {
		// given
		String id = "202411345";

		// when
		User result = userFacade.getUserById(id);

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
		assertThatThrownBy(() -> {
			userFacade.getUserById(id);
		}).isInstanceOf(UserNotFoundException.class);
	}
}
*/

