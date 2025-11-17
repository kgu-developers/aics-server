package kgu.developers.domain.certificate.infrastructure.entity;

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
import kgu.developers.domain.certificate.domain.Certificate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@Table(
	name = "certificate",
	indexes = {
		@Index(name = "idx_certificate_schedule_id", columnList = "schedule_id"),
	})
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class CertificateJpaEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long scheduleId;

	@Column(nullable = false)
	private Long certificateFileId;

	@Column(nullable = false)
	private boolean approval = false;

	public static CertificateJpaEntity toEntity(Certificate certificate) {
		if (certificate == null) {
			return null;
		}

		return CertificateJpaEntity.builder()
			.id(certificate.getId())
			.scheduleId(certificate.getScheduleId())
			.certificateFileId(certificate.getCertificateFileId())
			.approval(certificate.isApproval())
			.build();
	}

	public static Certificate toDomain(CertificateJpaEntity entity) {
		if (entity == null) {
			return null;
		}

		return Certificate.of(
			entity.id,
			entity.scheduleId,
			entity.certificateFileId,
			entity.approval,
			entity.getCreatedAt(),
			entity.getUpdatedAt(),
			entity.getDeletedAt()
		);
	}
}
