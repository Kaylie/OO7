/**
 * @author : Kaylie Anderson
 * @date   : 6/12/2016
 * @version: version1
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @name : Main
 * @author : Kaylie Anderson
 * @decr :
 */
public class Game {

	// Game class variables

	// TODO: until class Player is finished use Stack<String>
	static Stack<String> players = new Stack<String>();

	static int[] currentValidBid = { 0, 0 };

	static String currentPlayer;

	/**
	 * @name : Table
	 * @author :
	 * @decr :
	 */
	public static class Table implements ActionListener {

		/*
		 * @name   : actionPerformed
		 * @author : 
		 * @decr   : java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent) Catches any event triggered that has an ActionListener
		 * attached
		 * @param  : ActionEvent e
		 */
		public void actionPerformed(ActionEvent e) {
			// TODO
		}

		/*
		 * @name   : createAndShowGUI
		 * @author : 
		 * @decr   :
		 * @param  : void
		 */
		public static void createAndShowGUI() {
			// TODO
		}

	} // end class Table

	/*
	 * @name : createPlayers
	 * @author : Kaylie Anderson
	 * @decr : creates the number of player objects user selected from GUI item
	 * cmbNumPlayers after btnStart event is triggered.
	 * @param : void
	 */
	private static void createPlayers() {
		// TODO
		// called by btnStart event
		// get value from cmbNumPlayers
		int numPlayers = 0;

		// create new players based on numPlayers
		// and store Players in Stack
		for (int i = 0; i < numPlayers; i++) {
			// TODO

			// PlayerID
			// This line will be moved into Player Class
			// And a new Player obj created here
			String playerID = "Player" + i;
			players.push(playerID);

			// Create new player
			// Player p = newPlayer(i);
			// players.push(p);
		}
	}

	/*
	 * @name : declareWinner
	 * 
	 * @author : Kaylie Anderson
	 * 
	 * @decr : Set GUI items to display the winner of a round or if there is
	 * only one player left set GUI items to display remaining player as game
	 * winner.
	 * 
	 * @param : void
	 */
	private static void declareWinner() {
		// TODO
		players.peek();
	}

	/*
	 * @name : removePlayers
	 * 
	 * @author : Kaylie Anderson
	 * 
	 * @decr : Remove player from game. This Function disables players portal,
	 * grays out their name in the Game Table, and removes them from the stack.
	 * 
	 * @param : void
	 */
	private static void removePlayers() {
		// TODO
		// lots more to do here
		players.pop();

		// check if game should continue
		validateGame();
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
	private static int[] validateBid(int dieNum, int faceValue) {
		// TODO
		return null;
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
	 * @param : int dieNum, int faceValue
	 */
	private static boolean validateChallenge(int dieNum, int faceValue) {
		// TODO
		// currentPlayer == loser
		// loser.loseDie();
		return false;
	}

	/*
	 * @name : validateGame
	 * 
	 * @author : Kaylie Anderson
	 * 
	 * @decr : Check if there are at least 2 players in stack. If there are less
	 * then 2 Players return false. Else return true
	 * 
	 * @param :
	 */
	private static boolean validateGame() {
		if (players.size() < 2) {
			return false;
		} else {
			return true;
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

		// Create Game
		// Game g = new Game();

		// Create Game Table
		//final Table gameTable = new Table();

		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				//gameTable.createAndShowGUI();
			}

		}); // end SwingUtilities

		int i = 0;
		int playerIndex = 0;

		// not sure if the below will work
		do {

			// get first player in line to start round
			if (i == 0 && currentPlayer != null) {
				playerIndex = players.search(currentPlayer);
			}

			// first round of game
			if (currentPlayer == null) {
				playerIndex = 0;
			}

			// TODO: comment this in
			// players.elementAt(i).play();
			
			// player will trigger either validateBid() or
			// validateChallenge() from play()

			// get next player
			// if at stak's last element and loop not done set
			// player index to 0
			//if (players.lastElement() == players.elementAt(i)) {
			//	playerIndex = 0;
			//}

			// increase loopCount
			i++;

		} while (validateGame());

	} // end main

} // end class
