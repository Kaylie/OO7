
/**
 * @author : Kaylie Anderson
 * @date   : 6/12/2016
 * @version: version1
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @name : Main
 * @author : Kaylie Anderson
 * @decr :
 */
public class Game {

	// Game class variables

	// TODO: until class Player is finished use Stack<String>
	static Stack<Player> players = new Stack<Player>();

	static int[] currentValidBid = { 0, 0 };
	
	static int playerNum = 2; // TODO this value gets set by gameTable GUI

	static Player currentPlayer;
	static Player startingPlayer;
	static Player lastPlayer;

	/**
	 * @name : Table
	 * @author :
	 * @decr :
	 */
	public static class Table implements ActionListener {
		// Global variable definitions for GUI items

		JPanel row1;

		JButton btnBid;

		public JPanel tablePanel() {

			// We create a bottom JPanel to place everything on.
			JPanel portalGUI = new JPanel();
			portalGUI.setLayout(null);

			// Creation of a Panel to contain the title labels
			row1 = new JPanel();
			row1.setLayout(null);
			row1.setLocation(10, 10);
			row1.setSize(480, 40);
			// row1.setBackground(Color.blue);
			portalGUI.add(row1);

			// We create a button and manipulate it using the syntax we have
			// used before. Now each button has an ActionListener which posts
			// its action out when the button is pressed.
			btnBid = new JButton("Hi");
			btnBid.setLocation(10, 10);
			btnBid.setSize(100, 40);
			btnBid.addActionListener(this);
			row1.add(btnBid);

			portalGUI.setOpaque(true);
			return portalGUI;
		}

		/*
		 * @name : actionPerformed
		 * 
		 * @author :
		 * 
		 * @decr :
		 * 
		 * @param : ActionEvent e
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnBid) {

				if (btnBid.getText().equals("Hi")) {
					btnBid.setText("Bye");
				} else {
					btnBid.setText("Hi");
				}

			}
		}

		/*
		 * @name : createAndShowGUI
		 * 
		 * @author : Kaylie Anderson
		 * 
		 * @decr : creates and opens GUI
		 * 
		 * @param : void
		 */
		public static void createAndShowGUI() {

			JFrame.setDefaultLookAndFeelDecorated(true);
			JFrame frame = new JFrame("Game Table");

			// create and set up the jframe.
			Table t = new Table();
			frame.setContentPane(t.tablePanel());

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(500, 280);
			frame.setVisible(true);
		}

	} // end class Table

	/*
	 * @name : createPlayers
	 * 
	 * @author : Kaylie Anderson
	 * 
	 * @decr : creates the number of player objects user selected from GUI item
	 * cmbNumPlayers after btnStart event is triggered.
	 * 
	 * @param : void
	 */
	private static void createPlayers() {
		
		for (int i = 0; i < playerNum; i++ ) {
			
			players.push(new Player(i));
			
			// set starting player of first round
			if (i == 0) {
				currentPlayer = players.elementAt(i);
				startingPlayer = players.elementAt(i);
			} // end if
		} // end for

	}


	/*
	 * @name : validateBid
	 * 
	 * @author : Kaylie Anderson
	 * 
	 * @decr : Make sure a players bid is valid by checking that is is greater
	 * then currentValidBid[], and that the bid is comprised of valid numbers
	 * ranges.
	 * 
	 * @param : int dieNum, int faceValue
	 */
	public static boolean validateBid(int dieNum, int faceValue) {
		
		// only need to check one var for zero
		// if true this is the first bid in a round
		currentValidBid[0] = 0;
		
		// dieNum must be greater than 1
		if (dieNum < 1) { 
			return false; 
		}
				
		// faceValue can only be values 1 - 5 exclusive
		if(faceValue < 1 || faceValue > 6) { 
			return false; 
		}
		
		// compare new bid against current bid, 
		// if new bid is valid assign it as the current bid
		if(dieNum <= currentValidBid[0] && faceValue <= currentValidBid[1]) { 
			return false; 
		}
			
		// if code reaches this far the new bid is valid
		// assign bid as new bid
		currentValidBid[0] = dieNum;
		currentValidBid[1] = faceValue;
				
		return true;
	}
	
	

