package kgu.developers.domain.professor.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.common.domain.PriorityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Professor extends BaseTimeEntity implements PriorityEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false, length = 10)
	private String name;

	@Column(nullable = false, length = 10)
	private String officeLoc;

	@Column(nullable = false, unique = true, length = 15)
	private String contact;

	@Column(nullable = false, unique = true, length = 50)
	private String email;

	@Column(nullable = false, length = 15)
	private String course;

	@Column(nullable = false)
	private Integer priority;

	public static Professor create(String name, String officeLoc, String contact, String email, String course) {
		return Professor.builder()
			.name(name)
			.officeLoc(officeLoc)
			.contact(contact)
			.email(email)
			.course(course)
			.priority(0)
			.build();
	}

	public void updateProfessor(String name, String officeLoc, String contact, String email, String course) {
		this.name = name;
		this.officeLoc = officeLoc;
		this.contact = contact;
		this.email = email;
		this.course = course;
	}

	@Override
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}
