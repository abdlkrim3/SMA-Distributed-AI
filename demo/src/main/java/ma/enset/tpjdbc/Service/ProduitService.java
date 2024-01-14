package ma.enset.tpjdbc.Service;

import ma.enset.tpjdbc.Dao.entities.Produit;

import java.util.List;

public interface ProduitService {
    void addProduit(Produit p);
    void deletProduoit(Produit p);
    List<Produit> getALL();
    List<Produit> getProduitParMc(String mc);
    void updateProduit(Produit p);
}
