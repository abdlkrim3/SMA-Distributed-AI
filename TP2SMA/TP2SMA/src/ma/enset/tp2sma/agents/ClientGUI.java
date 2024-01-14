package ma.enset.tp2sma.agents;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ma.enset.tp2sma.agents.ClientAgent;

public class ClientGUI extends Application {
     private ClientAgent clientAgent;
    private ObservableList<String> data= FXCollections.observableArrayList();
    public static void main(String[] args) throws Exception {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        startCotainer();
        BorderPane root=new BorderPane();

        ListView<String> listView=new ListView<>(data);
        Button buttonSend=new Button("Send");
        TextField textMessage=new TextField();
        Label labelMessage=new Label("Message");
        HBox hBox=new HBox(labelMessage,textMessage,buttonSend);
        root.setCenter(listView);
        root.setBottom(hBox);
        Scene scene=new Scene(root,400,300);
        primaryStage.setScene(scene);
        primaryStage.show();
        buttonSend.setOnAction(event -> {
            String message=textMessage.getText();

            GuiEvent guiEvent=new GuiEvent(this,1);
            guiEvent.addParameter(message);
            clientAgent.onGuiEvent(guiEvent);

            data.add(message);
            textMessage.setText("");
        });
    }
    private void startCotainer() throws Exception {
        Runtime runtime=Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer container=runtime.createAgentContainer(profile);
        AgentController agentClient1=container.createNewAgent("client2","ma.enset.tp2sma.agents.ClientAgent",new Object[]{this});
        agentClient1.start();
    }

    public void setClientAgent(ClientAgent clientAgent) {
        this.clientAgent = clientAgent;
    }
}
