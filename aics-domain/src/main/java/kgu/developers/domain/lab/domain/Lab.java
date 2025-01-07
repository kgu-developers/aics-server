package kgu.developers.domain.lab.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.file.domain.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Lab extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false, length = 16)
	private String name;

	@Column(nullable = false, length = 10)
	private String location;

	@Column(nullable = false, length = 50)
	private String site;

	@Column(nullable = false, length = 16)
	private String professor;

	@OneToOne
	@JoinColumn(name = "file_id")
	private FileEntity file;

	public static Lab create(String name, String location, String site, String professor) {
		return Lab.builder()
			.name(name)
			.location(location)
			.site(site)
			.professor(professor)
			.build();
	}

	public void updateName(String name) {
		this.name = name;
	}

	public void updateLocation(String location) {
		this.location = location;
	}

	public void updateSite(String site) {
		this.site = site;
	}

	public void updateProfessor(String professor) {
		this.professor = professor;
	}
}
