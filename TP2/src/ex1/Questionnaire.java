package ex1;

public class Questionnaire {
    public String titre;
    Question []quest=new Question[5];
    public Questionnaire(String titre,Question q1,Question q2,Question q3,Question q4,Question q5){
        this.titre=titre;
        quest[0]=q1;
        quest[1]=q2;
        quest[2]=q3;
        quest[3]=q4;
        quest[4]=q5;
    }
    public String toString(){
        return quest[0]+"\n"+quest[1]+"\n"+quest[2]+"\n"+quest[3]+"\n"+quest[4];
    }

}
