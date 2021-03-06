
/**
 * @author : Kaylie Anderson & Abraham Assad
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

/**
 * @name : Game
 * @author : Abraham Assad & Kaylie Anderson
 * @decr :
 */
@SuppressWarnings("serial")
public class Game extends JFrame implements ActionListener {

	// class variables
	
	private static Stack<Player> players = new Stack<Player>();

	private static int[] currentValidBid = { 0, 0 };

	private static int playerNum = 0; 
	
	private static boolean isCupShaken = true; 

	private static Player currentPlayer;
	private static Player startingPlayer;
	private static Player lastPlayer;

	// class GUI components 
	
	private Container con;

	private JPanel pnlGameTable, pnlOutcome, pnlRow1, pnlRow2, pnlRules, pnlSetup;

	private JButton btnCancel, btnRules, btnStart;

	private JLabel lblNumPlayers;

	private static JLabel lblBidder, lblChallenger, lblTitle, lblPlayer1Dice, lblPlayer2Dice, lblPlayer3Dice, 
    				lblPlayer4Dice;

	private JComboBox<String> cmbNumPlayers;
	
	private JTextArea txtRules;
	
	private static JTextArea txtWinner;

	private JScrollPane spRules;
	
	// Game constructor
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
		pnlOutcome = new JPanel();
		pnlOutcome.setLayout(null);
		pnlOutcome.setLocation(15, 50);
		pnlOutcome.setSize(462, 160);
		pnlOutcome.setBorder(border);
		pnlOutcome.setVisible(false);   
		pnlOutcome.setBackground(Color.lightGray);
		pnlGameTable.add(pnlOutcome);
		
		// Creation of the Game title
		lblTitle = new JLabel("Welcome to Liar's Dice");
		lblTitle.setLocation(0, 5);
		lblTitle.setSize(480, 25);
		lblTitle.setHorizontalAlignment(0);
		lblTitle.setFont(new Font("Lucida", Font.PLAIN, 32));
		lblTitle.setForeground(Color.black);
		lblTitle.setVisible(true);           
		pnlRow1.add(lblTitle);
		
		
		// import the game rules text below
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
        	
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		//Creation of outcome panel text
		lblBidder = new JLabel("Bidder");
		lblBidder.setLocation(155, 0);
		lblBidder.setSize(154, 40);
		lblBidder.setHorizontalAlignment(0);
		lblBidder.setFont(new Font("Lucida", Font.PLAIN, 18));
		lblBidder.setForeground(Color.black);
		lblBidder.setVisible(false);
		lblBidder.setBorder(border);
		pnlOutcome.add(lblBidder);
		
		lblChallenger = new JLabel("Challenger");
		lblChallenger.setLocation(155, 40);
		lblChallenger.setSize(154, 40);
		lblChallenger.setHorizontalAlignment(0);
		lblChallenger.setFont(new Font("Lucida", Font.PLAIN, 18));
		lblChallenger.setForeground(Color.black);
		lblChallenger.setVisible(false);
		lblChallenger.setBorder(border);
		pnlOutcome.add(lblChallenger);
		
		txtWinner = new JTextArea("Winner!");
		txtWinner.setLocation(155, 40);
		txtWinner.setSize(154, 40);
		txtWinner.setFont(new Font("Lucida", Font.BOLD, 18));
		txtWinner.setBackground(Color.darkGray);
		txtWinner.setForeground(Color.lightGray);
		txtWinner.setVisible(false);
		pnlOutcome.add(txtWinner);
		
		// Dice labels
		lblPlayer1Dice = new JLabel("");
		lblPlayer1Dice.setLocation(308, 0);
		lblPlayer1Dice.setSize(154, 40);
		lblPlayer1Dice.setHorizontalAlignment(0);
		lblPlayer1Dice.setFont(new Font("Lucida", Font.PLAIN, 12));
		lblPlayer1Dice.setForeground(Color.black);
		lblPlayer1Dice.setVisible(false);
		lblPlayer1Dice.setBorder(border);
		pnlOutcome.add(lblPlayer1Dice);
		
