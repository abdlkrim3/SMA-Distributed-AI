package ex2;

public class Manager extends Employe{
    private String service;
    public Manager(String service) {
        this.service = service;
    }
    public Manager(String nom, String prenom, String email, String tele, int salaire, String service) {
        super(nom, prenom, email, tele, salaire);
        this.service = service;
    }
    public void afficher(){
        super.afficher();
        System.out.println("Service : "+this.service);
    }
    public float calculerSalaire(){
        return (this.salaire+=this.salaire*0.2);
    }
    public void setService(String service) {
        this.service = service;
    }
    public String getService() {
        return service;
    }
}
