import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Board extends JPanel {
	private int HEIGHT;
	private int WIDTH;
	private int CHECK_X;//X-coordinate of the boundary piece 
	private int CHECK_Y;//Y-coordinate of the boundary piece
	private ArrayList<Point> legalMoves;	
	protected ArrayList<Integer> passcount; //keep track of number of valid move (0) and passes (1)
	protected boolean toggleGuide;//Toggle switch for move guide
	protected int movecount;//keep track of number of move
	protected Point point;	
	protected Color color;//Color of player
	protected Piece[][] board;//Array holding game pieces

	public Board() {		
		HEIGHT = 400;
		WIDTH = 400;
		toggleGuide = true;		

		resetCheckXY();
		StartPosition();

		setPreferredSize(new Dimension(500, 500));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setFocusable(true);
	}

	public void StartPosition() {
		color= Color.BLACK;
		legalMoves= new ArrayList<Point>();
		passcount = new ArrayList<Integer>();
		movecount = 0;
		board = new Piece[8][8];		
		board[3][3] = new Piece(Color.WHITE);
		board[4][4] = new Piece(Color.WHITE);
		board[3][4] = new Piece(Color.BLACK);
		board[4][3] = new Piece(Color.BLACK);
		checkForPossibleMove();
		repaint();		
	}

	public void resetCheckXY(){
		CHECK_X=0;
		CHECK_Y=0;
	}

	public boolean checkForPossibleMove(){		
		legalMoves.clear();
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				if(board[i][j]==null){
					Point current = new Point((i*50+50),(j*50+50));
					if (isValidMove(current)){					
						legalMoves.add(current);					
					}
				}
			}
		}
		return !legalMoves.isEmpty();
	}	

	public boolean checkConsecutivePass(){
		boolean check = false;

		if (passcount.size()>=2){	
			for (int i=0, j=1; i<passcount.size()-2 && j<passcount.size()-1; i++,j++){
				if (passcount.get(i)==1 && passcount.get(j)==1){
					check = true;
					break;
				}
			}
		}		
		return check;		
	}

	//Translate pixel coordinate to array indexes
	public Point translateToBoard(Point p){
		if (p.x>=50&&p.x<=450&&p.y>=50&&p.y<=450){
		int x = (p.x-50)/50;
		int y = (p.y-50)/50;		
		return new Point(x,y);
		}
		else return p;
	}

	//Check to make sure that adjacent pieces are of different colors
	public boolean checkFirst(int x, int y){
		if(x>=0&&x<=7&&y>=0&&y<=7){
			if(board[x][y]!=null){
				if (board[x][y].color==color){
					return false;
				} else return true;
			} else return false;
		}else return false;
	}

	public boolean makeMove(Point p) {

		boolean check = false;		
		p = translateToBoard(p);
		int x = p.x;
		int y = p.y;
		

		if ((x>=0 && x<=7) && (y>=0 && y<=7)) {
			if (board[x][y]==null){				
				board[x][y]=new Piece(color);
				movecount++;
				check = true;
			}			
		}
		return check;
	}

	public void playerSwitch(){
		if (color==Color.WHITE) {
			color = Color.BLACK;
		} else color = Color.WHITE;				
	}

	public void flipPiece(Point p, Color color){

		Point temp = p;
		p=this.translateToBoard(p);
		if(board[p.x][p.y]==null){
			p = temp;

			if (checkDiagLowerLeft(p)){			
				p=this.translateToBoard(p);
				for(int i=p.x-1,j=p.y+1; i>CHECK_X&&j<CHECK_Y; i--,j++){		
					if (board[i][j]!=null){
						board[i][j].setColor(color);						
					}
				}
				p = temp;			
			}

			if (checkDiagUpperLeft(p)){
				p=this.translateToBoard(p);
				for(int i=p.x-1,j=p.y-1; i>CHECK_X&&j>CHECK_Y; i--,j--){

					if (board[i][j]!=null){
						board[i][j].setColor(color);					
					}							
				}
				p = temp;			
			}

			if (checkDiagLowerRight(p)){
				p=this.translateToBoard(p);
				for(int i=p.x+1,j=p.y+1; i<CHECK_X&&j<CHECK_Y; i++,j++){

					if (board[i][j]!=null){
						board[i][j].setColor(color);					
					}									
				}
				p = temp;			
			}

			if (checkDiagUpperRight(p)){
				p=this.translateToBoard(p);
				for(int i=p.x+1,j=p.y-1; i<CHECK_X&&j>CHECK_Y; i++,j--){

					if (board[i][j]!=null){
						board[i][j].setColor(color);					
					}									
				}
				p = temp;				
			}

			if (checkHorizontalRight(p)){
				p=this.translateToBoard(p);
				for(int i=p.x+1,j=p.y; i<CHECK_X; i++){

					if (board[i][j]!=null){
						board[i][j].setColor(color);					
					}							
				}
				p = temp;					
			}	

			if (checkHorizontalLeft(p)){
				p=this.translateToBoard(p);
				for(int i=p.x-1,j=p.y; i>CHECK_X; i--){

					if (board[i][j]!=null){
						board[i][j].setColor(color);					
					}									
				}
				p = temp;						
			}

			if (checkVerticalUp(p)){
				p=this.translateToBoard(p);
				for(int i=p.x,j=p.y-1; j>CHECK_Y; j--){

					if (board[i][j]!=null){
						board[i][j].setColor(color);					
					}							
				}
				p = temp;				
			}

			if (checkVerticalDown(p)){
				p=this.translateToBoard(p);

				for(int i=p.x,j=p.y+1; j<CHECK_Y; j++){					
					if (board[i][j]!=null){
						board[i][j].setColor(color);
					}								
				}p = temp;					
			}
		}
	}

	public boolean isValidMove(Point p){
		if(checkDiagLowerLeft(p)||checkDiagUpperLeft(p)||
				checkDiagLowerRight(p)||checkDiagUpperRight(p)||
				checkHorizontalLeft(p)||checkHorizontalRight(p)||
				checkVerticalUp(p)||checkVerticalDown(p)){
			return true;
		}else return false;
	}	

	public boolean checkDiagUpperLeft(Point p){
		p=this.translateToBoard(p);
		boolean check = false;

		if(checkFirst(p.x-1,p.y-1)){
			for(int i=p.x-2,j=p.y-2; (i>=0)&&(j>=0); i--,j--){			
				if(board[i][j]==null) {
					check = false; break;
				}
				else {
					if(board[i][j].color==color){
						check = true;
						CHECK_X=i;
						CHECK_Y=j;
						break;
					}					
				}
			}
		}
		return check;		
	}

	public boolean checkDiagLowerLeft(Point p){
		p=this.translateToBoard(p);		
		boolean check = false;

		if (checkFirst(p.x-1,p.y+1)) {
			for(int i=p.x-2,j=p.y+2; (i>=0)&&(j<=7); i--,j++){				

				if(board[i][j]==null) {
					check = false; break;
				}
				else {
					if(board[i][j].color==color){
						check = true;
						CHECK_X=i;
						CHECK_Y=j;
						break;
					}					
				}
			}
		}		
		return check;		
	}

	public boolean checkDiagUpperRight(Point p){
		p=this.translateToBoard(p);
		boolean check = false;

		if (checkFirst(p.x+1,p.y-1)){
			for(int i=p.x+2,j=p.y-2; (i<=7)&&(j>=0); i++,j--){
				if(board[i][j]==null) {
					check = false; break;
				}
				else {
					if(board[i][j].color==color){
						check = true;
						CHECK_X=i;
						CHECK_Y=j;
						break;
					}					
				}
			}
		}		
		return check;			
	}

	public boolean checkDiagLowerRight(Point p){		
		p=this.translateToBoard(p);
		boolean check = false;		
		if (checkFirst(p.x+1,p.y+1)){
			for(int i=p.x+2,j=p.y+2; (i<=7)&&(j<=7); i++,j++){
				if(board[i][j]==null) {
					check = false; break;
				}
				else {
					if(board[i][j].color==color){
						check = true;
						CHECK_X=i;
						CHECK_Y=j;
						break;
					}					
				}
			}
		}		
		return check;		
	}

	public boolean checkHorizontalLeft(Point p){
		p=this.translateToBoard(p);
		boolean check = false;	
		if (checkFirst(p.x-1,p.y)){
			for(int i=p.x-2; (i>=0); i--){
				if (board[i][p.y]==null) {
					check = false; break;
				}
				else {
					if(board[i][p.y].color==color){
						check = true;
						CHECK_X=i;
						CHECK_Y=p.y;
						break;
					}					
				}
			}
		}		
		return check;		
	}

	public boolean checkHorizontalRight(Point p){
		p=this.translateToBoard(p);
		boolean check = false;		
		if (checkFirst(p.x+1,p.y)){
			for(int i=p.x+2; (i<=7); i++){
				if (board[i][p.y]==null) {
					check = false; break;
				}
				else {
					if(board[i][p.y].color==color){
						check = true;
						CHECK_X=i;
						CHECK_Y=p.y;
						break;
					}					
				}
			}
		}
		return check;	
	}

	public boolean checkVerticalUp(Point p){
		p=this.translateToBoard(p);
		boolean check = false;		
		if (checkFirst(p.x,p.y-1)){
			for(int j=p.y-2; j>=0; j--){

				if(board[p.x][j]==null) {check=false; break;}
				else {				
					if(board[p.x][j].color==color){
						check = true;
						CHECK_X=p.x;
						CHECK_Y=j;
						break;
					}										
				}
			}
		}		
		return check;		
	}

	public boolean checkVerticalDown(Point p){
		p=this.translateToBoard(p);
		boolean check = false;		
		if (checkFirst(p.x,p.y+1)){
			for(int j=p.y+2; j<=7; j++){
				if(board[p.x][j]==null) {
					check = false; break;
				}
				else {
					if(board[p.x][j].color==color){
						check = true;
						CHECK_X=p.x;
						CHECK_Y=j;						
						break;
					}					
				}
			}
		}		
		return check;		
	}

	// DEBUGGING CODE - USED TO VERIFY COLOR of PIECES IN ARRAY
	//	private void printColor(){
	//		for(int i=0; i<8; i++){
	//			for (int j=0; j<8; j++){				
	//				if (board[i][j]!=null)
	//				{
	//					System.out.println(i+""+j+board[i][j].color);
	//
	//				}
	//			}
	//		}
	//	}

	public void paintComponent(Graphics g){			

		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 500, 500);
		g.setColor(new Color(139,69,19));
		g.fillRect(0, 0, 500, 500);//Paint Board Boundary
		String[] top_label = {"A","B","C","D","E","F","G","H"};
		String[] side_label = {"1","2","3","4","5","6","7","8"};

		for (int i = 74; i<=450; i=i+50){//Paint Board Label Bar
			g.setColor(Color.BLACK);
			g.drawString(top_label[(i-50)/50],i,28);
			g.drawString(side_label[(i-50)/50],25,i);
		}

		for (int i = 50; i<=HEIGHT; i=i+50){			
			for (int j = 50; j<=WIDTH; j=j+50) {
				g.setColor(Color.BLACK);
				g.drawRect(i,j,50,50);
				if (legalMoves.contains(new Point(i,j))&&!toggleGuide){//Paint Suggested Moves
					g.setColor(new Color(50,205,50));
				}
				else {
					g.setColor(new Color(34,139,34));
				}				
				g.fillRect(i+1, j+1, 49, 49);//Paint Board
				if (board[(i-50)/50][(j-50)/50]!=null) {
					board[(i-50)/50][(j-50)/50].paintPiece(g,(i+1),(j+1));//Paint Pieces
				}
			}
		}				
	}
}
