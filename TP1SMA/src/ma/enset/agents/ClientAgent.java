package ma.enset.agents;

import jade.core.AID;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class ClientAgent extends GuiAgent {
    private ClientGUI clientGUI;
    @Override
    protected void setup() {
        clientGUI=(ClientGUI) getArguments()[0];
        clientGUI.setClientAgent(this);
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
        message.addReceiver(new AID("Server",AID.ISLOCALNAME));
        message.setContent(guiEvent.getParameter(0).toString());
        send(message);
    }
}
