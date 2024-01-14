package ma.enset.agents;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import javafx.application.Application;
import javafx.application.Platform;
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

public class JoueurContainer2 extends Application{
    private JoueurAgent2 joueurAgent2;
    private ObservableList<String>data= FXCollections.observableArrayList();

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        startContainer();
        BorderPane root=new BorderPane();
        ListView<String> listView=new ListView<>(data);
        listView.setStyle("-fx-background-color: GRAY;");
        Button buttonSend=new Button("Send");
        buttonSend.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        TextField textFieldMwssage=new TextField();
        root.setStyle("-fx-background-color: BEIGE;");
        Label labelMessage=new Label("Message");
        labelMessage.setStyle("-fx-font: normal bold 20px 'serif' ");
        HBox hBox=new HBox();
        hBox.getChildren().addAll(labelMessage,textFieldMwssage,buttonSend);
        root.setBottom(hBox);
        root.setCenter(listView);
        Scene scene=new Scene(root,400,300);
        stage.setScene(scene);
        stage.setTitle("Joueur2");
        stage.show();
        buttonSend.setOnAction(actionEvent -> {
            String message=textFieldMwssage.getText();
            GuiEvent guiEvent=new GuiEvent(this,1);
            guiEvent.addParameter(message);
            joueurAgent2.onGuiEvent(guiEvent);
            data.add(message);
            textFieldMwssage.setText("");
        });
    }
    private void startContainer() throws Exception {
        Runtime runtime=Runtime.instance();
        ProfileImpl profileImpl=new ProfileImpl();
        profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
        AgentController agentController1 = agentContainer.createNewAgent("JoueurAgent2","ma.enset.agents.JoueurAgent2",new Object[]{this});
        agentController1.start();
    }

    public void setClientAgent(JoueurAgent2 joueurAgent2) {
        this.joueurAgent2 = joueurAgent2;
    }
    public void showMessage(ObservableList<String> message){
        Platform.runLater(()->{
            data.clear();
            data.addAll(message);
        });
    }
}
