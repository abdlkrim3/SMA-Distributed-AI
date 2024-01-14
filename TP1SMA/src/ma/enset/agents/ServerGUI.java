package ma.enset.agents;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ma.enset.agents.ClientAgent;

public class ServerGUI extends Application {
    ClientAgent clientAgent;
    private ObservableList<String> data= FXCollections.observableArrayList();

    public static void main(String[] args) throws ControllerException {
        launch(args);
    }
    private void startContainer() throws Exception {
        Runtime runtime=Runtime.instance();
        ProfileImpl profileImpl=new ProfileImpl();
        profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
        AgentController agentController =agentContainer.createNewAgent("Server","ma.enset.agents.ServerAgent",new Object[]{this});
        agentController.start();
    }
    @Override
    public void start(Stage stage) throws Exception {
        startContainer();
        BorderPane root=new BorderPane();
        ListView<String> listView=new ListView<>(data);
        root.setCenter(listView);
        Scene scene =new Scene(root,400,300);
        stage.setScene(scene);
        stage.show();


    }
    public void showMessage(ObservableList<String> message){
        data.clear();
        data.addAll(message);

    }
}
