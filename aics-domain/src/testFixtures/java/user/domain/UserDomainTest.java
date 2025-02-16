package user.domain;

import kgu.developers.common.domain.BaseRole;
import kgu.developers.domain.user.domain.Major;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.exception.AlreadyDeletedUserException;
import kgu.developers.domain.user.exception.DeptCodeNotValidException;
import kgu.developers.domain.user.exception.EmailDomainNotValidException;
import kgu.developers.domain.user.exception.InvalidPasswordException;
import kgu.developers.domain.user.exception.NotDeletableUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static kgu.developers.common.domain.BaseRole.ADMIN;
import static kgu.developers.domain.user.domain.Major.CSE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDomainTest {
	private User user;
	private static final String ID = "202411345";
	private static final String PASSWORD = "$2a$10$ViIAGtB9Y/9cE//3WY6i4e6RQVHbJhQQDWshsFlElNnyz88.8EOu2";
	private static final String NAME = "홍길동";
	private static final String EMAIL = "valid@kgu.ac.kr";
	private static final String VALID_EMAIL = "valid@kgu.ac.kr";
	private static final String PHONE = "010-1234-5678";
	private static final Major MAJOR = CSE;
	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	@BeforeEach
	public void init() {
		user = createTestUser(ID, PASSWORD, EMAIL, MAJOR);
	}

	private User createTestUser(String id, String password, String email, Major major) {
		return User.create(id, password, NAME, email, PHONE, major, PASSWORD_ENCODER);
	}

	@Test
	@DisplayName("USER 객체를 생성할 수 있다")
	public void createUser_Success() {
		// when
		// then
		assertNotNull(user);
		assertEquals(ID, user.getId());
		assertTrue(PASSWORD_ENCODER.matches(PASSWORD, user.getPassword()));
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

		// when
		// then
		assertThatThrownBy(() -> createTestUser(ID, PASSWORD, null, MAJOR))
			.isInstanceOf(EmailDomainNotValidException.class);
	}

	@Test
	@DisplayName("잘못된 학과 코드로 USER 생성 시 DeptCodeNotValidException이 발생 한다")
	public void createUser_InvalidDeptCode_ThrowsException() {
		// given
		String id = "202410345";

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

	@Test
	@DisplayName("잘못된 이메일 도메인으로 수정 요청 시 EmailDomainNotValidException이 발생 한다")
	public void update_InvalidEmail_ThrowsException() {

		// when
		// then
		assertThatThrownBy(() -> user.updateEmail(null))
			.isInstanceOf(EmailDomainNotValidException.class);
	}

	@Test
	@DisplayName("updatePassword는 User의 password를 수정한다")
	public void updatePassword_Success() {
		// given
		String newPassword = "newPassword";

		// when
		user.updatePassword(newPassword, PASSWORD_ENCODER);

		// then
		assertTrue(PASSWORD_ENCODER.matches(newPassword, user.getPassword()));
		assertFalse(PASSWORD_ENCODER.matches(PASSWORD, user.getPassword()));
	}

	@Test
	@DisplayName("validateDeletable은 일반 유저인지 판별한다")
	public void validateDeletable_Success() {
		// given
		// when
		// then
		assertDoesNotThrow(() -> user.validateDeletable());
	}

	@Test
	@DisplayName("validateDeletable은 관리자인 경우 NotDeletableUserException를 반환한다")
	public void validateDeletable_throwsException() {
		// given
		// when
		User user = User.builder()
			.role(ADMIN)
			.build();

		// then
		assertThatThrownBy(user::validateDeletable)
			.isInstanceOf(NotDeletableUserException.class);
	}

	@Test
	@DisplayName("isDeleted는 아직 삭제하지 않은 경우 아무일도 하지 않는다")
	public void isDeleted_Success() {
		// given
		// when
		// then
		assertDoesNotThrow(() -> user.isDeleted());
	}

	@Test
	@DisplayName("isDeleted는 이미 삭제한 경우 AlreadyDeletedUserException를 반환한다")
	public void isDeleted_throwsException() {
		// given
		// when
		user.delete();

		// then
		assertThatThrownBy(user::isDeleted)
			.isInstanceOf(AlreadyDeletedUserException.class);
	}
}
