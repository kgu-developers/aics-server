package kgu.developers.core.infra.config;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import kgu.developers.core.infra.AicsConfig;

@EnableJpaAuditing
public class JpaAuditingConfig implements AicsConfig {
}
