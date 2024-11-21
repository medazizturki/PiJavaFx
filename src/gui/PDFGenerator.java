/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author azizb
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.evenement;


import entities.participation;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.EvenementService;

public class PDFGenerator {

    List<participation> list = new ArrayList<>();

    public void GeneratePdf(String filename, List<participation> list) throws FileNotFoundException, DocumentException,
            BadElementException, IOException, InterruptedException, SQLException {

        Document document = new Document() {
        };

        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
        document.open();
         Image logo = Image.getInstance("C:\\Users\\octanet\\Desktop\\integration\\logo.png");
         logo.scaleToFit(80, 80);
         document.add(logo);

        int i = 1;
        for (participation participation : list) {
            int id= participation.getEvenement_id();
            EvenementService es=new EvenementService();
            evenement e=new evenement();
          e=  es.findEvenement(id);
      String  nom=  e.getNom_evenement();

            document.add(new Paragraph("L evenement N°" + i + " Associé a vous est"));
            document.add(new Paragraph("La date de participation est:" + participation.getDate_participation()));
            document.add(new Paragraph("nom de l evenement est =:" + nom));
            document.add(new Paragraph("          "));

            document.add(new Paragraph("*******************************************************"));

            i = i + 1;
        }
        document.add(new Paragraph("                              Pas des Participation                  "));

        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }
}