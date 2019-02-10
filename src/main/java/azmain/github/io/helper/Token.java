package azmain.github.io.helper;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

public class Token {
	public static String getToken(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[128];
        random.nextBytes(bytes);
        Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String token = encoder.encodeToString(bytes);
        return token.substring(0,25);
    }
}
