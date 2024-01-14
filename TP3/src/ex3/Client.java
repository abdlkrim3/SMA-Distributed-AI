package ex3;

import java.util.ArrayList;
import java.util.List;
public class Client {
    private String nom;
    private String prenom;
    private String addres;
    private String tel;
    private String email;
    private String ville;

    public Client() {
    }

    public Client(String nom, String prenom, String addres, String tel, String email, String ville) {
        this.nom = nom;
        this.prenom = prenom;
        this.addres = addres;
        this.tel = tel;
        this.email = email;
        this.ville = ville;
    }
    List<Commande> listCommande=new ArrayList<>();
    public void ajouterCommand(Commande cmd){
        for (Commande commande:listCommande) {
           if(commande.getReference()== cmd.getReference())listCommande.add(cmd);
        }
    }
    public void supprimerCommande(int ref){
        for (Commande commande:listCommande) {
            if(commande.getReference()== ref)listCommande.remove(commande);
        }
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setAddres(String addres) {
        this.addres = addres;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getAddres() {
        return addres;
    }
    public String getTel() {
        return tel;
    }
    public String getEmail() {
        return email;
    }
    public String getVille() {
        return ville;
    }
    @Override
    public String toString() {
        return "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", addres='" + addres + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", ville='" + ville + '\'' ;
    }
}
