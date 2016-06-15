import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Player {
	
	Player(int id) {

				final Portal p = new Portal();

				// Schedule a job for the event-dispatching thread:
				// creating and showing this application's GUI.
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						p.createAndShowGUI();
					}

				}); // end SwingUtilities
	}
	
    /**
     * @name   : Portal
	 * @author : Kaylie Anderson
	 * @decr   : TODO
     */
	public class Portal implements ActionListener {

	    // Global variable definitions for GUI items

	    JPanel row1, row2, row3, actionPanel, bidPanel, cupPanel;
	    
	    JLabel lblErrorMsg, lblCurrentBid, lblPlayerID,
	           lblDie1, lblDie2, lblDie3, lblDie4, lblDie5;
	    
	    JButton btnBid, btnChallenge, btnHideDice;
	    
	    JTextField txtBidDieValue, txtBidDieNum; 

	    public JPanel portalPanel () {

	        // We create a bottom JPanel to place everything on.
	        JPanel portalGUI = new JPanel();
	        portalGUI.setLayout(null);

	        // Creation of a Panel to contain the title labels
	        row1 = new JPanel();
	        row1.setLayout(null);
	        row1.setLocation(10, 10);
	        row1.setSize(480, 40);
	        //row1.setBackground(Color.blue);
	        portalGUI.add(row1);
	        
	        lblCurrentBid = new JLabel("Current Bid");
	        lblCurrentBid.setLocation(0, 0);
	        lblCurrentBid.setSize(100, 40);
	        lblCurrentBid.setHorizontalAlignment(0);
	        lblCurrentBid.setForeground(Color.black);
	        row1.add(lblCurrentBid);
	        
	        lblPlayerID = new JLabel("Player n");
	        lblPlayerID.setLocation(240, 0);
	        lblPlayerID.setSize(380, 40);
	        lblPlayerID.setHorizontalAlignment(0);
	        lblPlayerID.setForeground(Color.black);
	        row1.add(lblPlayerID);
	        
	        row2 = new JPanel();
	        row2.setLayout(null);
	        row2.setLocation(10, 50);
	        row2.setSize(480, 20);
	        //row2.setBackground(Color.black);
	        portalGUI.add(row2);
	        
	        lblErrorMsg = new JLabel("Invalid bid. Please enter a new bid or challenge.");
	        lblErrorMsg.setLocation(0, 0);
	        lblErrorMsg.setSize(480, 20);
	        lblErrorMsg.setHorizontalAlignment(0);
	        lblErrorMsg.setForeground(Color.red);
	        lblErrorMsg.setVisible(false); // if bid invalid show this error
	        row2.add(lblErrorMsg);
	        
	        row3 = new JPanel();
	        row3.setLayout(null);
	                     // left, down
	        row3.setLocation(10, 70);
	        row3.setSize(480, 180);
	        //row3.setBackground(Color.pink);
	        portalGUI.add(row3);
	        
	        actionPanel = new JPanel();
	        actionPanel.setLayout(null);
	        actionPanel.setLocation(10, 10);
	        actionPanel.setSize(100, 160);
	        row3.add(actionPanel);
	        
	        // We create a button and manipulate it using the syntax we have
	        // used before. Now each button has an ActionListener which posts 
	        // its action out when the button is pressed.
	        btnBid = new JButton("Bid");
	        btnBid.setLocation(0, 40);
	        btnBid.setSize(100, 40);
	        btnBid.addActionListener(this);
	        actionPanel.add(btnBid);
	        
	        bidPanel = new JPanel();
	        bidPanel.setLayout(null);
	        bidPanel.setLocation(0, 80);
	        bidPanel.setSize(100, 80);
	        //bidPanel.setBackground(Color.green);
	        actionPanel.add(bidPanel);
	        
	        txtBidDieValue = new JTextField("Enter Die Value");
	        txtBidDieValue.setLocation(0, 0);
	        txtBidDieValue.setSize(100, 40);
	        txtBidDieValue.addActionListener(this);
	        bidPanel.add(txtBidDieValue);
	        
	        txtBidDieNum = new JTextField("Enter Die Num");
	        txtBidDieNum.setLocation(0, 40);
	        txtBidDieNum.setSize(100, 40);
	        txtBidDieNum.addActionListener(this);
	        bidPanel.add(txtBidDieNum);
	       

	        btnChallenge = new JButton("Challenge");
	        btnChallenge.setLocation(0, 0);
	        btnChallenge.setSize(100, 40);
	        btnChallenge.addActionListener(this);
	        actionPanel.add(btnChallenge);
	        
	        cupPanel = new JPanel();
	        cupPanel.setLayout(null);
	        cupPanel.setLocation(120, 10);
	        cupPanel.setSize(350, 160);
	        cupPanel.setBackground(Color.lightGray);
	        row3.add(cupPanel);
	        
	        btnHideDice = new JButton("Show Dice");
	        btnHideDice.setLocation(0, 0);
	        btnHideDice.setSize(350, 40);
	        btnHideDice.addActionListener(this);
	        cupPanel.add(btnHideDice);
	        
	        portalGUI.setOpaque(true);
	        return portalGUI;
	    }

	    /*
		 * @name   : actionPerformed
		 * @author : Kaylie Anderson
		 * @decr   : java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 *           Catches any event triggered that has an ActionListener attached
		 * @param  : ActionEvent e
		 */
	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == btnBid) {
	        	
	        	actionPanel.setVisible(true);
	            
	        } else if (e.getSource() == btnChallenge) {
	        	
	            btnChallenge.setText("Challenge Placed");
	            handleBid();
	            
	        } else if(e.getSource() == btnHideDice) {
	        	// need if statement for show or hide text
	        	btnHideDice.setText("Hide Dice");
	        
	        } else if(e.getSource() == txtBidDieValue) {

	        	// cover txtbox with bid btn
	        	
	        } else if(e.getSource() == txtBidDieNum) {

	        	// cover txtbox with bid btn
	        	
	        }
	    }

	    /*
	     * @name   : createAndShowGUI
		 * @author : Kaylie Anderson
		 * @decr   : creates and opens GUI
		 * @param  : void
	     */
	    public void createAndShowGUI() {

	        JFrame.setDefaultLookAndFeelDecorated(true);
	        JFrame frame = new JFrame("Playern's Portal");

	        //create and set up the jframe.
	        Portal p = new Portal();
	        frame.setContentPane(p.portalPanel());

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(500, 280);
	        frame.setVisible(true);
	    }
	    
} // end class Portal
	
	private void handleBid () {
		boolean isBidValid = Game.validateBid(1, 1);
		
		if (isBidValid) {
			// call next player
			Game.turnOver(this);
		} else {
			// signal to player to reenter bid
		}
	}


} // end class Player
