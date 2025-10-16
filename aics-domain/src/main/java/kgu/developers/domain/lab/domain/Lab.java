package kgu.developers.domain.lab.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class Lab{

	private Long id;
	private String name;
	private String loc;
	private String site;
	private String advisor;
	private Long fileId;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;


	public static Lab create(String name, String loc, String site, String advisor, Long fileId) {
		return Lab.builder()
			.name(name)
			.loc(loc)
			.site(site)
			.advisor(advisor)
			.fileId(fileId)
			.build();
	}

	public void updateName(String name) {
		this.name = name;
	}

	public void updateLoc(String loc) {
		this.loc = loc;
	}

	public void updateSite(String site) {
		this.site = site;
	}

	public void updateAdvisor(String advisor) {
		this.advisor = advisor;
	}

	public void updateFileId(Long fileId) {
		this.fileId = fileId;
	}
}
