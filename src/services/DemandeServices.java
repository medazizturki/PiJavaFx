/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static com.mysql.jdbc.Messages.getString;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.Demande;

import utils.MyDB;

/**
 *
 * @author Marwen.M
 */
public class DemandeServices  {
     Statement ste;
   Connection conn = MyDB.getInstance().getCnx();
   
    
   public void ajouterDemande (Demande D)  {
       
       try{
           String req ="INSERT INTO `demande` (`id`,`offre_id`,`user_id`,`cv`,`description`,`traitement`) VALUES ('" + D.getId() +"','" + D.getId_offre()+"','" + D.getUser_id()+"','" + D.getCv() +"','" + D.getDescription()+ "','" + D.getTraitement() + "')";
           ste = conn.createStatement();
           ste.executeUpdate(req);
           System.err.println("Demande  Ajouter!!!");
           
       } catch (SQLException ex){
           System.out.println("Demande  non ajoute");
           System.out.println(ex.getMessage());
       }
       
       
           
   }
   public List<Demande> afficherDemande(){
       List<Demande> list = new ArrayList<>();
       try {
           String req ="select * from demande";
           Statement st = conn.createStatement();
           ResultSet RS = st.executeQuery(req);
           while (RS.next()) {
            Demande D = new Demande();
            D.setId(RS.getInt(1));
            D.setCv(RS.getString("cv"));
            D.setDescription(RS.getString("description"));
            D.setTraitement(RS.getString("traitement"));
            D.setUser_id(RS.getInt(5));
            D.setId_offre(RS.getInt(6));
            
                   
        
                      
              
                
             list.add(D);
               
           }
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
   } 
      return list; 
       
  }
   public List<Demande> afficherDemandeparid(int id){
       List<Demande> list = new ArrayList<>();
       try {
           String req ="Select * from demande  where id =" +id;
           Statement st = conn.createStatement();
           ResultSet RS = st.executeQuery(req);
           while (RS.next()) {
            Demande D = new Demande();
            D.setId(RS.getInt(1));
            D.setUser_id(RS.getInt(2));
            D.setId_offre(RS.getInt(3));
            D.setCv(RS.getString("cv"));
            D.setDescription(RS.getString("description"));
            D.setTraitement(RS.getString("traitement"));
                      
              
                
            list.add(D);
               
           }
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
   } 
      return list; 
       
  }
   public List<Demande> afficherDemandeparcategorie (){
       List<Demande> list = new ArrayList<>();
       try {
           String req ="Select * from demande  where traitement ='en cours de traitement'" ;
           
           Statement st = conn.createStatement();
           ResultSet RS = st.executeQuery(req);
           
           while (RS.next()) {
            Demande D = new Demande();
            D.setId(RS.getInt(1));
            D.setUser_id(RS.getInt(2));
            D.setId_offre(RS.getInt(3));
            D.setCv(RS.getString("cv"));
            D.setDescription(RS.getString("description"));
            D.setTraitement(RS.getString("traitement"));
                      
              
                
               list.add(D);
               
           }
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
   } 
      return list; 
       
  }
   public void modifierdemande (Demande D){
      
      try {
         
          String req ="UPDATE demande SET `offre_id`='"+D.getId_offre()+"',`user_id`='"+D.getUser_id()+"',`cv`='"+D.getCv()+"',`description`='"+D.getDescription()+"',`traitement`='"+D.getTraitement()+"' WHERE id="+D.getId()+";";
          Statement st = conn.createStatement();
          st.executeUpdate(req);
          System.out.println("demande updated !!");
          
      }catch (SQLException ex) {
          System.out.println("demande not Updated");
          System.out.println(ex.getMessage());
      }
  }

   
    public void supprimerDemande(int id){
       try {
           String req = "DELETE from `demande` WHERE id = " + id;
            ste = conn.createStatement();
           ste.executeUpdate(req);
           System.out.println("demande Deleted!!!");
           
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
   }
}
