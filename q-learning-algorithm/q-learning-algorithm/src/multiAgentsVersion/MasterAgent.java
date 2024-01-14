package multiAgentsVersion;

import common.QLUtils;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MasterAgent extends Agent {
    List<AgentResult> agentResults=new ArrayList<>();

    @Override
    protected void setup() {

        addBehaviour(new Behaviour() {
            int cpt=0;
            @Override
            public void action() {
                ACLMessage receivedMSG = receive();
                if (receivedMSG!=null){
                    cpt++;
                    String[] content=receivedMSG.getContent().split(";");
                    double alpha=Double.parseDouble(content[0]);
                    double gamma=Double.parseDouble(content[1]);
                    double epselon=Double.parseDouble(content[2]);
                    String path=content[3];
                    int iterations=Integer.parseInt(content[4]);
                    System.out.println(content[3]);
                    System.out.println(content[4]);
                    agentResults.add(new AgentResult(alpha,gamma,epselon,path,iterations,receivedMSG.getSender().getName()));
                }else {
                    block();
                }
            }

            @Override
            public boolean done() {
                if(cpt==QLUtils.numberOfAgents){
                    Collections.sort(agentResults,Collections.reverseOrder());
                    AgentResult bestPerformance=agentResults.get(0);
                    System.out.println("---------- The agent that found the best path is "+bestPerformance.getAgentName()+" -------------");
                    System.out.println("ALPHA (learning rate) : "+bestPerformance.getAlpha());
                    System.out.println("GAMMA (discount factor) : "+bestPerformance.getGamma());
                    System.out.println("EPSELON (probability of exploration/exploitation) : "+bestPerformance.getEpselon());
                    System.out.println("PATH TO GOAL : "+bestPerformance.getPath());
                    System.out.println(agentResults.get(0).toString());
                    return true;
                }
                System.out.println("agent");
                return false;
            }
        });
    }
}
