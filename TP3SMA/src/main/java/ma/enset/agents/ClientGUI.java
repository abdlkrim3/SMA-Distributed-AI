package ma.enset.agents;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ma.enset.agents.ClientAgent;

public class ClientGUI extends Application {
    private ClientAgent clientAgent;
    private ObservableList<String> data= FXCollections.observableArrayList();
    private ObservableList<String> data2= FXCollections.observableArrayList();

    public static void main(String[] args) throws Exception {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        startCotainer();
        BorderPane root=new BorderPane();
        HBox hBox1=new HBox();
        VBox vBox=new VBox();
        Button buttonSearch = new Button("Search");
        TextField textFieldSearch=new TextField();
        ListView<String> listView=new ListView<>(data);
        listView.setStyle("-fx-min-height: 100px");
        ListView<String> listView2=new ListView<>(data2);
        Button buttonDetails=new Button("Details");
        Button buttonConfirm=new Button("Confirm");
        HBox hBox=new HBox(buttonDetails,buttonConfirm);
        hBox1.getChildren().addAll(textFieldSearch,buttonSearch);
        vBox.getChildren().addAll(listView,hBox);
        root.setTop(hBox1);
        root.setCenter(vBox);
        root.setBottom(listView2);
        Scene scene=new Scene(root,400,300);
        primaryStage.setScene(scene);
        primaryStage.show();
        buttonSearch.setOnAction(actionEvent -> {
            String search =textFieldSearch.getText();
            data.clear();
            GuiEvent guiEvent = new GuiEvent(this,1);
            guiEvent.addParameter(search);
            clientAgent.onGuiEvent(guiEvent);
            textFieldSearch.setText("");
        });
        buttonDetails.setOnAction(event -> {
            String message=listView.getSelectionModel().getSelectedItem();
            GuiEvent guiEvent=new GuiEvent(this,2);
            guiEvent.addParameter(message);
            clientAgent.onGuiEvent(guiEvent);
            data2.add(message);
        });
        buttonConfirm.setOnAction(actionEvent -> {
            GuiEvent guiEvent=new GuiEvent(this,3);
            clientAgent.onGuiEvent(guiEvent);
            data2.add(" Purchase completed successfully");
        });
    }
    private void startCotainer() throws Exception {
        Runtime runtime=Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer container=runtime.createAgentContainer(profile);
        AgentController agentClient1=container.createNewAgent("client2","ma.enset.agents.ClientAgent",new Object[]{this});
        agentClient1.start();
    }

    public void setClientAgent(ClientAgent clientAgent) {
        this.clientAgent = clientAgent;
    }
    public void showData(String service){
        Platform.runLater(()->{
            data.add(service);
        });

    }
    public void showData2(String service){
        Platform.runLater(()->{
            data2.clear();
            data2.add(service);
        });

    }
}
