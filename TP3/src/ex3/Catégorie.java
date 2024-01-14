package ex3;

import java.util.ArrayList;
import java.util.List;

public class Catégorie {
    private String nom;
    private String description;
    List<Ordinateur> ordinateurs=new ArrayList();
    public Catégorie(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
    public void ajouterOrdinateur(Ordinateur ordinateur){
        int cmp=0;
        for (Ordinateur ord:ordinateurs) {
            if(ordinateur.getNom()==ord.getNom()) cmp++;
        }
        if(cmp==0) ordinateurs.add(ordinateur);
        else System.out.println("cet ordinateur existe deja");
    }

    public void supprimerOrdinateur(String nom) {
        for (Ordinateur ord : ordinateurs) {
            if (ord.getNom() == nom) ordinateurs.remove(ord);
        }
    }
    public void rechercherParPrix(float prx){
        for (Ordinateur ord:ordinateurs) {
                if(ord.getPrix()==prx) System.out.println(ord);
        }
    }
}
