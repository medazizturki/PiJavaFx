/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import IService.ISerivce;
import Utils.MyConnexion;
import entites.reclamation;
import entites.reponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 21656
 */
public class reponse_Service  implements ISerivce<reponse> {
private Connection c = MyConnexion.getInsCon().getcnx();

    @Override
    public void Ajouter(reponse t) throws SQLException {
     try {
            String requete = "INSERT INTO `reponse`( `reponse`, `reclamation_id`) VALUES (?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setString(1, t.getReponse());
            pst.setInt(2, t.getId_reclamation());

            pst.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    
    
    }
     public ObservableList<reponse> serach(String cas) throws SQLException {
        ObservableList<reponse> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `reclamation` where  id LIKE '%" + cas + "%'or reponse LIKE '%" + cas + "%'  or  reclamation_id LIKE '%" + cas + "%' ";
       try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

               list.add(new reponse(rs.getInt("id"),rs.getString("reponse"),rs.getInt("reclamation_id")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
    @Override
    public void Supprimer(int t) throws SQLException {
        String requete = "DELETE FROM `reponse` WHERE `id`=" + String.valueOf(t) + "";
        try {

            PreparedStatement pst = c.prepareStatement(requete);
            pst.execute(requete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(reponse t, int id) throws SQLException {
          try {
            String requete = "UPDATE `reponse` SET `reponse`=?,`reclamation_id`=? WHERE `id`=?";
            
            // binding vaalue 
            
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setString(1, t.getReponse());
            pst.setInt(2, t.getId_reclamation());
            pst.setInt(3, id);
            pst.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<reponse> Affichertout() throws SQLException { // kolll 
        ObservableList<reponse> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `reponse` ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            
            while (rs.next()) {
                list.add(new reponse(rs.getInt("id"),rs.getString("reponse"),rs.getInt("reclamation_id")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }
      public ObservableList<reponse> Affichertout_reclamation(int id_rec) throws SQLException { // haseb reclamation
        ObservableList<reponse> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `reponse` rep inner join reclamation rec where rep.reclamation_id = rec.id AND rep.reclamation_id="+id_rec;
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            
            while (rs.next()) {
                list.add(new reponse(rs.getInt("id"),rs.getString("reponse"),rs.getInt("reclamation_id")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }
    
}
