package javaminigames;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Icons {
    // tictactoe    
    public static int tictactoeImgSize = 150;
    private static String xImgUrl = "res/tictactoe/x.png";
    private static String oImgUrl = "res/tictactoe/o.png";
    private static String blankTicTacToeImgUrl = "res/blank.png";
    public static ImageIcon xImg;
    public static ImageIcon oImg;
    public static ImageIcon blankTicTacToeImg;
    
    // checkers
    public static int checkersImgSize = 100;
    private static String blackImgUrl = "res/checkers/blackChecker.png";
    private static String blackKingImgUrl  = "res/checkers/blackCheckerKing.png";
    private static String redImgUrl = "res/checkers/redChecker.png";
    private static String redKingImgUrl = "res/checkers/redCheckerKing.png"; 
    public static ImageIcon blackImg;
    public static ImageIcon blackKingImg;
    public static ImageIcon redImg;
    public static ImageIcon redKingImg;    
    
    private static String blackSelectedImgUrl = "res/checkers/blackChecker_selected.png";
    private static String blackKingSelectedImgUrl  = "res/checkers/blackCheckerKing_selected.png";
    private static String redSelectedImgUrl = "res/checkers/redChecker_selected.png";
    private static String redKingImgSelectedUrl = "res/checkers/redCheckerKing_selected.png"; 
    public static ImageIcon blackSelectedImg;
    public static ImageIcon blackKingSelectedImg;
    public static ImageIcon redSelectedImg;
    public static ImageIcon redKingSelectedImg;
    
    private static String blankCheckersImgUrl = "res/blank.png";
    public static ImageIcon blankCheckersImg;
    private static String possibleMoveImgUrl = "res/checkers/possibleMove.png";
    public static ImageIcon possibleMoveImg;
    private static String possibleCaptureImgUrl = "res/checkers/possibleCapture.png";
    public static ImageIcon possibleCaptureImg;
    
    public static void initializeIcons(){        
        // tictactoe
        xImg = scaleIcon(xImgUrl, tictactoeImgSize, tictactoeImgSize);
        oImg = scaleIcon(oImgUrl, tictactoeImgSize, tictactoeImgSize);
        blankTicTacToeImg = scaleIcon(blankTicTacToeImgUrl, tictactoeImgSize, tictactoeImgSize);
        
        // checkers
        redImg = scaleIcon(redImgUrl, checkersImgSize, checkersImgSize);
        redKingImg = scaleIcon(redKingImgUrl, checkersImgSize, checkersImgSize);
        blackImg = scaleIcon(blackImgUrl, checkersImgSize, checkersImgSize);
        blackKingImg = scaleIcon(blackKingImgUrl, checkersImgSize, checkersImgSize);
        
        redSelectedImg = scaleIcon(redSelectedImgUrl, checkersImgSize, checkersImgSize);
        redKingSelectedImg = scaleIcon(redKingImgSelectedUrl, checkersImgSize, checkersImgSize);
        blackSelectedImg = scaleIcon(blackSelectedImgUrl, checkersImgSize, checkersImgSize);
        blackKingSelectedImg = scaleIcon(blackKingSelectedImgUrl, checkersImgSize, checkersImgSize);
        
        blankCheckersImg = scaleIcon(blankCheckersImgUrl, checkersImgSize, checkersImgSize);
        possibleMoveImg = scaleIcon(possibleMoveImgUrl, checkersImgSize, checkersImgSize);
        possibleCaptureImg = scaleIcon(possibleCaptureImgUrl, checkersImgSize, checkersImgSize);
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
