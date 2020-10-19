package tictactoe;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Tile {
    private char state = ' ';
    private JButton tileButton;
    private Board parent;
    
    public Tile(Board parentBoard){
        parent = parentBoard;
        tileButton = new JButton();
        tileButton.addActionListener((ActionEvent e) -> {
            JButton b = (JButton)e.getSource();
            getTicTacToe().gameOver();
        });
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
}


