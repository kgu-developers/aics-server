package kgu.developers.domain.post.application.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.domain.post.domain.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostSchedulingService {
	private final PostRepository postRepository;

	public static final int POST_RETENTION_DAYS = 60 * 60 * 24 * 30;
	private LocalDateTime lastScheduledRun;

	@Scheduled(cron = "0 0 0 * * *")
	@Transactional
	public void cleanupOldDeletedPosts() {
		postRepository.deleteAllByDeletedAtBefore(POST_RETENTION_DAYS);
		lastScheduledRun = LocalDateTime.now();
	}

	public String getFormattedLastCleanupRunTime() {
		if (lastScheduledRun == null) {
			return "아직 클린업 작업이 실행되지 않았습니다.";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
		return "최근 삭제된 게시글 정리 시간: " + lastScheduledRun.format(formatter);
	}
}
