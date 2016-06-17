
/**
 * @author : Kaylie Anderson
 * @date   : 6/12/2016
 * @version: version1
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @name : Game
 * @author : Abraham Assad & Kaylie Anderson
 * @decr :
 */
public class Game extends JFrame implements ActionListener {

	// class variables
	
	static Stack<Player> players = new Stack<Player>();

	static int[] currentValidBid = { 0, 0 };

	static int playerNum = 0; // TODO this value gets set by gameTable GUI

	static Player currentPlayer;
	static Player startingPlayer;
	static Player lastPlayer;

	// class GUI components 
	
	Container con;

	JPanel pnlGameTable, pnlOutcomePanel, pnlRow1, pnlRow2, pnlRules, pnlSetup;

	JButton btnCancel, btnRules, btnStart;

	JLabel lblBidder, lblChallenger, lblMsg, lblNumPlayers, lblPlayerDice, lblPlayerID, lblRules;

	JComboBox cmbNumPlayers;

	JScrollPane spRules;

	JTextField txtBidDiceNum, txtBidDieValue;

	public Game() {
		
		super("Game Table");
		
	    //// Create GUI ////

		setBounds(100, 100, 300, 100);

		// We create a bottom JPanel to place everything on.
		pnlGameTable = new JPanel();
		pnlGameTable.setLayout(null);

		// Creation of a Panel to contain the title labels
		pnlRow1 = new JPanel();
		pnlRow1.setLayout(null);
		pnlRow1.setLocation(10, 10);
		pnlRow1.setSize(480, 40);
		pnlGameTable.add(pnlRow1);

		// We create a button and manipulate it using the syntax we have
		// used before. Now each button has an ActionListener which posts
		// its action out when the button is pressed.
		btnCancel = new JButton("Hi");
		btnCancel.setLocation(10, 10);
		btnCancel.setSize(100, 40);
		btnCancel.addActionListener(this);
		pnlRow1.add(btnCancel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);
		con = this.getContentPane(); 
		this.setSize(500, 280);
		con.add(pnlGameTable); 
		setVisible(true);
	}

	/*
	 * @name  : actionPerformed
	 * 
	 * @decr  :
	 * 
	 * @param : ActionEvent e
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {

			if (btnCancel.getText().equals("Hi")) {
				btnCancel.setText("Bye");
			} else {
				btnCancel.setText("Hi");
			}

		}
	}
	
	/*
	 * @name : createPlayers
	 * 
	 * @decr : creates the number of player objects user selected from GUI item
	 * cmbNumPlayers after btnStart event is triggered.
	 * 
	 * @param : void
	 */
	private static void setPlayerNum(int n) {
		playerNum = n;
	}
	
	/*
	 * @name : createPlayers
	 * 
	 * @decr : creates the number of player objects user selected from GUI item
	 * cmbNumPlayers after btnStart event is triggered.
	 * 
	 * @param : void
	 */
	private static void createPlayers() {

		for (int i = 0; i < playerNum; i++) {

			players.push(new Player(i));

			// set starting player of first round
			if (i == 0) {
				currentPlayer = players.elementAt(i);
				startingPlayer = players.elementAt(i);
			} // end if
		} // end for

	}

	/*
	 * @name : getCurrentBid
	 * 
	 * @decr : returns current bid
	 * 
	 * @param : int[] currentValidBid
	 */
	public static int[] getCurrentValidBid() {
		return currentValidBid;
	}
	
	/*
	 * @name : setWin
	 * 
	 * @decr : 
	 * 
	 * @param : Player p
	 */
	private static void setWin(Player p) {
		
	}
	
	/*
	 * @name : setLose
	 * 
	 * @decr : 
	 * 
	 * @param : Player p
	 */
	private static void setLose(Player p) {
	
		// check if player should be removed
		removePlayer(p);
		
	}
	
