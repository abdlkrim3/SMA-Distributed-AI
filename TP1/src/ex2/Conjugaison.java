package ex2;

import java.util.Scanner;

public class Conjugaison {
    private static String verbe;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Saisie un verbe du premier groupe : ");
        verbe = scan.nextLine();
        if (verbe.endsWith("er")) {
            verbe = verbe.substring(0, verbe.length() - 2);
            System.out.println("Le verbe "+verbe+"er au present : " );
            System.out.println("je " + verbe + "e\n"+
                               "tu " + verbe + "es\n"+
                               "il " + verbe + "e\n"+
                             "nous " + verbe + "ons\n"+
                             "vous " + verbe + "ez\n"+
                              "ils " + verbe + "ent");
        } else {
            System.out.println("Attention!! Le verbe " + verbe + " n'appartient pas au premiere groupe");
        }

    }
}
