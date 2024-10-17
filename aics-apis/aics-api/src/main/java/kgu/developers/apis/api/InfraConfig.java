package kgu.developers.apis.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import kgu.developers.core.infra.AicsConfigGroup;
import kgu.developers.core.infra.EnableAicsConfig;

@Configuration(proxyBeanMethods = false)
@ComponentScan("kgu.developers.core.domain")
@EnableAicsConfig({
	AicsConfigGroup.JPA,
	AicsConfigGroup.JPA_AUDITING,
	AicsConfigGroup.PROPERTIES,
	AicsConfigGroup.SWAGGER
})
class InfraConfig {

}
