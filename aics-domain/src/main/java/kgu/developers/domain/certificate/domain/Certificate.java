package kgu.developers.domain.certificate.domain;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Certificate {
	private Long id;
	private Long scheduleId;
	private Long certificateFileId;
	private boolean approval;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;

	public static Certificate create(
		Long scheduleId,
		Long certificateFileId
	) {
		return Certificate.builder()
			.scheduleId(scheduleId)
			.certificateFileId(certificateFileId)
			.approval(false)
			.build();
	}

	public static Certificate of(
		Long id,
		Long scheduleId,
		Long certificateFileId,
		boolean approval,
		LocalDateTime createdAt,
		LocalDateTime updatedAt,
		LocalDateTime deletedAt) {
		Certificate certificate = new Certificate();
		certificate.id = id;
		certificate.scheduleId = scheduleId;
		certificate.certificateFileId = certificateFileId;
		certificate.approval = approval;
		certificate.createdAt = createdAt;
		certificate.updatedAt = updatedAt;
		certificate.deletedAt = deletedAt;
		return certificate;
	}
}
