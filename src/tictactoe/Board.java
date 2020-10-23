package tictactoe;

import java.util.concurrent.ThreadLocalRandom;
import javaminigames.Settings;
import javax.swing.*;

public class Board {
    public int boardSize = 3;
    TicTacToe parent;
    Tile tiles[][];
    
    public Board(TicTacToe parentBoard){
        parent = parentBoard;
        // initialize 2D tile array
        tiles = new Tile[boardSize][boardSize];
        for(int i = 0; i < boardSize; ++i){
            for(int j = 0; j < boardSize; ++j){
                tiles[i][j] = new Tile(this);
            }
        }
    }
    public Board(TicTacToe parentBoard, int size){
        boardSize = size;
        parent = parentBoard;
        // initialize 2D tile array
        tiles = new Tile[boardSize][boardSize];
        for(int i = 0; i < boardSize; ++i){
            for(int j = 0; j < boardSize; ++j){
                tiles[i][j] = new Tile(this);
            }
        }
    }
    
    public void clearBoard(){
        for(int i = 0; i < boardSize; ++i){
            for(int j = 0; j < boardSize; ++j){
                tiles[i][j].setState(' ');
                tiles[i][j].clearIcon();
            }
        }
    }
    public JPanel addButtonsToPanel(JPanel toAdd){
        for(int i = 0; i < boardSize; ++i){
            for(int j = 0; j < boardSize; ++j){
                toAdd.add(tiles[i][j].getButton());
            }
        }
        return toAdd;
    }
    
    public boolean isFull(){
        boolean full = true;
        for(int i = 0; i < boardSize; ++i){
            for(int j = 0; j < boardSize; ++j){
                if(tiles[i][j].getState() == ' '){
                    full = false;
                }
            }
        }
        return full;
    }

    
    public void computerMove(){     
        if(Settings.TICTACTOE_SMARTAI){
            /**
            LOGIC BREAKDOWN (in order of priority):
            * If we can make a winning move, do it
            * If we can block the player from making a winning move, do it
            * If we have one O in a row/column/diagonal, add a second if the lane is open
            * Otherwise, random move 
            **/
            // "If we can make a winning move, do it"
            // CHECK FOR TWO IN A ROW
            for(int i = 0; i < boardSize; ++i){
                int oCount = 0;
                for(int j = 0; j < boardSize; ++j){
                    if(tiles[i][j].getState() == 'o'){
                        oCount++;                        
                    }
                }
                // if we have two in this row
                if(oCount == boardSize - 1){
                    // find the blank tile (if it exists) and store it in tmp
                    Tile tmp = null;
                    for(int k = 0; k < boardSize; ++k){
                        if(tiles[i][k].getState() == ' '){
                                tmp = tiles[i][k];
                        }
                    }
                    if(tmp != null){
                        tmp.setIcon('o');
                        tmp.setState('o');
                        return;
                    }
                }
            }
            // CHECK FOR TWO IN A COLUMN
            for(int j = 0; j < boardSize; ++j){
                int oCount = 0;
                for(int i = 0; i < boardSize; ++i){
                    if(tiles[i][j].getState() == 'o'){
                        oCount++;                        
                    }
                }
                // if we have two in this row
                if(oCount == boardSize - 1){
                    // find the blank tile (if it exists) and store it in tmp
                    Tile tmp = null;
                    for(int k = 0; k < boardSize; ++k){
                        if(tiles[k][j].getState() == ' '){
                            tmp = tiles[k][j];
                            break;
                        }
                    }
                    if(tmp != null){
                        tmp.setIcon('o');
                        tmp.setState('o');
                        return;
                    }
                }
            }            
            // CHECK FOR TWO IN A DOWN RIGHT DIAGONAL         
            int oCount = 0;
            for(int i = 0; i < boardSize; ++i){
                if(tiles[i][i].getState() == 'o'){
                    oCount++;
                }
            }
            if(oCount == boardSize - 1){
                Tile tmp = null;
                for(int i = 0; i < boardSize; ++i){
                    if(tiles[i][i].getState() == ' '){
                        tmp = tiles[i][i];
                        break;
                    }
                }
                if (tmp != null){
                    tmp.setIcon('o');
                    tmp.setState('o');
                    return;
                }
            }            
            // CHECK FOR TWO IN A DOWN LEFT DIAGONAL         
            oCount = 0;
            for(int i = 0; i < boardSize; ++i){
                if(tiles[i][boardSize - 1 - i].getState() == 'o'){
                    oCount++;
                }
            }
            System.out.println("COUNT: "+oCount);
            if(oCount == boardSize - 1){
                Tile tmp = null;
                for(int i = 0; i < boardSize; ++i){
                    if(tiles[i][boardSize - 1 - i].getState() == ' '){
                        tmp = tiles[i][boardSize - 1 - i];
                        break;
                    }
                }
                if (tmp != null){
                    tmp.setIcon('o');
                    tmp.setState('o');
                    return;
                }
            }
            
            
            // else do random
            randomComputerMove();
            
            
        }
        else{
            randomComputerMove();
        }
    }
    public void randomComputerMove(){
        int xRand = ThreadLocalRandom.current().nextInt(0, boardSize);
        int yRand = ThreadLocalRandom.current().nextInt(0, boardSize);
        while(tiles[xRand][yRand].getState() != ' '){
            xRand = ThreadLocalRandom.current().nextInt(0, boardSize);
            yRand = ThreadLocalRandom.current().nextInt(0, boardSize);
        }
        tiles[xRand][yRand].setIcon('o');
        tiles[xRand][yRand].setState('o');
    }
    
    public char checkWin(){
        char winner = ' ';
        char prev;
        int soFar;
        
        // horizontal
        for (int i = 0; i < boardSize; i++){
            soFar = 0;
            prev = ' ';      
            for (int j = 0; j < boardSize; j++){
                soFar++;
                if (j != 0 && prev != tiles[i][j].getState())
                    soFar = 0;
                if (tiles[i][j].getState() == ' ')
                    soFar = 0;
                if (soFar >= boardSize)
                    winner = tiles[i][j].getState();                
                prev = tiles[i][j].getState();
            }
        }
        
        // vertical
        for (int j = 0; j < boardSize; j++){
            soFar = 0;
            prev = ' ';      
            for (int i = 0; i < boardSize; i++){
                soFar++;
                if (i != 0 && prev != tiles[i][j].getState())
                    soFar = 0;
                if (tiles[i][j].getState() == ' ')
                    soFar = 0;
                if (soFar >= boardSize)
                    winner = tiles[i][j].getState();                
                prev = tiles[i][j].getState();
            }
        }    
        
        // down right diagonal
        if(tiles[0][0].getState() != ' '){                 
            boolean match = true;
            prev = tiles[0][0].getState();
            for (int k = 1; k < boardSize; ++k){
                if(tiles[k][k].getState() != prev){
                    match = false;
                }
            }
            if(match){
                winner = prev;
            }
        }
        
        // down left diagonal
        if(tiles[boardSize-1][0].getState() != ' '){                 
            boolean match = true;
            prev = tiles[boardSize-1][0].getState();
            for (int k = boardSize - 1; k >= 0; --k){
                if(tiles[k][boardSize - 1 - k].getState() != prev){
                    match = false;
                }
            }
            if(match){
                winner = prev;
            }
        }
        return winner;
    }
    
}
