package kgu.developers.core.infra.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import kgu.developers.core.infra.AicsConfig;

@ConfigurationPropertiesScan(basePackages = "kgu.developers")
public class PropertiesConfig implements AicsConfig {
}
