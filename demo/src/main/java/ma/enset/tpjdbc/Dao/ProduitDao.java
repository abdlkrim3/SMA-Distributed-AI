package ma.enset.tpjdbc.Dao;

import ma.enset.tpjdbc.Dao.entities.Produit;

import java.util.List;

public interface ProduitDao extends Dao<Produit>{
    List<Produit> findProduitByMc(String mc) ;

}
