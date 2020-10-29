package javaminigames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import tictactoe.TicTacToe;


public class Settings implements ItemListener {
    // TICTACTOE SETTINGS
    public static int TICTACTOE_BOARDSIZE = 3;
    public static boolean TICTACTOE_SMARTAI = false;
    
    // CHECKERS 
    public static boolean CHECKERS_PLAYASBLACK = false;
    
    JPanel settingsPanel;
    JLabel[] headers;
    String[] headerText = {"TicTacToe Settings", "Checkers Settings", "Chess Settings"};
    JCheckBox smartAI;
    JButton mainMenuButton;
    
    JFrame parentFrame;
    
    public void load(JFrame menuFrame){ 
        parentFrame = menuFrame;
        settingsPanel = new JPanel();
        settingsPanel.setLayout(new BorderLayout());
        
        
        headers = new JLabel[headerText.length];
        for(int i = 0; i < headerText.length; ++i){
            headers[i] = new JLabel(headerText[i]);
        }
        
        // TIC TAC TOE SETTINGS
        settingsPanel.add(headers[0]);
        
        smartAI = new JCheckBox("Smart AI", TICTACTOE_SMARTAI);
        smartAI.addItemListener(this);
        settingsPanel.add(smartAI, BorderLayout.CENTER);
        
        // CHECKERS SETTINGS
        
        // CHESS SETTINGS
        
        // MAIN MENU BUTTON        
        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener((ActionEvent event) -> {
            settingsPanel.setVisible(false);
            JavaMinigames.loadMainFrame();
        });
        settingsPanel.add(mainMenuButton, BorderLayout.LINE_END);
        
        parentFrame.add(settingsPanel);
        settingsPanel.setVisible(true);
    
    }    
    
    public void itemStateChanged(ItemEvent e){ 
        // if the state of checkbox1 is changed 
        if (e.getSource() == smartAI) {
            if (e.getStateChange() == 1) 
                TICTACTOE_SMARTAI = true; 
            else                
                TICTACTOE_SMARTAI = false; 
        }
    }
}
