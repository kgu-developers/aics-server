package kgu.developers.domain.club.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
public class Club{

	private Long id;
	private String name;
	private String description;
	private String site;
	private Long fileId;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;

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

	public void updateFileId(Long fileId) {
		this.fileId = fileId;
	}
}
