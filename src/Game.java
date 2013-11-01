import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;


public class Game {

	private Game() {
		final JFrame frame = new JFrame ( "Java Othello" );
		frame.setLocation(200,100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());

		final Board board = new Board();			

		final WarningBar warning = new WarningBar();		
		final Score scorebar = new Score(board);
		final MoveList list = new MoveList();
		frame.add(list, BorderLayout.EAST);

		final JPanel MainBoard = new JPanel();
		MainBoard.setLayout(new BorderLayout());
		MainBoard.add(board, BorderLayout.CENTER);
		MainBoard.add(scorebar, BorderLayout.NORTH);
		MainBoard.add(warning, BorderLayout.SOUTH);

		board.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0){
				if (arg0.getButton()==MouseEvent.BUTTON1){//Only React to Left Clicks					
					board.point = arg0.getPoint();
					board.passcount.add(new Integer(0));//Add move holder				
					if (board.isValidMove(board.point)){
						warning.setLabel("");
						while(board.isValidMove(board.point)){//Flip the pieces in all applicable direction						
							Point p = new Point (board.translateToBoard(board.point));							
							if (board.board[p.x][p.y]!=null){
								if (board.movecount==60 || board.checkConsecutivePass()){
									//Display When User Attempt to Make Move When Game Ended
									warning.setLabel("GAME ENDED - START A NEW GAME TO CONTINUE");
								}
								else {
									//Display When User Attempt to Make Move On Non-Empty Space
									warning.setLabel("INVALID MOVE - TRY A HIGHLIGHTED SQUARE");									
								}break;							
							}
							board.flipPiece(board.point,board.color);

						}
						boolean checkMoveMade = board.makeMove(board.point);//Make the move and Store Boolean Value

						if (checkMoveMade) {
							list.addMove(board.color, board.translateToBoard(board.point));//Add Move Coordinate to MoveList
							board.playerSwitch();//Switch Player Color
						}


						//Execute only when there is no possible move for current player
						while (!board.checkForPossibleMove()){
							board.passcount.add(new Integer(1));//Add pass holder
							board.playerSwitch();							
							board.repaint();						
							scorebar.setString();

							if (board.checkConsecutivePass()){//Break if both user passed consecutively
								break;
							}
						}
						board.repaint();															
						scorebar.setString();//Update Score Bar
					}
					else {
						if (board.checkConsecutivePass()||board.movecount==60){							
							warning.setLabel("GAME ENDED - START A NEW GAME TO CONTINUE");
						}
						//Display When Move Is Attempted Outside Playing Area
						else warning.setLabel("INVALID MOVE - TRY A HIGHLIGHTED SQUARE");						
					}
				}				
			}		
		});

		gameNew.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {				
				board.StartPosition();				
				list.reset();
				scorebar.setString();

			}
		});

		final JMenuItem gameQuit = new JMenuItem ( "Quit" );
		gameQuit.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				System.exit(0);				
			}
		});

		Main.add(gameNew);
		Main.add(gameQuit);


		final JMenu Help = new JMenu ( "Help" );		

		final JMenuItem InstructionMenu = new JMenuItem ( "Instruction");
		InstructionMenu.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				Instruction new_instruction = new Instruction();
				new_instruction.setVisible(true);
			}
		});

		final JMenuItem FeatureListMenu = new JMenuItem ( "Feature List");
		FeatureListMenu.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e){
				FeaturesList flist = new FeaturesList();
				flist.setVisible(true);
			}
		});

		final JMenuItem AboutMenu = new JMenuItem ( "About" );
		AboutMenu.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				About new_about = new About();
				new_about.setVisible(true);
			}
		});

		final JMenu Option = new JMenu("Options");
		final JMenu moveListToggle = new JMenu("Move List");		
		final JCheckBoxMenuItem toggleMoveGuide = new JCheckBoxMenuItem("Enable Move Guide");
		final JMenuItem ON = new JMenuItem("ON");
		final JMenuItem OFF = new JMenuItem("OFF");

		toggleMoveGuide.addActionListener(new ActionListener() {			
			public void actionPerformed (ActionEvent e){
				board.toggleGuide = !board.toggleGuide;
				board.repaint();
			}
		});


		ON.addActionListener(new ActionListener() {			
			public void actionPerformed (ActionEvent e){
				list.setVisible(true);
				frame.pack();
			}
		});

		OFF.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e){
				list.setVisible(false);
				frame.pack();
			}
		});
		moveListToggle.add(ON);
		moveListToggle.add(OFF);
		Option.add(moveListToggle);
		Option.add(toggleMoveGuide);

		Help.add(InstructionMenu);
		Help.add(FeatureListMenu);
		Help.add(AboutMenu);


		menuBar.add(Main);
		menuBar.add(Option);
		menuBar.add(Help);	

		frame.add(menuBar, BorderLayout.NORTH);

		frame.add(MainBoard, BorderLayout.CENTER);	

		frame.pack();
		frame.setVisible(true);		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Game();
			}
		});
	}
}
