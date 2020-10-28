package checkers;

import java.awt.event.ActionEvent;
import javaminigames.Icons;
import javax.swing.*;

public class Tile {
    private char state = ' ';
    private JButton tileButton;
    private Board parent;
    
    public Tile(Board parentBoard){
        parent = parentBoard;
        tileButton = new JButton();
        
        tileButton.addActionListener((ActionEvent e) -> {
            Checker thisChecker = parent.getCheckerForCoords(parent.getCoordsForTile(this));
            if(thisChecker != null){
            
            }
            switch(state){
                case 'r':
                    tileButton.setIcon(Icons.redSelectedImg);
                    break;
                case 'j':
                    tileButton.setIcon(Icons.redKingSelectedImg);
                    break;
                case 'b':
                    tileButton.setIcon(Icons.blackSelectedImg);
                    break;
                case 'k':
                    tileButton.setIcon(Icons.blackKingSelectedImg);
                    break;
                default:
                    break;
            }
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
    
    public void clearIcon(){
        tileButton.setIcon(null);
    }
        
    public void updateIcon(){
        if(state == 'r'){
            tileButton.setIcon(Icons.redImg);
        }
        else if(state == 'j'){
            tileButton.setIcon(Icons.redKingImg);
        }
        else if(state == 'b'){
            tileButton.setIcon(Icons.blackImg);
        }
        else if(state == 'k'){
            tileButton.setIcon(Icons.blackKingImg);
        }
        else if(state == ' '){
            tileButton.setIcon(Icons.blankCheckersImg);
        }
    }
}
