package ma.enset.ga.sma;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.util.*;

public class IslandAgent extends Agent {
    List<Individual> individuals=new ArrayList<>();
    Individual firstFitness;
    Individual secondFitness;
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
        initialaizePopulation();
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
                            case "crossover":crossover(receivedMSG);break;
                        }
                    }

                }else {
                    block();
                }
            }
        });
    }
    public void initialaizePopulation(){
        for (int i=0;i<GAUtils.POPULATION_SIZE;i++){
            individuals.add(new Individual());
        }
    }

    private void mutation(){
        int index=rnd.nextInt(GAUtils.CHROMOSOME_SIZE);
        if (rnd.nextDouble()<GAUtils.MUTATION_PROB){
            individuals.get(individuals.size()-2).getGenes()[index]=GAUtils.CHARATERS.charAt(rnd.nextInt(GAUtils.CHARATERS.length()));
        }
        index=rnd.nextInt(GAUtils.CHROMOSOME_SIZE);
        if (rnd.nextDouble()<GAUtils.MUTATION_PROB){
            individuals.get(individuals.size()-1).getGenes()[index]=GAUtils.CHARATERS.charAt(rnd.nextInt(GAUtils.CHARATERS.length()));
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
    private void  crossover(ACLMessage receivedMSG){
        int pointCroisment=rnd.nextInt(GAUtils.CHROMOSOME_SIZE-2);
        pointCroisment++;
        Individual individual1=new Individual();
        Individual individual2=new Individual();
        for (int i=0;i<individual1.getGenes().length;i++) {
            individual1.getGenes()[i]=firstFitness.getGenes()[i];
            individual2.getGenes()[i]=secondFitness.getGenes()[i];
        }
        for (int i=0;i<pointCroisment;i++) {
            individual1.getGenes()[i]=secondFitness.getGenes()[i];
            individual2.getGenes()[i]=firstFitness.getGenes()[i];
        }
        individuals.set(individuals.size()-2,individual1);
        individuals.set(individuals.size()-1,individual2);

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
