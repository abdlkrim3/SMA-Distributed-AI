package ma.enset.aes;

import ma.enset.CryptographyUtils;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class GenerateRSAKeys {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPair keyPair = CryptographyUtils.generateRSAKeys();
        PrivateKey privateKey=keyPair.getPrivate();
        PublicKey publicKey=keyPair.getPublic();
        String encodedPK = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String encodedPbK = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println("====== private key ========");
        System.out.println(encodedPK);
        System.out.println("====== public key ========");
        System.out.println(encodedPbK);

    }
}
