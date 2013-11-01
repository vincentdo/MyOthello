import java.awt.Color;
import java.awt.Graphics;


public class Piece {	
	private int DIAMETER;
	protected Color color;

	public Piece(Color c){		
		DIAMETER = 48;
		color = c;
	}
	
	public void setColor(Color c){
		this.color = c;
	}
	
	public void paintPiece(Graphics g, int x, int y) {
		g.setColor(Color.BLACK);
		g.drawOval(x, y, DIAMETER, DIAMETER);
		g.setColor(color);
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
}
