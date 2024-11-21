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
public class AcceuilbackController implements Initializable {

    @FXML
    private Button Evenementb;
    @FXML
    private Button Rendezvousb;
    @FXML
    private Button Reservationb;
    @FXML
    private Button Ressourceb;
    @FXML
    private Button Offreb;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Reponse;
    @FXML
    private Button Reponse1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Evenementb(ActionEvent event) {
        
                        
                                try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterEvenement.fxml"));
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
    private void Rendezvousb(ActionEvent event) {
    
                            
                                try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
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
    private void Reservationb(ActionEvent event) {
                
                        
                                try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation.fxml"));
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
    private void Ressourceb(ActionEvent event) {
                                      
                                try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("ressource.fxml"));
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
    private void Offreb(ActionEvent event) {
                                
                                try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("offreFXML.fxml"));
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
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_Reclamation.fxml"));
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
    private void Reponse(ActionEvent event) {
                 try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_Reponse.fxml"));
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
    private void user(ActionEvent event) {
                 try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("UserFXML.fxml"));
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
