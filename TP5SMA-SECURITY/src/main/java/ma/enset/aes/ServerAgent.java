package ma.enset.aes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class ServerAgent extends Agent {
    @Override
    protected void setup() {
        String secret=(String) getArguments()[0];
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receive = receive();
                if (receive!=null){
                    String cryptedEncodedMsg =receive.getContent();
                    byte [] cryptedMsg= Base64.getDecoder().decode(cryptedEncodedMsg);
                    try {
                        SecretKey secretKey=new SecretKeySpec(secret.getBytes(),"AES");
                        Cipher cipher=Cipher.getInstance("AES");
                        cipher.init(Cipher.DECRYPT_MODE,secretKey);
                        byte [] decryptedMsg=cipher.doFinal(cryptedMsg);
                        System.out.println(new String(decryptedMsg));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    block();
                }

            }
        });
    }
}
