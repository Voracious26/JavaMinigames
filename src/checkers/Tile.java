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
            Tile selectedTile = parent.getSelectedTile();
            
            if(selectedTile != null){
                CHECKER sState = selectedTile.getState();
                if((sState == CHECKER.BLACK || sState == CHECKER.BLACKKING && Settings.CHECKERS_PLAYASBLACK) || 
                        ((sState == CHECKER.RED || sState == CHECKER.REDKING) && !Settings.CHECKERS_PLAYASBLACK)){
                    if(this.state == CHECKER.BLANK){
                        Checker checkerToMove;
                        checkerToMove = parent.getCheckerForCoords(parent.getCoordsForTile(selectedTile));
                        
                        int oldCoords[] = new int[]{checkerToMove.getX(), checkerToMove.getY()};
                        int newCoords[] = parent.getCoordsForTile(this);
                        
                        boolean canMove = false;
                        
                        if(newCoords[0] == oldCoords[0] - 1){
                            if(newCoords[1] == oldCoords[1] - 1 || newCoords[1] == oldCoords[1] + 1){
                                canMove = true;
                            }
                        }
                        if(checkerToMove.getType() == CHECKER.REDKING || checkerToMove.getType() == CHECKER.BLACKKING){
                            if(newCoords[0] == oldCoords[0] + 1){
                                if(newCoords[1] == oldCoords[1] - 1 || newCoords[1] == oldCoords[1] + 1){
                                    canMove = true;
                                }
                            }
                        }
                        
                        if(canMove){
                            checkerToMove.setX(newCoords[0]);
                            checkerToMove.setY(newCoords[1]);
                            if(newCoords[0] == 0 && !(checkerToMove.getType() == CHECKER.REDKING || checkerToMove.getType() == CHECKER.BLACKKING)){
                                checkerToMove.king();
                                king();
                            }
                            parent.updateBoard();
                        }
                    }
                }
            }
            if(selectedTile != this){
                parent.deselectAll();
            }
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
            updateIcon();
            if(thisChecker != null){
                parent.showPossibleMoves(this);
                parent.showPossibleCaptures(this);
            }
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
