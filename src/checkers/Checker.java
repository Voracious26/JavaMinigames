/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

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
}
