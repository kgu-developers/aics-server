package kgu.developers.api.config;

import kgu.developers.infra.AicsConfigGroup;
import kgu.developers.infra.EnableAicsConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ComponentScan("kgu.developers")
@EnableAicsConfig({
	AicsConfigGroup.JPA,
	AicsConfigGroup.JPA_AUDITING,
	AicsConfigGroup.PROPERTIES
})
class InfraConfig {

}
