package ma.enset.tp5smasecyrity;

public class SimpleAgentContainer {
    public static void main(String[] args) throws StaleProxyException {
        Runtime instance = Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer=instance.createAgentContainer(profile);
        AgentController agentSeller=agentContainer.createNewAgent("server","ma.enset.tp5smasec",new Object[]{});
        AgentController agentBuyer=agentContainer.createNewAgent("client","ma.enset.sma.BuyerAgent",new Object[]{});
        agentSeller.start();
        agentBuyer.start();

    }
}
