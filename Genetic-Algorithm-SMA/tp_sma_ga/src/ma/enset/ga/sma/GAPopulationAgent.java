package ma.enset.ga.sma;

import jade.core.AID;
import jade.core.Agent;
import ma.enset.ga.sequencial.Individual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GAPopulationAgent extends Agent {
    List<IndividualAgent> individuals=new ArrayList<>();
    Map<AID,Integer> agentsFitness=new HashMap<>();
    @Override
    protected void setup() {

    }
}
