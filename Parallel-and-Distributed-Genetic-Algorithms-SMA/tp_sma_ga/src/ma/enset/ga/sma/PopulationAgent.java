package ma.enset.ga.sma;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.util.*;

public class PopulationAgent extends Agent {
    List<Individual> individuals=new ArrayList<>();
    Individual firstFitness;
    Individual secondFitness;
    private int fitness;
    Random rnd=new Random();
    @Override
    protected void setup() {
        DFAgentDescription dfAgentDescription=new DFAgentDescription();
        dfAgentDescription.setName(getAID());
        ServiceDescription serviceDescription=new ServiceDescription();
        serviceDescription.setType("ga");
        serviceDescription.setName("ga_ma");
        dfAgentDescription.addServices(serviceDescription);
        try {
            DFService.register(this,dfAgentDescription);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

            for (int i=0;i<GAUtils.POPULATION_SIZE;i++){
                individuals.add(new Individual());
            }
        //mutation
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receivedMSG = receive();
                if(receivedMSG!=null){
                    if (receivedMSG != null){
                        switch (receivedMSG.getContent()){
                            case "mutation":mutation();break;
                            case "fitness" : calculateFintess(receivedMSG);break;
                            case "chromosome":sendChromosome(receivedMSG);break;
                            default:changeChromosome(receivedMSG);break;
                        }
                    }

                }else {
                    block();
                }
            }
        });
    }

    private void mutation(){
        int index=rnd.nextInt(GAUtils.CHROMOSOME_SIZE);
        if (rnd.nextDouble()<GAUtils.MUTATION_PROB){
            individuals.get(GAUtils.POPULATION_SIZE-1).getGenes()[index]=GAUtils.CHARATERS.charAt(rnd.nextInt(GAUtils.CHARATERS.length()));
        }
    }

    private void calculateFintess(ACLMessage receivedMSG){
        for (int i=0;i<GAUtils.POPULATION_SIZE;i++){
            individuals.get(i).calculateFitness();
        }
        sortPopulation();
        selection();
        ACLMessage replyMsg=receivedMSG.createReply();
        replyMsg.setContent(String.valueOf(firstFitness.getFitness()));
        send(replyMsg);
    }
    private void sendChromosome(ACLMessage receivedMSG){
        ACLMessage replyMsg=receivedMSG.createReply();
        replyMsg.setContent(new String(individuals.get(0).getGenes()));
        send(replyMsg);
    }
    private void  changeChromosome(ACLMessage receivedMSG){
        char []oldGeenes=individuals.get(GAUtils.POPULATION_SIZE-1).getGenes();
        char []newGenes =receivedMSG.getContent().toCharArray();
        for (int i=0;i<oldGeenes.length;i++){
            individuals.get(GAUtils.POPULATION_SIZE-1).getGenes()[i]=newGenes[i];
        }

        mutation();
        calculateFintess(receivedMSG);
    }
    public void selection(){
        firstFitness=individuals.get(0);
        secondFitness=individuals.get(1);
    }

    public void sortPopulation(){
        Collections.sort(individuals,Collections.reverseOrder());
    }
    @Override
    protected void takeDown() {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}
