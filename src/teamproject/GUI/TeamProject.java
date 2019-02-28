/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ahmetsesli
 */
public class TeamProject {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LogIn logIn = new LogIn();
        
        JFrame frame = new JFrame();
        frame.add(logIn);
        
        frame.setVisible(true);
        
    }    
}
