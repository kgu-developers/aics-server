package kgu.developers.apis.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// FileHandler 주입을 위한 scanBasePackages 설정
@SpringBootApplication(scanBasePackages = {"kgu.developers.apis", "kgu.developers.globalutils"})
public class AicsApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(AicsApiApplication.class, args);
	}
}