package tictactoe;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Tile {
    private static String xImgUrl = "res/x.png";
    private static String oImgUrl = "res/o.png";
    private static ImageIcon xImg;
    private static ImageIcon oImg;
    public static int imgSize = 150;
    private char state = ' ';
    private JButton tileButton;
    private Board parent;
    
    public Tile(Board parentBoard){
        xImg = scaleIcon(xImgUrl, imgSize, imgSize);
        oImg = scaleIcon(oImgUrl, imgSize, imgSize);
        parent = parentBoard;
        tileButton = new JButton();
        tileButton.addActionListener((ActionEvent e) -> {
            JButton b = (JButton)e.getSource();            
            b.setIcon(xImg);
            setState('x');
            if(parent.isFull()){
                getTicTacToe().gameOver();        
            }
            else{
                parent.computerMove();
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
    
    private TicTacToe getTicTacToe(){
        return parent.parent;
    }
    
    public void clearIcon(){
        tileButton.setIcon(null);
    }
    
    public void setIcon(char icon){
        switch(icon){
            case 'x':
                tileButton.setIcon(xImg);
                break;
            case 'o':
                tileButton.setIcon(oImg);
                break;
        }
    }
    
    public static ImageIcon scaleIcon(String url, int width, int height){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);   
        return new ImageIcon(dimg);
    }
}


