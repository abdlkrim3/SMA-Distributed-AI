package ma.enset.ga.sma;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.util.*;

public class MasterAgentGA extends Agent {
    List<AgentFitness> agentsFitness=new ArrayList<>();
    Random rnd=new Random();
    @Override
    protected void setup() {
        DFAgentDescription dfAgentDescription=new DFAgentDescription();
        ServiceDescription serviceDescription=new ServiceDescription();
        serviceDescription.setType("ga");
        dfAgentDescription.addServices(serviceDescription);
        try {
            DFAgentDescription[] agentsDescriptions = DFService.search(this, dfAgentDescription);
            for (DFAgentDescription dfAD:agentsDescriptions) {
                agentsFitness.add(new AgentFitness(dfAD.getName(),0));
            }
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        calculateFintness();
        SequentialBehaviour sequentialBehaviour= new SequentialBehaviour();
        sequentialBehaviour.addSubBehaviour(new Behaviour() {
            int cpt=0;
            @Override
            public void action() {
                ACLMessage receivedMSG = receive();
                if (receivedMSG!=null){
                    cpt++;
                    int fintess=Integer.parseInt(receivedMSG.getContent());
                    AID sender=receivedMSG.getSender();
                    setAgentFintess(sender,fintess);
                    if(cpt==GAUtils.POPULATION_SIZE){
                        Collections.sort(agentsFitness,Collections.reverseOrder());
                        showPopulation();
                    }
                }else {
                    block();
                }
            }
            @Override
            public boolean done(){
                return cpt==GAUtils.POPULATION_SIZE;
            }
        });

        sequentialBehaviour.addSubBehaviour(new Behaviour() {
            int it=0;
            AgentFitness agent1;
            AgentFitness agent2;
            @Override
            public void action() {
                selection();
                crossover();
                Collections. sort(agentsFitness, Collections.reverseOrder());
                sendMessage(agentsFitness.get(0).getAid(),"chromosome",ACLMessage.REQUEST);
                ACLMessage aclMessage=blockingReceive();
                System.out.println(it +"  : " +aclMessage.getContent()+ " "+ agentsFitness.get(0).getFitness());
                it++;
            }
            public void selection(){
                agent1=agentsFitness.get(0);
                agent2=agentsFitness.get(1);
            }
            public void crossover(){
                sendMessage(agent1.getAid(),"crossover",ACLMessage.REQUEST);
                sendMessage(agent2.getAid(),"crossover",ACLMessage.REQUEST);
                ACLMessage receivedMsg1=blockingReceive();
                ACLMessage receivedMsg2=blockingReceive();
                setAgentFintess(receivedMsg1.getSender(), Integer.parseInt(receivedMsg1.getContent()));
                setAgentFintess(receivedMsg2.getSender(), Integer.parseInt(receivedMsg2.getContent()));
            }
            @Override
            public boolean done(){
                return it == GAUtils.MAX_IT || (agentsFitness.get(0).getFitness() == GAUtils.MAX_FITNESS);
            }

        });
        addBehaviour(sequentialBehaviour);
    }
    private void calculateFintness(){
        ACLMessage message=new ACLMessage(ACLMessage.REQUEST);

        for (AgentFitness agf:agentsFitness) {
            message.addReceiver(agf.getAid());
        }
        message.setContent("fitness");
        send(message);

    }
    private void setAgentFintess(AID aid,int fitness){
        for (int i=0;i<GAUtils.POPULATION_SIZE;i++){
            if(agentsFitness.get(i).getAid().equals(aid)){
                agentsFitness.get(i).setFitness(fitness);
                break;
            }
        }
    }
    private void sendMessage(AID aid,String content,int performative){
        ACLMessage message=new ACLMessage(performative);
        message.setContent(content);
        message.addReceiver(aid);
        send(message);

    }
    private void showPopulation(){
        for (AgentFitness agentFitness:agentsFitness) {
            System.out.println(agentFitness.getAid().getName()+" "+agentFitness.getFitness());
        }
    }
}
