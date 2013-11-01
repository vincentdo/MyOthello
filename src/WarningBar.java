import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WarningBar extends JPanel{
		
		private String label;
		private JLabel warninglabel;
		
		
		public WarningBar(){
			warninglabel = new JLabel();			
			add(warninglabel);
			setPreferredSize(new Dimension(500, 30));
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
			setLocation(0,470);
			setFocusable(true);			
		}
		
		public void setLabel(String s){
			
			label = s;		
			warninglabel.setText(label);
		}
	}