package kgu.developers.domain.user.application.command;

import kgu.developers.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class UserSchedulingService {
	private final UserRepository userRepository;

	public static final int USER_RETENTION_DAYS = 30;
	private LocalDateTime lastScheduledRun;

//	@Scheduled(cron = "0 0 0 1 * ?")
	@Scheduled(cron = "0 0/1 * * * ?")
	@Transactional
	public void cleanupOldDeletedPosts() {
		userRepository.deleteAllByDeletedAtBefore(USER_RETENTION_DAYS);
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
