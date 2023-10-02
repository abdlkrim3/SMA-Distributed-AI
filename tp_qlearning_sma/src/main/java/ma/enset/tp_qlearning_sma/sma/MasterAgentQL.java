package ma.enset.tp_qlearning_sma.sma;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class MasterAgentQL extends Agent {
    private int bestIterations = Integer.MAX_VALUE;
    private String bestSolution = "";

    @Override
    protected void setup() {
        addBehaviour(new ReceiveSolutionBehaviour());
    }

    private class ReceiveSolutionBehaviour extends CyclicBehaviour {
        @Override
        public void action() {
            MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
            ACLMessage msg = myAgent.receive(template);

            if (msg != null) {
                int iterations = Integer.parseInt(msg.getContent());
                if (iterations < bestIterations) {
                    bestIterations = iterations;
                    bestSolution = msg.getSender().getLocalName();
                }
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
}
