package kgu.developers.common.auth.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String issuer;
    private String secretKey;

    public JwtProperties(String issuer, String secretKey) {
        this.issuer = issuer;
        this.secretKey = secretKey;
    }
}
