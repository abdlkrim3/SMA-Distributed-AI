package ex3;

public class Application {
    public static void main(String[] args) {

        Ordinateur ord1=new Ordinateur("ord1","hp",
                            "description1",3000,6);
        Ordinateur ord2=new Ordinateur("ord2","del",
                            "description2",3500,4);
        Ordinateur ord3=new Ordinateur("ord3","lenovo",
                            "description3",2500,8);
        Catégorie ct1=new Catégorie("ordinateur","descriptionOrdinateur");
        ct1.ajouterOrdinateur(ord1);
        ct1.ajouterOrdinateur(ord2);
        ct1.ajouterOrdinateur(ord3);
        Client clt1=new Client("client1","prenom1","adrese1",
                            "0654328765","client1prenom1@gmail.com","mohammedia");
        Commande cmd1=new Commande(123,clt1,"26-11-2022",
                                    "encour de traitement");
        LigneCommande lcmd1=new LigneCommande(2,cmd1,ord1);
        LigneCommande lcmd2=new LigneCommande(1,cmd1,ord2);
        LigneCommande lcmd3=new LigneCommande(4,cmd1,ord3);
        System.out.println(cmd1.toString());


    }
}
