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

	JLabel lblErrorMsg, lblMsg, lblCurrentBid, lblDie1, lblDie2, lblDie3, lblDie4, lblDie5;

	JButton btnBid, btnChallenge, btnHideDice;

	JTextField txtBidDieValue, txtBidDieNum;
	
	Stack<JLabel> lblDice = new Stack<JLabel>();  
	
	
	/*
	 * @name : Player(int id)
	 * 
	 * @decr : setup GUI and enable the first player to play
	 * 
	 * @param : void
	 */
	public Player(int id) { // changed this so I could use x for x and y coordinates

		super("Player" + (id+1));

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
		pnlRow1.setSize(480, 20);
		pnlRow1.setBackground(Color.white);
		pnlPortal.add(pnlRow1);
		
		int[] arr = Game.getCurrentValidBid();
		String s = "Current Bid = Die Number: " + arr[0] +  " Face Value: " + arr[1]; // don't use s
		lblCurrentBid = new JLabel(s); // TODO - get real
		lblCurrentBid.setLocation(90, 0);
		lblCurrentBid.setSize(300, 20);
		lblCurrentBid.setHorizontalAlignment(0);
		lblCurrentBid.setForeground(Color.black);
		Font font = lblCurrentBid.getFont();
		Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
		lblCurrentBid.setFont(boldFont);
		pnlRow1.add(lblCurrentBid);  

		pnlRow2 = new JPanel();
		pnlRow2.setLayout(null);
		pnlRow2.setLocation(10, 30);
		pnlRow2.setSize(480, 40);
		pnlPortal.add(pnlRow2);

		lblErrorMsg = new JLabel("Invalid bid. Please enter a new bid or challenge.");
		lblErrorMsg.setLocation(0, 0);
		lblErrorMsg.setSize(480, 40);
		lblErrorMsg.setHorizontalAlignment(0);
		lblErrorMsg.setForeground(Color.red);
		lblErrorMsg.setVisible(false);
		pnlRow2.add(lblErrorMsg);
		
		lblMsg = new JLabel("");
		lblMsg.setLocation(0, 0);
		lblMsg.setSize(480, 40);
		lblMsg.setHorizontalAlignment(0);
		lblMsg.setForeground(Color.black);
		lblMsg.setVisible(false);
		pnlRow2.add(lblMsg);

		pnlRow3 = new JPanel();
		pnlRow3.setLayout(null);
		pnlRow3.setLocation(10, 70);
		pnlRow3.setSize(480, 180);
		pnlPortal.add(pnlRow3);

		pnlAction = new JPanel();
		pnlAction.setLayout(null);
		pnlAction.setLocation(10, 10);
		pnlAction.setSize(100, 160);
		pnlAction.setBackground(Color.lightGray);
		pnlRow3.add(pnlAction); // 30

		btnBid = new JButton("Bid");
		btnBid.setLocation(0, 40);
		btnBid.setSize(100, 40);
		btnBid.setEnabled(false);
		btnBid.addActionListener(this);
		pnlAction.add(btnBid);

		pnlBid = new JPanel();
		pnlBid.setLayout(null);
		pnlBid.setLocation(0, 80);
		pnlBid.setSize(100, 80);
		pnlBid.setBackground(Color.lightGray);
		pnlAction.add(pnlBid);

		txtBidDieValue = new JTextField("Dice Number");
		txtBidDieValue.setLocation(0, 0);
		txtBidDieValue.setSize(100, 40);
		txtBidDieValue.setFont(new Font("Lucida", Font.PLAIN, 10));
		txtBidDieValue.setVisible(false);
		txtBidDieValue.addActionListener(this);
		pnlBid.add(txtBidDieValue);

		txtBidDieNum = new JTextField("Number of Dice");
		txtBidDieNum.setLocation(0, 40);
		txtBidDieNum.setSize(100, 40);
		txtBidDieNum.setFont(new Font("Lucida", Font.PLAIN, 10));
		txtBidDieNum.setVisible(false);
		txtBidDieNum.addActionListener(this);
		pnlBid.add(txtBidDieNum);

		btnChallenge = new JButton("Challenge");
		btnChallenge.setLocation(0, 0);
		btnChallenge.setSize(100, 40);
		btnChallenge.setEnabled(false);
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
		
		lblDie5 = new JLabel();
		lblDie4 = new JLabel();
		lblDie3 = new JLabel();
		lblDie2 = new JLabel();
		lblDie1 = new JLabel();
		
		lblDice.push(lblDie5);
		lblDice.push(lblDie4);
		lblDice.push(lblDie3);
		lblDice.push(lblDie2);
		lblDice.push(lblDie1);  
		
        Stack<Die> dice = playerCup.getDice();
        
		for (int i = 0; i < lblDice.size(); i++) {
			s = "" + dice.elementAt(i).getFaceValue();
			lblDice.elementAt(i).setText(s);
			lblDice.elementAt(i).setLocation(((i + 1) * 50), 50);
			lblDice.elementAt(i).setSize(40, 40);
			lblDice.elementAt(i).setFont(new Font("Lucida", Font.PLAIN, 30)); // 24 = size
			lblDice.elementAt(i).setHorizontalAlignment(0);
			lblDice.elementAt(i).setForeground(Color.black);
			lblDice.elementAt(i).setVisible(false);
			pnlCup.add(lblDice.elementAt(i));
		}

		pnlPortal.setOpaque(true);
		
		int xAdjust = 0;
		int yAdjust = 0;
		
		switch(id) {
		case 0 :
			xAdjust = -600;
			yAdjust = -375;
			break;
		case 1 :  
			xAdjust = 365;
			yAdjust = -375;
			break;
		case 2 :
			xAdjust = -600;
			yAdjust = 25;
			break;
		case 3 :
			xAdjust = 365;
			yAdjust = 25;
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
		this.setResizable(false);

		//// Setup first players GUI ////
		if (id == 0) {
			play(true);
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
			
			Game.playerIsPlaying(this);

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
			
			Game.playerIsPlaying(this);
			
			// if plater wrote in txts and then decided to bid
			txtBidDieValue.setVisible(false);
			txtBidDieNum.setVisible(false);

			Game.validateChallenge();

		} else if (e.getSource() == btnHideDice) {

			if (btnHideDice.getText().equals("Show Dice")) {
				btnHideDice.setText("Hide Dice");
				for (int i = 0; i < lblDice.size(); i++) {
					lblDice.elementAt(i).setVisible(true);
				}
			} else {
				btnHideDice.setText("Show Dice");
				for (int i = 0; i < lblDice.size(); i++) {
					lblDice.elementAt(i).setVisible(false);
				}
			}

		}
		
		// Actions that happen no matter what GUI component was triggered
		
		txtBidDieValue.setText("Dice Number");
		txtBidDieNum.setText("Number of Dice");
		
		// if any action happen reset general msg label
		if (lblMsg.isVisible()) {
			lblMsg.setVisible(false);
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
	 * @name : getPlayerCup
	 * 
	 * @decr : return the player's cup object
	 * 
	 * @param : void
	 */
	public void removeDieFromCup() {
		
		playerCup.loseDie();
		lblDice.lastElement().setVisible(false);
		System.out.println("359 = " + lblDice.lastElement().getText());
		
		lblDice.pop();
	}
	
	/*
	 * @name : shakeCup
	 * 
	 * @decr : get new dice values and refreshes lbls
	 *         to show updated dice values
	 * 
	 * @param : void
	 */
	public void shakeCup() {
		
		// shake dice
		playerCup.shake();
		
		Stack<Die> d = playerCup.getDice();
		
		// reset lbl text
		for(int i = 0; i < lblDice.size(); i++) {
			lblDice.elementAt(i).setText("" + d.elementAt(i).getFaceValue());
			System.out.println(i + " = " + d.elementAt(i).getFaceValue() );
		}
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
		//TODO reenable bid buttons when appears
		
		// send diceNum and faceValue to
		
		String faceValue = this.txtBidDieValue.getText();
		String dieNum = this.txtBidDieNum.getText();
		this.lblErrorMsg.setVisible(false);
		boolean valid = true;
		for (int i = 0; i < faceValue.length(); i++) {
			char c = faceValue.charAt(i);
			if (!Character.isDigit(c)) {
				valid = false;
			}
		}
		for (int a = 0; a < dieNum.length(); a++) {
			System.out.println("a" + a);
			System.out.println("Die Num Length" + dieNum.length());
			char b = dieNum.charAt(a);
			if (!Character.isDigit(b)) {
				valid = false;
			}
		}
		if (valid == true) {
			int face = Integer.parseInt(faceValue);
			int die = Integer.parseInt(dieNum);
			
			System.out.println("face = " + face);
			System.out.println("die = " + die );
			
			if (Game.validateBid(die, face) == true) {
				this.pnlBid.setEnabled(false);
				this.btnBid.setEnabled(false);
				this.btnChallenge.setEnabled(false);
				this.txtBidDieNum.setText("Dice Number");
				this.txtBidDieValue.setText("Number of Dice");
				Game.turnOver(this);
				//txtBidDieValue = new JTextField("Enter Dice Number");
			}else {
				this.lblErrorMsg.setVisible(true);
				this.btnBid.setText("Bid");
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
	public void play(boolean isFistTurn) {
		
		// first turn of a round the player has no one 
		// to challenge so deisablbe challenge btn
		if (isFistTurn) {
			this.btnChallenge.setEnabled(false);
		} else {
			this.btnChallenge.setEnabled(true);
		}
		
		// enable this players buttons		
		this.btnBid.setText("Bid");
		this.btnBid.setVisible(true);
		
		this.pnlBid.setEnabled(true);
		this.btnBid.setEnabled(true);
		
		this.lblErrorMsg.setVisible(false);
		
		// disable this players buttons
	}

} // end class Player
