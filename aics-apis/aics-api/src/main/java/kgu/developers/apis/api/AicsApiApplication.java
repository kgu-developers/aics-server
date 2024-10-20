package kgu.developers.apis.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AicsApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(AicsApiApplication.class, args);
	}
}