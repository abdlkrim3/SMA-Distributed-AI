package ma.enset.ga.sequencial;

import java.util.Arrays;
import java.util.Random;

public class AGApp {
    private static final int MAX_IT=100;
    private static final int MAX_FITNESS=7;
    public static void main(String[] args) {

       Population population=new Population();
       population.initialaizePopulation();
       population.calculateIndFintess();
       population.sortPopulation();
       int it=0;
       System.out.println("Chromosome :"+Arrays.toString(population.getFitnessIndivd().getGenes())+" fitness :"+population.getFitnessIndivd().getFitness());

        while (population.getFitnessIndivd().getFitness()<MAX_FITNESS){
            population.selection();
            population.crossover();
            Random random=new Random();
            if (random.nextInt(101)<50)
                population.mutation();
            population.calculateIndFintess();
            population.sortPopulation();
            System.out.println("Iteration :"+it+"  Chromosome :"+Arrays.toString(population.getFitnessIndivd().getGenes())+" fitness :"+population.getFitnessIndivd().getFitness());

         it++;
        }

    }
}
