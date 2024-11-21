/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import entities.User;
import services.UserController;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nl.captcha.Captcha;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class LoginFXMLController implements Initializable {

   

    @FXML
    private TextField EmailL;

    @FXML
    private TextField PasswordL;

    @FXML
    private Button bouttonLogin;
    
    @FXML
    private ImageView cap;
            @FXML
    private TextField code;
            
            private    int passwordAttempts = 0;
    @FXML
    private Label emaillabel;
    @FXML
    private Button switchregisterP;
    @FXML
    private Label passwordlabel;
    @FXML
    private Button forgetpasswordP;
    @FXML
    private Label registerLabel;
    @FXML
    private Label labeluser;
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserController UserController  = new UserController();
  captcha =  setCaptcha();
    
    }
          
       

       
   
    @FXML
    public void login(ActionEvent event) {
   /* String email = EmailL.getText();
    String password = PasswordL.getText();
    
      //if (email.equals("seifeddin.manai@esprit.tn") && password.equals("wxcvbn")) 
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ADO-DOC :: Success Message");
        alert.setHeaderText(null);
        alert.setContentText("Bienvenue Admin");
        alert.showAndWait();
*/
  
     /*  try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("UserFXML.fxml"));
             Parent root = loader.load();
             Scene scene = new Scene(root);
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
             stage.toFront();
         } catch (IOException ex) {
             System.out.println(ex.getMessage());
         }*/
       String email = EmailL.getText();
        String password = PasswordL.getText();

        // create an instance of the UserService class
        UserController UserController  = new UserController();
        // call the authenticate method on the UserService instance
        User loggedInUser = UserController.authenticate(email, password);

        if(captcha.isCorrect(code.getText())){
        
        
        if (loggedInUser != null) {
            String roles = loggedInUser.getRoles();
            if (roles.contains("[\"Admin\"]")) {
                redirectToDashboard();
            } else if (roles.contains("[\"client\"]")){
                redirectToProfile();
            }}
         else{
              captcha =  setCaptcha();
            code.setText("");
              }
        } else {
displayErrorMessage();
            
            // Bloquer l'interface si l'utilisateur saisit un mot de passe incorrect 3 fois
            passwordAttempts++;
            if (passwordAttempts == 3) {
                bouttonLogin.setDisable(true);
                PasswordL.setDisable(true);
                EmailL.setDisable(true);
                switchregisterP.setDisable(true);
                 forgetpasswordP.setDisable(true);
                 passwordlabel.setDisable(true);
                 cap.setDisable(true);
                 code.setDisable(true);
                 emaillabel.setDisable(true);
                 labeluser.setDisable(true);
                 registerLabel.setDisable(true);
                 
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                          bouttonLogin.setDisable(false);
                PasswordL.setDisable(false);
                EmailL.setDisable(false);
                switchregisterP.setDisable(false);
                                forgetpasswordP.setDisable(false);
                 emaillabel.setDisable(false);
                 passwordlabel.setDisable(false);
                       labeluser.setDisable(false);
                             registerLabel.setDisable(false);

                 cap.setDisable(false);
                 code.setDisable(false);
                            passwordAttempts = 0;
                        });
                    }
        
                }, 60000); // 1 minute en millisecondes
            
            Notifications.create()
                    .title("Notification")
                    .text("L'interface est bloqué 1 min  !")
                    .position(Pos.BOTTOM_RIGHT)
                    .showInformation();
            }
            }
            
        }

    
    
    @FXML
     public void switchRegister(ActionEvent event) {
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
             FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterFXML.fxml"));
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
     public void btnForgetPassword(ActionEvent event) throws IOException {
        // Code pour effectuer la déconnexion
    // Fermer la session actuelle
    // Rediriger l'utilisateur vers la page de connexion

    // Créer une instance de la page de connexion
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ForgotPassword.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);

    // Obtenir la référence de la scène actuelle
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    
    // Définir la nouvelle scène
    stage.setScene(scene);
    stage.show();
    }
    
    
    
    
     
   
      private void redirectToDashboard() {
        try { 
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("acceuilback.fxml"));
            Parent root = loader.load();
            
         
            Scene scene = new Scene(root);

            // Get the current stage
            Stage stage = (Stage) EmailL.getScene().getWindow();

            // Set the new scene to the current stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
      
      
      public Captcha setCaptcha() {
        Captcha captchaV = new Captcha.Builder(250, 150)
                .addText()
                .addBackground()
                .addNoise()
               // .gimp()
                .addBorder()
                .build();

        System.out.println(captchaV.getImage());
        Image image = SwingFXUtils.toFXImage(captchaV.getImage(), null);

        cap.setImage(image);

        return captchaV;
    }
    Captcha captcha;
      
    
       private void redirectToProfile() {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("acceuilfront.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Get the current stage
            Stage stage = (Stage) EmailL.getScene().getWindow();

            // Set the new scene to the current stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        private void displayErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "plz verify input", ButtonType.OK);
        alert.showAndWait();
    } 
    
}
