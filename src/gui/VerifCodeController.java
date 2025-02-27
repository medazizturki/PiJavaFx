/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WIN 10 PRO
 */
public class VerifCodeController implements Initializable {

    @FXML
    private TextField tfCode;
    @FXML
    private Button BtnConfirmerCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BtnConfirmerCode(ActionEvent event) {
        if (Integer.parseInt(tfCode.getText()) == ForgotPasswordController.code)
        {
              try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/ResetPassword.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
        }
        else 
        {
            Alert A = new Alert(Alert.AlertType.WARNING);
            A.setContentText("Code erroné ! ");
            A.show();
            
        }
    }

    @FXML
    private void btnAnnulerCode(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/ForgotPassword.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
    }
    }
    

