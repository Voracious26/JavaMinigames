
package checkers;

import java.util.Arrays;

public class Move {
    public int[] src = new int[]{-1,-1};
    public int[] dst = new int[]{-1,-1};
    public boolean capt = false;
    
    
    public Move(int[] newSrc, int[] newDst, boolean newCapt){
        src[0] = newSrc[0];
        src[1] = newSrc[1];
        dst[0] = newDst[0];
        dst[1] = newDst[1];
        capt = newCapt;
    }
    public void setSrc(int[] newSrc){
        src[0] = newSrc[0];
        src[1] = newSrc[1];
    }
    public void setDst(int[] newDst){
        dst[0] = newDst[0];
        dst[1] = newDst[1];
    }
    
    public int[] getCaptCoords(){
        int x = (src[0]+dst[0])/2;
        int y = (src[1]+dst[1])/2;
        int out[] = new int[]{x,y};
        return out;
    }
    
    @Override
    public String toString(){
        return "Move from "+Arrays.toString(src)+" to "+Arrays.toString(dst)+", capturing: "+capt;
    }
    
}
