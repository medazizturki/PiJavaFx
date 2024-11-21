/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.DocumentException;
import entities.UserDetails;
import entities.evenement;
import entities.participation;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import services.ParticipationService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.EvenementService;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.User;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.controlsfx.control.Notifications;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterEvenementController implements Initializable {

    @FXML
    private TextField descriptionEventField;
    @FXML
    private DatePicker dateEventField;
    @FXML
    private TextField typeEventField;
    @FXML
    private TextField imageEventField;
    @FXML
    private TextField nomEventField;
    @FXML
    private Button supprimerBoutton;
    @FXML
    private Button ajouterButton;
    @FXML
    private TableView<evenement> evenementTv;
    @FXML
    private TableColumn<evenement, String> nomEventTv;
    @FXML
    private TableColumn<evenement, String> typeEventTv;
    @FXML
    private TableColumn<evenement, String> imageEventTv;
    @FXML
    private TableColumn<evenement, String> dateEventTv;
    @FXML
    private TableColumn<evenement, String> descriptionEventTv;
//    private TableColumn<evenement, Integer> nbrpartTv;  
    @FXML
    private TableColumn<evenement, String> dateFinEventTv;
    @FXML
    private TableColumn<evenement, String> ColorEventTv;
    @FXML
    private TableColumn<evenement, Integer> nbrparticipantsTv;
    @FXML 
    private TextField nbparticipantsField;

    @FXML
    private Label partError;
    @FXML
    private Label idLabel;
    @FXML
    private TextField idmodifierField;
    @FXML
    private ImageView imageview;
    private TextField rechercher;
    @FXML
    private ImageView QrCode;
    @FXML
    private Button musicButton;
    @FXML
   
    private Button pauseMusicButton;
    private Date date1;
    ObservableList<evenement> events;
    EvenementService Ev=new EvenementService();
    ParticipationService Pservice =new ParticipationService();
   
    @FXML
    private DatePicker dateFinEventField;
    @FXML
    private Button map;
    @FXML
    private Button Statistique;
PDFGenerator pdf =new PDFGenerator(); 
    @FXML
    private Button modifierEvenementButton;
    @FXML
    private Button RetourB;
    @FXML
    private Button afficherParticipationsButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO          
        partError.setVisible(false);
        //idLabel.setText("");
        getEvents(); 
        
        
        mediaPlayer.play();
       // Image img = new Image("C:\\xampp\\htdocs\\music\\ala.jpg");
        Notifications notificationBuilder = Notifications.create()
                .title("Musique")
                .text("      Musique Jouée");
//         Notifications notificationBuilder = Notifications.create()
//                .title("Musique")
//                .text("      Musique Jouée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
//                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        //notificationBuilder.show();
    
        }
  
    
        
       String path = "C:\\xampp2\\htdocs\\music\\Alok & Alan Walker.mp3";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    
     private boolean NoDate() {
         LocalDate currentDate = LocalDate.now();     
         LocalDate myDate = dateEventField.getValue(); 
         int comparisonResult = myDate.compareTo(currentDate);      
         boolean test = true;
        if (comparisonResult < 0) {
        // myDate est antérieure à currentDate
        test = true;
        } else if (comparisonResult > 0) {
         // myDate est postérieure à currentDate
         test = false;
        }
        return test;
    }
      void retour(ActionEvent event) {
 try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("acceuilback.fxml"));
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
    private void ajouterEvenement(ActionEvent event) {
   
         int part=0;
        if ((nomEventField.getText().length() == 0) || (typeEventField.getText().length() == 0) || (imageEventField.getText().length() == 0) || (nbparticipantsField.getText().length() == 0)|| (descriptionEventField.getText().length() == 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("Fields cannot be empty");
            alert.showAndWait();
        }
       else if (NoDate() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("la date date doit être aprés la date d'aujourd'hui");
            alert.showAndWait();
        }
       else{     
            try {
                part = Integer.parseInt(nbparticipantsField.getText());
                partError.setVisible(false);
            } catch (Exception exc) {
                System.out.println("Number of participants int");
                partError.setVisible(true);
                return;
            }
            if(part<=0)
            {Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("le nombre de participants doit être supp à 0");
            alert.showAndWait();
            partError.setVisible(true);}
            else
            {
        evenement e = new evenement();
        e.setNom_evenement(nomEventField.getText());
        e.setType_evenement(typeEventField.getText());
        e.setLieu_evenement(descriptionEventField.getText());
        e.setColor("color auto");
        
        java.util.Date date_debut=java.util.Date.from(dateEventField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date sqlDate = new Date(date_debut.getTime());
        
        java.util.Date date_fin=java.util.Date.from(dateFinEventField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date sqlDate1 = new Date(date_fin.getTime());
        
        e.setDate_debut(sqlDate);
        e.setDate_fin(sqlDate1);
        
        e.setNbrparticipants(Integer.valueOf(nbparticipantsField.getText()));
        
        //lel image
        e.setImage_evenement(imageEventField.getText()); 
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Event add");
            alert.setContentText("Event added successfully!");
            alert.showAndWait();      
        try {
            Ev.ajouterEvenement(e);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }      
        getEvents();
        
        
        Notifications notificationBuilder = Notifications.create()
                .title("evenement")
                .text(" Evenement ajouté");
//         Notifications notificationBuilder = Notifications.create()
//                .title("Musique")
//                .text("      Musique Jouée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
//                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }}}
    
    //fin d ajout d'un evenement
    private void reset() {
        nomEventField.setText("");
        typeEventField.setText("");
        descriptionEventField.setText("");
        imageEventField.setText("");
        nbparticipantsField.setText("");
        dateEventField.setValue(null);  
        dateFinEventField.setValue(null); 
    }
    
   public void getEvents() {  
         try {
            // TODO
            List<evenement> evenements = Ev.recupererEvenement();
            ObservableList<evenement> olp = FXCollections.observableArrayList(evenements);
            evenementTv.setItems(olp);
            nomEventTv.setCellValueFactory(new PropertyValueFactory("nom_evenement"));
            typeEventTv.setCellValueFactory(new PropertyValueFactory("type_evenement"));
            imageEventTv.setCellValueFactory(new PropertyValueFactory("image_evenement"));
            dateEventTv.setCellValueFactory(new PropertyValueFactory("date_debut"));
            descriptionEventTv.setCellValueFactory(new PropertyValueFactory("lieu_evenement"));
//            nbrpartTv.setCellValueFactory(new PropertyValueFactory("nb_participants"));
            dateFinEventTv.setCellValueFactory(new PropertyValueFactory("date_fin"));
            ColorEventTv.setCellValueFactory(new PropertyValueFactory("color"));
            nbrparticipantsTv.setCellValueFactory(new PropertyValueFactory("nb_participants"));
           // this.delete();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }//get events

    @FXML
    private void supprimerEvenement(ActionEvent event) {
           evenement e = evenementTv.getItems().get(evenementTv.getSelectionModel().getSelectedIndex());
        try {
            Ev.supprimerEvenement(e);
        } catch (SQLException ex) {
            Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("Event delete");
        alert.setContentText("Event deleted successfully!");
        alert.showAndWait();        
        getEvents();  
        
        Notifications notificationBuilder = Notifications.create()
                .title("Evenement")
                .text("      Evenement suprimié");
//         Notifications notificationBuilder = Notifications.create()
//                .title("Musique")
//                .text("      Musique Jouée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
//                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    private void afficherEvenement(ActionEvent event) {
         try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("afficherEvenement.fxml"));
            typeEventField.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

   @FXML
   private void modifierEvenement(ActionEvent event) throws SQLException {
        evenement e = new evenement();
        e.setId(Integer.valueOf(idmodifierField.getText()));
        e.setNom_evenement(nomEventField.getText());
        e.setType_evenement(typeEventField.getText());
        e.setLieu_evenement(descriptionEventField.getText()); 
        Date d=Date.valueOf(dateEventField.getValue());
        Date d1=Date.valueOf(dateFinEventField.getValue());
        e.setDate_debut(d);
        e.setDate_fin(d1);
        e.setImage_evenement(imageEventField.getText());
        e.setNbrparticipants(Integer.valueOf(nbparticipantsField.getText()));         
        Ev.modifierEvenement(e);
        reset();
        getEvents();  
        Notifications notificationBuilder = Notifications.create()
                .title("Evenement")
                .text(" Evenement Modifié");
//         Notifications notificationBuilder = Notifications.create()
//                .title("Musique")
//                .text("      Musique Jouée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
//                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }
    @FXML
    //ta3 tablee bch nenzel 3ala wehed ya5tarou w yet3abew textfield
    private void choisirEvent(MouseEvent event) throws IOException {
        evenement e = evenementTv.getItems().get(evenementTv.getSelectionModel().getSelectedIndex());     
        //idLabel.setText(String.valueOf(e.getId_event()));
        idmodifierField.setText(String.valueOf(e.getId()));
        nomEventField.setText(e.getNom_evenement());
        typeEventField.setText(e.getType_evenement());
        imageEventField.setText(e.getImage_evenement());
        descriptionEventField.setText(e.getLieu_evenement());
        //dateEventField.setValue((e.getDate()));
        nbparticipantsField.setText(String.valueOf(e.getNbrparticipants()));       
//        //lel image
//        String path = e.getImage_evenement();
//               File file=new File(path);
//              Image img = new Image(file.toURI().toString());
//                imageview.setImage(img);


        String x = e.getImage_evenement();
        String DBPath = "C:\\Users\\octanet\\Downloads\\test1\\test1\\public\\uploads\\"  + x;
               File file=new File(DBPath);
              Image img = new Image(file.toURI().toString());
                imageview.setImage(img);
             
        //////////////      
            String filename = Ev.GenerateQrEvent(e);
            System.out.println("filename lenaaa " + filename);
            String path1="C:\\xampp2\\htdocs\\PI\\Evenement\\imgQr\\qrcode"+filename;
             File file1=new File(path1);
              Image img1 = new Image(file1.toURI().toString());
              //Image image = new Image(getClass().getResourceAsStream("src/utils/img/" + filename));
            QrCode.setImage(img1);  
    }

    private void participer(ActionEvent event) {

        User u=new User();
        LocalDate dateActuelle = LocalDate.now();
        Date dateSQL = Date.valueOf(dateActuelle);
        participation p=new participation();
        p.setDate_participation(dateSQL);
        //p.setEvenement();
        p.setEvenement_id(Integer.parseInt(idmodifierField.getText()));
        p.setUser_id(u.getId());
        Pservice.ajouterParticipation(p);
    }

//    private void afficherParticipations(ActionEvent event) {     
//         try {
//            //navigation
//            Parent loader = FXMLLoader.load(getClass().getResource("afficherParticipation.fxml"));
//            typeEventField.getScene().setRoot(loader);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }    
//    }

    @FXML
    private void uploadImage(ActionEvent event)throws FileNotFoundException, IOException  {
        Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\Users\\octanet\\Downloads\\test1\\test1\\public\\uploads\\"  + x;
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path=file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
            imageview.setImage(img);    
            imageEventField.setText(""+ x);
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();          
        } else {
            System.out.println("error");
        }
        
    }
    
    
    @FXML
    private void excelEvent(ActionEvent event) {
           
try{
String filename="C:\\xampp2\\htdocs\\fichierExcelJava\\dataEvent.xls" ;
    HSSFWorkbook hwb=new HSSFWorkbook();
    HSSFSheet sheet =  hwb.createSheet("new sheet");
    HSSFRow rowhead=   sheet.createRow((short)0);
rowhead.createCell((short) 0).setCellValue("nom ");
rowhead.createCell((short) 1).setCellValue("type ");
rowhead.createCell((short) 2).setCellValue("lieu ");
rowhead.createCell((short) 5).setCellValue("nbr particpant");
List<evenement> evenements = Ev.recupererEvenement();
  for (int i = 0; i < evenements.size(); i++) {          
HSSFRow row=   sheet.createRow((short)i);
row.createCell((short) 0).setCellValue(evenements.get(i).getNom_evenement());
row.createCell((short) 1).setCellValue(evenements.get(i).getType_evenement());
row.createCell((short) 2).setCellValue(evenements.get(i).getLieu_evenement());
row.createCell((short) 5).setCellValue((evenements.get(i).getNbrparticipants()));
i++;
            }
int i=1;
    FileOutputStream fileOut =  new FileOutputStream(filename);
hwb.write(fileOut);
fileOut.close();
System.out.println("Your excel file has been generated!");
 File file = new File(filename);
        if (file.exists()){ 
        if(Desktop.isDesktopSupported()){
            Desktop.getDesktop().open(file);
        }}       
} catch ( Exception ex ) {
    System.out.println(ex);
}
    }   
    
    @FXML
    private void exportpdf(ActionEvent event) throws SQLException {
         if(evenementTv.getSelectionModel().getSelectedItem()!= null){
        evenement O = evenementTv.getSelectionModel().getSelectedItem();
        int id=O.getId();
         List<participation> list=new ArrayList<>();
        ParticipationService ls= new ParticipationService();
         list= ls.displayAllList1(id);
            try {
                try {
					pdf.GeneratePdf("Participant", list);
				}catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            // TODO Auto-generated catch block
            
            } catch (IOException ex) {
                Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
    
    private void pdfEvent(ActionEvent event) throws FileNotFoundException, SQLException, IOException {        
       // evenement tab_Recselected = evenementTv.getSelectionModel().getSelectedItem();
               long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateLyoum = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);//yyyyMMddHHmmss
        System.out.println("Date d'aujourdhui : " + DateLyoum);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(DateLyoum + ".pdf")));//yyyy-MM-dd
            document.open();
            Paragraph ph1 = new Paragraph("Voici un rapport détaillé de notre application qui contient tous les événements . Pour chaque événement, nous fournissons des informations telles que la date d'aujourdhui :" + DateRapport );
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(8);
            //On créer l'objet cellule.
            PdfPCell cell;
            //contenu du tableau.
            table.addCell("image_evenement");
            table.addCell("type_evenement");
            table.addCell("nom_evenement");
            table.addCell("lieu_evenement");
            table.addCell("date_debut");
            table.addCell("date_fin");
            table.addCell("color");
            table.addCell("nb_participants");
            evenement r = new evenement();
            Ev.recupererEvenement().forEach(e
                    -> {
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(String.valueOf(e.getImage_evenement()));
                table.addCell(String.valueOf(e.getType_evenement()));
                table.addCell(String.valueOf(e.getNom_evenement())); 
                table.addCell(String.valueOf(e.getLieu_evenement()));   
                table.addCell(String.valueOf(e.getDate_debut()));
                table.addCell(String.valueOf(e.getDate_fin())); ;
                table.addCell(String.valueOf(e.getColor())); ;
                table.addCell(String.valueOf(e.getNbrparticipants()));     
            }
            );
            
//       Image img = Image.getInstance("C:\\Users\\msi\\Desktop\\projet yocef\\reclamation\\src\\com\\img\\Exchange.png12.png");
//       img.scaleAbsoluteHeight(60);
//       img.scaleAbsoluteWidth(100);
//       img.setAlignment(Image.ALIGN_RIGHT);
//       document.add(img);
            document.add(ph1);
            document.add(ph2);
            document.add(table);
             } catch (Exception e) {
            System.out.println(e);
        }
        document.close();

        ///Open FilePdf
        File file = new File(DateLyoum + ".pdf");
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) //checks file exists or not  
        {
            desktop.open(file); //opens the specified file   
        }
    } 
    private void rechercherEvent(KeyEvent event) {
        
        EvenementService bs=new EvenementService(); 
        evenement b= new evenement();
        ObservableList<evenement>filter= bs.chercherEvent(rechercher.getText());
        populateTable(filter);
    }
     private void populateTable(ObservableList<evenement> branlist){
       evenementTv.setItems(branlist);
   
       }

    @FXML
    private void playMusic(ActionEvent event) {
        
        mediaPlayer.play();
       // Image img = new Image("C:\\xampp\\htdocs\\music\\ala.jpg");
        Notifications notificationBuilder = Notifications.create()
                .title("Musique")
                .text("      Musique Jouée");
//         Notifications notificationBuilder = Notifications.create()
//                .title("Musique")
//                .text("      Musique Jouée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
//                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    
        }


    @FXML
    private void pauseMusic(ActionEvent event) {
        
               mediaPlayer.pause();
        //Image img = new Image("fllogo.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Musique")
                .text("      Musique Arrêtée");
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    @FXML
    private void map(ActionEvent event) throws IOException {
                 Parent previousScene = FXMLLoader.load(getClass().getResource("Map.fxml"));
    Scene scene = new Scene(previousScene);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
        
    }
    public void setlocal(String ad){
        this.descriptionEventField.setText(ad);
    }

    @FXML
    private void Statistique(ActionEvent event) {
        
                // Create a map to store the frequency of each type
    Map<String, Integer> typeFrequency = new HashMap<>();

    // Loop through the items in the TableView
    for (evenement terrain : evenementTv.getItems()) {
        String type = terrain.getType_evenement();
        if (typeFrequency.containsKey(type)) {
            typeFrequency.put(type, typeFrequency.get(type) + 1);
        } else {
            typeFrequency.put(type, 1);
        }
    }

    // Create a PieChart data set
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    for (String type : typeFrequency.keySet()) {
        int frequency = typeFrequency.get(type);
        double percentage = (double) frequency / evenementTv.getItems().size() * 100;
        String percentageText = String.format("%.2f%%", percentage);
        PieChart.Data slice = new PieChart.Data(type + " " + percentageText, frequency);
        pieChartData.add(slice);
    }

    // Create a PieChart with the data set
    PieChart chart = new PieChart(pieChartData);

    // Show percentage values in the chart's tooltip
    for (final PieChart.Data data : chart.getData()) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText(String.format("%.2f%%", (data.getPieValue() / evenementTv.getItems().size() * 100)));
        Tooltip.install(data.getNode(), tooltip);
    }

    // Show the chart in a new window
    Scene scene = new Scene(chart);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();

        
    }

    

    @FXML
    private void RetourM(ActionEvent event) {
                                 try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("acceuilback.fxml"));
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
    private void afficherParticipation(ActionEvent event) {
                   try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("afficherParticipation.fxml"));
            typeEventField.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }
    


}

 


    





    

