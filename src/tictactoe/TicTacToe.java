package tictactoe;

import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;
import javaminigames.JavaMinigames;

public class TicTacToe {    
    // GUI elements
    public JFrame mainFrame;   
    public JPanel gameBoardPanel;
    public JTextArea infoArea;
    // infoArea text
    private String startMsg = "It's Tic Tac Toe time. Click on a square to make your turn when you're ready.";
    private String winMsg = "You won the game! Congratulations.";
    private String lossMsg = "You lost. May you never forget it.";
    private String tieMsg = "It's a tie. How unexpected.";
    // game variables
    public boolean gameOver = false;
    public int boardSize = 3; 
    public Board gameBoard;
    
    public TicTacToe(JFrame menuFrame){          
        gameBoard = new Board(this);
        infoArea = new JTextArea (1, 30);
        infoArea.setText(startMsg);
        
        mainFrame = menuFrame;
        gameBoardPanel = generateBoardPanel();
        mainFrame.setSize(500,500);        
        mainFrame.add(gameBoardPanel, BorderLayout.CENTER);
        mainFrame.add(infoArea, BorderLayout.SOUTH);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        
    }
    
    public void setInfoMsg(String msg){
        infoArea.setText(msg);
    }
    
    public JPanel generateBoardPanel(){
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize,boardSize)); 
        gameBoard.addButtonsToPanel(boardPanel);
        return boardPanel;
    }
    
    public void returnToMainMenu(){
        mainFrame.remove(gameBoardPanel);
        mainFrame.remove(infoArea);
        JavaMinigames.loadMainFrame();
    }
}
