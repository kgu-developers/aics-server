package kgu.developers.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

	@Value("${POST_CLEANUP_CRON:0 0 0 * * *}")
	private String postCleanupCron;

	@Value("${POST_RETENTION_DAYS:30}")
	private int postRetentionDays;

	@Bean
	public String postCleanupCron() {
		return postCleanupCron;
	}

	@Bean
	public int postRetentionDays() {
		return postRetentionDays;
	}
}
