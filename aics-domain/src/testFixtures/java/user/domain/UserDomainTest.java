package user.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kgu.developers.domain.user.domain.Major;
import kgu.developers.domain.user.domain.Role;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.exception.DeptCodeNotValidException;
import kgu.developers.domain.user.exception.EmailDomainNotValidException;
import kgu.developers.domain.user.exception.InvalidPasswordException;

public class UserDomainTest {

	@Test
	@DisplayName("USER 객체를 생성할 수 있다")
	public void createUser_Success() {
		// given
		String id = "202411345";
		String password = "password";
		String name = "홍길동";
		String email = "valid@kgu.ac.kr";
		String phone = "010-1234-5678";
		Major major = Major.CSE;

		// when
		User user = User.create(id, password, name, email, phone, major);

		// then
		assertNotNull(user);
		assertEquals(id, user.getId());
		assertEquals(password, user.getPassword());
		assertEquals(name, user.getName());
		assertEquals(email, user.getEmail());
		assertEquals(phone, user.getPhone());
		assertEquals(Role.USER, user.getRole());
		assertEquals(major, user.getMajor());
	}

	@Test
	@DisplayName("잘못된 이메일 도메인으로 USER 생성 시 EmailDomainNotValidException이 발생 한다")
	public void createUser_InvalidEmailDomain_ThrowsException() {
		// given
		String id = "202411345";
		String password = "password";
		String name = "홍길동";
		String email = "valid@gmail.com"; // 잘못된 이메일 도메인
		String phone = "010-1234-5678";
		Major major = Major.CSE;

		// when
		// then
		assertThatThrownBy(() -> {
			User.create(id, password, name, email, phone, major);
		}).isInstanceOf(EmailDomainNotValidException.class);
	}

	@Test
	@DisplayName("잘못된 학과 코드로 USER 생성 시 DeptCodeNotValidException이 발생 한다")
	public void createUser_InvalidDeptCode_ThrowsException() {
		// given
		String id = "202410345"; // 잘못된 학과 코드
		String password = "password";
		String name = "홍길동";
		String email = "valid@kyonggi.ac.kr";
		String phone = "010-1234-5678";
		Major major = Major.CSE;

		// when
		// then
		assertThatThrownBy(() -> {
			User.create(id, password, name, email, phone, major);
		}).isInstanceOf(DeptCodeNotValidException.class);
	}

	@Test
	@DisplayName("비밀번호가 일치하지 않을 시 InvalidPasswordException이 발생 한다")
	public void isPasswordMatching_InvalidPassword_ThrowsException() {
		// given
		String rawPassword = "invalidPassword";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		User user = User.create(
			"202411345",
			passwordEncoder.encode("correctPassword"),
			"홍길동",
			"valid@kgu.ac.kr",
			"010-1234-5678",
			Major.CSE
		);

		// when
		// then
		assertThatThrownBy(() -> {
			user.isPasswordMatching(rawPassword, passwordEncoder);
		}).isInstanceOf(InvalidPasswordException.class);
	}
}
