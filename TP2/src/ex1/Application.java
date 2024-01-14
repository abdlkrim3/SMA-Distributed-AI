package ex1;

import java.util.Scanner;

public class Application {
    public static void main(String []args) {
        Scanner scan=new Scanner(System.in);
        //Création du réponses
        Reponse[] r1 = new Reponse[3];
        Reponse[] r2 = new Reponse[3];
        Reponse[] r3 = new Reponse[3];
        Reponse[] r4 = new Reponse[3];
        Reponse[] r5 = new Reponse[3];

        //Création des question avec ses réponses
         r1[0]=new Reponse("jre",false);
         r1[1]=new Reponse("jdk",true);
         r1[2]=new Reponse("jvm",false);
        Question q1 = new Question("Q1", "Compiler,déboguer et exécuter un programme java", 4,r1[0],r1[1],r1[2]);

        r2[0]=new Reponse("jre",false);
        r2[1]=new Reponse("jdk",false);
        r2[2]=new Reponse("jvm",true);
        Question q2 = new Question("Q2", "Convirtire du byte code en code spécifique à la machine", 4, r2[0], r2[1], r2[2]);

        r3[0]=new Reponse("jre",true);
        r3[1]=new Reponse("jit",false);
        r3[2]=new Reponse("jvm",false);
        Question q3 = new Question("Q3", "Exécuter le programme java", 4, r3[0], r3[1], r3[2]);

        r4[0]=new Reponse("jre",false);
        r4[1]=new Reponse("jit",true);
        r4[2]=new Reponse("jvm",false);
        Question q4 = new Question("Q4", "Optimiser le byte code", 4, r4[0], r4[1], r4[2]);

        r5[0]=new Reponse(".java",false);
        r5[1]=new Reponse(".class",true);
        r5[2]=new Reponse(".jdk",false);
        Question q5 = new Question("Q1", "Les fichiers java compilés sont de type : fichier.''", 4,r5[0],r5[1],r5[2]);

        //Création de premiere questionnaire
        Questionnaire qq1=new Questionnaire("QQ1",q1,q2,q3,q4,q5);

        //Affichage du questionnaire
        int score=0;
        for(int i=0;i<5;i++){

            System.out.println(qq1.quest[i]);
            System.out.println("Choisez votre réponse : ");
            String r =scan.nextLine().toLowerCase();
            for(int j=0;j<3;j++) {
                if (r.equals(qq1.quest[i].rep[j].titre) && qq1.quest[i].rep[j].correcte) score += qq1.quest[i].score;
            }
        }

        //Affichage du score
        System.out.println("***************** Votre score = "+score+" *************\n");

        //Afichage de la coreection du questionnaire
        System.out.println("***************** Correction *******************");
        for(int i=0;i<5;i++){
            System.out.println(qq1.quest[i]);
            for(int j=0;j<3;j++) {
                if(qq1.quest[i].rep[j].correcte) System.out.println("la réponse correct est : "+qq1.quest[i].rep[j].titre);
            }
        }
        System.out.println("************************************************");


    }
}
