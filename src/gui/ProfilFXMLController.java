/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import services.UserController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import entities.User;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class ProfilFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private TextField full_namet;
 
    @FXML
    private TextField emailt;
    @FXML
    private TextField telephonet;
    UserController su = new UserController();
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
    private Button Reclamation1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          int userId = MyDB.getUserId();
        User user = new UserController().getUserById(userId);
        if (user != null) {
        full_namet.setText(user.getName());
        emailt.setText(user.getEmail());
        telephonet.setText(Integer.toString(user.getTelephone()));     
    }
        
        
    }    
    

    @FXML
    private void edit(ActionEvent event) {
        
        int userId = MyDB.getUserId();
        User user = new User();
        user.setId(userId);
        user.setEmail(emailt.getText());
       
        int telephoneNumber = Integer.parseInt(telephonet.getText());
        user.setTelephone(telephoneNumber); 
      
        user.setRoles("");
        user.setName(full_namet.getText());
        su.modifierUF(user);
    }


    
   @FXML
    void retour(ActionEvent event) {
 try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("acceuilfront.fxml"));
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
