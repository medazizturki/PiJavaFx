/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import entities.User;

/**
 *
 * @author Seif
 */
public interface IuserController {
    public void ajouterUser(User u);
    public void modifierUser(User u , int id);
    public void modifierU(User u);
    public void supprimerUser(int id);
    public List <User> afficherUser();
    
    public User authenticate(String email, String password);
    
}
