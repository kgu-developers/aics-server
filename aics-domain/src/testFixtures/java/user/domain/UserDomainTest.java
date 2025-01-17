package user.domain;

import static kgu.developers.domain.user.domain.Major.CSE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kgu.developers.common.domain.BaseRole;
import kgu.developers.domain.user.domain.Major;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.exception.DeptCodeNotValidException;
import kgu.developers.domain.user.exception.EmailDomainNotValidException;
import kgu.developers.domain.user.exception.InvalidPasswordException;

public class UserDomainTest {
	private User user;
	private static final String ID = "202411345";
	private static final String PASSWORD = "$2a$10$ViIAGtB9Y/9cE//3WY6i4e6RQVHbJhQQDWshsFlElNnyz88.8EOu2";
	private static final String NAME = "홍길동";
	private static final String EMAIL = "valid@kgu.ac.kr";
	private static final String VALID_EMAIL = "valid@kgu.ac.kr";
	private static final String PHONE = "010-1234-5678";
	private static final Major MAJOR = CSE;

	@BeforeEach
	public void init() {
		user = createTestUser(ID, PASSWORD, EMAIL, MAJOR);
	}

	private User createTestUser(String id, String password, String email, Major major) {
		return User.create(id, password, NAME, email, PHONE, major);
	}

	@Test
	@DisplayName("USER 객체를 생성할 수 있다")
	public void createUser_Success() {
		// when
		// then
		assertNotNull(user);
		assertEquals(ID, user.getId());
		assertEquals(PASSWORD, user.getPassword());
		assertEquals(NAME, user.getName());
		assertEquals(VALID_EMAIL, user.getEmail());
		assertEquals(PHONE, user.getPhone());
		assertEquals(BaseRole.USER, user.getRole());
		assertEquals(MAJOR, user.getMajor());

	}

	@Test
	@DisplayName("잘못된 이메일 도메인으로 USER 생성 시 EmailDomainNotValidException이 발생 한다")
	public void createUser_InvalidEmailDomain_ThrowsException() {
		// given
		String email = "valid@gmail.com";

		// when
		// then
		assertThatThrownBy(() -> createTestUser(ID, PASSWORD, email, MAJOR))
			.isInstanceOf(EmailDomainNotValidException.class);
	}

	@Test
	@DisplayName("이메일 Null로 USER 생성 시 EmailDomainNotValidException이 발생 한다")
	public void createUser_NullEmailDomain_ThrowsException() {
		// given
		String email = "valid@gmail.com";

		// when
		// then
		assertThatThrownBy(() -> createTestUser(ID, PASSWORD, null, MAJOR))
			.isInstanceOf(EmailDomainNotValidException.class);
	}

	@Test
	@DisplayName("잘못된 학과 코드로 USER 생성 시 DeptCodeNotValidException이 발생 한다")
	public void createUser_InvalidDeptCode_ThrowsException() {
		// given
		String id = "202410345"; // 잘못된 학과 코드

		// when
		// then
		assertThatThrownBy(() -> createTestUser(id, PASSWORD, VALID_EMAIL, MAJOR))
			.isInstanceOf(DeptCodeNotValidException.class);
	}

	@Test
	@DisplayName("비밀번호가 일치하지 않을 시 InvalidPasswordException이 발생 한다")
	public void isPasswordMatching_InvalidPassword_ThrowsException() {
		// given
		String invalidPassword = "invalidPassword";

		// when
		// then
		assertThatThrownBy(() -> user.isPasswordMatching(invalidPassword, new BCryptPasswordEncoder()))
			.isInstanceOf(InvalidPasswordException.class);
	}
}
