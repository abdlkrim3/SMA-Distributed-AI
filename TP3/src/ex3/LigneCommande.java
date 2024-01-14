package ex3;

public class LigneCommande {
    private int quantitie;
    Commande cmd1=new Commande();
    Ordinateur ord1=new Ordinateur();
    public LigneCommande() {
    }
    public LigneCommande(int quantitie, Commande cmd1, Ordinateur ord1) {
        this.quantitie = quantitie;
        this.cmd1 = cmd1;
        this.ord1 = ord1;
    }

    public void setQuantitie(int quantitie) {
        this.quantitie = quantitie;
    }
    public int getQuantitie() {
        return quantitie;
    }
    @Override
    public String toString() {
        return "quantitie=" + quantitie +
                ", cmd1=" + cmd1 +
                ", ord1=" + ord1 +
                '}';
    }
}
