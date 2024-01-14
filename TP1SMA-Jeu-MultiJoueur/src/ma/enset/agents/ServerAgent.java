package ma.enset.agents;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServerAgent extends GuiAgent {
    private ServerContainer serverContainer;
    int nbMagique=new Random().nextInt(100);
    private List<AID>listAIDs=new ArrayList<>();
    int nbJoeur;
    private ObservableList<String> data= FXCollections.observableArrayList();
    @Override
    protected void setup() {
        serverContainer =(ServerContainer)getArguments()[0];
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        AID receiver1 = new AID("JoueurAgent1", AID.ISLOCALNAME);
        AID receiver2 = new AID("JoueurAgent2", AID.ISLOCALNAME);
        listAIDs.add(receiver1);
        listAIDs.add(receiver2);
        for (AID aid:listAIDs) {
            message.addReceiver(aid);
        }
        message.setContent("Veulliez tenter un nombre :");
        send(message);

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg= receive();
                if(msg!=null){

                    data.add(msg.getContent());
                    serverContainer.showMessage(data);
                    if(!data.isEmpty()){
                        nbJoeur=Integer.parseInt(msg.getContent());
                        if(nbJoeur==nbMagique){
                            for (AID aid:listAIDs) {
                                if((msg.getSender().getName()).equals(aid.getName())){
                                    ACLMessage message = new ACLMessage(ACLMessage.INFORM);
                                    message.addReceiver(msg.getSender());
                                    message.setContent("Bravo vous avez trouve le nombre magique");
                                    send(message);
                                }else {
                                    ACLMessage message = new ACLMessage(ACLMessage.INFORM);
                                    message.addReceiver(aid);
                                    message.setContent("le joueur "+msg.getSender().getLocalName()+" a trouve le nombre magique");
                                    send(message);
                                }
                            }
                        }else if (nbJoeur>nbMagique) {
                            ACLMessage message = new ACLMessage(ACLMessage.INFORM);
                            message.addReceiver(msg.getSender());
                            message.setContent("Le nombre "+nbJoeur+" est superieur au nombre magique!!");
                            send(message);

                        }else {
                            ACLMessage message = new ACLMessage(ACLMessage.INFORM);
                            message.addReceiver(msg.getSender());
                            message.setContent("Le nombre "+nbJoeur+" est inferieur au nombre magique!!");
                            send(message);
                        }
                    }
                }else{
                    block();
                }

            }
        });

    }



    @Override
    protected void takeDown() {
        System.out.println("*****Avant de mourir *****");
    }

    @Override
    protected void afterMove() {
        System.out.println("***** apres migration *****");
    }

    @Override
    protected void beforeMove() {
        System.out.println("***** avant migration *****");
    }

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {

    }
}
