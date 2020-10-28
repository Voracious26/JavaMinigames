package tictactoe;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javaminigames.Icons;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Tile {
    private char state = ' ';
    private JButton tileButton;
    private Board parent;
    
    public Tile(Board parentBoard){
        parent = parentBoard;
        tileButton = new JButton();
        tileButton.addActionListener((ActionEvent e) -> {
            char gameWinner;
            if(state == ' ' && !getTicTacToe().gameOver){
                tileButton.setIcon(Icons.xImg);
                setState('x');                
                gameWinner = parent.checkWin();
                if(gameWinner != ' '){
                    getTicTacToe().endWithWinner(gameWinner);
                }
                else if(parent.isFull()){
                    getTicTacToe().endWithWinner(gameWinner);        
                }
                else{
                    parent.computerMove();                    
                    gameWinner = parent.checkWin();
                    if(gameWinner != ' '){
                        getTicTacToe().endWithWinner(gameWinner);
                    }
                    else if(parent.isFull()){
                        getTicTacToe().endWithWinner(gameWinner);        
                    }
                }
            }
        });
        setIcon(' ');
    }
    
    public char getState(){
        return state;
    }
    public void setState(char newState){
        state = newState;
    }
    
    public JButton getButton(){
        return tileButton;
    }
    
    private TicTacToe getTicTacToe(){
        return parent.parent;
    }
    
    public void clearIcon(){
        setIcon(' ');
    }
    
    public void setIcon(char icon){
        switch(icon){
            case 'x':
                tileButton.setIcon(Icons.xImg);
                break;
            case 'o':
                tileButton.setIcon(Icons.oImg);
                break;
            case ' ':
                tileButton.setIcon(Icons.blankTicTacToeImg);
                break;
        }
    }
}


