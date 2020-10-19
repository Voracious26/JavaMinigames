package tictactoe;

import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;
import javaminigames.JavaMinigames;

public class TicTacToe {    
    // GUI elements
    public JFrame mainFrame;   
    public JPanel gameBoardPanel;
    public JPanel infoPanel;
    public JTextArea infoArea;
    public JButton playAgainButton;
    public JButton mainMenuButton;
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
        // initialize infoPanel
        infoPanel = new JPanel();
        infoArea = new JTextArea (1, 30);
        infoArea.setText(startMsg);
        infoArea.setEditable(false);
        playAgainButton = new JButton("Play again");
        mainMenuButton = new JButton("Main menu");
        mainMenuButton.addActionListener((ActionEvent e) -> {
            returnToMainMenu();
        });
        playAgainButton.addActionListener((ActionEvent e) -> {
            playAgain();
        });
        hideGameOver();
        infoPanel.add(infoArea);
        infoPanel.add(playAgainButton);
        infoPanel.add(mainMenuButton);
        
        // gameboard
        gameBoard = new Board(this);
        gameBoardPanel = generateBoardPanel();
        
        // initialize mainFrame
        mainFrame = menuFrame;
        mainFrame.setSize(600,600);        
        mainFrame.add(gameBoardPanel, BorderLayout.CENTER);
        mainFrame.add(infoPanel, BorderLayout.SOUTH);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        
    }
    public void hideGameOver(){
        infoArea.setVisible(true);
        playAgainButton.setVisible(false);
        mainMenuButton.setVisible(false);    
    }
    public void showGameOver(){
        infoArea.setVisible(false);
        playAgainButton.setVisible(true);
        mainMenuButton.setVisible(true);
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
    public void gameOver(){
        showGameOver();
    }
    public void playAgain(){
        hideGameOver();
        setInfoMsg(startMsg);
        gameBoard.clearBoard();
    }
    public void returnToMainMenu(){
        mainFrame.remove(gameBoardPanel);
        mainFrame.remove(infoPanel);
        JavaMinigames.loadMainFrame();
    }
}