		lblPlayer2Dice = new JLabel("");
		lblPlayer2Dice.setLocation(308, 40);
		lblPlayer2Dice.setSize(154, 40);
		lblPlayer2Dice.setHorizontalAlignment(0);
		lblPlayer2Dice.setFont(new Font("Lucida", Font.PLAIN, 12));
		lblPlayer2Dice.setForeground(Color.black);
		lblPlayer2Dice.setVisible(false);
		lblPlayer2Dice.setBorder(border);
		pnlOutcome.add(lblPlayer2Dice);
		
		lblPlayer3Dice = new JLabel("");
		lblPlayer3Dice.setLocation(308, 80);
		lblPlayer3Dice.setSize(154, 40);
		lblPlayer3Dice.setHorizontalAlignment(0);
		lblPlayer3Dice.setFont(new Font("Lucida", Font.PLAIN, 12));
		lblPlayer3Dice.setForeground(Color.black);
		lblPlayer3Dice.setVisible(false);
		lblPlayer3Dice.setBorder(border);
		pnlOutcome.add(lblPlayer3Dice);
		
		lblPlayer4Dice = new JLabel("");
		lblPlayer4Dice.setLocation(308, 120);
		lblPlayer4Dice.setSize(154, 40);
		lblPlayer4Dice.setHorizontalAlignment(0);
		lblPlayer4Dice.setFont(new Font("Lucida", Font.PLAIN, 12));
		lblPlayer4Dice.setForeground(Color.black);
		lblPlayer4Dice.setVisible(false);
		lblPlayer4Dice.setBorder(border);
		pnlOutcome.add(lblPlayer4Dice);

		// Button for user to advance past rules
		btnRules = new JButton("Got it!");
		btnRules.setLocation(0, 0);
		btnRules.setSize(465, 40);
		btnRules.addActionListener(this);
		btnRules.setBackground(Color.white);
		pnlRow2.add(btnRules);
		
		// back out button
		btnCancel = new JButton("Back Out");
		btnCancel.setLocation(365, 75);
		btnCancel.setSize(100, 40);
		btnCancel.addActionListener(this);
		btnCancel.setBackground(Color.white);
		pnlSetup.add(btnCancel);
		
		// Button for user to accept players and start game
		btnStart = new JButton("Start Game");
		btnStart.setLocation(0, 75);
		btnStart.setSize(360, 40);
		btnStart.addActionListener(this);
		btnStart.setBackground(Color.white);
		pnlSetup.add(btnStart);
		
		// Text instruction for number of players
		lblNumPlayers = new JLabel(" Choose the number of players :");
		lblNumPlayers.setLocation(0, 30);
		lblNumPlayers.setSize(360, 40);
		lblNumPlayers.setHorizontalAlignment(10);
		lblNumPlayers.setFont(new Font("Lucida", Font.PLAIN, 20));
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
		pnlGameTable.setMaximumSize(new Dimension(500,280));
		this.setResizable(false);
		
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
			
