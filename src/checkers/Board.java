package checkers;

import checkers.Checker.CHECKER;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javaminigames.Icons;
import javaminigames.Settings;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.util.Random;

public class Board {
    public int boardSize = 8;
    Checkers parent;
    Tile tiles[][];
    ArrayList<Checker> redCheckers = new ArrayList<Checker>();
    ArrayList<Checker> blackCheckers = new ArrayList<Checker>();
    private Random rand;
    
    public Board(Checkers parentBoard){
        parent = parentBoard;
        rand = new Random();
        // initialize 2D tile array
        tiles = new Tile[boardSize][boardSize];
        for(int i = 0; i < boardSize; ++i){
            for(int j = 0; j < boardSize; ++j){
                tiles[i][j] = new Tile(this);
            }
        }
        for(int i = 0; i < boardSize; i+=2){
            redCheckers.add(new Checker(boardSize-1, i, CHECKER.RED));
            redCheckers.add(new Checker(boardSize-2, i+1, CHECKER.RED));
            redCheckers.add(new Checker(boardSize-3, i, CHECKER.RED));
        }
                
        for(int i = 0; i < boardSize; i+=2){
            //blackCheckers.add(new Checker(0, i+1, CHECKER.BLACK));
            blackCheckers.add(new Checker(1, i, CHECKER.BLACK));
            //blackCheckers.add(new Checker(2, i+1, CHECKER.BLACK));
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
                tiles[i][j].setState(CHECKER.BLANK);
                tiles[i][j].updateIcon();
            }
        }
    }
    
    public void deselectAll(){
        for(int i = 0; i < boardSize; ++i){
            for(int j = 0; j < boardSize; ++j){
                tiles[i][j].setSelected(false);
                tiles[i][j].updateIcon();
            }
        }
    
    }
    
    public void updateBoard(){
        clearBoard();
        for(Checker c : redCheckers){
            if(c.getType() == CHECKER.REDKING){
                tiles[c.getX()][c.getY()].setState(CHECKER.REDKING);                
            }
            else if(c.getType() == CHECKER.RED){
                tiles[c.getX()][c.getY()].setState(CHECKER.RED); 
            }            
        }
        for(Checker c : blackCheckers){
            if(c.getType() == CHECKER.BLACKKING){
                tiles[c.getX()][c.getY()].setState(CHECKER.BLACKKING);                
            }
            else if(c.getType() == CHECKER.BLACK){
                tiles[c.getX()][c.getY()].setState(CHECKER.BLACK); 
            }            
        }
        for(int i = 0; i < boardSize; ++i){
            for(int j = 0; j < boardSize; ++j){
                tiles[i][j].updateIcon();
            }
        }
    }
    
    public int[] getCoordsForTile(Tile tile){
        int[] coords = new int[]{-1,-1};
        
        for(int i = 0; i < boardSize; ++i){
            for(int j = 0; j < boardSize; ++j){
                if(tiles[i][j] == tile){
                    coords[0] = i;
                    coords[1] = j;
                }
            }
        }
        return coords;
    }
    
    public Checker getCheckerForCoords(int[] coords){
        Checker tmp;
        if(coords[0] != -1 && coords[1] != -1){
            for(int i = 0; i < redCheckers.size(); ++i){
                tmp = redCheckers.get(i);
                if(tmp.getX() == coords[0] && tmp.getY() == coords[1]){
                    return tmp;
                }    
            }
            for(int i = 0; i < blackCheckers.size(); ++i){
                tmp = blackCheckers.get(i);
                if(tmp.getX() == coords[0] && tmp.getY() == coords[1]){
                    return tmp;
                }
            }
        }
        return null;
    }
      
    public Tile getSelectedTile(){
        for(int i = 0; i < boardSize; ++i){
            for(int j = 0; j < boardSize; ++j){
                if(tiles[i][j].getSelected() == true){
                    return tiles[i][j];
                }
            }
        }
        return null;
    }
    
    public void removeChecker(Checker toRemove){
        if(toRemove.getType() == CHECKER.BLACK || toRemove.getType() == CHECKER.BLACKKING){
            blackCheckers.remove(toRemove);
        }
        else{
            redCheckers.remove(toRemove);
        }
    }
    
    public void displayMoves(ArrayList<Move> possibleMoves){
        updateBoard();
        for(Move move : possibleMoves){
            ImageIcon iconToSet;
            if(move.capt){
                iconToSet = Icons.possibleCaptureImg;
            }
            else{
                iconToSet = Icons.possibleMoveImg;
            }
            if(move.dst[0] >= 0 && move.dst[0] < boardSize && move.dst[1] >= 0 && move.dst[1] < boardSize){
                tiles[move.dst[0]][move.dst[1]].setIcon(iconToSet);
            }
        }
    }
    
    public void makeMove(Move move){
        Checker toMove = getCheckerForCoords(move.src);
        toMove.setX(move.dst[0]);
        toMove.setY(move.dst[1]);
        if(move.capt){
            Checker toRemove = getCheckerForCoords(move.getCaptCoords());
            removeChecker(toRemove);
        }
        if(Settings.CHECKERS_PLAYASBLACK){            
            if(toMove.getType() == CHECKER.RED && move.dst[0] == boardSize-1){
                toMove.king();
            }
            else if(toMove.getType() == CHECKER.BLACK && move.dst[0] == 0){
                toMove.king();
            }
            
        }
        else{
            if(toMove.getType() == CHECKER.RED && move.dst[0] == 0){
                toMove.king();
            }
            else if(toMove.getType() == CHECKER.BLACK && move.dst[0] == boardSize-1){
                toMove.king();
            }
        }
    }
    
    public void computerMove(){
        ArrayList<Move> possibleMoves = new ArrayList<Move>();
        if(Settings.CHECKERS_PLAYASBLACK){
            for(Checker checker : redCheckers){
                possibleMoves.addAll(Checker.generateMovesArray(checker, this));
            }
        }
        else{
            for(Checker checker : blackCheckers){
                possibleMoves.addAll(Checker.generateMovesArray(checker, this));
            }
        }
        if(possibleMoves.size() == 0){
            return;
        }
        else{
            int moveIndex = rand.nextInt(possibleMoves.size());
            makeMove(possibleMoves.get(moveIndex));   
        }
    }
    
    public Boolean inBounds(int[] nums){
        if(nums[0] >= 0 && nums[0] <= boardSize - 1){
            if(nums[1] >= 0 && nums[1] <= boardSize - 1){
                return true;
            }
        }
        return false;
    }
}
