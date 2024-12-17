package kgu.developers.infra.config;


import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Value("${jasypt.encryptor.secret-key}")
    private String encryptorSecretKey;

    @Bean
    public StandardPBEStringEncryptor jasyptStringEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(encryptorSecretKey);
        encryptor.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        return encryptor;
    }
}
