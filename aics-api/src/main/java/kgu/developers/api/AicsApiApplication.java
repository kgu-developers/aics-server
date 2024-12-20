package kgu.developers.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableRedisRepositories(basePackages = "kgu.developers.domain.refreshtoken.domain")
public class AicsApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(AicsApiApplication.class, args);
	}
}