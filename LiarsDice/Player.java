import java.util.Stack;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.omg.CORBA.Request;


/*
 * @author : John DePaco, Kaylie Anderson, and Cameron Walworth
 * 
 * @name : Player
 * 
 * @decr : One player represents one user in the game. It is the 
 * player's private portal to access their game information and 
 * interact with the game.
 * 
 * @param : void
 */
public class Player extends JFrame implements ActionListener {

	// class variables
	int PlayerID;
	private Cup playerCup;

	// class GUI components

	Container con;

	JPanel pnlPortal, pnlRow1, pnlRow2, pnlRow3, pnlAction, pnlBid, pnlCup;

	JLabel lblErrorMsg, lblCurrentBid, lblDie1, lblDie2, lblDie3, lblDie4, lblDie5;

	JLabel[] lblDice = { lblDie1, lblDie2, lblDie3, lblDie4, lblDie5 };

	JButton btnBid, btnChallenge, btnHideDice;

	JTextField txtBidDieValue, txtBidDieNum;
	
	
	/*
	 * @name : Player(int id)
	 * 
	 * @decr : setup GUI and enable the first player to play
	 * 
	 * @param : void
	 */
	public Player(int id) { // changed this so I could use x for x and y coordinates

		super("Player" + id);

		PlayerID = id;

		//// Player Setup ////
		playerCup = new Cup();
		playerCup.shake();
		playerCup.displayDiceValues();

		//// Create GUI ////

		setBounds(100, 100, 300, 100);

		// We create a bottom JPanel to place everything on.
		pnlPortal = new JPanel();
		pnlPortal.setLayout(null);

		// Creation of a Panel to contain the title labels
		pnlRow1 = new JPanel();
		pnlRow1.setLayout(null);
		pnlRow1.setLocation(10, 10);
		pnlRow1.setSize(480, 40);
		pnlPortal.add(pnlRow1);
		int[] arr = Game.getCurrentValidBid();
		String s = "Current Bid = Die Number: " + arr[0] +  " Face Value: " + arr[1]; // don't use s
		lblCurrentBid = new JLabel(s); // TODO - get real
														// current bid
		lblCurrentBid.setLocation(0, 0);
		lblCurrentBid.setSize(275, 40);
		lblCurrentBid.setHorizontalAlignment(0);
		lblCurrentBid.setForeground(Color.black);
		pnlRow1.add(lblCurrentBid);

		//lblPlayerID = new JLabel("Player" + PlayerID);
		//lblPlayerID.setLocation(240, 0);
		//lblPlayerID.setSize(380, 40);
		//lblPlayerID.setHorizontalAlignment(0);
		//lblPlayerID.setForeground(Color.black);
		//pnlRow1.add(lblPlayerID);

		pnlRow2 = new JPanel();
		pnlRow2.setLayout(null);
		pnlRow2.setLocation(10, 50);
		pnlRow2.setSize(480, 20);
		pnlPortal.add(pnlRow2);

		lblErrorMsg = new JLabel("Invalid bid. Please enter a new bid or challenge.");
		lblErrorMsg.setLocation(0, 0);
		lblErrorMsg.setSize(480, 20);
		lblErrorMsg.setHorizontalAlignment(0);
		lblErrorMsg.setForeground(Color.red);
		lblErrorMsg.setVisible(false);
		pnlRow2.add(lblErrorMsg);

		pnlRow3 = new JPanel();
		pnlRow3.setLayout(null);
		pnlRow3.setLocation(10, 70);
		pnlRow3.setSize(480, 180);
		pnlPortal.add(pnlRow3);

		pnlAction = new JPanel();
		pnlAction.setLayout(null);
		pnlAction.setLocation(10, 10);
		pnlAction.setSize(100, 160);
		pnlRow3.add(pnlAction);

		btnBid = new JButton("Bid");
		btnBid.setLocation(0, 40);
		btnBid.setSize(100, 40);
		btnBid.addActionListener(this);
		pnlAction.add(btnBid);

		pnlBid = new JPanel();
		pnlBid.setLayout(null);
		pnlBid.setLocation(0, 80);
		pnlBid.setSize(100, 80);
		pnlAction.add(pnlBid);

		txtBidDieValue = new JTextField("Enter Die Value");
		txtBidDieValue.setLocation(0, 0);
		txtBidDieValue.setSize(100, 40);
		txtBidDieValue.setFont(new Font("Lucida", Font.PLAIN, 10));
		txtBidDieValue.setVisible(false);
		txtBidDieValue.addActionListener(this);
		pnlBid.add(txtBidDieValue);

		txtBidDieNum = new JTextField("Enter Die Num");
		txtBidDieNum.setLocation(0, 40);
		txtBidDieNum.setSize(100, 40);
		txtBidDieNum.setFont(new Font("Lucida", Font.PLAIN, 10));
		txtBidDieNum.setVisible(false);
		txtBidDieNum.addActionListener(this);
		pnlBid.add(txtBidDieNum);

		btnChallenge = new JButton("Challenge");
		btnChallenge.setLocation(0, 0);
		btnChallenge.setSize(100, 40);
		btnChallenge.addActionListener(this);
		pnlAction.add(btnChallenge);

		pnlCup = new JPanel();
		pnlCup.setLayout(null);
		pnlCup.setLocation(120, 10);
		pnlCup.setSize(350, 160);
		pnlCup.setBackground(Color.lightGray);
		pnlRow3.add(pnlCup);

		btnHideDice = new JButton("Show Dice");
		btnHideDice.setLocation(0, 0);
		btnHideDice.setSize(350, 40);
		btnHideDice.addActionListener(this);
		pnlCup.add(btnHideDice);
		
        Stack<Die> dice = playerCup.getDice();
		for (int i = 0; i < lblDice.length; i++) {
			s = "" + dice.elementAt(i).getFaceValue();
			lblDice[i] = new JLabel(s);
			lblDice[i].setLocation(((i + 1) * 50), 50);
			lblDice[i].setSize(40, 40);
			lblDice[i].setFont(new Font("Lucida", Font.PLAIN, 30)); // 24 = size
			lblDice[i].setHorizontalAlignment(0);
			lblDice[i].setForeground(Color.black);
			lblDice[i].setVisible(false);
			pnlCup.add(lblDice[i]);
		}

		pnlPortal.setOpaque(true);

		if (PlayerID != 0) {
			this.pnlAction.setVisible(false);
			this.pnlAction.setBackground(Color.BLACK);
			this.pnlBid.setEnabled(false);
			this.btnBid.setEnabled(false);
			this.btnChallenge.setEnabled(false);
		}
		
		int xAdjust = 0;
		int yAdjust = 0;
		
		switch(id) {
		case 0 :
			xAdjust = -500;
			yAdjust = -200;
			break;
		case 1 :  
			xAdjust = 500;
			yAdjust = -200;
			break;
		case 2 :
			xAdjust = -500;
			yAdjust = 200;
			break;
		case 3 :
			xAdjust = 500;
			yAdjust = 200;
			break;	
		}
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = xAdjust + ( (int) ((dimension.getWidth() - getWidth()) / 2) );
	    int y = yAdjust + ( (int) ((dimension.getHeight() - getHeight()) / 2) );
	    setLocation(x, y);
		
		
		
		
        this.setVisible(true);

		con = this.getContentPane();
		this.setSize(500, 280);
		con.add(pnlPortal);
		setVisible(true);

		//// Setup first players GUI ////
		if (id == 0) {
			play();
		}

	} // end constructor

