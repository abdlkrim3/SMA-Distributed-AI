package ma.enset.rsa;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class ClientAgent extends Agent {
    @Override
    protected void setup() {
        String encodedPbK =(String) getArguments()[0];
        String message ="Hello Server";
        try {
            byte [] decodedPbK =Base64.getDecoder().decode(encodedPbK);
            KeyFactory keyFactory=KeyFactory.getInstance("RSA");
            PublicKey publicKey=keyFactory.generatePublic(new X509EncodedKeySpec(decodedPbK));
            Cipher cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            byte[] crypteMsg = cipher.doFinal(message.getBytes());
            String cryptedEncodedMsg =Base64.getEncoder().encodeToString(crypteMsg);
            ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
            aclMessage.addReceiver(new AID("server",AID.ISLOCALNAME));
            aclMessage.setContent(cryptedEncodedMsg);

            send(aclMessage);
            System.out.println(Arrays.toString(crypteMsg));
            System.out.println(cryptedEncodedMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
