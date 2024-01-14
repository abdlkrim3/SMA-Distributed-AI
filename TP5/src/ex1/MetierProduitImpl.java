package ex1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MetierProduitImpl implements IMetier<Produit> {
    private String save="produits.dat";
    List <Produit> listProd=new ArrayList<>();
    @Override
    public Produit add(Produit prod){
        listProd.add(prod);
        return null;
    }

    @Override
    public List<Produit> getAll() {
        File f1=new File(save);
        FileInputStream fis= null;
        try {
            fis = new FileInputStream(f1);
            ObjectInputStream ois=new ObjectInputStream(fis);
            Object e;
            while((e=ois.readObject())!=null){
                System.out.println(e);;
            }
            ois.close();
        } catch (IOException | ClassNotFoundException e) {

        }
        return listProd;
    }

    @Override
    public Produit findById(long id) {
        for (Produit prod:listProd) {
            if(prod.getId()==id) System.out.println(prod);
        }
        return null;
    }
    @Override
    public void delete(long id) {
        for (Produit prod:listProd) {
            if(prod.getId()==id) listProd.remove(prod);
        }

    }

    @Override
    public void saveAll() {
        File f1=new File(save);
        FileOutputStream fos= null;
        try {
            fos = new FileOutputStream(f1);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(listProd);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
