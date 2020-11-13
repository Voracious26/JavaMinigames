package checkers;

import checkers.Checker.CHECKER;
import static checkers.Checker.CHECKER;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            handleClick();
        });
    }
        
    public void handleClick(){    
            Checker thisChecker = parent.getCheckerForCoords(parent.getCoordsForTile(this));
            Tile selectedTile = parent.getSelectedTile();
            ArrayList<Move> possibleMoves = Checker.generateMovesArray(parent.getCheckerForCoords(parent.getCoordsForTile(selectedTile)), parent);
            if(selectedTile != null){
                if(this.state == CHECKER.BLANK){
                    Move thisMove = new Move(parent.getCoordsForTile(selectedTile),parent.getCoordsForTile(this),false);
                    Boolean moving = false;
                    for(Move move : possibleMoves){
                        if(move.isEqual(thisMove)){
                            thisMove = move;
                            moving = true;
                            parent.computerMove();
                            break;
                        }
                    }
                    if(moving){
                        parent.makeMove(thisMove);
                    }
                }
                else{
                    if(selectedTile != this){
                        parent.deselectAll();
                        selected = true;
                    }
                }
                parent.updateBoard();
            }
            else{            
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
                }possibleMoves = Checker.generateMovesArray(parent.getCheckerForCoords(parent.getCoordsForTile(selectedTile)), parent);
                parent.displayMoves(possibleMoves);
            }
            
            parent.updateBoard();
            selectedTile = parent.getSelectedTile();
            if(selectedTile != null){
                possibleMoves = Checker.generateMovesArray(parent.getCheckerForCoords(parent.getCoordsForTile(selectedTile)), parent);
                parent.displayMoves(possibleMoves);
            }
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

    public void setIcon(ImageIcon img){
        tileButton.setIcon(img);
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
    public void king(){
        switch(state){
            case RED:
                state = CHECKER.REDKING;
                break;
            case BLACK:
                state = CHECKER.BLACKKING;
                break;
        }
    }
}
