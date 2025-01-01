package kgu.developers.domain.comment.application.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import kgu.developers.domain.comment.domain.CommentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentSchedulingService {
	private final CommentRepository commentRepository;

	public static final int COMMENT_RETENTION_DAYS = 60 * 60 * 24 * 30;
	private LocalDateTime lastScheduledRun;

	@Scheduled(cron = "0 0 0 * * *")
	public void cleanupOldDeletedComments() {
		commentRepository.deleteAllByDeletedAtBefore(COMMENT_RETENTION_DAYS);
		lastScheduledRun = LocalDateTime.now();
	}

	public String getFormattedLastCleanupRunTime() {
		if (lastScheduledRun == null) {
			return "아직 클린업 작업이 실행되지 않았습니다.";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
		return "최근 삭제된 댓글 정리 시간: " + lastScheduledRun.format(formatter);
	}
}
