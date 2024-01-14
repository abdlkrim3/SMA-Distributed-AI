package ma.enset.tpjdbc.Dao;

import ma.enset.tpjdbc.Dao.entities.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDaoImpl implements ProduitDao {
    @Override
    public List<Produit> findAll() {
        Connection connection=SingletonConnexionDB.getConnaction();
        List<Produit>produits=new ArrayList<>();
        try{
            PreparedStatement stm=connection.prepareStatement("select * from PRODUITS");
        ResultSet rs=stm.executeQuery();
        while(rs.next()){
            Produit p=new Produit();
            p.setId(rs.getInt("ID"));
            p.setNom(rs.getString("NOM"));
            p.setDescription(rs.getString("DESCRIPTION"));
            p.setPrix(rs.getFloat("PRIX"));
            p.setQuantite(rs.getInt("QUANTITE"));
            produits.add(p);
        }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return produits;
    }

    @Override
    public Produit findByID(int id){
        Connection connection=SingletonConnexionDB.getConnaction();
        Produit p=new Produit();
        try{
        PreparedStatement stm=connection.prepareStatement("select * from PRODUITS where ID=?");
        stm.setInt(1,id);
        ResultSet rs=stm.executeQuery();
        if(rs.next()){
            p.setId(rs.getInt("ID"));
            p.setNom(rs.getString("NOM"));
            p.setDescription(rs.getString("DESCRIPTION"));
            p.setPrix(rs.getFloat("PRIX"));
            p.setQuantite(rs.getInt("QUANTITE"));
        }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public Produit save(Produit o){
                Connection connection=SingletonConnexionDB.getConnaction();
        try{
                PreparedStatement pstm=connection.prepareStatement("insert into PRODUITS(NOM,DESCRIPTION,PRIX,QUANTITE)"+" values(?,?,?,?)");
                pstm.setString(1,o.getNom());
                pstm.setString(2,o.getDescription());
                pstm.setFloat(3,o.getPrix());
                pstm.setInt(4,o.getQuantite());
                pstm.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            }
                return o;

        }

    @Override
    public boolean delete(Produit o){
            Connection connection=SingletonConnexionDB.getConnaction();
        try{
            PreparedStatement pstm=connection.prepareStatement("delete  from PRODUITS where ID=?");
            pstm.setInt(1,o.getId());
            pstm.executeUpdate();
        }catch (SQLException e){
            return false;
        }
        return true;
    }

    @Override
    public Produit update(Produit o){
                Connection connection=SingletonConnexionDB.getConnaction();
        try{
                PreparedStatement pstm=connection.prepareStatement("update PRODUITS set NOM=?,DESCRIPTION=?,PRIX=?,QUANTITE=? where ID=?");
                pstm.setString(1,o.getNom());
                pstm.setString(2,o.getDescription());
                pstm.setFloat(3,o.getPrix());
                pstm.setInt(4,o.getQuantite());
                pstm.setInt(5,o.getId());
                pstm.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            }
                return o;

        }

    @Override
    public List<Produit> findProduitByMc(String mc){
                Connection connection=SingletonConnexionDB.getConnaction();
                List<Produit>produits=new ArrayList<>();
                try{
                PreparedStatement pstm=connection.prepareStatement("select * from PRODUITS where NOM like ?");
                pstm.setString(1,"%"+mc+"%");
                ResultSet rs=pstm.executeQuery();
                while(rs.next()){
                    Produit p=new Produit();
                    p.setId(rs.getInt("ID"));
                    p.setNom(rs.getString("NOM"));
                    p.setDescription(rs.getString("DESCRIPTION"));
                    p.setPrix(rs.getFloat("PRIX"));
                    p.setQuantite(rs.getInt("QUANTITE"));
                    produits.add(p);
                }
                }catch(SQLException e){
                    e.printStackTrace();
                }
                return produits;

        }
}
