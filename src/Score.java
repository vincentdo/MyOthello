import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Score extends JPanel {
	private Board b;
	private int WHITE_SCORE;
	private int BLACK_SCORE;
	private String BlackLabel;
	private String WhiteLabel;
	private String PlayerLabel;
	private JLabel Wlabel;
	private JLabel Blabel;
	private JLabel PLabel;

	public Score(Board boardx) {
		b=boardx;		
		Wlabel = new JLabel();
		Blabel = new JLabel();
		PLabel = new JLabel();
		setString();		
		add(Wlabel);
		add(new JLabel("             "));//Spacer
		add(PLabel);
		add(new JLabel("             "));//Spacer
		add(Blabel);		
		
		setSize(500, 30);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setFocusable(true);	
	}
	
	public void setScore(){

		WHITE_SCORE = 0;
		BLACK_SCORE = 0;
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++) {
				if (b.board[i][j]!=null){
					if (b.board[i][j].color == Color.WHITE) {
						WHITE_SCORE++;
					}
					if (b.board[i][j].color == Color.BLACK) {
						BLACK_SCORE++;					
					}
				} 
			}
		}		
	}	
	
	public void setString(){
		setScore();		
		WhiteLabel = "White: "+WHITE_SCORE;
		BlackLabel = "Black: "+BLACK_SCORE;			

		if (b.movecount==60){// Game Ends After 60 Moves
			if (WHITE_SCORE>BLACK_SCORE){
				PlayerLabel = "GAME OVER - WHITE WINS";
			}
			if (BLACK_SCORE>WHITE_SCORE){
				PlayerLabel = "GAME OVER - BLACK WINS";
			}
			if (BLACK_SCORE==WHITE_SCORE){
				PlayerLabel = "GAME OVER - IT'S A DRAW";
			}
		}	
		else if(b.checkConsecutivePass()&&b.movecount!=60){//Game Ends After Consecutive Pass
			if (WHITE_SCORE>BLACK_SCORE){
				PlayerLabel = "BOTH PLAYER PASSED - WHITE WINS";
			}
			if (BLACK_SCORE>WHITE_SCORE){
				PlayerLabel = "BOTH PLAYER PASSED - BLACK WINS";
			}
		}	
		else {
			if (b.color==Color.WHITE){
				PlayerLabel = "WHITE'S  TURN";
			}
			else PlayerLabel = "BLACK'S  TURN";
		}
		Wlabel.setText(WhiteLabel);
		Blabel.setText(BlackLabel);
		PLabel.setText(PlayerLabel);
	}


}
