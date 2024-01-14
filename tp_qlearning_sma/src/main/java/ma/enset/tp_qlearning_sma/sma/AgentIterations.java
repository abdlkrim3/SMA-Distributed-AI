package ma.enset.tp_qlearning_sma.sma;

import jade.core.AID;

public class AgentIterations implements Comparable{
    private AID aid;
    private int iteration;

    public AgentIterations(AID aid, int iteration) {
        this.aid = aid;
        this.iteration = iteration;
    }

    public AID getAid() {
        return aid;
    }

    public void setAid(AID aid) {
        this.aid = aid;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }
    @Override
    public int compareTo(Object o) {
        AgentIterations agentIterations =(AgentIterations) o;
        if (this.iteration > agentIterations.iteration)
            return 1;
        else if(this.iteration < agentIterations.iteration){
            return -1;
        }else
            return 0;
    }
}
