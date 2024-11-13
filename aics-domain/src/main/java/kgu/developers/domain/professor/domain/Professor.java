package kgu.developers.domain.professor.domain;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kgu.developers.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Professor extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false, length = 10)
	private String name;

	@Column(nullable = false)
	@Enumerated(STRING)
	private Role role;

	@Column(nullable = false, unique = true, length = 15)
	private String contact;

	@Column(nullable = false, unique = true, length = 50)
	private String email;

	public static Professor create(String name, Role role, String contact, String email) {
		return Professor.builder()
			.name(name)
			.role(role)
			.contact(contact)
			.email(email)
			.build();
	}

	public void updateName(String name) {
		this.name = name;
	}

	public void updateRole(Role role) {
		this.role = role;
	}

	public void updateContact(String contact) {
		this.contact = contact;
	}

	public void updateEmail(String email) {
		this.email = email;
	}
}
