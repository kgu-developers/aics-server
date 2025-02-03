package kgu.developers.auth.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import kgu.developers.infra.AicsConfigGroup;
import kgu.developers.infra.EnableAicsConfig;

@Configuration(proxyBeanMethods = false)
@ComponentScan("kgu.developers")
@EnableAicsConfig({
	AicsConfigGroup.JPA,
	AicsConfigGroup.JPA_AUDITING,
	AicsConfigGroup.PROPERTIES,
})
class InfraConfig {

}
