package kgu.developers.globalutils.decryption;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DecryptionUtil {

    private final StandardPBEStringEncryptor encryptor;

    public DecryptionUtil(
            @Value("${jasypt.encryptor.secret-key}")
            String encryptorSecretKey
    ) {
        this.encryptor = new StandardPBEStringEncryptor();
        this.encryptor.setPassword(encryptorSecretKey);
        this.encryptor.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
    }

    public String decrypt(String encryptedText) {
        return encryptor.decrypt(encryptedText);
    }
}