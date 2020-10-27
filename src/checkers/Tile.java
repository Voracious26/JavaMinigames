package checkers;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Tile {
    private static String blackImgUrl = "res/blackChecker.png";
    private static String blackKingImgUrl  = "res/blackCheckerKing.png";
    private static String redImgUrl = "res/redChecker.png";
    private static String redKingImgUrl = "res/redChecker.png";
    private static ImageIcon blackImg;
    private static ImageIcon blackKingImg;
    private static ImageIcon redImg;
    private static ImageIcon redKingImg;
    public static int imgSize = 100;
    private char state = ' ';
    private JButton tileButton;
    private Board parent;
    
    public Tile(Board parentBoard){
        redImg = scaleIcon(redImgUrl, imgSize, imgSize);
        redKingImg = scaleIcon(redKingImgUrl, imgSize, imgSize);
        blackImg = scaleIcon(blackImgUrl, imgSize, imgSize);
        blackKingImg = scaleIcon(blackKingImgUrl, imgSize, imgSize);
        parent = parentBoard;
        tileButton = new JButton();
        
        tileButton.addActionListener((ActionEvent e) -> {
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
    
    public void clearIcon(){
        tileButton.setIcon(null);
    }
        
    public void updateIcon(){
        if(state == 'r'){
            tileButton.setIcon(redImg);
        }
        else if(state == 'j'){
            tileButton.setIcon(redKingImg);
        }
        else if(state == 'b'){
            tileButton.setIcon(blackImg);
        }
        else if(state == 'k'){
            tileButton.setIcon(blackKingImg);
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
