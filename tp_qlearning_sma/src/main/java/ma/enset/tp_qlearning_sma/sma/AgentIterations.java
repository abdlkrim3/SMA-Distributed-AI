package ma.enset.tp_qlearning_sma.sma;

import jade.core.AID;

public class AgentIterations implements Comparable{
    private AID aid;
    private int fitness;

    public AgentIterations(AID aid, int fitness) {
        this.aid = aid;
        this.fitness = fitness;
    }

    public AID getAid() {
        return aid;
    }

    public void setAid(AID aid) {
        this.aid = aid;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
    @Override
    public int compareTo(Object o) {
        AgentIterations agentIterations =(AgentIterations) o;
        if (this.fitness> agentIterations.fitness)
            return 1;
        else if(this.fitness< agentIterations.fitness){
            return -1;
        }else
            return 0;
    }
}
