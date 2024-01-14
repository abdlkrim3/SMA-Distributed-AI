package ex1;

public class Question {
    public String titre;
    public String description;
    public int score;
    Reponse []rep=new Reponse[3];
    public Question(String titre,String description,int score,Reponse opt1,Reponse opt2,Reponse opt3){
        this.titre=titre;
        this.description=description;
        this.score+=score;
         rep[0]=opt1;
         rep[1]=opt2;
         rep[2]=opt3;

    }
    public String toString(){
        return titre+" : "+description+" ."+"\n"+"-"+rep[0]+"\n"+"-"+rep[1]+"\n"+"-"+rep[2];
    }

}
