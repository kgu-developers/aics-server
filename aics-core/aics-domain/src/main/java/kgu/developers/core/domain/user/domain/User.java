package kgu.developers.core.domain.user.domain;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static kgu.developers.core.domain.user.domain.Role.*;
import static kgu.developers.core.domain.user.domain.Status.*;
import static lombok.AccessLevel.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kgu.developers.core.common.domain.BaseTimeEntity;
import kgu.developers.core.domain.major.Major;
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
public class User extends BaseTimeEntity {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false, updatable = false, length = 10)
	private String personalId;

	@Column(nullable = false, length = 20)
	private String password;

	@Column(nullable = false, length = 20)
	private String name;

	@Column(nullable = false, length = 8)
	private String birth;

	@Column(nullable = false)
	@Enumerated(STRING)
	private Gender gender;

	@Column(nullable = false)
	@Enumerated(STRING)
	private Grade grade;

	@Column(nullable = false)
	@Enumerated(STRING)
	private Status status;

	@Column(nullable = false)
	@Enumerated(STRING)
	private Role role;

	@Column(nullable = false)
	private boolean hasAiAccess;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "major_id")
	private Major major;

	@OneToMany(mappedBy = "author", cascade = ALL, fetch = LAZY)
	List<Post> posts = new ArrayList<>();

	public static User create(String personalId, String password, String name, String birth, Gender gender,
		Grade grade) {
		return User.builder()
			.personalId(personalId)
			.password(password)
			.name(name)
			.birth(birth)
			.gender(gender)
			.grade(grade)
			.status(INSCHOOL)
			.role(GUEST)
			.hasAiAccess(false)
			.build();
	}
}
