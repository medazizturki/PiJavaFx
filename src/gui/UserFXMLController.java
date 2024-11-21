/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import services.PasswordEncryption;
import services.UserController;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.Initializable;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import entities.User;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import services.BCrypt;




/**
 * FXML Controller class
 *
 * @author Seif
 */
public class UserFXMLController implements Initializable {

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
    @FXML
    private TableColumn<User, String> tve;

    @FXML
    private TableColumn<User, Integer> tvi;

    @FXML
    private TableColumn<User, String> tvn;

    @FXML
    private TableColumn<User, String> tvp;

    @FXML
    private TableColumn<User, String> tvr;

    @FXML
    private TableColumn<User, Integer> tvt;
    @FXML
    private TableView<User> tableprod;
  
     @FXML
    private ImageView code_qr;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // roleu.setItems((ObservableList<String>) roleu);
        //System.out.println(roleu.getSelectionModel().getSelectedIndex());
       roleu.setItems(roley);
       tableprod.setEditable(true);
        afficher();
        
    }    
    
    
        public void afficher()
{
     tvi.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
    tvn.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
    tvn.setEditable(true);
    tvn.setCellFactory(TextFieldTableCell.forTableColumn());
    tve.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
    tve.setEditable(true);
    tve.setCellFactory(TextFieldTableCell.forTableColumn());
    tvp.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
    tvp.setEditable(true);
    tvp.setCellFactory(TextFieldTableCell.forTableColumn()); 
    tvr.setCellValueFactory(new PropertyValueFactory<User,String>("roles"));
    tvr.setEditable(true);
    tvr.setCellFactory(TextFieldTableCell.forTableColumn());
    tvt.setCellValueFactory(new PropertyValueFactory<User,Integer>("telephone"));
    tvt.setEditable(true);
    
    
 
    tableprod.setItems(FXCollections.observableArrayList( ps.afficherUser()));
    ObservableList<User> oList = FXCollections.observableArrayList(ps.afficherUser());
        FilteredList<User> filteredData = new FilteredList<User>(oList, b -> true);
        
        SortedList<User> sortedList = new SortedList <User>(filteredData);
        sortedList.comparatorProperty().bind(tableprod.comparatorProperty())    ;
        tableprod.setItems(sortedList);
        
        
       // Assuming user_listfx is a TableView of User objects
// and the columns have already been set up


    
    
}
       
        /*
    public void afficher() {
    // Set up the columns of the TableView
      tvi.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
    tvn.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
    tvn.setEditable(true);
    tvn.setCellFactory(TextFieldTableCell.forTableColumn());
    tve.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
    tve.setEditable(true);
    tve.setCellFactory(TextFieldTableCell.forTableColumn());
    tvp.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
    tvp.setEditable(true);
    tvp.setCellFactory(TextFieldTableCell.forTableColumn()); 
    tvr.setCellValueFactory(new PropertyValueFactory<User,String>("roles"));
    tvr.setEditable(true);
    tvr.setCellFactory(TextFieldTableCell.forTableColumn());
    tvt.setCellValueFactory(new PropertyValueFactory<User,Integer>("telephone"));
    tvt.setEditable(true);
    
    
 
    tableprod.setItems(FXCollections.observableArrayList( ps.afficherUser()));
    ObservableList<User> oList = FXCollections.observableArrayList(ps.afficherUser());
        FilteredList<User> filteredData = new FilteredList<User>(oList, b -> true);
        
    // Set up the search functionality
    TextField searchField = new TextField();
    searchField.setPromptText("Search");

    FilteredList<User> filteredList = new FilteredList<>(tableprod.getItems(), p -> true);

    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredList.setPredicate(user -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();

            if (user.getName().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Search by name
            } else if (user.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Search by email
            } else if (String.valueOf(user.getId()).toLowerCase().contains(lowerCaseFilter)) {
                return true; // Search by ID
            } else if (user.getRoles().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Search by roles
            } else if (String.valueOf(user.getTelephone()).toLowerCase().contains(lowerCaseFilter)) {
                return true; // Search by telephone
            }

            return false; // No match found
        });
    });

    SortedList<User> sortedList = new SortedList<>(filteredList);
    sortedList.comparatorProperty().bind(tableprod.comparatorProperty());

    // Add the search field and TableView to a VBox
    VBox vbox = new VBox(searchField, tableprod);
}
*/
        
    @FXML
    void onActionadd(ActionEvent event) throws Exception {
     /*   
         if(nomu.getText().isEmpty()  || emailu.getText().isEmpty() || passwordu.getText().isEmpty() ||telephoneu.getText().isEmpty() || (roleu.getSelectionModel().getSelectedIndex())==-1)
        {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Add Error");
alert.setContentText("all fields must  not be empty !");

alert.showAndWait();
        }
       */
     
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
                          if (ps.emailExist(email)) {
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
            afficher();
            
            
                }
        
    
    @FXML
    void onActionsupp(ActionEvent event) {
         User u = tableprod.getSelectionModel().getSelectedItem();
        ps.supprimerUser(u.getId());
        afficher();
    }
    
@FXML
    private void nommu(TableColumn.CellEditEvent<User, String> event) {
        User F = tableprod.getSelectionModel().getSelectedItem();
         F.setName(event.getNewValue());
         ps.modifierU(F);
    }
                
    

 @FXML
    void qr_code(ActionEvent event) {

User p = tableprod.getSelectionModel().getSelectedItem();
      

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String Information = "nom de user : "+p.getName()+"\n"+"télephone : "+p.getTelephone();
        int width = 300;
        int height = 300;
        BufferedImage bufferedImage = null;
         try{
            BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
            code_qr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            
        } catch (WriterException ex) {
        }
        
    }
    
       @FXML
    void stat(ActionEvent event) {

        
           // Create a map to store the frequency of each type
    Map<String, Integer> typeFrequency = new HashMap<>();

    // Loop through the items in the TableView
    for (User user : tableprod.getItems()) {
        String type = user.getRoles();
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
        double percentage = (double) frequency / tableprod.getItems().size() * 100;
        String percentageText = String.format("%.2f%%", percentage);
        PieChart.Data slice = new PieChart.Data(type + " " + percentageText, frequency);
        pieChartData.add(slice);
    }

    // Create a PieChart with the data set
    PieChart chart = new PieChart(pieChartData);

    // Show percentage values in the chart's tooltip
    for (final PieChart.Data data : chart.getData()) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText(String.format("%.2f%%", (data.getPieValue() / tableprod.getItems().size() * 200)));
        Tooltip.install(data.getNode(), tooltip);
    }

    // Show the chart in a new window
    Scene scene = new Scene(chart);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
    }
        
       void tri(ActionEvent event) {

    
        
    }
    
      void web_cam(ActionEvent event) {

   /*     
        Thread wcam = new Thread()
        {
        @Override
        public void run()
        {
        cvCapture cap = opencv_highgui.cvCreateCameraCapture(0);
        opencv_highgui.cvSetCaptureProperty(cap, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 20);
        
                opencv_highgui.cvSetCaptureProperty(cap, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 20);

       Lp1Image gimg = opencv_highgui.cvQueryFrame(cap);
       CanvasFrame can = new CanvasFrame("Title");
       
       while(can.Isvible() && (gimg = opencv_highgui.cvQueryFrame(cap)) !=null )
           
       {
       
       can.showImage(gimg);
       
       }
        
        
        }
        
        
        
        
        };
        wcam.start();
        
        
        */
    }

   

  /*  @FXML
    private void pdf(ActionEvent event) {
    }
    
       Document document = new Document(PageSize.A4);
  
    //Font font = FontFactory.getFont("/fonts/VTFRedzone-Classic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);

    try {
        PdfWriter.getInstance(document, new FileOutputStream("user.pdf"));

        document.open();

        Paragraph paragraph = new Paragraph("Détails de l'utilisateur");
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        document.add(Chunk.NEWLINE);

        PdfPTable pdfTable = new PdfPTable(2);

        ObservableList<User> selectedUsers = tableprod.getSelectionModel().getSelectedItems();

        pdfTable.addCell("Nom du champ");
        pdfTable.addCell("Valeur");

        for (User user : selectedUsers) {
            pdfTable.addCell("ID");
            pdfTable.addCell(String.valueOf(user.getId()));

            pdfTable.addCell("Nom");
            pdfTable.addCell(user.getName());

            pdfTable.addCell("Adresse email");
            pdfTable.addCell(user.getEmail());

            pdfTable.addCell("Telephone");
              pdfTable.addCell(String.valueOf(user.getTelephone()));

            pdfTable.addCell("Mot de passe");
            pdfTable.addCell(user.getPassword());

         

            // Add image to the PDF
          

            pdfTable.completeRow(); // Add a new row for each selected item
        }

        document.add(pdfTable);

        document.close();

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Export PDF");
        alert.setHeaderText(null);
        alert.setContentText("Le fichier PDF a été généré avec succès !");
        alert.showAndWait();
    } catch (Exception e) {
        e.printStackTrace();
    }*/
   @FXML
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
    }
    
