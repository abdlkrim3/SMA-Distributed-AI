package ex2;

public abstract class Employe {
    private String nom;
    private String prenom;
    private String email;
    private String tele;
    protected float salaire;
    public Employe(){
        this.nom="";
        this.prenom="";
        this.email="";
        this.tele="";
        this.salaire=0;
    }

    public Employe(String nom, String prenom, String email, String tele, int salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tele = tele;
        this.salaire = salaire;
    }

    public void afficher() {
        System.out.println(" Nom : " + nom + '\n' +
                " Prénom : " + prenom + '\n' +
                " Email : " + email + '\n' +
                " Telephone : " + tele + '\n' );
    }

    public abstract float calculerSalaire();
}
