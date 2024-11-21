/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author octanet
 */
public class AcceuilfrontController implements Initializable {

    @FXML
    private Button Evenement;
    @FXML
    private Button Rendezvous;
    @FXML
    private Button Profil;
    @FXML
    private Button Ressource;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Evenementb;
    @FXML
    private Button Rendezvousb;
    @FXML
    private Button Ressourceb;
    @FXML
    private Button Offreb;
    @FXML
    private Button Reclamation1;
    @FXML
    private Button Reclamation11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
             FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/rendezfront.fxml"));
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
    
   @FXML
    private void profil(ActionEvent event) {
        
                        
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
}
