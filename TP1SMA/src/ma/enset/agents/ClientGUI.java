package ma.enset.agents;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ma.enset.agents.ClientAgent;

public class ClientGUI extends Application{
    private ClientAgent clientAgent;
    private ObservableList<String>data= FXCollections.observableArrayList();

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        startContainer();
        BorderPane root=new BorderPane();
        ListView<String> listView=new ListView<>(data);
        Button buttonSend=new Button("Send");
        TextField textFieldMwssage=new TextField();
        Label labelMessage=new Label("Message");
        HBox hBox=new HBox();
        hBox.getChildren().addAll(labelMessage,textFieldMwssage,buttonSend);
        root.setBottom(hBox);
        root.setCenter(listView);
        Scene scene=new Scene(root,400,300);
        stage.setScene(scene);
        stage.show();
        buttonSend.setOnAction(actionEvent -> {
            String message=textFieldMwssage.getText();
            GuiEvent guiEvent=new GuiEvent(this,1);
            guiEvent.addParameter(message);
            clientAgent.onGuiEvent(guiEvent);
            data.add(message);
            textFieldMwssage.setText("");
        });
    }
    private void startContainer() throws Exception {
        Runtime runtime=Runtime.instance();
        ProfileImpl profileImpl=new ProfileImpl();
        profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
        AgentController agentController1 = agentContainer.createNewAgent("Client2","ma.enset.agents.ClientAgent",new Object[]{this});
        agentController1.start();
    }

    public void setClientAgent(ClientAgent clientAgent) {
        this.clientAgent = clientAgent;
    }
}
