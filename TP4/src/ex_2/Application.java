package ex_2;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        IProduitMetier produit= new MetierProduitImpl();
        Scanner scan=new Scanner(System.in);
        int choix=0;
        while (choix!=6){
            System.out.println("******************** Menu ********************\n"+
                            "1. Afficher la liste des produits."+"\n"+
                            "2. Rechercher un produit par son id."+"\n"+
                            "3. Ajouter un nouveau produit dans la liste."+"\n"+
                            "4. Supprimer un produit par id."+"\n"+
                            "5. Sauvegarder les produits."+"\n"+
                            "6. Quitter ce programme."+"\n"+
                    "****************************************"+"\n"+
                    "Votre choix! : ");
            choix= scan.nextInt();
            switch (choix){
                case 1:
                    System.out.println(produit.getAll());break;
                case 2:
                    System.out.println("Saisir l'id du produit à chercher : ");
                    int id=scan.nextInt();
                    System.out.println(produit.findById(id));
                    break;

                case 3:
                    Produit produit1= new Produit();
                    produit.add(produit1);
                    System.out.println("Entrer l'id du produit : ");
                    produit1.setId(scan.nextInt());
                    System.out.println("Entrer le nom du produit : ");
                    scan.nextLine();
                    produit1.setNom(scan.nextLine());
                    System.out.println("Entrer le prix du produit : ");
                    produit1.setPrix(scan.nextFloat());
                    System.out.println("Entrer la description du produit : ");
                    scan.nextLine();
                    produit1.setDescription(scan.nextLine());
                    System.out.println("Entrer la marque du produit : ");
                    produit1.setMarque(scan.nextLine());
                    System.out.println("Entrer le nombre  de ce produit en stock : ");
                    produit1.setNbrEnStock(scan.nextInt());
                    break;

                case 4:
                    System.out.println("Entrer l'id du produit à supprimer :");
                    produit.delete(scan.nextInt());
                    break;

                case 5:
                    produit.saveAll();
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
