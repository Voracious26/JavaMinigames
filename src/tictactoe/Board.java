package tictactoe;

import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

public class Board {
    int boardSize = 3; 
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
        int xRand = ThreadLocalRandom.current().nextInt(0, boardSize);
        int yRand = ThreadLocalRandom.current().nextInt(0, boardSize);
        while(tiles[xRand][yRand].getState() != ' '){
            xRand = ThreadLocalRandom.current().nextInt(0, boardSize);
            yRand = ThreadLocalRandom.current().nextInt(0, boardSize);
        }
        tiles[xRand][yRand].setIcon('o');
        tiles[xRand][yRand].setState('o');
    }
    
}
