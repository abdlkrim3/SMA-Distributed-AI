package multiAgentsVersion;

public class AgentResult implements Comparable{
    public double alpha;
    public double gamma;
    public double epselon;
    public String path;
    public int pathLength;
    public int iterations;
    public String agentName;

    public AgentResult(double alpha, double gamma,double epselon, String path, int iterations,String agentName) {
        this.alpha = alpha;
        this.gamma = gamma;
        this.epselon = epselon;
        this.path = path;
        this.pathLength=path.split(" -> ").length;
        this.iterations = iterations;
        this.agentName = agentName;

    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public double getEpselon() {
        return epselon;
    }

    public void setEpselon(double epselon) {
        this.epselon = epselon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }


    @Override
    public int compareTo(Object o) {
        AgentResult agentResult=(AgentResult) o;
        if (this.iterations>agentResult.iterations && this.pathLength>agentResult.pathLength)
            return -1;
        else if(this.iterations<agentResult.iterations && this.pathLength<agentResult.pathLength){
            return 1;
        }else
            return 0;
    }
}
