package kgu.developers.domain.file.infrastructure;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "file")
public class FilePathProperties {
    private String url;
    private String uploadPath;
    private Set<String> disallowedExtensions;
}

