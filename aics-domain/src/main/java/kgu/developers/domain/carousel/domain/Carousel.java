package kgu.developers.domain.carousel.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Carousel {
	private final Long id;
	private String text;
	private String link;
	private Long fileId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;

	public static Carousel create(
		String text,
		String link,
		Long fileId
	) {
		return new Carousel(
			null,
			text,
			link,
			fileId,
			LocalDateTime.now(),
			LocalDateTime.now(),
			null
		);
	}

	public void updateText(String text) {
		this.text = text;
	}

	public void updateLink(String link) {
		this.link = link;
	}

	public void updateFileId(Long fileId) {
		this.fileId = fileId;
	}
}


