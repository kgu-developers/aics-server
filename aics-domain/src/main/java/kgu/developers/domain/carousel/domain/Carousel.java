package kgu.developers.domain.carousel.domain;

import static jakarta.persistence.CascadeType.REMOVE;
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
public class Carousel extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String text;

	@Column(nullable = false)
	private String link;

	@OneToOne(cascade = REMOVE)
	@JoinColumn(name = "file_id", nullable = false)
	private FileEntity file;

	public static Carousel create(String text, String link, FileEntity file) {
		return Carousel.builder()
			.text(text)
			.link(link)
			.file(file)
			.build();
	}

	public void updateText(String text) {
		this.text = text;
	}

	public void updateLink(String link) {
		this.link = link;
	}

	public void updateFile(FileEntity file) {
		this.file = file;
	}
}

