package tictactoe;

import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;
import javaminigames.JavaMinigames;

public class TicTacToe {    
    // GUI elements
    public JFrame mainFrame;   
    public JPanel gameBoardPanel;
    // game variables
    public boolean gameOver = false;
    public int boardSize = 3; 
    public Board gameBoard;
    
    public TicTacToe(JFrame menuFrame){          
        gameBoard = new Board(this);
        
        mainFrame = menuFrame;
        gameBoardPanel = generateBoardPanel();
        mainFrame.setSize(500,500);        
        mainFrame.add(gameBoardPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        
    }
    
    public JPanel generateBoardPanel(){
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize,boardSize)); 
        gameBoard.addButtonsToPanel(boardPanel);
        return boardPanel;
    }
    
    public void returnToMainMenu(){
        mainFrame.remove(gameBoardPanel);
        JavaMinigames.loadMainFrame();
    }
}
