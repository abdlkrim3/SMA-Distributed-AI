package multiAgentsVersion;

import common.QLUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QLearningAgent extends Agent {
    Random rd=new Random();
    double ALPHA= rd.nextDouble();
    double GAMMA= rd.nextDouble();
    double EPSELON= rd.nextDouble();
    double [][]qtable=new double[QLUtils.GRID_SIZE* QLUtils.GRID_SIZE][QLUtils.ACTION_SIZE];
    int stateI;
    int stateJ;
    @Override
    protected void setup() {
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.setName(getAID());
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("QL");
        serviceDescription.setName("QLearning");
        dfAgentDescription.addServices(serviceDescription);
        try {
            DFService.register(this, dfAgentDescription);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        addBehaviour(new Behaviour() {
            int it_epoch=0;
            Long it= 0L;
            int currentState;
            int nextState;
            int act,act1;

            @Override
            public void action() {
                resetState();
                while (!finished()){
                    currentState=stateI*QLUtils.GRID_SIZE+stateJ;
                    act=chooseAction(EPSELON);
                    nextState=executeAction(act);
                    act1=chooseAction(0);
                    qtable[currentState][act]=qtable[currentState][act]+ALPHA*(QLUtils.gridRewards[stateI][stateJ]+GAMMA*qtable[nextState][act1]-qtable[currentState][act]);
                    it++;
                }
            }

            @Override
            public boolean done() {
                if(it_epoch<QLUtils.MAX_EPOCH){
                    it_epoch++;
                    return false;
                }
                String message= ALPHA +";"+GAMMA+";"+EPSELON+";"+getOptimalPath()+";"+it;
                sendMessage(message);
                return true;
            }
        });

    }


    @Override
    protected void takeDown() {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }


    // functions
    public void resetState() {
        stateI = 6;
        stateJ = 0;
    }

    public int chooseAction(double eps) {
        Random rnd = new Random();
        double bestQ = 0;
        int act = 0;
        if (rnd.nextDouble() < eps) {
            // exploration
            act = rnd.nextInt(QLUtils.ACTION_SIZE);
        } else {
            // exploitation
            int st = stateI * QLUtils.GRID_SIZE + stateJ;
            for (int i = 0; i < QLUtils.ACTION_SIZE; i++) {
                if (qtable[st][i] > bestQ) {
                    bestQ = qtable[st][i];
                    act = i;
                }
            }
        }
        return act;
    }

    public int executeAction(int act) {
        stateI = Math.max(0, Math.min(QLUtils.actions[act][0] + stateI, 6));
        stateJ = Math.max(0, Math.min(QLUtils.actions[act][1] + stateJ, 6));
        return stateI * QLUtils.GRID_SIZE + stateJ;
    }

    public boolean finished() {
        return QLUtils.gridRewards[stateI][stateJ] == 1;
    }
    public void sendMessage(String content){
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.addReceiver(new AID("masterAgent", AID.ISLOCALNAME));
        message.setContent(content);
        send(message);
    }
    public String getOptimalPath(){
        List<String> states=new ArrayList<>();
        resetState();
        while (!finished()){
            int act=chooseAction(0);
            states.add(String.valueOf(stateI*QLUtils.GRID_SIZE+stateJ));
            executeAction(act);
        }
        return String.join(" -> ", states);

    }

}

