

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Rendezvous;
import utils.MyDB;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.rmi.Naming.list;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author chino
 */
public class ServiceRendezvous implements Iservice {
    
                  Rendezvous p =new Rendezvous();
                    public Statement ste;
    public PreparedStatement pst;
  
 Connection cnx;

    public ServiceRendezvous() {
        cnx = MyDB.getInstance().getCnx();
    }
 
   // @Override
    
    public void add(Rendezvous t) throws SQLException  {
      
        String qry ="INSERT INTO `rendezvous`( `nom_rendezvous`, `prenom_rendezvous`, `lieu_rendezvous`, `email_rendezvous`, `date_rendezvous`, `color`, `user_id` ) VALUES ('"+t.getNomRendezvous()+"','"+t.getPrenomRendezvous()+"','"+t.getLieuRendezvous()+"','"+t.getEmailRendezvous()+"','"+t.getDateRendezvous()+"','"+t.getColor()+"','"+t.getUser_id()+"')";

       
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        
    
    }
public List<Rendezvous> afficher() throws SQLException {
        List<Rendezvous> Factures = new ArrayList<>();
      try {
            String qry ="SELECT * FROM `Rendezvous` ";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                p.setIdRendezvous(rs.getInt(1));
                p.setNomRendezvous(rs.getString("nom_rendezvous"));
                  p.setPrenomRendezvous(rs.getString("prenom_rendezvous"));
                    p.setLieuRendezvous(rs.getString("lieu_rendezvous"));
                      p.setEmailRendezvous(rs.getString("email_rendezvous"));
                        p.setDateRendezvous(rs.getDate("date_rendezvous"));
                        p.setColor(rs.getString("color"));
          
                //p.setAdresses(rs.getString(5));

                Factures.add(p);

            }
            return Factures;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Factures;
        
    }
    
    
     public List<Rendezvous> recupererrendezVous() throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        List<Rendezvous> evenements = new ArrayList<>();
        String s = "select * from rendezvous";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Rendezvous e = new Rendezvous();
            e.setNomRendezvous(rs.getString("nom_rendezvous"));
            e.setPrenomRendezvous(rs.getString("prenom_rendezvous"));
            e.setEmailRendezvous(rs.getString("email_rendezvous"));
            e.setLieuRendezvous(rs.getString("lieu_rendezvous"));
            e.setDateRendezvous(rs.getDate("date_rendezvous"));
            e.setUser_id(rs.getInt("user_id"));
            e.setColor(rs.getString("color"));
            e.setIdRendezvous(rs.getInt("id"));

