package application.com.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class PasswordEncryptor
{
    private static final String SECRET_KEY = "secret";
    private static final String ALGORITHM = "HmacSHA256";

    public static String encrypt(String input) throws Exception {
        Mac mac = Mac.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        mac.init(keySpec);

        byte[] rawHmac = mac.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(rawHmac);
    }
}
