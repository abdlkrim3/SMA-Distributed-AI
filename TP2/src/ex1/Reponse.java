package ex1;

public class Reponse {
    public String titre;
    public boolean correcte;
    public Reponse(String titre,boolean correcte){
        this.titre=titre;
        this.correcte=correcte;

    }
    public String toString(){
        return titre;
    }
}