	/*
	 * @name : actionPerformed
	 * 
	 * @decr :
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * Catches any event triggered that has an ActionListener attached
	 * 
	 * @param : ActionEvent e
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBid) {

			if (btnBid.getText().equals("Bid")) {
				btnBid.setText("Enter Bid");

				// set text fields to true so player can enter bid
				txtBidDieValue.setVisible(true);
				txtBidDieNum.setVisible(true);

			} else if (btnBid.getText().equals("Enter Bid")) {
				btnBid.setText("Bid Entered");
				txtBidDieValue.setVisible(false);
				txtBidDieNum.setVisible(false);

				// call bid() to process text filed data
				bid();
			}

		} else if (e.getSource() == btnChallenge) {

			if (btnChallenge.getText().equals("C1")) {
				btnChallenge.setText("C0");
			} else {
				btnChallenge.setText("C1");
			}

			Game.validateChallenge();

		} else if (e.getSource() == btnHideDice) {

			if (btnHideDice.getText().equals("Show Dice")) {
				btnHideDice.setText("Hide Dice");
				for (int i = 0; i < lblDice.length; i++) {
					lblDice[i].setVisible(true);
				}
			} else {
				btnHideDice.setText("Show Dice");
				for (int i = 0; i < lblDice.length; i++) {
					lblDice[i].setVisible(false);
				}
			}

		} else if (e.getSource() == txtBidDieValue) {

			// cover txtbox with bid btn

		} else if (e.getSource() == txtBidDieNum) {

			// cover txtbox with bid btn

		}
	}

	/*
	 * @name : setPlayerID
	 * 
	 * @decr : TODO - do we really need this?
	 * 
	 * @param : int x
	 */
	public void setPlayerID(int x) {
		PlayerID = x;

	}

