import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.omg.CORBA.Request;

public class Player extends JFrame implements ActionListener {
	
	// class variables
	int PlayerID;
	private Cup playerCup;

	// class GUI components 
	
	Container con;

	JPanel pnlPortal, pnlRow1, pnlRow2, pnlRow3, pnlAction, pnlBid, pnlCup;

	JLabel lblErrorMsg, lblCurrentBid, lblPlayerID, lblDie1, lblDie2, lblDie3, lblDie4, lblDie5;

	JLabel[] lblDice = { lblDie1, lblDie2, lblDie3, lblDie4, lblDie5 };

	JButton btnBid, btnChallenge, btnHideDice;

	JTextField txtBidDieValue, txtBidDieNum;

	public Player(int x) { // note consider changing x to something more descriptive?
		
		super("Player Portal");
		
		PlayerID = x; 
		
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

		lblCurrentBid = new JLabel("Current Bid = 0"); //TODO - get real current bid
		lblCurrentBid.setLocation(0, 0);
		lblCurrentBid.setSize(100, 40);
		lblCurrentBid.setHorizontalAlignment(0);
		lblCurrentBid.setForeground(Color.black);
		pnlRow1.add(lblCurrentBid);

		lblPlayerID = new JLabel("Player" + PlayerID);
		lblPlayerID.setLocation(240, 0);
		lblPlayerID.setSize(380, 40);
		lblPlayerID.setHorizontalAlignment(0);
		lblPlayerID.setForeground(Color.black);
		pnlRow1.add(lblPlayerID);

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

		for (int i = 0; i < lblDice.length; i++) {
			lblDice[i] = new JLabel(" D ");
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
		

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		con = this.getContentPane(); 
		this.setSize(500, 280);
		con.add(pnlPortal); 
		setVisible(true); 
		
		
	    //// Setup first players GUI ////
		if (x == 0) {
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
	
	
	public void setPlayerID(int x) {
		PlayerID = x;

	}

	public int getPlayerID() {
		return PlayerID;

	}
	
	public Cup getPlayerCup() {
		return playerCup;

	}

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
				Game.turnOver(this);
			}
		} else {
			this.lblErrorMsg.setVisible(true);
			// TODO: enter code to renable screen for player to enter new bid or
			// challenge
		}
	}

	public void play() {
		// enable this players buttons
		btnBid.getText().equals("Bid");

		// disable this players buttons
	}


} // end class Player
