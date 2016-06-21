import java.util.*;
/*
 * @name : Die
 * @author : Cameron Walworth, Abraham Assad
 * @decr :
 */public class Die {
    private int faceValue = 0;

    /*
     * @name : Die
     * @decr : Is the constructor for the class
     * @param : void
     */
    public Die(){  
        
    }
    
    /*
     * @name : getFaceValue
     * @decr : Produces the face value for the die
     * @param : int
     */
    public int getFaceValue(){   
        return faceValue;
    }
    
    /*
     * @name : generateSpots
     * @decr : Generates at random the spots and values
     * @param : int
     */
    public int generateSpots(){   
        Random r = new Random();
        faceValue = r.nextInt(6)+1;
        return faceValue;
    }
}

