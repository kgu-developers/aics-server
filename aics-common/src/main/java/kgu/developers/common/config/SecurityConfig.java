package kgu.developers.common.config;

import kgu.developers.common.auth.filter.TokenAuthenticationFilter;
import kgu.developers.common.auth.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	private final TokenProvider tokenProvider;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
			.cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
			.csrf(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.logout(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeHttpRequests(auth -> auth
				.requestMatchers(SWAGGER_PATTERNS).permitAll()
				.requestMatchers(STATIC_RESOURCES_PATTERNS).permitAll()
				.requestMatchers(PERMIT_ALL_PATTERNS).permitAll()
				.requestMatchers(PUBLIC_ENDPOINTS).permitAll()
				.anyRequest().authenticated()
			)
			.build();
	}

	private static final String[] SWAGGER_PATTERNS = {
		"/swagger-ui/**",
		"/actuator/**",
		"/v3/api-docs/**",
	};

	private static final String[] STATIC_RESOURCES_PATTERNS = {
		"/img/**",
		"/css/**",
		"/js/**",
		"/cloud/**",
	};

	private static final String[] PERMIT_ALL_PATTERNS = {
		"/error",
		"/favicon.ico",
		"/index.html",
		"/",
	};

	private static final String[] PUBLIC_ENDPOINTS = {
		"/api/v1/users/signup",
		"/api/v1/auth/**",
		"/api/v1/professors",
		"/api/v1/abouts",
		"/api/v1/clubs",
		"/api/v1/posts/**",
		"/api/v1/graduation-users/**",
		"/api/v1/labs",
		"/api/v1/comments",
		"/api/v1/carousels",
		"/api/v1/thesis",
		"/api/v1/certificate",
		"/api/v1/schedules",
	};

	CorsConfigurationSource corsConfigurationSource() {
		return request -> {
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowedHeaders(Collections.singletonList("*"));
			config.setAllowedMethods(Collections.singletonList("*"));
			config.setAllowedOriginPatterns(Arrays.asList(
				"http://localhost:3000",
				"http://localhost:3001",
				"http://localhost:8080",
				"http://localhost:8081",
				"http://localhost:8082",
				"https://aics-api.kgudevelopers.monster",
				"https://aics-admin.kgudevelopers.monster",
				"https://aics-auth.kgudevelopers.monster",
				"https://aics-client-dev.vercel.app/",
				"https://aics-client-graduate.vercel.app/",
				"http://175.45.195.63",
				"http://203.249.22.207",
				"http://13.125.230.147"
			));
			config.setAllowCredentials(true);
			return config;
		};
	}

	public TokenAuthenticationFilter tokenAuthenticationFilter() {
		return new TokenAuthenticationFilter(tokenProvider);
	}

	@Bean
	public AuthenticationManager authenticationManager(
		BCryptPasswordEncoder bCryptPasswordEncoder
	) {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(bCryptPasswordEncoder);
		return new ProviderManager(authProvider);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
