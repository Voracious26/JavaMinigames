/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import java.awt.BorderLayout;
import javaminigames.JavaMinigames;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Checkers {    
    public JPanel gameBoardPanel;
    
    public Checkers(JFrame menuFrame){   
        gameBoardPanel = new JPanel();
        
        // initialize mainFrame     
        JavaMinigames.mainFrame.add(gameBoardPanel, BorderLayout.CENTER);
        JavaMinigames.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JavaMinigames.mainFrame.setVisible(true);
    }
}
