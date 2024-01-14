package ma.enset.tp_qlearning_sma.sequential;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class QlearningJavaFX extends Application {
    private final double ALPHA = 0.1;
    private final double GAMMA = 0.9;
    private int MAX_EPOCH = 2000;
    private final int GRID_SIZE = 7;
    private final int ACTION_SIZE = 4;

    private int[][] gridRewards;
    private double[][] qtable = new double[GRID_SIZE * GRID_SIZE][ACTION_SIZE];
    private int[][] actions;
    private int stateI;
    private int stateJ;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        runQlearning();
        showQTable();
    }

    public QlearningJavaFX() {
        actions = new int[][]{
                {0, -1}, // gauche
                {0, 1}, //droite
                {1, 0}, // bas
                {-1, 0} // haut
        };
        gridRewards = new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, -1, 0, 0, 0, -1, 0},
                {0, 0, 0, -1, 0, 0, -1},
                {0, 0, -1, 0, 0, 0, -1},
                {0, 0, 0, 0, 0, 0, 0},
                {-1, 0, 0, -1, 0, 0, 0},
                {0, 0, 0, 0, -1, 0, 1}
        };
    }

    public void resetState() {
        stateI = 0;
        stateJ = 0;
    }

    public int chooseAction(double eps) {
        Random rnd = new Random();
        double bestQ = 0;
        int act = 0;
        if (rnd.nextDouble() < eps) {
            // exploration
            act = rnd.nextInt(ACTION_SIZE);
        } else {
            // exploitation
            int st = stateI * GRID_SIZE + stateJ;
            for (int i = 0; i < ACTION_SIZE; i++) {
                if (qtable[st][i] > bestQ) {
                    bestQ = qtable[st][i];
                    act = i;
                }
            }
        }
        return act;
    }

    public int executeAction(int act) {
        stateI = Math.max(0, Math.min(actions[act][0] + stateI, GRID_SIZE - 1));
        stateJ = Math.max(0, Math.min(actions[act][1] + stateJ, GRID_SIZE - 1));
        return stateI * GRID_SIZE + stateJ;
    }

    public boolean finished() {
        return gridRewards[stateI][stateJ] == 1;
    }

    public void showQTable() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        for (int i = 0; i < qtable.length; i++) {
            for (int j = 0; j < qtable[i].length; j++) {
                Label label = new Label(String.format("%.2f", qtable[i][j]));
                gridPane.add(label, j, i);
            }
        }

        // Add action labels to the grid
        resetState();
        while (!finished()) {
            int act = chooseAction(0);
            Label actionLabel = new Label("Action: " + act);
            gridPane.add(actionLabel, stateJ, stateI);
            executeAction(act);
        }

        Scene scene = new Scene(gridPane);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Q-Table with Actions");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getActionSymbol(int[] action) {
        if (action[0] == 0 && action[1] == -1) {
            return "←";
        } else if (action[0] == 0 && action[1] == 1) {
            return "→";
        } else if (action[0] == 1 && action[1] == 0) {
            return "↓";
        } else if (action[0] == -1 && action[1] == 0) {
            return "↑";
        } else {
            return "";
        }
    }

    public void runQlearning() {
        int it_epoch = 0;
        int it = 0;
        int currentState;
        int nextState;
        int act, act1;
        while (it_epoch < MAX_EPOCH) {
            resetState();
            while (!finished()) {
                currentState = stateI * GRID_SIZE + stateJ;
                act = chooseAction(0.4);
                nextState = executeAction(act);
                act1 = chooseAction(0);
                qtable[currentState][act] = qtable[currentState][act] + ALPHA * (gridRewards[stateI][stateJ] + GAMMA * qtable[nextState][act1] - qtable[currentState][act]);
                it++;
            }
            it_epoch++;
        }
        System.out.println(it);
    }
}


