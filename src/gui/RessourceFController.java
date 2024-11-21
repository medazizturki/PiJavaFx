/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Ressource;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.RessourceService;

/**
 * FXML Controller class
 *
 * @author octanet
 */
public class RessourceFController implements Initializable {

    @FXML
    private TableView<Ressource> afficher_tab;
    @FXML
    private TableColumn<Ressource,String> fx_id;
    @FXML
    private TableColumn<Ressource,String> fx_type;
    @FXML
    private TableColumn<Ressource,String> fx_dispo;
    @FXML
    private TableColumn<Ressource,String> fx_nom;
    private RessourceService RessourceService = new RessourceService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          // TODO       idColumn.setCellValueFactory(new PropertyValueFactory<>("id_cat_artic"));
        fx_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        fx_type.setCellValueFactory(new PropertyValueFactory<>("type_ressource"));
        fx_dispo.setCellValueFactory(new PropertyValueFactory<>("disponibilite_ressource"));
        
        fx_nom.setCellValueFactory(new PropertyValueFactory<>("nom_ressource"));
            // récupère les données des utilisateurs depuis la base de données
            List<Ressource> catarticleList = RessourceService.AfficherRessource();


        // affiche les données dans le tableau
        afficher_tab.getItems().setAll(catarticleList);
        
        // TODO
        // TODO
    }    
    @FXML
    private void Evenement(ActionEvent event) {
                
                                try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherEvenement.fxml"));
             Parent root = loader.load();
             Scene scene = new Scene(root);
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
             stage.toFront();
         } catch (IOException ex) {
             System.out.println(ex.getMessage());
         }
    }

    @FXML
    private void Rendezvous(ActionEvent event) {
        
                        
                                try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("front.fxml"));
             Parent root = loader.load();
             Scene scene = new Scene(root);
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
             stage.toFront();
         } catch (IOException ex) {
             System.out.println(ex.getMessage());
         }

    }

    @FXML
    private void Profil(ActionEvent event) {
                        
                                try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("profilFXML.fxml"));
             Parent root = loader.load();
             Scene scene = new Scene(root);
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
             stage.toFront();
         } catch (IOException ex) {
             System.out.println(ex.getMessage());
         }
    }

    @FXML
    private void Ressource(ActionEvent event) {
                      
                        
                                try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("ressourceF.fxml"));
             Parent root = loader.load();
             Scene scene = new Scene(root);
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
             stage.toFront();
         } catch (IOException ex) {
             System.out.println(ex.getMessage());
         }
    }

    @FXML
    private void Reclamation(ActionEvent event) {
             try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Fronteya.fxml"));
             Parent root = loader.load();
             Scene scene = new Scene(root);
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
             stage.toFront();
         } catch (IOException ex) {
             System.out.println(ex.getMessage());
         }
    }
    @FXML
    private void offre(ActionEvent event) {
             try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("offreFrontFXML.fxml"));
             Parent root = loader.load();
             Scene scene = new Scene(root);
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
             stage.toFront();
         } catch (IOException ex) {
             System.out.println(ex.getMessage());
         }
    }
}
