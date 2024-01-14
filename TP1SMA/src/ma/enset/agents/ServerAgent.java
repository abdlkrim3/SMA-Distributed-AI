package ma.enset.agents;

import jade.core.behaviours.*;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServerAgent extends GuiAgent {
    private ServerGUI serverGUI;
    private ObservableList<String> data= FXCollections.observableArrayList();
    @Override
    protected void setup() {
        serverGUI=(ServerGUI)getArguments()[0];

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg= receive();
                if(msg!=null){
                    data.add(msg.getContent());
                    serverGUI.showMessage(data);
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
