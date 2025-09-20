// 代码生成时间: 2025-09-21 07:04:35
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.util.Base64;

@Service
public class EncryptionDecryptionService {

    private static final String ALGORITHM = "AES";
    private static final byte[] key = new byte[16]; // AES requires a 128 bit key

    // Generate the AES key
    public SecretKey generateAESKey() throws GeneralSecurityException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(128); // Use 128 bit AES
        return keyGenerator.generateKey();
    }

    // Encrypt the plain text using the given key
    public String encrypt(String plainText, SecretKey key) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt the encrypted text using the given key
    public String decrypt(String encryptedText, SecretKey key) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    // Wrapper method to encrypt text with the generated key
    public String encryptText(String plainText) {
        try {
            SecretKey key = generateAESKey();
            return encrypt(plainText, key);
        } catch (GeneralSecurityException e) {
            // Handle encryption error
            e.printStackTrace();
            return null;
        }
    }

    // Wrapper method to decrypt text with the given key
    public String decryptText(String encryptedText, byte[] keyBytes) {
        try {
            SecretKey key = new SecretKeySpec(keyBytes, ALGORITHM);
            return decrypt(encryptedText, key);
        } catch (GeneralSecurityException e) {
            // Handle decryption error
            e.printStackTrace();
            return null;
        }
    }
}
