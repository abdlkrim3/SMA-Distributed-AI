package ex3;

import java.util.Scanner;


public class Menu {
    public static void main(String[]args){
        Scanner scan =new Scanner(System.in);
        String chaine = "";
        String choix = "";
        while(true){
            System.out.println("**********************Menu*********************\n" +
                    "saisir : lire une chaine de caracters\n" +
                    "afficher : afficher la chaine saisie.\n"+
                    "inverser : inverser la chaine saisie.\n"+
                    "Nombre de mots : compter le nombre de mots de la chaine saisie\n" +
                    "***********************************************");
            System.out.println("Veuillez choisir une choix : ");
            choix=scan.nextLine();
            String temp="";
            switch (choix) {
                case "saisir":System.out.println("Saisie une chaine : ");
                    chaine = scan.nextLine();
                    break;
                case "afficher": System.out.println("Voicie la chaine saisie : ");
                    System.out.println(chaine);
                    break;
                case "inverser": System.out.println("La chaine est inverser: ");
                    StringBuffer strb = new StringBuffer(chaine);
                    chaine = strb.reverse().toString();
                    System.out.println(chaine);
                    break;
                case "Nombre de mots": String cmpt[]= chaine.split("\\s+");

                    System.out.println("la chaine contient "+cmpt.length+" mots");break;
                default : System.out.println("Mouvais coix !! ");
            }
            System.out.println("Frappez une touche pour revenir au menu");
                temp=scan.nextLine();
        }
    }

}
