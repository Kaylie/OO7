import java.util.Stack;
public class Cup {
	private Stack<Die> dice = new Stack<Die>();
    
	   
    public Cup(){                             //Is the constructor.
        for (int i=0; i < 5; i++){           
            Die d = new Die();
            dice.push(d);
       }
    }
    
    
    public void displayDiceValues(){   //Takes the getFaceValue method from the Die class and displays the dice values.
       
        for (int i=dice.size();i>0;i--){ 
            System.out.print(dice.get(i-1).getFaceValue() + " ");
       }
       System.out.println("");
    }
    
    public Stack<Die> getDice(){   //needed dice values for player and game
        
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
    
    public void shake(){       //This 'shakes' the cup and produces a randomized value.
    
        for (int i=dice.size();i>0;i--){
             dice.get(i-1).generateSpots();
             System.out.println();
        }
        
    }

    public int getNumberOfDice(){     //Produces the number of dice left.
            return dice.size();
    
    }
    
}
