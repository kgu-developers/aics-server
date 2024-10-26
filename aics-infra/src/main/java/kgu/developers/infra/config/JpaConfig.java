package kgu.developers.infra.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import kgu.developers.infra.AicsConfig;

@Configuration
@EntityScan(basePackages = "kgu.developers.domain")
@EnableJpaRepositories(basePackages = "kgu.developers")
public class JpaConfig implements AicsConfig {
	private final EntityManager entityManager;

	public JpaConfig(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}
}
