package ma.enset.ga.sequencial;

import java.util.Random;

public class Individual implements Comparable{
    //Bonjour
    //chromosome
    private char genes[]=new char[7];
    private static final String  caracters= "abcdefghijklmnopkrstuvwxyz";
    private int fitness;
    private static final String target="bonjour";

    public Individual() {
        Random rnd=new Random();
        for (int i=0;i<genes.length;i++){
            genes[i]= caracters.charAt(rnd.nextInt(26));
        }
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
        if (this.fitness>individual.fitness)
            return 1;
        else if(this.fitness<individual.fitness){
            return -1;
        }else
            return 0;
    }
}
