package checkers;

import checkers.Checker.CHECKER;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JPanel;

public class Board {
    public int boardSize = 8;
    Checkers parent;
    Tile tiles[][];
    ArrayList<Checker> redCheckers = new ArrayList<Checker>();
    ArrayList<Checker> blackCheckers = new ArrayList<Checker>();
    
    public Board(Checkers parentBoard){
        parent = parentBoard;
        // initialize 2D tile array
        tiles = new Tile[boardSize][boardSize];
        for(int i = 0; i < boardSize; ++i){
            for(int j = 0; j < boardSize; ++j){
                tiles[i][j] = new Tile(this);
            }
        }
        for(int i = 0; i < boardSize; ++i){
            redCheckers.add(new Checker(boardSize-1, i, CHECKER.RED));
            redCheckers.add(new Checker(boardSize-2, i, CHECKER.RED));
        }
        for(int i = 0; i < boardSize; ++i){
            blackCheckers.add(new Checker(0, i, CHECKER.BLACK));
            blackCheckers.add(new Checker(1, i, CHECKER.BLACK));
        }
        updateBoard();
    }
    
    public JPanel addButtonsToPanel(JPanel toAdd){
        for(int i = 0; i < boardSize; ++i){
            for(int j = 0; j < boardSize; ++j){
                toAdd.add(tiles[i][j].getButton());
            }
        }
        return toAdd;
    }
    
    public void clearBoard(){
        for(int i = 0; i < boardSize; ++i){
            for(int j = 0; j < boardSize; ++j){
                tiles[i][j].setState(' ');
                tiles[i][j].clearIcon();
            }
        }
    }
    
    public void updateBoard(){
        clearBoard();
        for(Checker c : redCheckers){
            if(c.getType() == CHECKER.REDKING){
                tiles[c.getX()][c.getY()].setState('j');                
            }
            else if(c.getType() == CHECKER.RED){
                tiles[c.getX()][c.getY()].setState('r'); 
            }            
        }
        for(Checker c : blackCheckers){
            if(c.getType() == CHECKER.BLACKKING){
                tiles[c.getX()][c.getY()].setState('k');                
            }
            else if(c.getType() == CHECKER.BLACK){
                tiles[c.getX()][c.getY()].setState('b'); 
            }            
        }
        for(int i = 0; i < boardSize; ++i){
            for(int j = 0; j < boardSize; ++j){
                tiles[i][j].updateIcon();
            }
        }
    }
}