			btnRules.setVisible(false);

		}
		// commands for "Back out" button press
		if (e.getSource() == btnCancel) {

			pnlRules.setVisible(true);
			pnlSetup.setVisible(false);
			
			btnRules.setVisible(true);

		}
		// commands for "Start game" button press inc player creation
		if (e.getSource() == btnStart) {

			pnlSetup.setVisible(false);
			
			// generate player windows depending on combo
			// box selection
			playerNum = cmbNumPlayers.getSelectedIndex() + 2;
			createPlayers(playerNum);
			
			// create player flags for specific number of players
			Border border = BorderFactory.createLineBorder(Color.black);  //Default border is set here.
			JLabel[] lblPlayerID = new JLabel[4];
			for(int i=0;i<playerNum;i++){
				lblPlayerID[i] = new JLabel("Player " + (i+1));
				lblPlayerID[i].setLocation(0, i*40);
				lblPlayerID[i].setSize(154, 40);
				lblPlayerID[i].setHorizontalAlignment(0);
				lblPlayerID[i].setFont(new Font("Lucida", Font.PLAIN, 18));
				lblPlayerID[i].setForeground(Color.black);
				lblPlayerID[i].setVisible(true);
				lblPlayerID[i].setBorder(border);
				pnlOutcome.add(lblPlayerID[i]);
			}
			
			// change the title into current player
			lblTitle.setFont(new Font("Lucida", Font.PLAIN, 18));
			// hide unused gui elements
			lblTitle.setText("Player 1's turn...");
			btnRules.setVisible(false);
			pnlRow2.setVisible(false);
			
			pnlOutcome.setVisible(true);

		}
	}
	
	/*
	 * @name : populatePlayerDiceLabels
	 * 
	 * @decr : creates and populates the Dice label for a player
	 * 
	 * @param : void
	 */
	private static void populatePlayerDiceLabels() {
		
		//Creation of outcome panel's labels for player's dice
		// get dice values
		for (int i = 0; i < players.size(); i++) {
			
			String s = "";
			
			Stack<Die> playersDice = players.elementAt(i).getPlayerCup().getDice();
			
			// build String text
			for (int j = 0; j < playersDice.size(); j++) {
				
				if ( ( playersDice.size() - 1 ) == j ) {
					s = s.concat(playersDice.elementAt(j).getFaceValue() + "");
				} else {
					s = s.concat(playersDice.elementAt(j).getFaceValue() + ", ");
				} // end if else
			} // end for j
			
			//System.out.println("populatePlayerDiceLabels i = " + i);
			//System.out.println("populatePlayerDiceLabels s = " + s);
			
			
			// set text for player lbls
			switch(players.elementAt(i).getPlayerID()) {
			case 0: 
				//System.out.println("Player1 = " + s);
				lblPlayer1Dice.setText(s); 
				lblPlayer1Dice.setVisible(true);
				break;
				
			case 1: 
				//System.out.println("Player2 = " + s);
				lblPlayer2Dice.setText(s);
				lblPlayer2Dice.setVisible(true);
				break;
			case 2: 
				//System.out.println("Player3 = " + s);
				lblPlayer3Dice.setText(s); 
				lblPlayer3Dice.setVisible(true);
				break;
			case 3: 
				//System.out.println("Player4 = " + s);
				lblPlayer4Dice.setText(s); 
				lblPlayer4Dice.setVisible(true);
				break;
				
			} // end switch
			
			
		} // end for i
				
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
		// moves winner flag
		txtWinner.setVisible(true);
		txtWinner.setLocation(155, (40*p.getPlayerID()));
	}
	
	/*
	 * @name : setLose
	 * 
	 * @decr : 
	 * 
	 * @param : Player p
	 */
	private static void setLose(Player p) {
		
		p.setMessage("<html>You lost this round but you can still win the game. "
				+ "Please enter a bid to start the next round.</html>");
		
		// player who looses losses a die
		p.removeDieFromCup();
		
		startingPlayer = p;
	
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
			 //System.out.println("startingplayer post id = " + startingPlayer.getPlayerID());
			 // set starting player to the next player in line
			 startingPlayer = getNextPlayer(p);
			 //System.out.println("startingplayer id = " + startingPlayer.getPlayerID());
			 
			 //disable player's window
			 p.disableWindow();			 
			 
			 p.setMessage("<html>You are out of the game.</html>");
			 
			 // remove player
			 players.removeElementAt(index);

		 }
		 
		// reset player tracking variables
		currentPlayer = startingPlayer;
		lastPlayer = startingPlayer;
		//System.out.println("472");
		
		// check if game should continue
		validateGame(startingPlayer);
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
			currentPlayer = players.peek();
			lblTitle.setText("Player " + (currentPlayer.getPlayerID()+1) + " has won the game!");
			lblChallenger.setVisible(false);
			lblBidder.setVisible(false);
		} else {
			// increment the "next player to play" text
			lblTitle.setText("New Round! Player " + (p.getPlayerID()+1) + "'s turn...");
			// clean up flags
			lblBidder.setVisible(false);
			lblChallenger.setVisible(false);
			
			// reset game bid
			currentValidBid[0] = 0;
			currentValidBid[1] = 0;
			
			// start next round
			p.play(true);
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
		if (dieNum < 1 || dieNum > 99) {
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
		
		// moves bidder flag and hides winner flag
		txtWinner.setVisible(false);
		lblBidder.setVisible(true);
		lblBidder.setLocation(155, (40*currentPlayer.getPlayerID()));
		
		// set current bid for all players
		for (int i = 0; i < players.size(); i++) {
			String s = "Current Bid = Dice Number: " + faceValue 
					+  " : " + dieNum + " times"; // don't use s
			players.elementAt(i).getCurrentBid().setText(s);
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
	public static void validateChallenge(){
		
		isCupShaken = false; 
		
		// show all players dice
		populatePlayerDiceLabels();
		
		//disable all players screens
		// add all Player die vales to array
		// each index represents a face value
		for (int i = 0; i < players.size(); i++){
			players.elementAt(i).setButtons(false);
		}
		
		int[] diceValues = { 0, 0, 0, 0, 0, 0};
		
		// moves challenge flag
		lblChallenger.setVisible(true);
		lblChallenger.setLocation(155, (40*currentPlayer.getPlayerID()));
		
		// get dice values
		for (int i = 0; i < players.size(); i++) {

			Stack<Die> playersDice = players.elementAt(i).getPlayerCup().getDice();

			// add all die values
			for (int j = 0; j < playersDice.size(); j++) {

				switch ( playersDice.elementAt(j).getFaceValue()) {

				case 1:
					diceValues[0] += 1;
					break;
				case 2:
					diceValues[1] += 2;
					break;
				case 3:
					diceValues[2] += 3;
					break;
				case 4:
					diceValues[3] += 4;
					break;
				case 5:
					diceValues[4] += 5;
					break;
				case 6:
					diceValues[5] += 6;
					break;
				//default:
				//	System.out.println("Error: in default switch case of validateChallenge");
				//	break;
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
		//System.out.println("currentValidBid[0] = " + currentValidBid[0] );
		//System.out.println("currentValidBid[1] = " + currentValidBid[1] );
		//System.out.println("faceValue = " + faceValue );
		//System.out.println("totalBidValue = " + totalBidValue );
		//System.out.println("diceValues[faceValue] = " + diceValues[faceValue]);
		

	} // end validateChallenge
	
	
	/*
	 * @name : playerIsPlaying
	 * 
	 * @decr : If the player is the first player in a round hide
     * dice labels and reset GUI.
	 * 
	 * @param : Player p
	 */
	public static void playerIsPlaying(Player p) {
		
		// if it is the first play of the game
		if (p == startingPlayer && currentValidBid[0] == 0 
				&& currentValidBid[1] == 0) {
			lblPlayer1Dice.setVisible(false);
			lblPlayer2Dice.setVisible(false);
			lblPlayer3Dice.setVisible(false);
			lblPlayer4Dice.setVisible(false);
			//lblPlayer5Dice.setVisible(false);
			
			//start new round
			// shake dice for next round
			if(!isCupShaken) {
			for (int i = 0; i < players.size(); i++) {
				players.elementAt(i).shakeCup();
			}
			   isCupShaken = true; 
			}
		}
	}
	

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
	    lblTitle.setText("Player " + (currentPlayer.getPlayerID()+1) + "'s turn...");

		currentPlayer.play(false);
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
	 * @decr   : Create game obj and start the program 
	 * @param  : String[] args
	 */
	public static void main(String[] args) {
        new Game();
	} // end main

} // end class