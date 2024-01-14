package ex1;

import java.util.Scanner;

public class Application2 {
    public static void main(String[] args) {
        IMetier client= new MetierClientImpl();
        Scanner scan=new Scanner(System.in);
        int choix=0;
        while (choix!=6){
            System.out.println("******************** Menu ********************\n"+
                    "1. Afficher la liste des clients."+"\n"+
                    "2. Rechercher un clientt par son id."+"\n"+
                    "3. Ajouter un nouveau client dans la liste."+"\n"+
                    "4. Supprimer un client par id."+"\n"+
                    "5. Sauvegarder les clients."+"\n"+
                    "6. Quitter ce programme."+"\n"+
                    "****************************************"+"\n"+
                    "Votre choix! : ");
            choix= scan.nextInt();

            switch (choix){
                case 1:
                    System.out.println(client.getAll());break;
                case 2:
                    System.out.println("Saisir l'id du produit à chercher : ");
                    int id=scan.nextInt();
                    System.out.println(client.findById(id));
                    break;

                case 3:
                    Client client1= new Client();
                    client.add(client1);
                    System.out.println("Entrer l'id du client : ");
                    client1.setId(scan.nextInt());
                    System.out.println("Entrer le nom du client : ");
                    scan.nextLine();
                    client1.setNom(scan.nextLine());
                    System.out.println("Entrer le prenom du client : ");
                    client1.setPrenom(scan.nextLine());
                    System.out.println("Entrer l'adresse du client : ");
                    client1.setAddres(scan.nextLine ());
                    System.out.println("Entrer le nemuro de telephone du client : ");
                    client1.setTel(scan.nextLine());
                    System.out.println("Entrer l'email   de ce client : ");
                    client1.setEmail(scan.nextLine());
                    break;

                case 4:
                    System.out.println("Entrer l'id du client à supprimer :");
                    client.delete(scan.nextInt());
                    break;

                case 5:
                    client.saveAll();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Mauvais choix !!");
                    break;
            }

        }
    }
}
