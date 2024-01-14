package ma.enset.tpjdbc.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.tpjdbc.Dao.ProduitDaoImpl;
import ma.enset.tpjdbc.Dao.entities.Produit;
import ma.enset.tpjdbc.Service.ProduitService;
import ma.enset.tpjdbc.Service.ProduitServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class ProduitController2 implements Initializable {
    @FXML
    private TextField textNom;
    @FXML
    private TextField textDescription;
    @FXML
    private TextField textPrix;
    @FXML
    private TextField textQuantite;
    @FXML
    private TextField textSearch;
    @FXML
    private TableView<Produit> produitTableView;
    @FXML
    private TableColumn <Produit,Integer> colID;
    @FXML
    private TableColumn <Produit,String> colNom;
    @FXML
    private TableColumn <Produit,String> colDescription;
    @FXML
    private TableColumn <Produit,Float> colPrix;
    @FXML
    private TableColumn <Produit,Integer> colQuantite;
    ObservableList<Produit> produitList= FXCollections.observableArrayList();
    private ProduitService produitService=new ProduitServiceImpl(new ProduitDaoImpl());
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        produitTableView.setItems(produitList);
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        loadProduits();
        textSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                produitList.clear();
                System.out.println(t1);
                produitList.addAll(produitService.getProduitParMc(t1));
            }
        });
        produitTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Produit>() {
            @Override
            public void changed(ObservableValue<? extends Produit> observableValue, Produit produit, Produit t1) {
                if(t1!=null){
                    Produit p=new Produit();

                }
            }
        });
    }
    public void addProduit(){
        Produit p=new Produit();
        p.setNom(textNom.getText());
        p.setDescription(textDescription.getText());
        p.setPrix(Float.parseFloat(textPrix.getText()));
        p.setQuantite(Integer.parseInt(textQuantite.getText()));
        produitService.addProduit(p);
        loadProduits();
    }
    public void deletProduit(){
        Produit p=produitTableView.getSelectionModel().getSelectedItem();
        produitService.deletProduoit(p);
        loadProduits();
    }
    public void updateProduit(){
        Produit p=produitTableView.getSelectionModel().getSelectedItem();
        produitService.updateProduit(p);
        loadProduits();
    }
    private void loadProduits(){
        produitList.clear();
        produitList.addAll(produitService.getALL());
    }
}