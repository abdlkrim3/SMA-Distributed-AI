package ex1;

public class Application {
    public static void main(String[] args) {
        Personne adherent1 =new Adherent("adher1","pren1",
                                "adher1@gmail.co,","0623456789",
                                22,123455);
        Livre livre1=new Livre();
        livre1.setISBN(234);
        livre1.setTitre("Java");
        livre1.setAuteur(new Auteur("Aut1","AutPreno,1",
                                "aut1@gmail.com","0645385645",
                                45,345567));
        adherent1.afficher();
        livre1.afficher();
    }
}
