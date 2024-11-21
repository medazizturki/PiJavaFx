/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import services.IuserController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.User;
import utils.MyDB;

/**
 *
 * @author Seif
 */
public class UserController implements IuserController {
    Statement ste;
    Connection conn = MyDB.getInstance().getCnx();
    
    /**
     *
     * @param u
     */
    @Override
    public void ajouterUser(User u) {
        try {
            String req = "INSERT INTO `user` ( `email`, `roles`, `password`, `telephone`, `name`) VALUES ('"+ u.getEmail() + "','" + u.getRoles() +"','" +u.getPassword() +"','"+u.getTelephone()+"','" +u.getName()+"')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("user ajoute");
            
            
        }catch (SQLException ex){
            System.out.println("user non ajoutee");
            System.out.println(ex.getMessage());
        }
    }
 public User getUserById(int userId) {
        try {
            String qry = "SELECT id, name, email, telephone  FROM user WHERE id = ?";
            PreparedStatement ps = conn.prepareCall(qry);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setTelephone(rs.getInt(4));
             
                return user;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
 
 
 public User getUserByEmail(String userId) {
        try {
            String qry = "SELECT email  FROM user WHERE id = ?";
            PreparedStatement ps = conn.prepareCall(qry);
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setEmail(rs.getString(2));
             
                return user;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
 public void modifierUF (User F){
      
      try {
         
          String req ="UPDATE User SET email ='"+F.getEmail()+"'  , telephone = '"+F.getTelephone()+"', name = '"+F.getName()+"' WHERE id="+F.getId()+";";
        //  String req ="UPDATE demande SET `offre_id`='"+D.getId_offre()+"',`user_id`='"+D.getUser_id()+"',`cv`='"+D.getCv()+"',`description`='"+D.getDescription()+"',`traitement`='"+D.getTraitement()+"' WHERE id="+D.getId()+";";
          Statement st = conn.createStatement();
          st.executeUpdate(req);
          System.out.println("User updated !!");
          
      }catch (SQLException ex) {
          System.out.println("user not Updated");
          System.out.println(ex.getMessage());
      }
  }
    @Override
    public void modifierUser(User u, int id) {
        try {
            String req = "UPDATE `user` SET  `email` = '"+ u.getEmail() + "', `roles` ='" + u.getRoles() +"' , `password` = '"+u.getPassword() +"' , `telephone` = '"+u.getTelephone()+"', `name` = '"+u.getName()+"' WHERE id =" +id;
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("user Updated");
            
            
        }catch (SQLException ex){
            System.out.println("user non Updated");
            System.out.println(ex.getMessage());
        }
    }
    @Override
     public void modifierU (User F){
      
      try {
         
          String req ="UPDATE User SET `email`='"+F.getEmail()+"',`roles`='"+F.getRoles()+"'  , `password` = '"+F.getPassword() +"' , `telephone` = '"+F.getTelephone()+"', `name` = '"+F.getName()+"' WHERE id="+F.getId()+";";
          Statement st = conn.createStatement();
          st.executeUpdate(req);
          System.out.println("User updated !!");
          
      }catch (SQLException ex) {
          System.out.println("user not Updated");
          System.out.println(ex.getMessage());
      }
  }

    @Override
    public void supprimerUser(int id) {
        try {
            String req = "DELETE FROM `user` WHERE id =" +id;
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("user deleted");
            
            
        }catch (SQLException ex){
            System.out.println("erreur");
            System.out.println(ex.getMessage());
        }
    
    }
   
 
  public int ChercherMail(String email) {
        String req = "SELECT * from `user` WHERE `user`.`email` ='" + email + "'  ";
        try {
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getString("email").equals(email)) {
                    System.out.println("mail trouvé ! ");
                    return 1;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }
      public void ResetPaswword(String email, String password) {
        String req = "UPDATE user SET password = ? WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, password);
            ps.setString(2, email);

            ps.executeUpdate();
            System.out.println("Password updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    @Override
    public List<User> afficherUser() {
        List<User> list = new ArrayList<>();
        try {
            String req = "select * from user";
            Statement st = conn.createStatement();
            ResultSet RS = st.executeQuery(req);
            
            while (RS.next()) {
                      

                User u = new User();
                u.setId(RS.getInt(1));
                u.setEmail(RS.getString("email"));
                  u.setPassword(RS.getString("password"));
                
                u.setRoles(RS.getString("roles"));
                u.setTelephone(RS.getInt("telephone"));
                u.setName(RS.getString("name"));
                list.add(u);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

   
   @Override
public User authenticate(String email, String password) {
    try {
        String qry = "SELECT id, email, password, name, telephone, roles FROM user WHERE email = ?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String encryptedPassword = rs.getString("password");
            //String decryptedPassword = PasswordEncryption.decrypt(encryptedPassword);
            
    if (BCrypt.checkpw(password, encryptedPassword)) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(password);
                user.setTelephone(rs.getInt("telephone"));
                user.setName(rs.getString("name"));
                user.setRoles(rs.getString("roles"));
                // fetch roles from the database based on user's roles column
          //      List<String> roles = Arrays.asList(rs.getString("roles").split(","));
            //    user.setRoles(roles);

                MyDB.setUserId(user.getId());

                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        return null;
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
        return null;
    }
}
     
     
     
      public boolean emailExist(String email) {
        try {
            String qry = "SELECT email FROM user WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true; // Email already exists
            } else {
                return false; // Email does not exist
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
      
      
      
	public User finduser(int id) {
		User a = new User();
		try {
			String req = "Select * from user where id =" + id;
			ste = conn.createStatement();

			ResultSet RS = ste.executeQuery(req);
			RS.first();
			a.setId(RS.getInt("id"));
			a.setEmail(RS.getString("email"));
			 a.setRoles(RS.getString("roles"));
                a.setPassword(RS.getString("password"));
                a.setTelephone(RS.getInt("telephone"));
                a.setName(RS.getString("name"));
			ste.close();
		} catch (SQLException ex) {
			System.err.println("Probleme lors du lecture d'un Album");
			System.out.println(ex.getMessage());
		}

		return (a != null) ? a : null;
	}
        
        
        
        
        
        
        
        
        
        
        
        
        
        
       
    public void banuser( int id) {
        try {
            String req = "UPDATE `user` SET   `ban` = "+1+" WHERE id =" +id;
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("user Updated");
            
            
        }catch (SQLException ex){
            System.out.println("user non Updated");
            System.out.println(ex.getMessage());
        }
    }
    
    
    
      public int getban(int id) {
        int i=0;
          try {
            String qry = "SELECT ban FROM user WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(qry);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                i=rs.getInt(1);
            } 

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }
    
}

