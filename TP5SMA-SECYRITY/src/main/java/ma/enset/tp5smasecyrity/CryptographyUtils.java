package ma.enset.tp5smasecyrity;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class CryptographyUtils {
    public static generateRSAKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator= KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair=keyPairGenerator.generateKeyPair();
        return keyPair;
    }
}
