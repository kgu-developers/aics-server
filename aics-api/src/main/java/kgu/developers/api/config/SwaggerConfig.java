package kgu.developers.api.config;

import static java.lang.String.format;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

	@Value("${docs.api-docs-url}")
	private String apiDocsUrl;

	@Value("${docs.admin-docs-url}")
	private String adminDocsUrl;

	@Value("${docs.auth-docs-url}")
	private String authDocsUrl;

	private final Environment environment;

	private static final Map<String, String> PROFILE_SERVER_URL_MAP = Map.of(
		"local", "http://localhost:8080",
		"development", "https://aics-api.ummdev.com"
	);

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.info(apiInfo())
			.addSecurityItem(securityRequirement())
			.servers(initializeServers())
			.components(components());
	}

	private List<Server> initializeServers() {
		return PROFILE_SERVER_URL_MAP.entrySet().stream()
			.filter(entry -> environment.matchesProfiles(entry.getKey()))
			.map(entry -> openApiServer(entry.getValue(), "AICS-HOME API " + entry.getKey().toUpperCase()))
			.collect(Collectors.toList());
	}

	private Server openApiServer(String url, String description) {
		return new Server().url(url).description(description);
	}

	private SecurityRequirement securityRequirement() {
		return new SecurityRequirement().addList("bearer");
	}

	private Info apiInfo() {
		return new Info()
			.title("AICS-HOME API")
			.description(getDescription());
	}

	private Components components() {
		return new Components().addSecuritySchemes("bearer", securityScheme());
	}

	private SecurityScheme securityScheme() {
		return new SecurityScheme()
			.name("bearer")
			.type(SecurityScheme.Type.HTTP)
			.scheme("bearer")
			.bearerFormat("JWT");
	}

	private String getDescription() {
		return format("""
				AI 컴퓨터공학부 커뮤니티, AICS-HOME API 입니다.
				
				로그인 API를 통해 액세스 토큰을 발급 받고 헤더에 값을 넣어주세요.
				
				별다른 절차 없이 API를 사용하실 수 있습니다.
				
				<ul>
					<li>사용자 API 문서: <a href="%s" target="_blank">%s</a></li><br>
					<li>관리자 API 문서: <a href="%s" target="_blank">%s</a></li><br>
					<li>인증/인가 API 문서: <a href="%s" target="_blank">%s</a></li>
            	</ul>
				""",
			apiDocsUrl, apiDocsUrl,
			adminDocsUrl, adminDocsUrl,
			authDocsUrl, authDocsUrl);
	}
}
