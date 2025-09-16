package kgu.developers.domain.professor.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class Professor {
	private Long id;
	private String name;
	private Role role;
	private String contact;
	private String email;
	private String img;
	private String officeLoc;

	private LocalDateTime deletedAt;

	public static Professor create(String name, Role role, String contact,
								   String email, String img, String officeLoc) {
		return Professor.builder()
			.name(name)
			.role(role)
			.contact(contact)
			.email(email)
			.img(img)
			.officeLoc(officeLoc)
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

	public void updateImage(String image) {
		this.img = image;
	}

	public void updateOfficeLoc(String officeLoc) {
		this.officeLoc = officeLoc;
	}

	public void markDeleted() { this.deletedAt = LocalDateTime.now(); }

}
