package ex2;

public class Application {
    public static void main(String[] args) {
        Employe ingenieur1=new Ingenieur("ing1","prenom1",
                                    "ing1@gmail.com","0624356464",
                                    9000,"DEVOPS");
        Employe manager1=new Manager("manager1","prenom1",
                                "manager1@gmail.com","0624653864",
                                15000,"WEB");
        System.out.println("********* Information de l'ingénieur ***********");
        ingenieur1.afficher();
        System.out.println("********* Salaire *********");
        System.out.println(ingenieur1.calculerSalaire());
        System.out.println("********* Information du manager ***********");
        manager1.afficher();
        System.out.println("********* Salaire *********");
        System.out.println(manager1.calculerSalaire());

    }
}
