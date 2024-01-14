package ex1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Client implements Serializable {
    private int id;
    private String nom;
    private String prenom;
    private String addres;
    private String tel;
    private String email;

    public Client() {
    }

    public Client(int id, String nom, String prenom, String addres, String tel, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.addres = addres;
        this.tel = tel;
        this.email = email;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getAddres() {
        return addres;
    }
    public void setAddres(String addres) {
        this.addres = addres;
    }
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return  "id=" + id +"\n"+
                "nom='" + nom +"\n"+
                "prenom='" + prenom +"\n"+
                "adresse='" + addres +"\n"+
                "tel='" + tel +"\n"+
                "email='" + email;
    }
}
