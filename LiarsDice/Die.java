import java.util.*;
public class Die {
	private int faceValue = 0;

	public Die(){   //Is the constructor for the class
	    
	}
	public int getFaceValue(){   //Produces the face value for the die
		return faceValue;
	}
	public int generateSpots(){   //Generates at random the spots and values
		Random r = new Random();
		faceValue = r.nextInt(6)+1;
		return faceValue;
	}
}
