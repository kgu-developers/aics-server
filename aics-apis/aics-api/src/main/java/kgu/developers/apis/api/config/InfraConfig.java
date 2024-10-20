package kgu.developers.apis.api.config;

import kgu.developers.core.infra.AicsConfigGroup;
import kgu.developers.core.infra.EnableAicsConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ComponentScan("kgu.developers.core")
@EnableAicsConfig({
	AicsConfigGroup.JPA,
	AicsConfigGroup.JPA_AUDITING,
	AicsConfigGroup.PROPERTIES,
	AicsConfigGroup.SWAGGER
})
class InfraConfig {

}
