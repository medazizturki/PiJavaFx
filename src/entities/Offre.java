/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;


/**
 *
 * @author dell
 */
public class Offre {
    private int id;
    private String nom_offre;
    private Date datepub_offre;

    public Offre(int id, String nom_offre, Date datepub_offre) {
        this.id = id;
        this.nom_offre = nom_offre;
        this.datepub_offre = datepub_offre;
    }
   

    public Offre(String nom_offre, Date datepub_offre) {
        this.nom_offre = nom_offre;
        this.datepub_offre = datepub_offre;
    }

    public Offre() {
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", nom_offre=" + nom_offre + ", datepub_offre=" + datepub_offre + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_offre() {
        return nom_offre;
    }

    public void setNom_offre(String nom_offre) {
        this.nom_offre = nom_offre;
    }

    public Date getDatepub_offre() {
        return datepub_offre;
    }

    public void setDatepub_offre(Date datepub_offre) {
        this.datepub_offre = datepub_offre;
    }
    
    
}
