package kgu.developers.globalutils.encryption;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AesUtil {

	private static final String ALGORITHM = "AES";
	private static String SECRET_KEY = "";

	public AesUtil(@Value("${file.secret-key}") String secretKey) {
		SECRET_KEY = secretKey;
	}

	public static String encrypt(String input) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			byte[] encryptedBytes = cipher.doFinal(input.getBytes());
			return Base64.getEncoder().encodeToString(encryptedBytes);
		} catch (Exception e) {
			log.error("암호화 도중 에러가 발생했습니다.: {}", e.getMessage());
		}
		return null;
	}

	public static String decrypt(String encryptedInput) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			byte[] encryptedBytes = Base64.getDecoder().decode(encryptedInput);
			byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
			return new String(decryptedBytes);
		} catch (Exception e) {
			log.error("복호화 도중 에러가 발생했습니다.: {}", e.getMessage());
		}
		return null;
	}
}
