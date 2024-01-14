package ma.enset.tp_qlearning_sma.sma;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.util.*;

import static ma.enset.tp_qlearning_sma.sma.QLUtils.gridRewards;
import static ma.enset.tp_qlearning_sma.sma.QLUtils.qtable;

public class QLearningAgent extends Agent {
    List<String> actions=new ArrayList<>();
    private int stateI;
    private int stateJ;
    private  int itearation;
    private String solution;
    Random rnd=new Random();
    @Override
    protected void setup() {
        DFAgentDescription dfAgentDescription=new DFAgentDescription();
        dfAgentDescription.setName(getAID());
        ServiceDescription serviceDescription=new ServiceDescription();
        serviceDescription.setType("QL");
        serviceDescription.setName("QL_ma");
        dfAgentDescription.addServices(serviceDescription);
        try {
            DFService.register(this,dfAgentDescription);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        runQlearning();
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receivedMSG = receive();
                if(receivedMSG!=null){
                    if (receivedMSG != null){
                        switch (receivedMSG.getContent()){
                            case "iteration":sendIteration(receivedMSG);break;
                            case "path" : SendSolution(receivedMSG);break;
                        }
                    }

                }else {
                    block();
                }
            }
        });
    }

    private void sendIteration(ACLMessage receivedMSG){
        ACLMessage replyMsg=receivedMSG.createReply();
        replyMsg.setContent(new String(String.valueOf(itearation)));
        send(replyMsg);
    }

    private void SendSolution(ACLMessage receivedMSG){
        ACLMessage replyMsg=receivedMSG.createReply();
        replyMsg.setContent(actions.toString());
        send(replyMsg);
    }

    public void resetState(){
        stateI=0;
        stateJ=0;
    }
    public int chooseAction(double eps){
        Random rnd=new Random();
        double bestQ=0;
        int act=0;
        if(rnd.nextDouble()<eps){
            // exploration
            act=rnd.nextInt(QLUtils.ACTION_SIZE);
        }
        else {
            // exploitation
            int st=stateI*QLUtils.GRID_SIZE+stateJ;
            for(int i=0;i<QLUtils.ACTION_SIZE;i++){
                if(qtable[st][i]>bestQ){
                    bestQ=qtable[st][i];
                    act=i;
                }
            }
        }
        return act;
    }
    public int executeAction(int act){
        stateI=Math.max(0,Math.min(QLUtils.actions[act][0]+stateI,QLUtils.GRID_SIZE-1));
        stateJ=Math.max(0,Math.min(QLUtils.actions[act][1]+stateJ,QLUtils.GRID_SIZE-1));
        return stateI*QLUtils.GRID_SIZE+stateJ;
    }
    public boolean finished(){
        return gridRewards[stateI][stateJ]==1;
    }
    public void showQTable(){
        System.out.println("******** Q-table *********");
        for(double []line:qtable){
            System.out.print("[");
            for(double qvalue:line){
                System.out.print(String.format("%.2f",qvalue)+",");
            }
            System.out.print("]");
            System.out.println();
        }
        System.out.println("******** best actions to goal *********");
        resetState();
        while (!finished()){
            int act=chooseAction(0);
            System.out.println("S"+(stateI*QLUtils.GRID_SIZE+stateJ)+" ==> A"+act);
            actions.add("S"+(stateI*QLUtils.GRID_SIZE+stateJ)+" ==> A"+act);
            executeAction(act);
        }
        System.out.println("final state : "+(stateI*QLUtils.GRID_SIZE+stateJ));
    }
    public void runQlearning(){
        int it_epoch=0;
        int it=0;
        int currentState;
        int nextState;
        int act,act1;
        while (it_epoch<QLUtils.MAX_EPOCH){
            resetState();
            while (!finished()){
                currentState=stateI*QLUtils.GRID_SIZE+stateJ;
                act=chooseAction(0.4);
                nextState=executeAction(act);
                act1=chooseAction(0);
                qtable[currentState][act]=qtable[currentState][act]+QLUtils.ALPHA*(gridRewards[stateI][stateJ]+QLUtils.GAMMA*qtable[nextState][act1]-qtable[currentState][act]);
                it++;
            }
            it_epoch++;
        }
        showQTable();
        System.out.println("Iteration : " +it);
        itearation=it;
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
