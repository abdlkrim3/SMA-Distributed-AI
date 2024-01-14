package ex_2;

import java.io.Serializable;
public class Produit implements Serializable {
    private long id;
    private String nom;
    private String description;
    private float prix;
    private String marque;
    private int nbrEnStock;

    public Produit() {}

    public Produit(int id, String nom, String description, float prix, String marque, int nbrEnStock) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.marque = marque;
        this.nbrEnStock = nbrEnStock;
    }

    public long getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getDescription() {
        return description;
    }
    public float getPrix() {
        return prix;
    }
    public String getMarque() {
        return marque;
    }
    public int getNbrEnStock() {
        return nbrEnStock;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrix(float prix) {
        this.prix = prix;
    }
    public void setMarque(String marque) {
        this.marque = marque;
    }
    public void setNbrEnStock(int nbrEnStock) {
        this.nbrEnStock = nbrEnStock;
    }
    @Override
    public String toString() {
        return "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", marque='" + marque + '\'' +
                ", nbrEnStock=" + nbrEnStock +
                '}';
    }
}
