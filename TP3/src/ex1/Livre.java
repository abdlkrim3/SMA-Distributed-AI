package ex1;

public class Livre {
    private int ISBN;
    private String titre;
    private Auteur auteur;
    public void afficher(){
        System.out.println("********** Information du livre **********");
        auteur.afficher();
        System.out.println("ISBN : ");
        System.out.println(this.getISBN());
        System.out.println("Titre : "+this.titre);
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }
    public int getISBN() {
        return ISBN;
    }
    public String getTitre() {
        return "titre : "+titre;
    }
    public Auteur getAuteur() {
        return auteur;
    }
}
