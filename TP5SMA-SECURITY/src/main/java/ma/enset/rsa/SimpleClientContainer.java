package ma.enset.rsa;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import ma.enset.CryptographyUtils;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class SimpleClientContainer {
    public static void main(String[] args) throws StaleProxyException, NoSuchAlgorithmException {
        Runtime instance = Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer=instance.createAgentContainer(profile);
        String encodedPbK = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKzF1YJK4z/SBTDZz+P7uzIUnG/N8jBD6D6YYyESY2qRBcQhegnJIr/9mErWdHEhHFt/IaZRLmuE3rbPBLi8W7UCAwEAAQ==";
        AgentController agentClient=agentContainer.createNewAgent("client","ma.enset.rsa.ClientAgent",new Object[]{encodedPbK});
        agentClient.start();

    }
}
