import java.awt.BorderLayout;
import java.awt.Dimension;
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


public class FeaturesList extends JDialog {
	private JPanel mainPane;
	private JTextPane mainText;
	private JPanel buttonPane;
	private JButton close;

	
	public FeaturesList () {
		
		mainPane = new JPanel();
		mainText = new JTextPane();
		buttonPane = new JPanel();
		close = new JButton();		
		
		setTitle ("Features List");
		setName ("Features List");		
		
		mainText.setText(
				"IMPLEMENTED FEATURES\n\n" +
				"Optional Move Guide and Move List can be enabled or disabled under the Option menu.\n\n" +				
				"Game engine automatically checks board current position after every turn for possible move and handle passing accordingly");
		mainText.setEditable(false);
		mainPane.setMaximumSize(new Dimension(600,300));
			
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
		
		setResizable(true);
		setMinimumSize(new Dimension(700,200));		
		setLocation(100,100);		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
}
