package kgu.developers.domain.carousel.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Carousel extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String text;

	@Column(nullable = false)
	private String link;

	@Column(nullable = false)
	private Long fileId;

	public static Carousel create(String text, String link, Long fileId) {
		return Carousel.builder()
			.text(text)
			.link(link)
			.fileId(fileId)
			.build();
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

