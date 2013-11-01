import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;


public class MoveList extends JPanel {
	
	private JScrollPane scroll;
	private JPanel topPane;
	private JPanel centerPane;
	private JTextPane move_black;
	private JTextPane move_white;	
	private String blacktext;
	private String whitetext;
	private String prefix;
	private int suffix;
	private int movecount;
	
	public MoveList (){
		setLayout(new BorderLayout());
		move_black = new JTextPane();		
		move_white = new JTextPane();
		move_black.setBackground(null);
		move_white.setBackground(null);
		topPane = new JPanel();		
		topPane.add(new JLabel("MOVE LIST"));
		centerPane = new JPanel();
		centerPane.add(move_black, BorderLayout.WEST);
		centerPane.add(move_white, BorderLayout.EAST);		
		move_black.setAlignmentY(SwingConstants.TOP);
		move_white.setAlignmentY(SwingConstants.TOP);
		reset();
		
		add(topPane, BorderLayout.NORTH);
		add(centerPane,BorderLayout.CENTER);				
				
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setFocusable(true);
		setVisible(false);
	}
	
	public void reset(){
		blacktext = "BLACK \n";
		whitetext = "WHITE \n";
		move_black.setText(blacktext);
		move_white.setText(whitetext);	
		movecount = 0;		
	}
	
	public void updatePrefix(Point p){
		
		switch(p.x){
		case 0: prefix = "A";break;
		case 1: prefix = "B";break;		
		case 2: prefix = "C";break;
		case 3: prefix = "D";break;
		case 4: prefix = "E";break;
		case 5: prefix = "F";break;
		case 6: prefix = "G";break;
		case 7: prefix = "H";break;			
		}		
	}
	
	public void updateSuffix(Point p){
		suffix = p.y+1;
	}
	
	public void addMove (Color c, Point p){
		updatePrefix(p);
		updateSuffix(p);
		movecount++;
		if (c==Color.BLACK){
			blacktext = blacktext + movecount + ". " + prefix + suffix + "\n";
			move_black.setText(blacktext);
		}
		else {
			whitetext = whitetext + movecount + ". " + prefix + suffix + "\n";
			move_white.setText(whitetext);
		}		
	}
}
