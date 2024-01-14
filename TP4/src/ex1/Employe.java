package ex1;

import java.io.Serializable;

public class Employe implements Serializable {
    private String nom;
    private String prenom;
    private float salaire;

    public Employe() {
    }

    public Employe(String nom, String prenom, float salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.salaire = salaire;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public float getSalaire() {
        return salaire;
    }
}
