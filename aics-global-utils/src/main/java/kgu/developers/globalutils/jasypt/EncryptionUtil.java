package kgu.developers.globalutils.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {

    private final StandardPBEStringEncryptor encryptor;

    public EncryptionUtil(
        @Value("${jasypt.encryptor.secret-key}")
        String encryptorSecretKey
    ) {
        this.encryptor = new StandardPBEStringEncryptor();
        this.encryptor.setPassword(encryptorSecretKey);
        this.encryptor.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
    }

    public String encrypt(String text) {
        return encryptor.encrypt(text);
    }
}
