package ex3;

public class Commande {
    private int reference;
    Client clt1=new Client();
    private String dateCommande;
    private  String etatCommande;
    public Commande() {
    }
    public Commande(int reference, Client clt1, String dateCommande, String etatCommande) {
        this.reference = reference;
        this.clt1 = clt1;
        this.dateCommande = dateCommande;
        this.etatCommande = etatCommande;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }
    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }
    public void setEtatCommande(String etatCommande) {
        this.etatCommande = etatCommande;
    }
    public int getReference() {
        return reference;
    }
    public String getDateCommande() {
        return dateCommande;
    }
    public String getEtatCommande() {
        return etatCommande;
    }
    @Override
    public String toString() {
        return "reference=" + reference +"\n"+
                " clt1=" + clt1 +"\n"+
                " dateCommande='" + dateCommande + '\'' +"\n"+
                " etatCommande='" + etatCommande + '\'' +"\n";
    }
}
