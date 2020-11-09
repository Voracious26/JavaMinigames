/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import java.util.ArrayList;
import java.util.Arrays;
import javaminigames.Settings;

/**
 *
 * @author harri
 */
public class Checker {
    public enum CHECKER{
        RED,
        REDKING,
        BLACK,
        BLACKKING,
        BLANK
    }
    
    private int x;
    private int y;
    private CHECKER type;
    
    public Checker(int xCoord, int yCoord, CHECKER checkerType){
        x = xCoord;
        y = yCoord;
        type = checkerType;
    }
    
    public int getX(){
        return x;
    }
    public void setX(int newX){
        x = newX;
    }
    
    public int getY(){
        return y;
    }
    public void setY(int newY){
        y = newY;
    }
    
    public CHECKER getType(){
        return type;
    }
    public void setType(CHECKER newType){
        type = newType;
    }
    
    public int[] getCoords(){
        int[] tmp = new int[]{x, y};
        return tmp;
    }
    
    public void king(){
        switch(type){
            case RED:
                type = CHECKER.REDKING;
                break;
            case BLACK:
                type = CHECKER.BLACKKING;
                break;
        }
    }
    
    
    public static boolean isOpponent(CHECKER checker){
        if(checker == CHECKER.BLACK || checker == CHECKER.BLACKKING){
            if(!Settings.CHECKERS_PLAYASBLACK){
                return true;
            }
        }
        else if(checker == CHECKER.RED || checker == CHECKER.REDKING){
            if(Settings.CHECKERS_PLAYASBLACK){
                return true;
            }
        }
        return false;
    }
    
    public static boolean isOpponent(CHECKER thisChecker, CHECKER otherChecker){
        if(thisChecker == CHECKER.BLACK || thisChecker == CHECKER.BLACKKING){
            if(otherChecker == CHECKER.RED || otherChecker == CHECKER.REDKING){
                return true;
            }
        }
        else if(thisChecker == CHECKER.RED || thisChecker == CHECKER.REDKING){
            if(otherChecker == CHECKER.BLACK || otherChecker == CHECKER.BLACKKING){
                return true;
            }
        }
        return false;
    }
    
    public static boolean isPlayers(CHECKER checker){
        if(!isOpponent(checker) && checker != CHECKER.BLANK){
            return true;
        }
        return false;
    }
    
    public static ArrayList<Move> generateMovesArray(Checker checkerToMove, Board b){
        ArrayList<Move> moves = new ArrayList<>();
        
        if(checkerToMove == null){
            return moves;
        }
        else{
            int oldCoords[] = new int[]{checkerToMove.getX(), checkerToMove.getY()};
            int newCoords[] = new int[2];
            
            // regular moves
            newCoords[0] = oldCoords[0] - 1;
            newCoords[1] = oldCoords[1] - 1;
            if(oldCoords[0] > 0 && oldCoords[1] > 0){
                if(b.tiles[newCoords[0]][newCoords[1]].getState() == CHECKER.BLANK){
                    moves.add(new Move(oldCoords, newCoords, false));
                }
            }
            
            newCoords[0] = oldCoords[0] - 1;
            newCoords[1] = oldCoords[1] + 1;            
            if(oldCoords[0] > 0 && oldCoords[1] < b.boardSize-1){
                if(b.tiles[newCoords[0]][newCoords[1]].getState() == CHECKER.BLANK){
                    moves.add(new Move(oldCoords, newCoords, false));
                }
            }   
            
            // king moves
            if(checkerToMove.getType() == CHECKER.BLACKKING || checkerToMove.getType() == CHECKER.REDKING){           
                newCoords[0] = oldCoords[0] + 1;
                newCoords[1] = oldCoords[1] - 1;
                if(oldCoords[0] < b.boardSize-1 && oldCoords[1] > 0){
                    if(b.tiles[newCoords[0]][newCoords[1]].getState() == CHECKER.BLANK){
                        moves.add(new Move(oldCoords, newCoords, false));
                    }
                }
                newCoords[1] = oldCoords[1] + 1;
                if(oldCoords[0] < b.boardSize-1 && oldCoords[1] < b.boardSize-1){
                    if(b.tiles[newCoords[0]][newCoords[1]].getState() == CHECKER.BLANK){
                        moves.add(new Move(oldCoords, newCoords, false));
                    }
                }
            
            }

            // regular captures
            Checker checkerToCapture;  
            newCoords[0] = oldCoords[0] + 2;
            newCoords[1] = oldCoords[1] + 2;
            checkerToCapture = b.getCheckerForCoords(new int[] {(newCoords[0]+oldCoords[0])/2, (newCoords[1]+oldCoords[1])/2});
            if(checkerToCapture != null){
                if(isOpponent(checkerToMove.getType(), checkerToCapture.getType())){
                    moves.add(new Move(oldCoords, newCoords, true));
                }
            }
            newCoords[1] = oldCoords[1] - 2;
            checkerToCapture = b.getCheckerForCoords(new int[] {(newCoords[0]+oldCoords[0])/2, (newCoords[1]+oldCoords[1])/2});
            if(checkerToCapture != null){
                if(isOpponent(checkerToMove.getType(), checkerToCapture.getType())){
                    moves.add(new Move(oldCoords, newCoords, true));
                }
            }
            
            // king captures
            newCoords[0] = oldCoords[0] - 2;
            newCoords[1] = oldCoords[1] + 2;
            checkerToCapture = b.getCheckerForCoords(new int[] {(newCoords[0]+oldCoords[0])/2, (newCoords[1]+oldCoords[1])/2});
            if(checkerToCapture != null){
                if(isOpponent(checkerToMove.getType(), checkerToCapture.getType())){
                    moves.add(new Move(oldCoords, newCoords, true));
                }
            }
            newCoords[1] = oldCoords[1] - 2;
            checkerToCapture = b.getCheckerForCoords(new int[] {(newCoords[0]+oldCoords[0])/2, (newCoords[1]+oldCoords[1])/2});
            if(checkerToCapture != null){
                if(isOpponent(checkerToMove.getType(), checkerToCapture.getType())){
                    moves.add(new Move(oldCoords, newCoords, true));
                }
            }
        }
        return moves;
    }
    
    public static void printMovesArray(ArrayList<Move> moves){
        System.out.println("Printing moves...");
        for(Move move : moves){
            System.out.println(move.toString());
        }
    }
}
