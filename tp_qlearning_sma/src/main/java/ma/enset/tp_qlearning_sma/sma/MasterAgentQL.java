package ma.enset.tp_qlearning_sma.sma;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MasterAgentQL extends Agent {
    private int bestIterations = Integer.MAX_VALUE;
    List<AgentIterations> agentIterations=new ArrayList<>();
    private String bestSolution = "";

    @Override
    protected void setup() {
        DFAgentDescription dfAgentDescription=new DFAgentDescription();
        ServiceDescription serviceDescription=new ServiceDescription();
        serviceDescription.setType("QL");
        dfAgentDescription.addServices(serviceDescription);
        try {
            DFAgentDescription[] agentsDescriptions = DFService.search(this, dfAgentDescription);
            for (DFAgentDescription dfAD:agentsDescriptions) {
                agentIterations.add(new AgentIterations(dfAD.getName(),0));
            }
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        getIterations();
        addBehaviour(new ReceiveSolutionBehaviour());
    }

    private class ReceiveSolutionBehaviour extends CyclicBehaviour {
        int cpt=0;
        @Override
        public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
                System.out.println(msg);
                cpt++;
                int iterations = Integer.parseInt(msg.getContent());
                AID sender=msg.getSender();
                setAgentIterations(sender,iterations);
                if(cpt==QLUtils.AGENT_SIZE){
                    Collections.sort(agentIterations,Collections.reverseOrder());
                    showAgentsIteration();
                }
                    bestIterations = agentIterations.get(0).getIteration();
                System.out.println(bestIterations);
                    sendMessage(agentIterations.get(0).getAid(),"path",ACLMessage.REQUEST);
                    ACLMessage aclMessage=blockingReceive();
                    bestSolution = aclMessage.getContent();
                System.out.println(bestSolution);
            } else {
                block();
            }
        }
    }

    @Override
    protected void takeDown() {
        System.out.println("Best solution: " + bestSolution);
        System.out.println("Iterations: " + bestIterations);
    }
    public void getIterations(){
        ACLMessage message=new ACLMessage(ACLMessage.REQUEST);
        for (AgentIterations agi:agentIterations) {
            message.addReceiver(agi.getAid());
        }
        message.setContent("iteration");
        send(message);
    }
    private void setAgentIterations(AID aid,int iteration){
        for (int i = 0; i< QLUtils.AGENT_SIZE; i++){
            if(agentIterations.get(i).getAid().equals(aid)){
                agentIterations.get(i).setIteration(iteration);
                break;
            }
        }
    }
    private void sendMessage(AID aid,String content,int performative) {
        ACLMessage message = new ACLMessage(performative);
        message.setContent(content);
        message.addReceiver(aid);
        send(message);
    }
    private void showAgentsIteration(){
        for (AgentIterations agentFitness:agentIterations) {
            System.out.println(agentFitness.getAid().getName()+" "+agentFitness.getIteration());
        }
    }
}
