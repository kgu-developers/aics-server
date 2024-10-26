package kgu.developers.infra.config;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import kgu.developers.infra.AicsConfig;

@EnableJpaAuditing
public class JpaAuditingConfig implements AicsConfig {
}
