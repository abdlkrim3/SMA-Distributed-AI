package ma.enset.ga.sma;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.wrapper.AgentContainer;
import ma.enset.ga.sequencial.Individual;

import java.util.Random;

public class IndividualAgent extends Agent implements Comparable{
    private char genes[]=new char[7];
    private static final String  caracters= "abcdefghijklmnopkrstuvwxyz";
    private static final String target="bonjour";
    private int fitness;
    Random rnd=new Random();
    @Override
    protected void setup() {
        //calculate the fitness
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                Random rnd=new Random();
                for (int i=0;i<genes.length;i++){
                    genes[i]= caracters.charAt(rnd.nextInt(26));
                }
            }
        });
        AgentContainer containerController = getContainerController();
        //containerController.cre
        //mutation


    }
    public void calculateFitness(){
        fitness=0;
        int i=0;
        for (char gene:genes) {
            if (gene==target.charAt(i)) {
                fitness++;
            }
            i++;
        }
    }

    public int getFitness() {
        return fitness;
    }

    public char[] getGenes() {
        return genes;
    }
    @Override
    public int compareTo(Object o) {
        Individual individual=(Individual) o;
        if (this.fitness>individual.getFitness())
            return 1;
        else if(this.fitness<individual.getFitness()){
            return -1;
        }else
            return 0;
    }
}