            evenements.add(e);

        }
        return evenements;
    }
    
    
        
        
     // @Override
    public void modifier(Rendezvous t) {
 try {
            String qry ="UPDATE rendezvous SET `nom_rendezvous`='"+t.getNomRendezvous()+"',`prenom_rendezvous`='"+t.getPrenomRendezvous()+"',`lieu_rendezvous`='"+t.getLieuRendezvous()+"',`email_rendezvous`='"+t.getEmailRendezvous()+"',`date_rendezvous`='"+t.getDateRendezvous()+"',`color`='"+t.getColor()+"',`user_id`='"+t.getUser_id()+"' WHERE id="+t.getIdRendezvous()+";";    
            cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        }
//@Override
    public void supprimer(int id) {
try {
            String qry ="DELETE from Rendezvous where id = "+id+";";
      cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }    }
    
    
     public void supprimerRendezVous(Rendezvous e) throws SQLException {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "delete from Rendezvous where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, e.getIdRendezvous());
        ps.executeUpdate();
        System.out.println("event with id= " + e.getIdRendezvous() + "  is deleted successfully");
    }
    
    
   /* public List<Rendezvous> afficherRendezvousparid(int id_Rendezvous){
       List<Rendezvous> list = new ArrayList<>();
       try {
           String req ="Select * from Rendezvous  where id =" +id_Rendezvous+";";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);
           while (rs.next()) {
            Rendezvous D = new Rendezvous();
          p.setIdRendezvous(rs.getInt(1));
                p.setNomRendezvous(rs.getString("nom_rendezvous"));
                  p.setPrenomRendezvous(rs.getString("prenom_rendezvous"));
                    p.setLieuRendezvous(rs.getString("lieu_rendezvous"));
                      p.setEmailRendezvous(rs.getString("email_rendezvous"));
                        p.setDateRendezvous(rs.getDate("date_rendezvous"));
                        p.setColor(rs.getString("color"));
                      
              
                
            list.add(D);
               
           }
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
   } 
      return list; 
       
  }*/
     public List<Rendezvous> afficherid(int id_Rendezvous) {
        List<Rendezvous> Factures = new ArrayList<>();
      try {
            String qry = "Select * from Rendezvous  where id =" +id_Rendezvous+";" ;
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                p.setIdRendezvous(rs.getInt(1));
                p.setNomRendezvous(rs.getString("nom_rendezvous"));
                  p.setPrenomRendezvous(rs.getString("prenom_rendezvous"));
                    p.setLieuRendezvous(rs.getString("lieu_rendezvous"));
                      p.setEmailRendezvous(rs.getString("email_rendezvous"));
                        p.setDateRendezvous(rs.getDate("date_rendezvous"));
                        p.setColor(rs.getString("color"));
          
                //p.setAdresses(rs.getString(5));

                Factures.add(p);

            }
            return Factures;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Factures;
        
    }
   public List<Rendezvous> afficherDemandeparcategorie (){
       List<Rendezvous> list = new ArrayList<>();
       try {
           String req ="Select * from Rendezvous  where traitement ='en cours de traitement'" ;
           
           Statement st = cnx.createStatement();
           ResultSet rs = st.executeQuery(req);
           
           while (rs.next()) {
            Rendezvous D = new Rendezvous();
          p.setIdRendezvous(rs.getInt(1));
                p.setNomRendezvous(rs.getString("nom_rendezvous"));
                  p.setPrenomRendezvous(rs.getString("prenom_rendezvous"));
                    p.setLieuRendezvous(rs.getString("lieu_rendezvous"));
                      p.setEmailRendezvous(rs.getString("email_rendezvous"));
                        p.setDateRendezvous(rs.getDate("date_rendezvous"));
                        p.setColor(rs.getString("color"));
                      
              
                
               list.add(D);
               
           }
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
   } 
      return list; 
       
  }




   
    public String GenerateQrEvent(Rendezvous event) throws FileNotFoundException, IOException {
        String eventName = "event name: " + event.getNomRendezvous() + "\n" + "rende vous date: " + event.getDateRendezvous() + "\n" + "rende vous lieu: " + event.getLieuRendezvous() + "\n";
        ByteArrayOutputStream out = QRCode.from(eventName).to(ImageType.JPG).stream();
        String filename = event.getNomRendezvous() + "_QrCode.jpg";
        //File f = new File("src\\utils\\img\\" + filename);
                File f = new File("C:\\xampp2\\htdocs\\PI\\Evenement\\imgQr\\qrcode" + filename);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(out.toByteArray());
        fos.flush();
       
        System.out.println("qr yemshi");
        return filename;
    }
    
    
      public List<Rendezvous> chercherEvent(String chaine) {
        String sql = "SELECT * FROM Rendezvous WHERE (nom_rendezvous LIKE ? or lieu_rendezvous LIKE ?  ) order by nom_rendezvous ";
        //Connection cnx= Maconnexion.getInstance().getCnx();
        String ch = "%" + chaine + "%";
        List<Rendezvous> myList = new ArrayList<>();
        try {

            Statement ste = cnx.createStatement();
            // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee = cnx.prepareStatement(sql);
            stee.setString(1, ch);
            stee.setString(2, ch);

            ResultSet rs = stee.executeQuery();
 while (rs.next()) {
            Rendezvous D = new Rendezvous();
          p.setIdRendezvous(rs.getInt(1));
                p.setNomRendezvous(rs.getString("nom_rendezvous"));
                  p.setPrenomRendezvous(rs.getString("prenom_rendezvous"));
                    p.setLieuRendezvous(rs.getString("lieu_rendezvous"));
                      p.setEmailRendezvous(rs.getString("email_rendezvous"));
                        p.setDateRendezvous(rs.getDate("date_rendezvous"));
                        p.setColor(rs.getString("color"));
                      
              
                
               myList.add(D);
               
           }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
      
      
      
    public Rendezvous findRendezvous(int id) {
		Rendezvous e = new Rendezvous();
		try {
			
                        String req = "Select * from Rendezvous where id =" + id;
			ste = cnx.createStatement();

			ResultSet rs = ste.executeQuery(req);
			rs.first();
                        
                        p.setIdRendezvous(rs.getInt(1));
                p.setNomRendezvous(rs.getString("nom_rendezvous"));
                  p.setPrenomRendezvous(rs.getString("prenom_rendezvous"));
                    p.setLieuRendezvous(rs.getString("lieu_rendezvous"));
                      p.setEmailRendezvous(rs.getString("email_rendezvous"));
                        p.setDateRendezvous(rs.getDate("date_rendezvous"));
                        p.setColor(rs.getString("color"));
                      
                
			ste.close();
		} catch (SQLException ex) {
			System.err.println("Probleme lors du lecture d'un Album");
			System.out.println(ex.getMessage());
		}

		return (e != null) ? e : null;
	}
    
    
}
