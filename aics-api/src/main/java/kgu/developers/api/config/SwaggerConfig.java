package kgu.developers.api.config;

import static java.lang.String.format;
import static org.springframework.security.config.Elements.JWT;

import java.util.HashMap;
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
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

	private final Environment environment;

	@Value("${profiles.api-port}")
	private int apiPort;

	@Value("${profiles.admin-api-port}")
	private int adminApiPort;

	private final Map<String, Map<String, Object>> profileServerConfig = new HashMap<>();

	@PostConstruct
	public void initializeProfileServerConfig() {
		profileServerConfig.put("local", Map.of("url", "http://localhost", "port", apiPort));
	}

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.info(apiInfo())
			.addSecurityItem(securityRequirement())
			.servers(initializeServers())
			.components(components());
	}

	private SecurityRequirement securityRequirement() {
		return new SecurityRequirement().addList(JWT);
	}

	private Info apiInfo() {
		return new Info()
			.title("AICS-HOME API")
			.description(getDescription());
	}

	private List<Server> initializeServers() {
		return profileServerConfig.entrySet().stream()
			.filter(entry -> environment.matchesProfiles(entry.getKey()))
			.map(entry -> {
				String url = (String) entry.getValue().get("url");
				int port = (int) entry.getValue().get("port");
				return openApiServer(url + ":" + port, "AICS-HOME API " + entry.getKey().toUpperCase());
			})
			.collect(Collectors.toList());
	}

	private Server openApiServer(String url, String description) {
		return new Server().url(url).description(description);
	}

	private Components components() {
		return new Components().addSecuritySchemes(JWT, securityScheme());
	}

	private SecurityScheme securityScheme() {
		return new SecurityScheme()
			.name(JWT)
			.type(SecurityScheme.Type.HTTP)
			.scheme("bearer")
			.bearerFormat(JWT);
	}

	private String getDescription() {
		return format("""
                AI 컴퓨터공학부 커뮤니티, AICS-HOME API 입니다.\n\n
                로그인 API를 통해 액세스 토큰을 발급 받고 헤더에 값을 넣어주세요 . \n\n
                별다른 절차 없이 API를 사용하실 수 있습니다.\n\n
                
                관리자 API 문서는 다음 링크에서 확인하실 수 있습니다.\n
                <ul>
                    <li>AICS-HOME ADMIN API : <a href="%s" target="_blank">%s</a></li>
                </ul>
                """,
			getAdminSwaggerByProfile("local"), getAdminSwaggerByProfile("local")
		);
	}

	private String getAdminSwaggerByProfile(String profile) {
		String url = (String) profileServerConfig.get(profile).get("url");
		return url + ":" + adminApiPort + "/swagger-ui/index.html";
	}
}
