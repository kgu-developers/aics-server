package kgu.developers.domain.club.domain;

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
public class Club extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 16)
	private String name;

	@Column(nullable = false, length = 100)
	private String description;

	@Column(length = 50)
	private String site;

	@Column(name = "file_id")
	private Long fileId;

	public static Club create(String name, String description, String site, Long fileId) {
		return Club.builder()
			.name(name)
			.description(description)
			.site(site)
			.fileId(fileId)
			.build();
	}

	public void updateName(String name) {
		this.name = name;
	}

	public void updateDescription(String description) {
		this.description = description;
	}

	public void updateSite(String site) {
		this.site = site;
	}

	public void updateFile(Long fileId) {
		this.fileId = fileId;
	}
}
