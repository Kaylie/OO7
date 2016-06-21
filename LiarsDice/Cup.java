import java.util.Stack;
/* 
* @author : Jacob Williams, Abraham Assad
* 
* @name : Cup
* 
* @decr : Builds a cup in which dice are stored.  Produces randomized values for those dice for the game.
* 
* @param : void
*/

public class Cup {
    private Stack<Die> dice = new Stack<Die>();
    
       
    public Cup(){                             //Is the constructor.
        for (int i=0; i < 5; i++){           
            Die d = new Die();
            dice.push(d);
       }
    }
    
    
    

    /*
	 * @name : displayDiceValues
	 * 
	 * @decr : Takes the getFaceValue method from the Die class and displays the dice values.
	 * 
	 * @param : void
	 */
	public void displayDiceValues(){   
       
        for (int i=dice.size();i>0;i--){ 
            System.out.print(dice.get(i-1).getFaceValue() + " ");
       }
       System.out.println("");
    }
    
    /*
	 * @name : getDice
	 * 
	 * @decr : Popultes a stack with dice
	 * 
	 * @param : Stack<Die>
	 */
	public Stack<Die> getDice(){   
        
        return dice;
    }
    
    /*
     * @name : loseDie
     * 
     * @decr : cup loses last die on stack
     * 
     * @param : void
     */
    public void loseDie() {
     
     dice.pop();
    
    }
    
    /*
	 * @name : shake
	 * 
	 * @decr : This 'shakes' the cup and produces a randomized value.
	 * 
	 * @param : void
	 */
	public void shake(){       
    
        for (int i=dice.size();i>0;i--){
             dice.get(i-1).generateSpots();
             System.out.println();
        }
        
    }

    /*
	 * @name : getNumberOfDice
	 * 
	 * @decr : Produces the number of dice left.
	 * 
	 * @param : int
	 */public int getNumberOfDice(){     
            return dice.size();
    
    }
    
}
