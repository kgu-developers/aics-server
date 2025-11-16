package kgu.developers.domain.thesis.infrastructure.entity;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.thesis.domain.Thesis;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(
	name = "thesis",
	indexes = {
		@Index(name = "idx_thesis_schedule_id", columnList = "schedule_id"),
	})
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class ThesisJpaEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long scheduleId;

	@Column(nullable = false)
	private Long thesisFileId;

	@Column(nullable = false)
	private boolean approval = false;

	public static ThesisJpaEntity toEntity(Thesis thesis) {
		if (thesis == null) {
			return null;
		}

		return ThesisJpaEntity.builder()
			.id(thesis.getId())
			.scheduleId(thesis.getScheduleId())
			.thesisFileId(thesis.getThesisFileId())
			.approval(thesis.isApproval())
			.build();
	}

	public static Thesis toDomain(ThesisJpaEntity entity) {
		if (entity == null) {
			return null;
		}

		return Thesis.of(
			entity.id,
			entity.scheduleId,
			entity.thesisFileId,
			entity.approval,
			entity.getCreatedAt(),
			entity.getUpdatedAt(),
			entity.getDeletedAt()
		);
	}
}
