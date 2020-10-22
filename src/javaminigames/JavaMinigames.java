package javaminigames;
import java.util.Scanner;
import tictactoe.TicTacToe;

import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;

public class JavaMinigames {
    private static Scanner scan;    
    // GUI elements
    private static JFrame mainFrame;    
    private static JButton[] menuButtons;
    private static JPanel mainPanel;
    private static JLabel welcomeMessage;    
    // GUI text
    private static String welcomeMsg = "Welcome to Java Minigames! Click to choose a game.";
    private static String[] buttonNames = {"TicTacToe", "Checkers", "Chess", "Settings"};    
    // Game objects
    private static TicTacToe tictactoe;
    private static Settings settings;
    
    public static void main(String[] args) {
        scan = new Scanner(System.in);
        mainFrame = new JFrame();  
        settings = new Settings();
        loadMainFrame();
    }
    
    public static void loadMainFrame(){
        mainPanel = generateMainMenuPanel();
        mainFrame.add(mainPanel);
        mainFrame.setSize(400, 100);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }    
    
    public static JPanel generateMainMenuPanel(){
        JPanel mainMenuPanel = new JPanel();
        // welcome text
        welcomeMessage = new JLabel(welcomeMsg);
        mainMenuPanel.add(welcomeMessage);               
        // buttons
        menuButtons = new JButton[buttonNames.length];
        for (int i = 0; i < menuButtons.length; ++i){
            menuButtons[i] = new JButton(buttonNames[i]);
            mainMenuPanel.add(menuButtons[i]);
        }        
        // click events
        menuButtons[0].addActionListener((ActionEvent event) -> {
            mainPanel.setVisible(false);
            tictactoe = new TicTacToe(mainFrame);
        });
        menuButtons[3].addActionListener((ActionEvent event) -> {
            mainPanel.setVisible(false);
            settings.load(mainFrame);
            
        });
        return mainMenuPanel;    
    }
}