	/*
	 * @name : getPlayerID
	 * 
	 * @decr : return the player's ID
	 * 
	 * @param : void
	 */
	public int getPlayerID() {
		return PlayerID;

	}

	/*
	 * @name : getPlayerCup
	 * 
	 * @decr : return the player's cup object
	 * 
	 * @param : void
	 */
	public Cup getPlayerCup() {
		return playerCup;
	}

	/*
	 * @name : bid
	 * 
	 * @decr : Check that data entered in txtBidDieValue and txtBidDieNum are
	 * actually numbers. Also check that DieValue data is between 1 and 6
	 * (exclusive). If data is valid set bid to Game so that is can be compared
	 * to the Game's current bid. Else display error and have player renter the
	 * play use case.
	 * 
	 * @param : void
	 */
	private void bid() { // diceNum faceValue
		// send diceNum and faceValue to
		String faceValue = this.txtBidDieValue.getText();
		String dieNum = this.txtBidDieNum.getText();
		boolean valid = true;
		for (int i = 0; i < faceValue.length(); i++) {
			char c = faceValue.charAt(i);
			if (!Character.isDigit(c)) {
				valid = false;
			}
		}
		for (int a = 0; a < dieNum.length(); a++) {
			char b = faceValue.charAt(a);
			if (!Character.isDigit(b)) {
				valid = false;
			}
		}
		if (valid == true) {
			int face = Integer.parseInt(faceValue);
			int die = Integer.parseInt(dieNum);
			if (Game.validateBid(die, face) == true) {
				this.pnlAction.setVisible(false);
				this.pnlAction.setBackground(Color.BLACK);
				this.pnlBid.setEnabled(false);
				this.btnBid.setEnabled(false);
				this.btnChallenge.setEnabled(false);
				Game.turnOver(this);
			}
		} else {
			this.lblErrorMsg.setVisible(true);
			this.btnBid.setText("Bid");
			// TODO: enter code to renable screen for player to enter new bid or
			// challenge
		}
	}

	/*
	 * @name : play TODO - method can now be finished
	 * 
	 * @decr : set this player's GUI up so user can take their turn.
	 * 
	 * @param : void
	 */
	public void play() {
		// enable this players buttons
		//this.btnBid.getText().equals("Bid");
		this.btnBid.setText("Bid");
		this.pnlAction.setVisible(true);
		this.pnlAction.setBackground(Color.BLACK);
		this.pnlBid.setEnabled(true);
		this.btnBid.setEnabled(true);
		this.btnChallenge.setEnabled(true);
		
		// disable this players buttons
	}

} // end class Player
