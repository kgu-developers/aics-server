package kgu.developers.core.domain.user.domain;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static kgu.developers.core.domain.user.domain.Role.*;
import static lombok.AccessLevel.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kgu.developers.core.common.domain.BaseTimeEntity;
import kgu.developers.core.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "\"user\"")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class User extends BaseTimeEntity implements UserDetails {

	@Id
	@Column(length = 10)
	private String userId;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, length = 10)
	private String name;

	@Column(unique = true, nullable = false, length = 50)
	private String email;

	@Column(unique = true, nullable = false, length = 15)
	private String phoneNumber;

	@Column(nullable = false)
	@Enumerated(STRING)
	private Role role;

	@Column(nullable = false)
	@Enumerated(STRING)
	private Major major;

	@Builder.Default
	@OneToMany(mappedBy = "author", cascade = ALL, fetch = LAZY)
	List<Post> posts = new ArrayList<>();

	public static User create(String userId, String password,
		String name, String email,
		String phoneNumber, Major major) {
		return User.builder()
			.userId(userId)
			.password(password)
			.name(name)
			.email(email)
			.phoneNumber(phoneNumber)
			.role(USER)
			.major(major)
			.build();
	}

	public void updateEmail(String email) {
		this.email = email;
	}

	public void updatePhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getUsername() {
		return userId;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void addPost(Post post) {
		posts.add(post);
		post.setAuthor(this);
	}
}
