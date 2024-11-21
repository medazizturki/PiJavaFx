/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import services.PasswordEncryption;
import services.UserController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import entities.User;
import services.BCrypt;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class RegisterFXMLController implements Initializable {

    /**
     * Initializes the controller class.

*/
    
    ObservableList<String> roley =FXCollections.observableArrayList("[\"Admin\"]","[\"client\"]");
     UserController ps = new UserController();
          @FXML
    private Button btndelete;

    @FXML
    private Button btninsert;

    @FXML
    private TextField emailu;

    @FXML
    private TextField nomu;

    @FXML
    private TextField passwordu;

    @FXML
    private ComboBox<String> roleu;

    @FXML
    private TextField telephoneu;

     UserController su = new UserController();
    
    
    
    
      public void switchLogin(ActionEvent event) {
   /* String email = EmailL.getText();
    String password = PasswordL.getText();
    
      //if (email.equals("seifeddin.manai@esprit.tn") && password.equals("wxcvbn")) 
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ADO-DOC :: Success Message");
        alert.setHeaderText(null);
        alert.setContentText("Bienvenue Admin");
        alert.showAndWait();
*/
  
       try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
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
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         roleu.setItems(roley);
    }    
    
    
    
    
        @FXML
    void singup(ActionEvent event) throws Exception {
/*
         if(nomu.getText().isEmpty()  || emailu.getText().isEmpty() || passwordu.getText().isEmpty() ||telephoneu.getText().isEmpty() || (roleu.getSelectionModel().getSelectedIndex())==-1)
        {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Add Error");
alert.setContentText("all fields must  not be empty !");

alert.showAndWait();
        }*/


        String email = emailu.getText();
        //String password = PasswordEncryption.encrypt(passwordu.getText());
        String pass = BCrypt.gensalt();
         String password = (passwordu.getText());
         String hashedPassword = BCrypt.hashpw(password, pass);
        String telephone = telephoneu.getText();
        String nom = nomu.getText();



 if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid email address", ButtonType.OK);
            alert.showAndWait();
            return;
        }
  if (password.length() < 8) {
        // Show an error message and return
        Alert alert = new Alert(Alert.AlertType.ERROR, "Password must be at least 8 characters long", ButtonType.OK);
        alert.showAndWait();
        return;
    }
         if(nomu.getText().isEmpty()  || emailu.getText().isEmpty() || passwordu.getText().isEmpty() ||telephoneu.getText().isEmpty() || (roleu.getSelectionModel().getSelectedIndex())==-1)
         {        // Show an error message and return
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields", ButtonType.OK);
        alert.showAndWait();
        
    }
            if (password.length() < 8) {
            // Show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR, "Password must be at least 8 characters long.", ButtonType.OK);
            alert.showAndWait();
            return;
        }
              if (telephone.length() < 8) {
        // Show an error message and return
        Alert alert = new Alert(Alert.AlertType.ERROR, "telephone must be at least 8 characters long", ButtonType.OK);
        alert.showAndWait();
        return;
        
    }
                       if (telephone.length() > 8) {
        // Show an error message and return
        Alert alert = new Alert(Alert.AlertType.ERROR, "telephone must be at least 8 characters long", ButtonType.OK);
        alert.showAndWait();
        return;
        
    }
                      if (su.emailExist(email)) {
        // Show an error message and return
        Alert alert = new Alert(Alert.AlertType.ERROR, "Email already exists", ButtonType.OK);
        alert.showAndWait();
        return;
    }
       
        
            //float x = parseFloat(prixp.getText());
            //User u = new User(nomu.getText(),emailu.getText(),"xxxx",passwordu.getText(),Integer.parseInt(telephoneu.getText()),1);
            User u = new User(emailu.getText(), roley.get(roleu.getSelectionModel().getSelectedIndex()),hashedPassword, Integer.parseInt(telephoneu.getText()), nomu.getText());
            System.out.println(u);
            ps.ajouterUser(u);
    try {
         Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/LoginFXML.fxml"));

                Scene scene = new Scene(page1);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);

                stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
        
        }
        
    }
    
    

