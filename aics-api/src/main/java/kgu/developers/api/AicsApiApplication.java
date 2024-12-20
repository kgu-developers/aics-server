package kgu.developers.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AicsApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(AicsApiApplication.class, args);
	}
}