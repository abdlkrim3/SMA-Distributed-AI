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

public class SimpleServerContainer {
    public static void main(String[] args) throws StaleProxyException, NoSuchAlgorithmException {
        Runtime instance = Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer=instance.createAgentContainer(profile);
        String encodedPK="MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEArMXVgkrjP9IFMNnP4/u7MhScb83yMEPoPphjIRJjapEFxCF6Cckiv/2YStZ0cSEcW38hplEua4Tets8EuLxbtQIDAQABAkAFGgMKdF9OdfX3nHmM2qo15IbpWZRHrub6MNy90BcFwVMQLJyViHz69yhSXS+yhm65EAvwXxTmNvFJ2Lr5F0aRAiEA30mfgmRKYFdCb1bzlbXonOUySXu651COBObeFAiO1jkCIQDGFaimSsIBE2ivLaxQpdEgNxMk+HgUiDBwoHgeX5TRXQIhAK4pUN91toW6yjjqC4jkoJFwPG8QsVInhP7RzLq3iiIRAiEAxTVE/aIJ+gY3v8gp+PTM+lS6o6EX8+EO0OKCFEM2pyUCICj993EYxiW7uCK171bwn4fMLIxQCpuHXUjPiEn1ChxA";
        AgentController agentServer=agentContainer.createNewAgent("server","ma.enset.rsa.ServerAgent",new Object[]{encodedPK});
        agentServer.start();

    }
}
