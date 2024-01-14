package ma.enset.tpjdbc.Dao;

import ma.enset.tpjdbc.Dao.entities.Produit;

import java.sql.SQLException;
import java.util.List;

public class DaoTest {
    public static void main(String[] args) throws SQLException {
        ProduitDao pDao=new ProduitDaoImpl();
        Produit p1=new Produit();
/*
        p2.setNom("MAC");
        p2.setDescription("description 2");
        p2.setPrix(20000);
        p2.setQuantite(4);
        pDao.save(p2);
          */
        Produit p2=pDao.findByID(2);
        p2.setPrix(22000);
        p2.setQuantite(9);
        pDao.update(p2);
        List<Produit> produits=pDao.findAll();
        for (Produit p:produits) {
            System.out.println(p.getId()+" "+p.getNom()+" "+p.getDescription()+" "+p.getPrix()+" "+p.getQuantite());
        }
    }
}
