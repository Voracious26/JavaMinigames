/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Checkers {    
    public JFrame mainFrame;   
    public JPanel gameBoardPanel;
    
    public Checkers(JFrame menuFrame){   
        gameBoardPanel = new JPanel();
        
        // initialize mainFrame
        mainFrame = menuFrame;      
        mainFrame.add(gameBoardPanel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
