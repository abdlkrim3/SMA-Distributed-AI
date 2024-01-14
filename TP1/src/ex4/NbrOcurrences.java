package ex4;

import java.util.Scanner;

public class NbrOcurrences {
    public static void main(String []args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Entrer une ligne de texte(max.100 caracteres) : ");
        String ch=scan.nextLine();
        String nb_occurrences[]={"a","b","c","d","e","f","g","h","i","j","k","l","m",
                                 "n","o","p","q","r","s","t","v","u","w","y","x","z"};
        String ch2=ch.toLowerCase();
        String abc[]=ch2.split("(?!^)");
        int cmpt=0;
        System.out.println("la chaine "+ch+" contient : ");
        for(int i =0;i<nb_occurrences.length;i++){
            for(int j=0;j<ch.length();j++){
                if(nb_occurrences[i].equals(abc[j]))cmpt++;
            }
            if(cmpt!=0)
            System.out.println(cmpt+"fois lettre'"+nb_occurrences[i]+"'");
            cmpt=0;
        }
    }
}
