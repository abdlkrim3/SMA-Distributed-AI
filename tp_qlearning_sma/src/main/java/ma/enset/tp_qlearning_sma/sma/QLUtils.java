package ma.enset.tp_qlearning_sma.sma;

public class QLUtils {
    private final double ALPHA=0.1;
    private final double GAMMA=0.9;
    private int MAX_EPOCH=2000;
    private final int GRID_SIZE=7;
    private final int ACTION_SIZE=4;

    private int [][]gridRewards;
    private double [][]qtable=new double[GRID_SIZE*GRID_SIZE][ACTION_SIZE];
    private int [][]actions;
    private int stateI;
    private int stateJ;
    public QLUtils(){
        actions=new int[][]{
                {0,-1}, // gauche
                {0,1}, //droite
                {1,0}, // bas
                {-1,0} // haut
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
}
