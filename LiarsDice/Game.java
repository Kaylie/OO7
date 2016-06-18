
/**
 * @author : Kaylie Anderson
 * @date   : 6/12/2016
 * @version: version1
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.border.Border;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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

	JLabel lblBidder, lblChallenger, lblMsg, lblNumPlayers,
	lblPlayerDice, lblPlayerID, lblTitle, lblPlayerID1, lblPlayerID2, lblPlayerID3, lblPlayerID4;

	JComboBox<String> cmbNumPlayers;
	
	JTextArea txtRules;

	JScrollPane spRules;

	JTextField txtBidDiceNum, txtBidDieValue;
	
	public Game() {
		
		super("Game Table");
		
	    //// Create GUI ////

		setBounds(100, 100, 300, 100);
		
		Border border = BorderFactory.createLineBorder(Color.black);  //Default border is set here.

		// We create a bottom JPanel to place everything on.
		pnlGameTable = new JPanel();
		pnlGameTable.setLayout(null);

		// Creation of a Panel to contain the title labels
		pnlRow1 = new JPanel();
		pnlRow1.setLayout(null);
		pnlRow1.setLocation(10, 10);
		pnlRow1.setSize(420, 40);
		pnlGameTable.add(pnlRow1);
		
		// Creation of a Panel to contain the rules text
		pnlRules = new JPanel();
		pnlRules.setLayout(null);
		pnlRules.setLocation(10, 50);
		pnlRules.setSize(480, 140);
		pnlGameTable.add(pnlRules);
		
		// Creation of a Panel to contain the setup page
		pnlSetup = new JPanel();
		pnlSetup.setLayout(null);
		pnlSetup.setLocation(10, 50);
		pnlSetup.setSize(480, 140);
		pnlGameTable.add(pnlSetup);
		pnlSetup.setVisible(false);
		
		// Creation of a Panel to contain the game start buttons
		pnlRow2 = new JPanel();
		pnlRow2.setLayout(null);
		pnlRow2.setLocation(10, 195);
		pnlRow2.setSize(480, 40);
		pnlGameTable.add(pnlRow2);
		
		//Creation of a Panel to contain the outcome labels
		pnlOutcomePanel = new JPanel();
		pnlOutcomePanel.setLayout(null);
		pnlOutcomePanel.setLocation(10, 50);
		pnlOutcomePanel.setSize(460, 160);
		pnlOutcomePanel.setBorder(border);
		pnlOutcomePanel.setVisible(false);
		pnlGameTable.add(pnlOutcomePanel);
		
		// Creation of the Game title
		lblTitle = new JLabel("Welcome to Liar's Dice");
		lblTitle.setLocation(0, 5);
		lblTitle.setSize(480, 25);
		lblTitle.setHorizontalAlignment(0);
		lblTitle.setFont(new Font("Lucida", Font.PLAIN, 32));
		lblTitle.setForeground(Color.black);
		lblTitle.setVisible(true);
		pnlRow1.add(lblTitle);
		

		
		// work on game rules text import
	    BufferedReader log=null;

        try {



        

    		// Creation of the game rules text
    		txtRules = new JTextArea();
    		txtRules.setSize(465, 140);
    		txtRules.setEditable ( false ); // set textArea non-editable
    		txtRules.setLocation(0, 0);
    		txtRules.setFont(new Font("Lucida", Font.PLAIN, 12));
    		txtRules.setForeground(Color.black);
    		txtRules.setVisible(true);
    		
    		// Creation of the rules scroll pane
    		spRules = new JScrollPane(txtRules);
    		spRules.setSize(465, 140);
    		spRules.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    		pnlRules.add(spRules);
    		
    		BufferedReader reader = new BufferedReader(new FileReader("RulesText.txt")); 
    		String line;
    		while((line = reader.readLine()) != null) {
    			txtRules.append(line + "\n");

             }
    		reader.close();
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
		}

		//Creation of outcome panel text
		
		
		lblPlayerID1 = new JLabel("Player 1");
		lblPlayerID1.setLocation(0, 0);
		lblPlayerID1.setSize(100, 40);
		lblPlayerID1.setHorizontalAlignment(0);
		lblPlayerID1.setFont(new Font("Lucida", Font.PLAIN, 18));
		lblPlayerID1.setForeground(Color.black);
		lblPlayerID1.setVisible(true);
		lblPlayerID1.setBorder(border);
		pnlOutcomePanel.add(lblPlayerID1);
		
	
		lblPlayerID2 = new JLabel("Player 2");
		lblPlayerID2.setLocation(0, 40);
		lblPlayerID2.setSize(100, 40);
		lblPlayerID2.setHorizontalAlignment(0);
		lblPlayerID2.setFont(new Font("Lucida", Font.PLAIN, 18));
		lblPlayerID2.setForeground(Color.black);
		lblPlayerID2.setVisible(true);
		lblPlayerID2.setBorder(border);
		pnlOutcomePanel.add(lblPlayerID2);
		
		lblPlayerID3 = new JLabel("Player 3");
		lblPlayerID3.setLocation(0, 80);
		lblPlayerID3.setSize(100, 40);
		lblPlayerID3.setHorizontalAlignment(0);
		lblPlayerID3.setFont(new Font("Lucida", Font.PLAIN, 18));
		lblPlayerID3.setForeground(Color.black);
		lblPlayerID3.setVisible(true);
		lblPlayerID3.setBorder(border);
		pnlOutcomePanel.add(lblPlayerID3);
		
		lblPlayerID4 = new JLabel("Player 4");
		lblPlayerID4.setLocation(0, 120);
		lblPlayerID4.setSize(100, 40);
		lblPlayerID4.setHorizontalAlignment(0);
		lblPlayerID4.setFont(new Font("Lucida", Font.PLAIN, 18));
		lblPlayerID4.setForeground(Color.black);
		lblPlayerID4.setVisible(true);
		lblPlayerID4.setBorder(border);
		pnlOutcomePanel.add(lblPlayerID4);  
		
		lblBidder = new JLabel("Bidder");
		lblBidder.setLocation(100, 0);
		lblBidder.setSize(100, 40);
		lblBidder.setHorizontalAlignment(0);
		lblBidder.setFont(new Font("Lucida", Font.PLAIN, 18));
		lblBidder.setForeground(Color.black);
		lblBidder.setVisible(true);
		lblBidder.setBorder(border);
		pnlOutcomePanel.add(lblBidder);
		
		lblChallenger = new JLabel("Challenger");
		lblChallenger.setLocation(100, 40);
		lblChallenger.setSize(100, 40);
		lblChallenger.setHorizontalAlignment(0);
		lblChallenger.setFont(new Font("Lucida", Font.PLAIN, 18));
		lblChallenger.setForeground(Color.black);
		lblChallenger.setVisible(true);
		lblChallenger.setBorder(border);
		pnlOutcomePanel.add(lblChallenger);
		
		
		
		
		// Button for user to advance past rules
		btnRules = new JButton("Got it!");
		btnRules.setLocation(0, 0);
		btnRules.setSize(465, 40);
		btnRules.addActionListener(this);
		pnlRow2.add(btnRules);
		
		// We create a button and manipulate it using the syntax we have
		// used before. Now each button has an ActionListener which posts
		// its action out when the button is pressed.
		btnCancel = new JButton("Back Out");
		btnCancel.setLocation(365, 75);
		btnCancel.setSize(100, 40);
		btnCancel.addActionListener(this);
		pnlSetup.add(btnCancel);
		
		// Button for user to accept players and start game
		btnStart = new JButton("Start Game");
		btnStart.setLocation(0, 75);
		btnStart.setSize(360, 40);
		btnStart.addActionListener(this);
		pnlSetup.add(btnStart);
		
		// Text instruction for number of players
		lblNumPlayers = new JLabel("Choose the number of players...");
		lblNumPlayers.setLocation(0, 30);
		lblNumPlayers.setSize(360, 40);
		lblNumPlayers.setHorizontalAlignment(10);
		lblNumPlayers.setFont(new Font("Lucida", Font.PLAIN, 24));
		lblNumPlayers.setForeground(Color.black);
		lblNumPlayers.setVisible(true);
		pnlSetup.add(lblNumPlayers);
		
		// Combo box for the number of players chosen
		String[] strPlayers = new String[] {"2", "3", "4"};	// used to fill in the combo box
		cmbNumPlayers = new JComboBox<String>(strPlayers);
		cmbNumPlayers.setLocation(365, 30);
		cmbNumPlayers.setSize(100, 40);
		cmbNumPlayers.addActionListener(this);
		pnlSetup.add(cmbNumPlayers);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int xAdjust = -100;
		int yAdjust = -175;
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    	int x = xAdjust + ( (int) ((dimension.getWidth() - getWidth()) / 2) );
	    	int y = yAdjust + ( (int) ((dimension.getHeight() - getHeight()) / 2) );
	    	setLocation(x, y);

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
		// commands for "Got it" button press
		if (e.getSource() == btnRules) {

			pnlRules.setVisible(false);
			pnlSetup.setVisible(true);

		}
		// commands for "Back out" button press
		if (e.getSource() == btnCancel) {

			pnlRules.setVisible(true);
			pnlSetup.setVisible(false);

		}
		// commands for "Start game" button press inc player creation
		if (e.getSource() == btnStart) {

			pnlSetup.setVisible(false);
			int playerNum = cmbNumPlayers.getSelectedIndex() + 2;
			
			createPlayers(playerNum);
			
			btnRules.setVisible(false);
			
			lblTitle.setText("");
			
			pnlRow2.setVisible(false);
			
			pnlOutcomePanel.setVisible(true);

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
	private static void createPlayers(int playerNum) {

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
		p.lblErrorMsg.setText("Winner");
		p.lblErrorMsg.setVisible(true);
		
	}
	
	/*
	 * @name : setLose
	 * 
	 * @decr : 
	 * 
	 * @param : Player p
	 */
	private static void setLose(Player p) {
		
		// !!!! everything with 4 //// needs to be commented in  !!!!
		
		
		p.lblErrorMsg.setText("Loser");
		////p.lblErrorMsg.setText("You lost this round but you can still win the game. "
		////		+ "Please enter a bid to start the next round.");
		p.lblErrorMsg.setVisible(true);
		
		// player who looses losses a die
		////p.getPlayerCup().loseDie();
		
		//startingPlayer = p;
	
		// check if player should be removed
		////removePlayer(p);
		

		// shake dice for next round
		////for (int i = 0; i < players.size(); i++) {
		////	players.elementAt(i).getPlayerCup().shake();
		////}
		
		// start next round
		////p.play();
		
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
			 
			 // set starting player to the next player in line
			 startingPlayer = getNextPlayer(p);
			 
			 // remove player
			 players.removeElementAt(index);

		 }
		 
		// reset player tracking variables
		currentPlayer = startingPlayer;
		lastPlayer = startingPlayer;

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

		// dieNum must be greater than 1
		if (dieNum < 1) {
			// Buttons are not being reshown
			return false;
		}

		// faceValue can only be values 1 - 5 exclusive
		if (faceValue < 1 || faceValue > 6) {
			// Buttons are not being reshown
			return false;
		}

		// compare new bid against current bid,
		// if new bid is valid assign it as the current bid
		if (dieNum <= currentValidBid[0] && faceValue <= currentValidBid[1]) {
			//System.out.println("dieNum <= currentValidBid[0] && faceValue <= currentValidBid[1]");
			return false;
		}

		// if code reaches this far the new bid is valid
		// assign bid as new bid
		currentValidBid[0] = dieNum;
		currentValidBid[1] = faceValue;
		
		// set current bid for all players
		for (int i = 0; i < players.size(); i++) {
			String s = "Current Bid = Die Number: " + dieNum 
					+  " Face Value: " + faceValue; // don't use s
			players.elementAt(i).lblCurrentBid.setText(s);
		}

		return true;
	}

	/*
	 * @name : validateChallenge
	 * 
	 * @decr : Decide if the bidder or challenger is the winner of a round. This
	 * method kicks off the LoseDie Use Case. Losing Player is assigned as
	 * currentPlayer.
	 * 
	 * @param : void
	 */
	public static void validateChallenge() {
		//disable all players screens
		// add all Player die vales to array
		// each index represents a face value
		for (int i = 0; i < players.size(); i++){
			players.elementAt(i).btnChallenge.setVisible(false);
			players.elementAt(i).btnBid.setVisible(false);
			
			
		}
		
		int[] diceValues = { 0, 0, 0, 0, 0, 0};
		
		// get dice values
		for (int i = 0; i < players.size(); i++) {

			Stack<Die> playersDice = players.elementAt(i).getPlayerCup().getDice();

			// add all die values
			for (int j = 0; j < playersDice.size(); j++) {

				switch ( playersDice.elementAt(j).getFaceValue()) {
				//switch (2) {
				case 1:
					diceValues[0] += 1;
					System.out.println("** 1 = "  + diceValues[0]);
					break;
				case 2:
					diceValues[1] += 2;
					System.out.println("** 2 = " + diceValues[1]);
					break;
				case 3:
					diceValues[2] += 3;
					System.out.println("** 3 = "  + diceValues[2]);
					break;
				case 4:
					diceValues[3] += 4;
					System.out.println("** 4 = "  + diceValues[3]);
					break;
				case 5:
					diceValues[4] += 5;
					System.out.println("** 5 = "  + diceValues[4]);
					break;
				case 6:
					diceValues[5] += 6;
					System.out.println("** 6 = "  + diceValues[5]);
					break;
				default:
					System.out.println("Error: in default switch case of validateChallenge");
					break;
				} // end switch
			} // end for

		} // end for
		
		int faceValue = currentValidBid[1]-1; // need to take away 1 so dice values match array index
		int totalBidValue = currentValidBid[0] * currentValidBid[1];


		// decide winner
		if (diceValues[faceValue] >= totalBidValue) {
			// bidder wins
			setWin(lastPlayer);  
			setLose(currentPlayer);  
		} else {
			// challenger wins
			setWin(currentPlayer);  
			setLose(lastPlayer); 
		}
		
		
		// DEBUG : print algorithm steps
		System.out.println("currentValidBid[0] = " + currentValidBid[0] );
		System.out.println("currentValidBid[1] = " + currentValidBid[1] );
		System.out.println("faceValue = " + faceValue );
		System.out.println("totalBidValue = " + totalBidValue );
		System.out.println("diceValues[faceValue] = " + diceValues[faceValue]);

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

		lastPlayer = p;
	    currentPlayer = getNextPlayer(p);

		currentPlayer.play();
	}
	
	
	/**
	 * @name   : Main
	 * @decr   : return the next player in the stack. If 
	 *           at stack's max index return the player
	 *           at index 0.
	 * @param  : String[] args
	 */
	private static Player getNextPlayer(Player p) {
		
		int index = players.indexOf(p);
		
		// Increment index to next position
		index++;
		
		// if index is greater then stack size
	    // the next player is at Stack index 0
		if (index < players.size()) {
			return players.elementAt(index);
		}
		
		return players.firstElement();

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
		//int playerNum = 2;
		
		//createPlayers();
		
		

	} // end main

} // end class