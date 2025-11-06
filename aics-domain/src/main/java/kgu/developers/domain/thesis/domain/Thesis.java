package kgu.developers.domain.thesis.domain;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Thesis {
	private Long id;
	private Long scheduleId;
	private Long thesisFileId;
	private boolean approval;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;

	public static Thesis of(
		Long id,
		Long scheduleId,
		Long thesisFileId,
		boolean approval,
		LocalDateTime createdAt,
		LocalDateTime updatedAt,
		LocalDateTime deletedAt) {
		Thesis thesis = new Thesis();
		thesis.id = id;
		thesis.scheduleId = scheduleId;
		thesis.thesisFileId = thesisFileId;
		thesis.approval = approval;
		thesis.createdAt = createdAt;
		thesis.updatedAt = updatedAt;
		thesis.deletedAt = deletedAt;
		return thesis;
	}
}