	/*
	 * @name : validateChallenge
	 * 
	 * @author : Kaylie Anderson
	 * 
	 * @decr : Decide if the bidder or challenger is the winner of a round. This
	 * method kicks off the LoseDie Use Case. Loseing Player is assigned as
	 * currentPlayer.
	 * 
	 * @param : void
	 */
	public static boolean validateChallenge() {
		
		boolean isChallengerWinner = true;
		Player winner;
		Player loser;

		// add all Player die vales to array
		// each index represents a face value
		int[] diceValues = {0,1,2,3,4,5,6};
		
		// get dice values
		for (int i = 0; i < players.size(); i++ ) {
			Stack<String> playersDice = new Stack<String>();
			//Stack<Die> playersDice = players.elementAt(i).getDieValues();
			
			// add all die values
			for (int j = 0; j < playersDice.size(); j++ ) {
			   
			   //TODO //switch ( playersDice.elementAt(j).faceValue(); ) {
			   switch ( 2 ) {
			   	case 1 : diceValues[i] += 1;
			   		break;
			   	case 2 : diceValues[i] += 2;
			   		break;
			   	case 3 : diceValues[i] += 3;
			   		break;
			   	case 4 : diceValues[i] += 4;
			   		break;
			   	case 5 : diceValues[i] += 5;
			   		break;
			   	case 6 : diceValues[i] += 6;
			   		break;
			   	default: System.out.println("Error: in default switch case of validateChallenge");
			   		break;
			   } // end switch
			} // end for

		} // end for
		
		//currentValidBid[0] = dieNum;
		//currentValidBid[1] = faceValue;
		int dieNum = currentValidBid[0];
		int faceValue = currentValidBid[1];
		int totalBidValue = currentValidBid[0] * currentValidBid[1];
		
		// total num of dice equaling facValue = num of dice of that value 
		// for the whole game table.
		int totalDieOfFaceValue = diceValues[faceValue] / faceValue;
		
		// decide winner
		if (totalDieOfFaceValue >= dieNum) {
			//bidder wins
			isChallengerWinner = false; 

			//lastPlayerset as winner
			//currentPlayer set as loser
			removePlayer(currentPlayer);
		} else {
			removePlayer(currentPlayer);
			//currentPlayer set as winner
			// lastPlayer set as loser
			removePlayer(lastPlayer);
		}
		
		return isChallengerWinner;
		
	}
	
	
	//static Player currentPlayer;
	//static Player startingPlayer;
	//static Player lastPlayer;
	public static void turnOver(Player p) {
		// get next player
		int index = players.indexOf(p);
		
		lastPlayer = players.elementAt(index);
		
		//Increment index to next position
		index++;
		
		// if index is greater then stack size 
		// the next player is at Stack index 0
		if (index < players.size() ) {
			currentPlayer = players.elementAt(index);
		} else {
			currentPlayer = players.elementAt(0);
		}
		
		// TODO - comment in
		//currentPlayer.play();
	}
	
	/*
	 * @name : removePlayers
	 * 
	 * @author : Kaylie Anderson
	 * 
	 * @decr : Remove player from game. This Function disables players portal,
	 * grays out their name in the Game Table, and removes them from the stack.
	 * 
	 * @param : Player p
	 */
	private static void removePlayer(Player p) {
		/* TODO comment in
		// check if losing player should be removed
		if ( p.getNumberOfDice() == 0) {
			// remove player
			int index = players.indexOf(p);
			players.removeElementAt(index);
			
			// check if game should continue
		    validateGame();
		}
		*/
	}


	/*
	 * @name : validateGame
	 * 
	 * @author : Kaylie Anderson
	 * 
	 * @decr : Check if there are at least 2 players in stack. If there are less
	 * then 2 Players return false. Else return true
	 * 
	 * @param : void
	 */
	private static void validateGame() {
		if (players.size() < 2) {
			// set GUI to display winner
		}
	} // end validateGame

	/**
	 * @name : Main
	 * @author : Kaylie Anderson
	 * @decr :
	 * @param :
	 *            String[] args
	 */
	public static void main(String[] args) {

		System.out.println("I am alive!!!!");

		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Table.createAndShowGUI();
			}

		}); // end SwingUtilities
		
		int playerNum = 2;
		createPlayers();
		

	} // end main

} // end class
