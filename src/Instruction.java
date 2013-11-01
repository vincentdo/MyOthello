import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class Instruction extends JDialog {

	private JPanel mainPane;
	private JTextPane mainText;
	private JPanel buttonPane;
	private JButton close;

	
	public Instruction () {
		
		mainPane = new JPanel();
		mainText = new JTextPane();
		buttonPane = new JPanel();
		close = new JButton();		
		
		setTitle ("Instruction");
		setName ("Instruction");		
		
		mainText.setText(
				"Othello Instruction\n\n" +
				"This game can be played with 2 players.\n\n" +
				"All moves can only be made with the left mouse click\n\n" +
				"The first player to go is black followed by white. \n\n" +
				"Possible moves for each player are highlighted in light green on their respective turn. \n\n" +
				"Each player alternate move until game end. If a player can not make a move he/she must pass. \n" +
				"***(Passing is handled automatically by the game's logic) \n\n" +
				"The game ends when there are no more squares on the board to be filled or if both player pass their turn consecutively. \n\n" +
				"The score will be calculated automatically and display in the score panel above the board.\n\n" +
				"The winner has the higher score. The game is a draw if both player has the same score.\n\n");
		mainText.setEditable(false);
		StyledDocument doc = mainText.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		JScrollPane scrollpane = new JScrollPane(mainText);		
		mainPane.add(scrollpane, BorderLayout.CENTER);
		
		close.setText("CLOSE");
		close.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(close);
		
		getContentPane().add(mainPane, BorderLayout.CENTER);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		
		setSize(700,300);
		setLocation(300,400);
		setResizable(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
}
