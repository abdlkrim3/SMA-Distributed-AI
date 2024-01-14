package ma.enset.tpjdbc.Service;

import ma.enset.tpjdbc.Dao.ProduitDao;
import ma.enset.tpjdbc.Dao.entities.Produit;

import java.util.List;

public class ProduitServiceImpl implements ProduitService{
    ProduitDao pdao;
    public ProduitServiceImpl(ProduitDao pdao){
        this.pdao=pdao;
    }
    @Override
    public void addProduit(Produit p) {
        pdao.save(p);
    }

    @Override
    public void deletProduoit(Produit p) {
        pdao.delete(p);

    }

    @Override
    public List<Produit> getALL() {
        return pdao.findAll();
    }

    @Override
    public List<Produit> getProduitParMc(String mc) {
        return pdao.findProduitByMc(mc);
    }

    @Override
    public void updateProduit(Produit p) {
        pdao.update(p);
    }
}
