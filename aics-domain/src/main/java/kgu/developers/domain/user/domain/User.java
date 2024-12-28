package kgu.developers.domain.user.domain;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static kgu.developers.domain.user.domain.DeptCode.isValidDeptCode;
import static lombok.AccessLevel.PROTECTED;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.user.exception.DeptCodeNotValidException;
import kgu.developers.domain.user.exception.EmailDomainNotValidException;
import kgu.developers.domain.user.exception.InvalidPasswordException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "\"user\"")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class User extends BaseTimeEntity implements UserDetails {

	@Id
	@Column(length = 10)
	private String id;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, length = 10)
	private String name;

	@Column(unique = true, nullable = false, length = 50)
	private String email;

	@Column(unique = true, nullable = false, length = 15)
	private String phone;

	@Column(nullable = false)
	@Enumerated(STRING)
	private Role role;

	@Column(nullable = false)
	@Enumerated(STRING)
	private Major major;

	@Builder.Default
	@OneToMany(mappedBy = "author", cascade = ALL, fetch = LAZY)
	List<Post> posts = new ArrayList<>();

	public static User create(String id, String password, String name, String email, String phone, Major major,
		PasswordEncoder passwordEncoder) {
		validateDept(id, email);
		return User.builder()
			.id(id)
			.password(passwordEncoder.encode(password))
			.name(name)
			.email(email)
			.phone(phone)
			.role(Role.USER)
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
		return Collections.singletonList(new SimpleGrantedAuthority(Role.USER.name()));
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
}
