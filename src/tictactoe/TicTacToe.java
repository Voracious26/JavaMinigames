package tictactoe;

import java.awt.event.*; 
import java.awt.*; 
import javaminigames.Icons;
import javax.swing.*;
import javaminigames.JavaMinigames;

public class TicTacToe {    
    // GUI elements
    public JPanel gameBoardPanel;
    public JPanel infoPanel;
    public JLabel infoArea;
    public JButton playAgainButton;
    public JButton mainMenuButton;
    // infoArea text
    private static final String startMsg = "It's Tic Tac Toe time. Click on a square to make your turn when you're ready.";
    private static final String winMsg = "You won the game! Congratulations.";
    private static final String lossMsg = "You lost. May you never forget it.";
    private static final String tieMsg = "It's a tie. How unexpected.";
    // game variables
    public boolean gameOver = false;
    public Board gameBoard;
    
    public TicTacToe(JFrame menuFrame){          
        // initialize infoPanel
        infoPanel = new JPanel();
        infoArea = new JLabel(startMsg);
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
        JavaMinigames.mainFrame.setSize(Icons.tictactoeImgSize*gameBoard.boardSize,Icons.tictactoeImgSize*gameBoard.boardSize);        
        JavaMinigames.mainFrame.add(gameBoardPanel, BorderLayout.CENTER);
        JavaMinigames.mainFrame.add(infoPanel, BorderLayout.SOUTH);
        JavaMinigames.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JavaMinigames.mainFrame.setVisible(true);
        
    }
    public void hideGameOver(){
        playAgainButton.setVisible(false);
        mainMenuButton.setVisible(false);    
    }
    public void showGameOver(){
        playAgainButton.setVisible(true);
        mainMenuButton.setVisible(true);
    }
    public void setInfoMsg(String msg){
        infoArea.setText(msg);
    }    
    public JPanel generateBoardPanel(){
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(gameBoard.boardSize,gameBoard.boardSize)); 
        gameBoard.addButtonsToPanel(boardPanel);
        return boardPanel;
    }
    public void gameOver(){
        showGameOver();
    }
    public void playAgain(){
        hideGameOver();
        setInfoMsg(startMsg);
        gameOver = false;
        gameBoard.clearBoard();
    }
    public void returnToMainMenu(){
        gameBoardPanel.setVisible(false);
        infoPanel.setVisible(false);
        JavaMinigames.loadMainFrame();
    }
    public void endWithWinner(char winner){
        switch(winner){
            case 'x':
                setInfoMsg(winMsg);
                break;
            case 'o':
                setInfoMsg(lossMsg);
                break;
            case ' ':
                setInfoMsg(tieMsg);
                break;        
        }
        gameOver = true;
        gameOver();
    }
}
