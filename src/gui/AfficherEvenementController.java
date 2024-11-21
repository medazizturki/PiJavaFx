/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import services.EvenementService;



import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficherEvenementController implements Initializable {

    
    private int currentPage = 0;
    private static final int ROWS_PER_PAGE = 2;


    @FXML
    private GridPane gridEvent;

    EvenementService Ev=new EvenementService();
    @FXML
    private TextField chercherEventField;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          // TODO
    afficherEvenement();

    
   try {
        List<evenement> evenements = Ev.recupererEvenement();
        int pageCount = (int) Math.ceil(evenements.size() / (double) ROWS_PER_PAGE);
        Pagination pagination = createPagination(pageCount);

        BorderPane root = new BorderPane();
        root.setTop(new HBox(new Label("Page: "), pagination));

        GridPane gridEvent = new GridPane(); // assuming this is defined elsewhere
        root.setCenter(gridEvent);
        BorderPane.setMargin(gridEvent, new Insets(10));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        afficherEvenement(); // assuming this method relies on the list of events
        stage.show();
    } catch (SQLException ex) {
        ex.printStackTrace(); // or show a message to the user
    }
        
               
    }    


    private void ajouterEvenement(ActionEvent event) {
      try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("ajouterEvenement.fxml"));
            chercherEventField.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
/*
    public void afficherEvenement(){
         try {
            List<evenement> evenements = Ev.recupererEvenement();
            gridEvent.getChildren().clear();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("evenement.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                EvenementController controller = loader.getController();
                controller.setEvennement(evenements.get(i));
                controller.setIdevent(evenements.get(i).getId());
                gridEvent.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
                if(evenements.get(i).getNbrparticipants()<=0)
                {
                 // Ev.supprimerEvenement(evenements.get(i));
                controller.arreterEvent();
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    
    */
    public void afficherEvenement() {
    try {
        List<evenement> evenements = Ev.recupererEvenement();
        gridEvent.getChildren().clear();
        int row = 0;
        int column = 0;
        int ROWS_PER_PAGE = 2;
        int startIndex = currentPage * ROWS_PER_PAGE;
        int endIndex = Math.min(startIndex + ROWS_PER_PAGE, evenements.size());
        for (int i = startIndex; i < endIndex; i++) {
            //chargement dynamique d'une interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("evenement.fxml"));
            AnchorPane pane = loader.load();
            
            //passage de parametres
            EvenementController controller = loader.getController();
            controller.setEvennement(evenements.get(i));
            controller.setIdevent(evenements.get(i).getId());
            gridEvent.add(pane, column, row);
            column++;
            if (column > 1) {
                column = 0;
                row++;
            }
            if (evenements.get(i).getNbrparticipants() <= 0) {
                // Ev.supprimerEvenement(evenements.get(i));
                controller.arreterEvent();
            }
        }
    } catch (SQLException | IOException ex) {
        System.out.println(ex.getMessage());
    }   
}
    
        private Pagination createPagination(int pageCount) {
    Pagination pagination = new Pagination(pageCount, currentPage);
    pagination.setPageFactory((pageIndex) -> {
        currentPage = pageIndex;
        afficherEvenement();
        return new VBox();
    });
    return pagination;
}

    @FXML
    private void rechercherevenement(KeyEvent event) {
        try {
            List<evenement> evenements = Ev.chercherEvent(chercherEventField.getText());
            gridEvent.getChildren().clear();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("evenement.fxml"));
                AnchorPane pane = loader.load();         
                //passage de parametres
                EvenementController controller = loader.getController();
                controller.setEvennement(evenements.get(i));
                controller.setIdevent(evenements.get(i).getId());
                gridEvent.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
                if(evenements.get(i).getNbrparticipants()<=0)
                {
                 // Ev.supprimerEvenement(evenements.get(i));
                controller.arreterEvent();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    
    
    

    @FXML
    private void trierEvenement(ActionEvent event) throws SQLException {
        try {
            List<evenement> evenements = Ev.trierEvent();
            gridEvent.getChildren().clear();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("evenement.fxml"));
                AnchorPane pane = loader.load();      
                //passage de parametres
                EvenementController controller = loader.getController();
                controller.setEvennement(evenements.get(i));
                controller.setIdevent(evenements.get(i).getId());
                gridEvent.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
                if(evenements.get(i).getNbrparticipants()<=0)
                {
                 // Ev.supprimerEvenement(evenements.get(i));
                controller.arreterEvent();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
 
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

    @FXML
    private void Retour(ActionEvent event) {
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
    
    
    
    
  
