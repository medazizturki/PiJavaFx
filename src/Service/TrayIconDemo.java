/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
/**
 *
 * @author FAROUK
 */
public class TrayIconDemo {
    
    
    public void notifme (String message)
    {
         if (SystemTray.isSupported()) {
            TrayIconDemo td = new TrayIconDemo();
             try {
                 td.displayTray(message);
             } catch (AWTException ex) {
                 Logger.getLogger(TrayIconDemo.class.getName()).log(Level.SEVERE, null, ex);
             }
        } else {
            System.err.println("System tray not supported!");
        }
    }
        public void displayTray (String message) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("/Service/code.png");
                URL imageURL = TrayIconDemo.class.getResource("/Service/code.png");

               ImageIcon icon = new ImageIcon(imageURL);

        TrayIcon trayIcon = new TrayIcon(icon.getImage(), "Tray Demo",null);
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage(" "+message+" ", "reclamation ", MessageType.INFO);
    }
}
