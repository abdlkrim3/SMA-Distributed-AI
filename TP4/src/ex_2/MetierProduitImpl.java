package ex_2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MetierProduitImpl implements IProduitMetier{
    List <Produit> listProd=new ArrayList<>();
    private String save="produits.dat";
    @Override
    public Produit add(Produit prod){
        listProd.add(prod);
        return null;
    }

    @Override
    public List<Produit> getAll() {
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
