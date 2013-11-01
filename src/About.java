import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class About extends JDialog {

	private JPanel mainPane;
	private JLabel ProjectName;
	private JLabel Author;
	private JLabel Contact;
	private JPanel buttonPane;
	private JButton close;
	
	public About () {
		
		setTitle ("About");
		setName ("About");
		
		mainPane = new JPanel();	
		buttonPane = new JPanel();
		close = new JButton();
		ProjectName = new JLabel("JAVA OTHELLO");
		Author = new JLabel("Vincent Do");
		Contact = new JLabel("<dodviet@sas.upenn.edu>");
		
		ProjectName.setHorizontalAlignment(SwingConstants.CENTER);
		Author.setHorizontalAlignment(SwingConstants.CENTER);
		Contact.setHorizontalAlignment(SwingConstants.CENTER);
		
		mainPane.setLayout(new BorderLayout());
		mainPane.add(ProjectName,BorderLayout.NORTH);
		mainPane.add(Author,BorderLayout.CENTER);
		mainPane.add(Contact,BorderLayout.SOUTH);
		
		close.setText("CLOSE");
		close.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(close);
		
		getContentPane().add(mainPane, BorderLayout.CENTER);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		setSize(300,150);
		setLocation(300,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
}
