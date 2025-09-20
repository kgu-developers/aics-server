package kgu.developers.domain.carousel.infrastructure.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.carousel.domain.Carousel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "carousel")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class CarouselJpaEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String text;

	@Column(nullable = false)
	private String link;

	@Column(nullable = false)
	private Long fileId;

	public static CarouselJpaEntity toEntity(Carousel carousel) {
		return CarouselJpaEntity.builder()
			.id(carousel.getId())
			.text(carousel.getText())
			.link(carousel.getLink())
			.fileId(carousel.getFileId())
			.build();
	}

	public Carousel toDomain() {
		return new Carousel(
			this.id,
			this.text,
			this.link,
			this.fileId,
			this.getCreatedAt(),
			this.getUpdatedAt(),
			this.getDeletedAt()
		);
	}
}
