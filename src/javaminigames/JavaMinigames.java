package javaminigames;
import java.util.Scanner;
import tictactoe.TicTacToe;

import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;

public class JavaMinigames {

    private static Scanner scan;
    
    // GUI elements
    private static JFrame mainMenu;    
    private static JButton[] menuButtons;
    private static JPanel buttonPanel;
    private static JLabel welcomeMessage;
    
    // GUI text
    private static String welcomeMsg = "Welcome to Java Minigames! Click to choose a game.";
    private static String[] buttonNames = {"TicTacToe", "Checkers", "Chess"};
    
    // Game objects
    private static TicTacToe tictactoe;
    
    public static void main(String[] args) {
        scan = new Scanner(System.in);
        initializeMenu();
        mainMenu.show();
    }
    
    public static void initializeMenu(){
        mainMenu = new JFrame();
        buttonPanel = new JPanel();
        
        welcomeMessage = new JLabel(welcomeMsg);
        buttonPanel.add(welcomeMessage);
        
        menuButtons = new JButton[buttonNames.length];
        for (int i = 0; i < menuButtons.length; ++i){
            menuButtons[i] = new JButton(buttonNames[i]);
            buttonPanel.add(menuButtons[i]);
        }
        
        mainMenu.add(buttonPanel);
        mainMenu.setSize(400, 100);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
