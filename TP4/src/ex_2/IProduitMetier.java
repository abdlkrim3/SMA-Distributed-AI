package ex_2;

import java.util.List;

public interface IProduitMetier {
    public Produit add(Produit p);
    public List<Produit> getAll();
    public Produit findById(long id);
    public void delete(long id);
    public void saveAll();

}
