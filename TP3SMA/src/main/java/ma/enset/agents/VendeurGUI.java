package ma.enset.agents;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.domain.FIPAException;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VendeurGUI extends Application {
    private ma.enset.agents.VendeurAgent serverAgent;

    public void setServerAgent(VendeurAgent serverAgent) {
        this.serverAgent = serverAgent;
    }

    private ObservableList<String> data= FXCollections.observableArrayList();
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    private void startConatiner() throws Exception {
        Runtime runtime=Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer container=runtime.createAgentContainer(profile);
        AgentController agent=container.createNewAgent("server","ma.enset.agents.VendeurAgent",new Object[]{this});
        agent.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        startConatiner();
        BorderPane root=new BorderPane();
        HBox hBox1 = new HBox();
        VBox vBox1 =new VBox();
        VBox vBox2 = new VBox();
        VBox vBoxTop = new VBox();
        Button buttonSave = new Button("Save");
        Button buttonupdate = new Button("update");
        Button buttonDelete = new Button("Delete");
        HBox hBox2 = new HBox();
        Label labelType = new Label("Type :");
        Label labelName = new Label("Name :");
        TextField textFieldType = new TextField();
        TextField textFieldName = new TextField();
        vBox1.getChildren().addAll(labelType,labelName);
        vBox2.getChildren().addAll(textFieldType,textFieldName);
        hBox1.getChildren().addAll(vBox1,vBox2);
        hBox2.getChildren().addAll(buttonSave,buttonupdate,buttonDelete);
        vBoxTop.getChildren().addAll(hBox1,hBox2);
        ListView<String>listView=new ListView<>(data);
        TableView<String> tableView = new TableView<>(data);
        TableColumn<String, String> typeColumn = new TableColumn<String, String>("Type");
        TableColumn<String, Integer> nameColumn = new TableColumn<String, Integer>("Name");
        try {
            typeColumn.setCellValueFactory(new PropertyValueFactory<String, String>(data.get(0)));
            nameColumn.setCellValueFactory(new PropertyValueFactory<String, Integer>(data.get(1)));
        }catch (Exception e){
            e.printStackTrace();
        }


        tableView.getColumns().add(typeColumn);
        tableView.getColumns().add(nameColumn);
        root.setTop(vBoxTop);
        root.setCenter(listView);
        Scene scene=new Scene(root,400,300);
        primaryStage.setScene(scene);
        primaryStage.show();
        buttonSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String type =textFieldType.getText();
                String name =textFieldName.getText();
                GuiEvent guiEvent = new GuiEvent(this,1);
                data.addAll(type+" "+name);
                guiEvent.addParameter(type);
                guiEvent.addParameter(name);
                serverAgent.onGuiEvent(guiEvent);
                textFieldType.setText("");
                textFieldName.setText("");

            }
        });
        buttonDelete.setOnAction(actionEvent -> {
            String elements =listView.getSelectionModel().getSelectedItem();
            data.remove(elements);
        });
    }
    public void showMessage(String message){
        Platform.runLater(()->{
            data.add(message);
        });

    }

}
