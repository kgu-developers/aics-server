package kgu.developers.common.config;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import kgu.developers.common.interceptor.LoggingInterceptor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
	private final LoggingInterceptor loggingInterceptor;

	@Value("${file.upload-path}")
	private String uploadPath;

	@Value("${file.url}")
	private String url;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler(url + "/**")
			.addResourceLocations("file:" + uploadPath + "/")
			.setCachePeriod(3600)
			.resourceChain(true)
			.addResolver(new PathResourceResolver() {
				@Override
				protected Resource getResource(
					@NonNull String resourcePath, @NonNull Resource location) throws IOException {
					Resource requestedResource = location.createRelative(resourcePath);
					if (requestedResource.exists() && requestedResource.isReadable()) {
						return requestedResource;
					}
					throw new FileNotFoundException();
				}
			});
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggingInterceptor);
	}
}
