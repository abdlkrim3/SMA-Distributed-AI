package ma.enset.tp_qlearning_sma.sma;


public class QLUtils {
    public static final double ALPHA=0.3;
    public static final double GAMMA=0.7;
    public static int MAX_EPOCH=100;
    public static final int GRID_SIZE=7;
    public static final int ACTION_SIZE=4;

    public static int [][]gridRewards= new int[][]{
            {0, 0, 0, 0, 0, 0, 1},
            {0, -1, 0, 0, 0, 0, 0},
            {0, 0, -1, 0, 0, 0, -1},
            {0, 0, -1, 0, 0, 0, -1},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, -1, 0, 0, 0},
            {0, 0, 0, 0, -1, 0, 0}
    };
    public static double [][]qtable=new double[GRID_SIZE*GRID_SIZE][ACTION_SIZE];
    public static int [][]actions=new int[][]{
            {0,-1}, // gauche
            {0,1}, //droite
            {1,0}, // bas
            {-1,0} // haut
    };
    public  int STATEI=0;
    public  int STATEJ=0;

    public static final int AGENT_SIZE=8;


}
