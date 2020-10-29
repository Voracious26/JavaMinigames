package checkers;

import checkers.Checker.CHECKER;
import static checkers.Checker.CHECKER;
import java.awt.event.ActionEvent;
import javaminigames.Icons;
import javaminigames.Settings;
import javax.swing.*;

public class Tile {
    private CHECKER state = CHECKER.BLANK;
    private boolean selected = false;
    private JButton tileButton;
    private Board parent;
    
    public Tile(Board parentBoard){
        parent = parentBoard;
        tileButton = new JButton();
        
        tileButton.addActionListener((ActionEvent e) -> {
            Checker thisChecker = parent.getCheckerForCoords(parent.getCoordsForTile(this));
            if(thisChecker != null){
                if(Settings.CHECKERS_PLAYASBLACK){
                    if(thisChecker.getType() == CHECKER.BLACK || thisChecker.getType() == CHECKER.BLACKKING){
                        selected = true;
                    }
                }
                else{
                    if(thisChecker.getType() == CHECKER.RED || thisChecker.getType() == CHECKER.REDKING){
                        selected = true;
                    }
                }
            }
            if(selected){
                parent.deselectAll();
                selected = true;                        
            }
            updateIcon();
        });
    }
        
    public CHECKER getState(){
        return state;
    }
    public void setState(CHECKER newState){
        state = newState;
    }
    
    public boolean getSelected(){
        return selected;
    }
    public void setSelected(boolean newSelected){
        selected = newSelected;
    }
    
    public JButton getButton(){
        return tileButton;
    }
    
    public void clearIcon(){
        tileButton.setIcon(null);
    }
        
    public void updateIcon(){
        if(selected){
                switch(state){
                    case RED:
                        tileButton.setIcon(Icons.redSelectedImg);
                        break;
                    case REDKING:
                        tileButton.setIcon(Icons.redKingSelectedImg);
                        break;
                    case BLACK:
                        tileButton.setIcon(Icons.blackSelectedImg);
                        break;
                    case BLACKKING:
                        tileButton.setIcon(Icons.blackKingSelectedImg);
                        break;
                    case BLANK:
                        tileButton.setIcon(Icons.blankCheckersImg);
                        break;
                    default:
                        break;
                }
        }
        else{
            switch(state){
                    case RED:
                        tileButton.setIcon(Icons.redImg);
                        break;
                    case REDKING:
                        tileButton.setIcon(Icons.redKingImg);
                        break;
                    case BLACK:
                        tileButton.setIcon(Icons.blackImg);
                        break;
                    case BLACKKING:
                        tileButton.setIcon(Icons.blackKingImg);
                        break;
                    case BLANK:
                        tileButton.setIcon(Icons.blankCheckersImg);
                        break;
                    default:
                        break;
            }
        }
    }
}
