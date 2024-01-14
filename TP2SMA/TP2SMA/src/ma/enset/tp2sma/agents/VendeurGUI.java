package ma.enset.tp2sma.agents;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class VendeurGUI extends Application {
    private VendeurAgent serverAgent;
    private ObservableList<String> data= FXCollections.observableArrayList();
    public static void main(String[] args) throws Exception {
       launch(args);
    }

    private void startConatiner() throws Exception {
        Runtime runtime=Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer container=runtime.createAgentContainer(profile);
        AgentController agent=container.createNewAgent("server","ma.enset.tp2sma.agents.VendeurAgent",new Object[]{this});
        agent.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        startConatiner();
        BorderPane root=new BorderPane();
        ListView<String> listView=new ListView<>(data);
        root.setCenter(listView);
        Scene scene=new Scene(root,400,300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void showMessage(String message){
        Platform.runLater(()->{
            data.add(message);
        });

    }

}
