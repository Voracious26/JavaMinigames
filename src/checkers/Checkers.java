/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javaminigames.JavaMinigames;
import java.awt.event.ActionEvent;
import javaminigames.Icons;
import javax.swing.*;

public class Checkers {    
    public JPanel gameBoardPanel;
    public Board gameBoard;
    
    public Checkers(JFrame menuFrame){  
        gameBoard = new Board(this);
        gameBoardPanel = new JPanel();
        gameBoardPanel = generateBoardPanel();
        JavaMinigames.mainFrame.setSize(Icons.checkersImgSize*gameBoard.boardSize,Icons.checkersImgSize*gameBoard.boardSize);      
        
        // initialize mainFrame     
        JavaMinigames.mainFrame.add(gameBoardPanel, BorderLayout.CENTER);
        JavaMinigames.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JavaMinigames.mainFrame.setVisible(true);
    }
    
    public JPanel generateBoardPanel(){
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(gameBoard.boardSize,gameBoard.boardSize)); 
        gameBoard.addButtonsToPanel(boardPanel);
        return boardPanel;
    }
}
