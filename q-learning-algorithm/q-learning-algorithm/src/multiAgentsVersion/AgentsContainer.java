package multiAgentsVersion;

import common.QLUtils;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class AgentsContainer {
    public static void main(String[] args) throws StaleProxyException {
        Runtime runtime=Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST,"localhost");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);
        AgentController mainAgent=null;
        for (int i = 0; i< QLUtils.numberOfAgents; i++){
            mainAgent = agentContainer.createNewAgent("Agent n°"+ i, QLearningAgent.class.getName(), new Object[]{});
            mainAgent.start();
        }

         mainAgent = agentContainer.createNewAgent("masterAgent", MasterAgent.class.getName(), new Object[]{});
        mainAgent.start();


    }
}
