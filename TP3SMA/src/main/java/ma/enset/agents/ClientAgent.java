package ma.enset.agents;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClientAgent extends GuiAgent {
    private ma.enset.agents.ClientGUI clientGUI;
    DFAgentDescription agentDescriptions[];
    @Override
    protected void setup() {
        clientGUI=(ma.enset.agents.ClientGUI)getArguments()[0];
        clientGUI.setClientAgent(this);
        ParallelBehaviour parallelBehaviour=new ParallelBehaviour();
        /*parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            private List<Float> prices=new ArrayList<>();
            @Override
            public void action() {
                ACLMessage message=receive();
                if(message!=null){
                    if(message.getPerformative()==ACLMessage.PROPOSE){
                        System.out.println(message);
                        prices.add(Float.parseFloat(message.getContent()));
                        if (prices.size()==2){
                            float prixMin=Math.min(prices.get(0),prices.get(1));
                            ACLMessage message1=message.createReply();
                            message1.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                            send(message1);
                        }
                    }else if(message.getPerformative()==ACLMessage.AGREE){
                        ACLMessage message1=message.createReply();
                        message1.setPerformative(ACLMessage.CONFIRM);
                        send(message1);
                    }
                }else {
                    block();
                }
            }
        });
        parallelBehaviour.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                DFAgentDescription dfAgentDescription=new DFAgentDescription();
                ServiceDescription service=new ServiceDescription();
                service.setType("gaming");
                dfAgentDescription.addServices(service);
                try {
                    agentDescriptions= DFService.search(getAgent(),dfAgentDescription);

                } catch (FIPAException e) {
                    e.printStackTrace();
                }
            }
        });
        addBehaviour(parallelBehaviour);*/
    }

    @Override
    protected void takeDown() {

    }

    @Override
    protected void beforeMove() {

    }

    @Override
    protected void afterMove() {

    }

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {
        ACLMessage message=receive();
        if (guiEvent.getType()==1){
            Iterator<String> it=guiEvent.getAllParameter();
            String type = it.next();
            DFAgentDescription dfAgentDescription=new DFAgentDescription();
            ServiceDescription service=new ServiceDescription();
            service.setType(type);
            dfAgentDescription.addServices(service);
            try {
                agentDescriptions= DFService.search(this,dfAgentDescription);
                for (DFAgentDescription df:agentDescriptions) {
                    Iterator<ServiceDescription> services=df.getAllServices();
                    while (services.hasNext()){
                        ServiceDescription description = services.next();
                        if(description.getType().equals(type))
                            clientGUI.showData(description.getName()+" "+description.getType()+" "+df.getName().getLocalName());
                    }
                }
            } catch (FIPAException e) {
                e.printStackTrace();
            }
        }else if (guiEvent.getType()==2){

            clientGUI.showData2("Purchase completed successfully");
            if(guiEvent.getAllParameter()!=null){
                ACLMessage request =new ACLMessage(ACLMessage.REQUEST);
                String choix =guiEvent.getParameter(0).toString();
                request.setContent(choix);
                request.addReceiver(new AID("server",AID.ISLOCALNAME));
                send(request);
            }if (message.getPerformative()==ACLMessage.PROPOSE){
                clientGUI.showData2(message.getContent());
                ACLMessage message1=message.createReply();
                message1.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                send(message1);
            }

        }else if (guiEvent.getType()==3){
            if(message.getPerformative()==ACLMessage.AGREE){
                clientGUI.showData2("Purchase completed successfully");
                ACLMessage message1=message.createReply();
                message1.setPerformative(ACLMessage.CONFIRM);
                send(message1);
            }else {
                clientGUI.showData2("Purchase refused");

            }
        }


    }
}
