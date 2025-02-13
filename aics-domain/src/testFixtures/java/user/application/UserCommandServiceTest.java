package user.application;

import kgu.developers.domain.user.application.command.UserCommandService;
import kgu.developers.domain.user.domain.Major;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.exception.DuplicatePasswordException;
import kgu.developers.domain.user.exception.UserIdDuplicateException;
import mock.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static kgu.developers.domain.user.domain.Major.CSE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserCommandServiceTest {
	private UserCommandService userCommandService;
	private User user;

	@BeforeEach
	public void init() {
		FakeUserRepository fakeUserRepository = new FakeUserRepository();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userCommandService = new UserCommandService(
			new BCryptPasswordEncoder(),
			fakeUserRepository
		);

		user = User.builder()
			.id("202411345")
			.password(passwordEncoder.encode("password"))
			.build();
		fakeUserRepository.save(user);
	}

	@Test
	@DisplayName("createUser는 유저를 생성할 수 있다")
	public void createUser_Success() {
		// given
		String userId = "202411347";
		String password = "123456";
		String name = "김철수";
		String email = "kim@kyonggi.ac.kr";
		String phone = "010-0000-0000";
		Major major = CSE;

		// when
		String result = userCommandService.createUser(userId, password, name, email, phone, major);

		// then
		assertEquals("202411347", result);
	}

	@Test
	@DisplayName("createUser는 중복된 아이디로 유저를 생성할 경우 UserIdDuplicateException을 발생시킨다")
	public void createUser_DuplicateId_ThrowsException() {
		// given
		String userId = "202411345";
		String password = "123456";
		String name = "김철수";
		String email = "kim@kyonggi.ac.kr";
		String phone = "010-0000-0000";
		Major major = CSE;

		// when
		// then
		assertThatThrownBy(() -> userCommandService.createUser(userId, password, name, email, phone, major))
			.isInstanceOf(UserIdDuplicateException.class);
	}

	@Test
	@DisplayName("updateUserDetails는 User의 정보를 수정할 수 있다")
	public void updateUserDetails_Success() {
		// given
		User user = User.builder().build();
		String newEmail = "kim@kyonggi.ac.kr";
		String newPhone = "010-0000-0000";

		// when
		userCommandService.updateUserDetails(user, newEmail, newPhone);

		// then
		assertEquals("kim@kyonggi.ac.kr", user.getEmail());
		assertEquals("010-0000-0000", user.getPhone());
	}

	@Test
	@DisplayName("updatePassword는 User의 비밀번호를 수정할 수 있다")
	public void updatePassword_Success() {
		// given
		String originalPassword = "password";
		String newPassword = "newPassword";

		// when
		// then
		assertDoesNotThrow(() -> userCommandService.updatePassword(user, originalPassword, newPassword));
	}

	@Test
	@DisplayName("updatePassword는 기존 비밀번호와 같은 비밀번호로 수정하면 DuplicatePasswordException를 발생시킨다")
	public void updatePassword_ThrowsException() {
		// given
		String originalPassword = "password";
		String newPassword = "password";

		// when
		// then
		assertThatThrownBy(() -> userCommandService.updatePassword(user, originalPassword, newPassword))
			.isInstanceOf(DuplicatePasswordException.class);
	}
}
