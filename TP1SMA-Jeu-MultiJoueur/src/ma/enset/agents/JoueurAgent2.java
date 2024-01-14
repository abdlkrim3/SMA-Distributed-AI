package ma.enset.agents;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JoueurAgent2 extends GuiAgent {
    private JoueurContainer2 joueurContainer;
    private ObservableList<String> data= FXCollections.observableArrayList();
    @Override
    protected void setup() {
        joueurContainer =(JoueurContainer2) getArguments()[0];
        joueurContainer.setClientAgent(this);
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg=receive();
                if(msg!=null){
                    data.add(msg.getContent());
                    joueurContainer.showMessage(data);
                }
                else{
                    block();
                }} });
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
        ACLMessage message=new ACLMessage(ACLMessage.REQUEST);
        message.addReceiver(new AID("ServerAgent",AID.ISLOCALNAME));
        message.setContent(guiEvent.getParameter(0).toString());
        send(message);
    }
}