	/*
	 * @name : removePlayers
	 * 
	 * @decr : Remove player from game. This Function disables players portal,
	 * grays out their name in the Game Table, and removes them from the stack.
	 * 
	 * @param : Player p
	 */
	private static void removePlayer(Player p) {
		// check if losing player should be removed
		 if ( p.getPlayerCup().getNumberOfDice() == 0) {
			 // remove player
			 int index = players.indexOf(p);
			 players.removeElementAt(index);
		 }

		// check if game should continue
		validateGame(p);
	}
	
	/*
	 * @name : validateGame
	 * 
	 * @decr : Check if there are at least 2 players in stack. If there are less
	 * then 2 Players return false. Else return true
	 * 
	 * @param : Player p
	 */
	private static void validateGame(Player p) {
		if (players.size() < 2) {
			// set GUI to display Game winner
		} else {
			//start new round
			p.play();
		}
	} // end validateGame

	/*
	 * @name : validateBid
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
		if (faceValue < 1 || faceValue > 6) {
			return false;
		}

		// compare new bid against current bid,
		// if new bid is valid assign it as the current bid
		if (dieNum <= currentValidBid[0] && faceValue <= currentValidBid[1]) {
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
	 * @decr : Decide if the bidder or challenger is the winner of a round. This
	 * method kicks off the LoseDie Use Case. Loseing Player is assigned as
	 * currentPlayer.
	 * 
	 * @param : void
	 */
	public static void validateChallenge() {

		// add all Player die vales to array
		// each index represents a face value
		int[] diceValues = { 0, 1, 2, 3, 4, 5, 6 };

		// get dice values
		for (int i = 0; i < players.size(); i++) {
			Stack<String> playersDice = new Stack<String>();
			// Stack<Die> playersDice = players.elementAt(i).getDieValues();

			// add all die values
			for (int j = 0; j < playersDice.size(); j++) {

				// TODO //switch ( playersDice.elementAt(j).faceValue(); ) {
				switch (2) {
				case 1:
					diceValues[i] += 1;
					break;
				case 2:
					diceValues[i] += 2;
					break;
				case 3:
					diceValues[i] += 3;
					break;
				case 4:
					diceValues[i] += 4;
					break;
				case 5:
					diceValues[i] += 5;
					break;
				case 6:
					diceValues[i] += 6;
					break;
				default:
					System.out.println("Error: in default switch case of validateChallenge");
					break;
				} // end switch
			} // end for

		} // end for

		// currentValidBid[0] = dieNum;
		// currentValidBid[1] = faceValue;
		int dieNum = currentValidBid[0];
		int faceValue = currentValidBid[1];
		int totalBidValue = currentValidBid[0] * currentValidBid[1];

		// total num of dice equaling facValue = num of dice of that value
		// for the whole game table.
		int totalDieOfFaceValue = diceValues[faceValue] / faceValue;

		// decide winner
		if (totalDieOfFaceValue >= dieNum) {
			// bidder wins
			setWin(lastPlayer);  
			setLose(currentPlayer);  
		} else {
			// challenger wins
			setWin(currentPlayer);  
			setLose(lastPlayer); 
		}

	} // end validateChallenge
	

	/*
	 * @name : turnOver
	 * 
	 * @decr : After player's turn is over they let the game know by calling
	 * this function. This method then sets the next player.
	 * 
	 * @param : Player p
	 */
	public static void turnOver(Player p) {
		// get next player
		int index = players.indexOf(p);

		lastPlayer = players.elementAt(index);

		// Increment index to next position
		index++;

		// if index is greater then stack size
		// the next player is at Stack index 0
		if (index < players.size()) {
			currentPlayer = players.elementAt(index);
		} else {
			currentPlayer = players.elementAt(0);
		}

		// TODO - comment in
		currentPlayer.play();
	}

	/**
	 * @name   : Main
	 * @decr   :
	 * @param  : String[] args
	 */
	public static void main(String[] args) {

		// DEBUG
		System.out.println("I am alive!!!!");

        new Game();

        // Canned player value
		setPlayerNum(4);
		
		createPlayers();

	} // end main

} // end class
