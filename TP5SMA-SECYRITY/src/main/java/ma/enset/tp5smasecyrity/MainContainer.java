package ma.enset.tp5smasecyrity;


import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;

public class MainContainer {
    public static void main(String[] args) throws ControllerException {
        Runtime instance = Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.GUI,"true");
        AgentContainer agentContainer=instance.createMainContainer(profile);
        agentContainer.start();
    }
}
