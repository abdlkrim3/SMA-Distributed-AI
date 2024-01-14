package ex1;

import java.util.Arrays;
import java.util.Scanner;

public class Notes {
    public static void main(String[] args) {

        // La saisie des notes
        Scanner scan = new Scanner(System.in);
        int nbrEtudiants;
        System.out.print("Entrer le nombre d'etudiants : ");
        nbrEtudiants = scan.nextInt();
        float notes[] = new float[nbrEtudiants];
        for (int i = 0; i < nbrEtudiants; i++) {
            System.out.print("Entrer une note pour l'Etudiant "+(i+1)+" : ");
            notes[i] = scan.nextFloat();
        }


        // Le trie et affichage des notes saisie
        Arrays.sort(notes);
        System.out.println("Voici Les notes saisie: ");
        for (float note : notes) {
            System.out.println(note);
        }

        // Note moyenne
        float moyenne = 0;
        for (float not : notes)
            moyenne += not;
        moyenne /= nbrEtudiants;
        System.out.println("La note moyenne est: " + moyenne);

        // Note maximale
        float noteMax = 0;
            noteMax=notes[notes.length-1];
        System.out.println("La note maximale est: " + noteMax);

        // Note minimale
        float noteMin = 0;
        noteMin=notes[0];
        System.out.println("La note minimale est: " + noteMin);

        // Nombre d'etudiants ayant la note saisie
        float noteSaisie;
        int cmpt = 0;
        System.out.print("Saisie une note: ");
        noteSaisie = scan.nextFloat();
        for (float note : notes)
            if (note == noteSaisie)
                cmpt++;
        System.out.println("Le nombre d'etudiants ayant la note: " + noteSaisie + " est: " + cmpt);


    }
}
