/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Marwen.M
 */
public class Demande {
       private int id,offre_id;
        private int user_id;
    private String cv,description,traitement="en cours de traitement";

    public Demande(int offre_id, String cv, String description) {
        this.offre_id = offre_id;
        this.cv = cv;
        this.description = description;
    }

    public Demande(int offre_id, int user_id, String cv, String description) {
        this.offre_id = offre_id;
        this.user_id = user_id;
        this.cv = cv;
        this.description = description;
    }
    

    public Demande(int id, int offre_id,int user_id, String cv, String description, String traitement) {
        this.id = id;
        this.offre_id = offre_id;
        this.user_id = user_id;
        this.cv = cv;
        this.description = description;
        this.traitement = traitement;
    }
     public Demande(int id, String cv, String description, String traitement,int user_id,int offre_id) {
        this.id = id;
        this.cv = cv;
        this.description = description;
        this.traitement = traitement;
      
        this.user_id = user_id;
        this.offre_id = offre_id;
    }

    
    public Demande() {
    }

    public Demande(int offre_id,String cv, String description, String traitement) {
         this.offre_id = offre_id;
         
        this.cv = cv;
        this.description = description;
        this.traitement = traitement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id= id;
    }

    public int getId_offre() {
        return offre_id;
    }

    public void setId_offre(int offre_id) {
        this.offre_id = offre_id;
    }
    
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ", offre_id=" + offre_id + ", user_id=" + user_id + ", cv=" + cv + ", description=" + description + ", traitement=" + traitement + '}';
    }

    
}
