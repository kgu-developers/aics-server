package kgu.developers.domain.user.domain;

import kgu.developers.common.domain.BaseRole;
import kgu.developers.domain.user.exception.AlreadyDeletedUserException;
import kgu.developers.domain.user.exception.DeptCodeNotValidException;
import kgu.developers.domain.user.exception.DuplicatePasswordException;
import kgu.developers.domain.user.exception.EmailDomainNotValidException;
import kgu.developers.domain.user.exception.InvalidPasswordException;
import kgu.developers.domain.user.exception.NotDeletableUserException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static kgu.developers.common.domain.BaseRole.USER;
import static kgu.developers.domain.user.domain.DeptCode.isValidDeptCode;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class User implements UserDetails {

	private String id;

	private String password;

	private String name;

	private String email;

	private String phone;

	private BaseRole role;

	private Major major;

	protected LocalDateTime createdAt;
	protected LocalDateTime updatedAt;
	protected LocalDateTime deletedAt;

	public static User create(String id, String password, String name, String email,
							  String phone, Major major, PasswordEncoder passwordEncoder) {
		validateDept(id, email);
		return User.builder()
			.id(id)
			.password(encodePassword(password, passwordEncoder))
			.name(name)
			.email(email)
			.phone(phone)
			.role(USER)
			.major(major)
			.build();
	}

	public void updateEmail(String email) {
		if (!isValidEmailDomain(email))
			throw new EmailDomainNotValidException();
		this.email = email;
	}

	public void updatePhone(String phone) {
		this.phone = phone;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(USER.name()));
	}

	@Override
	public String getUsername() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	private static void validateDept(String id, String email) {
		if (!isValidEmailDomain(email))
			throw new EmailDomainNotValidException();
		if (!isValidDeptCode(id))
			throw new DeptCodeNotValidException();
	}

	private static final List<String> ACCESSIBLE_EMAIL_DOMAINS = List.of(
		"@kyonggi.ac.kr",
		"@kgu.ac.kr"
	);

	private static boolean isValidEmailDomain(String email) {
		if (email == null) {
			return false;
		}

		return ACCESSIBLE_EMAIL_DOMAINS.stream()
			.anyMatch(email::endsWith);
	}

	public void isPasswordMatching(String rawPassword, PasswordEncoder passwordEncoder) {
		if (!passwordEncoder.matches(rawPassword, this.password)) {
			throw new InvalidPasswordException();
		}
	}

	public void isNewPasswordMatching(String rawPassword, PasswordEncoder passwordEncoder) {
		if (passwordEncoder.matches(rawPassword, this.password)) {
			throw new DuplicatePasswordException();
		}
	}

	public void updatePassword(String password, PasswordEncoder passwordEncoder) {
		this.password = encodePassword(password, passwordEncoder);
	}

	private static String encodePassword(String password, PasswordEncoder passwordEncoder) {
		return passwordEncoder.encode(password);
	}

	public void validateDeletable() {
		if (role != null && role != USER) {
			throw new NotDeletableUserException();
		}
	}

	public void isDeleted() {
		if (deletedAt != null)
			throw new AlreadyDeletedUserException();
	}

	public void delete(){
		deletedAt = LocalDateTime.now();
	}
}
