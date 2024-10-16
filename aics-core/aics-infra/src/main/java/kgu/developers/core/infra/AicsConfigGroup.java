package kgu.developers.core.infra;

import kgu.developers.core.infra.config.JpaAuditingConfig;
import kgu.developers.core.infra.config.JpaConfig;
import kgu.developers.core.infra.config.PropertiesConfig;
import kgu.developers.core.infra.config.SwaggerConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AicsConfigGroup {

	JPA(JpaConfig.class),
	JPA_AUDITING(JpaAuditingConfig.class),
	PROPERTIES(PropertiesConfig.class),
	SWAGGER(SwaggerConfig.class),
	;
	private final Class<? extends AicsConfig> configClass;
}
