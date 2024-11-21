/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Controller.EmailSender;
import services.UserController ;

/**
 * FXML Controller class
 *
 * @author WIN 10 PRO
 */
public class ForgotPasswordController implements Initializable {
    public static int code;
    public static String EmailReset ; 
    @FXML
    private TextField tfMailO;
    @FXML
    private Button BtnCode;
    
    ControleSaisieTextFields cs;

    public static int generateVerificationCode() {
        // Générer un code de vérification aléatoire à 6 chiffres
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCode(ActionEvent event) {
        code = generateVerificationCode();
        Alert A = new Alert(Alert.AlertType.WARNING);
        UserController su = new UserController();

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        boolean verifMail = tfMailO.getText().matches(emailRegex);

        if (!tfMailO.getText().equals("") && verifMail) {
            if (su.ChercherMail(tfMailO.getText()) == 1) {
                EmailReset = tfMailO.getText();
                EmailSender.sendEmail("mohamedsaleh.ghattas@esprit.tn", "saleh28042001saleh", tfMailO.getText(), "Verification code", "Votre code est : " + code);

                try {

                    Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/VerifCode.fxml"));

                    Scene scene = new Scene(page1);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);

                    stage.show();

                } catch (IOException ex) {

                    System.out.println(ex.getMessage());

                }

            } else {
                A.setContentText("pas de compte lié avec cette adresse ! ");
                A.show();
            }
        } else {
            A.setContentText("Veuillez saisir une adresse mail valide ! ");
            A.show();
        }
    }

    @FXML
    private void btnAnnulerForgot(ActionEvent event) {
        try {

                    Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/LoginFXML.fxml"));

                    Scene scene = new Scene(page1);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);

                    stage.show();

                } catch (IOException ex) {

                    System.out.println(ex.getMessage());

                }
    }

    }
    

