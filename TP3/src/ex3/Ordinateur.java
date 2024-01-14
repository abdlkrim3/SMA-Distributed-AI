package ex3;

public class Ordinateur {
    private String nom;
    private String marque;
    private String description;
    private float prix;
    private int nbrEnStock;
    public Ordinateur() {
    }

    public Ordinateur(String nom, String marque, String description, float prix, int nbrEnStock) {
        this.nom = nom;
        this.marque = marque;
        this.description = description;
        this.prix = prix;
        this.nbrEnStock = nbrEnStock;
    }

    @Override
    public String toString() {
        return "nom='" + nom + '\'' +
                ", marque='" + marque + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", nbrEnStock=" + nbrEnStock +
                '}';
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrix(float prix) {
        this.prix = prix;
    }
    public void setNbrEnStock(int nbrEnStock) {
        this.nbrEnStock = nbrEnStock;
    }
    public String getNom() {
        return nom;
    }
    public String getMarque() {
        return marque;
    }
    public String getDescription() {
        return description;
    }
    public int getNbrEnStock() {
        return nbrEnStock;
    }
    public float getPrix() {
        return prix;
    }
}
