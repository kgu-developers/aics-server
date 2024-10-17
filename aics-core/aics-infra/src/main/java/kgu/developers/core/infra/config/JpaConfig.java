package kgu.developers.core.infra.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import kgu.developers.core.infra.AicsConfig;

@EntityScan(basePackages = "kgu.developers.core.domain")
@EnableJpaRepositories(basePackages = "kgu.developers.core.domain")
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
