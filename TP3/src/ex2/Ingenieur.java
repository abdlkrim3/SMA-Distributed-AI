package ex2;

public class Ingenieur extends Employe {
    private String specialite;

    public Ingenieur(String nom, String prenom, String email, String tele, int salaire, String specialite) {
        super(nom, prenom, email, tele, salaire);
        this.specialite = specialite;
    }

    public Ingenieur(String specialite) {
        this.specialite = specialite;
    }
    public void afficher(){
        super.afficher();
        System.out.println("Spécialité : "+this.specialite);
    }
    public float calculerSalaire(){
        return (this.salaire+=this.salaire*0.15);
    }
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
    public String getSpecialite() {
        return specialite;
    }
}
