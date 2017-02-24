import java.util.Scanner;

public class NineBoard implements Cloneable {
	Board[][] superBoard = new Board[3][3];
	int xcord = 1; // we always have to start in the iddle unless directed otherwise
	int ycord = 1;
	int turn = 0;
	
	//same issue as the normal board
	@Override
	protected NineBoard clone() throws CloneNotSupportedException{
		Board[][] clone1 = new Board[3][3];
		
		for(int i =0;i<3;i++)
		{
			for(int j = 0; j < 3; j++)
			{
				clone1[i][j] = this.superBoard[i][j].clone();
			}
		}
		
		int clone2 = xcord;
		int clone3 = ycord;
		int clone4 = turn;
		
		NineBoard clone = new NineBoard(clone1,clone2,clone3,clone4);
		return clone;
	}
	
	//For some reason the super board was initializig to a null value so I have to manually initialize each board;
	public NineBoard(Board[][] board,int x,int y, int t) throws CloneNotSupportedException
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				this.superBoard[i][j] = board[i][j];
			}
		}
		
		this.xcord = x;
		this.ycord = y;
		this.turn = t;
		
	}
	
	public NineBoard() throws CloneNotSupportedException
	{
		char[][] temp = {{'s','s','s'},{'s','s','s'},{'s','s','s'}};
		Board temper = new Board(temp);
		for(int i = 0; i< 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				this.superBoard[i][j] = temper.clone();
			}
		}
	}
	//prints the nine board in an easy to view fashion
	public void printNineBoard()
	{
	

		for(int i = 0; i < 3; i++)
		{
			for(int a = 0; a < 3; a++)
			{
				for(int j = 0 ; j < 3;j++)
				{
					for(int b = 0; b < 3; b++)
					{
						System.err.print(this.superBoard[i][j].CurrentBoard[a][b] + " ");
					}
					System.err.print(" ");
				}
				System.err.println();
			}
			System.err.println();
		}
		
		
	}
	//sets a board on the nine board slot this will be useful later
	public void setABoard(int i, int j, Board board ) throws CloneNotSupportedException
	{
		this.superBoard[i][j] = board.clone(); 
	}
	//setters
	public void setPiece(int i, int j, int x, int y)
	{
		int test[] = {i,j,x,y};
		while(test[0] > 2 || test[1] >2 || test[2] > 2 || test[3] >2 || this.superBoard[test[0]][test[1]].CurrentBoard[test[2]][test[3]] == 'O' ||  this.superBoard[test[0]][test[1]].CurrentBoard[test[2]][test[3]] == 'X'  )
		{
			System.out.println("Please pick a legal move");
			Scanner in = new Scanner(System.in);
			test[0] = in.nextInt();
			test[1] = in.nextInt();
			test[2] = in.nextInt();
			test[3] = in.nextInt();
		}
			
		if(turn%2 == 0)
		{
			this.superBoard[test[0]][test[1]].CurrentBoard[test[2]][test[3]] = 'X';
			this.turn++;
		}
		else
		{

			this.superBoard[test[0]][test[1]].CurrentBoard[test[2]][test[3]] = 'O';
			this.turn++;
		}
		xcord = test[2];
		ycord = test[3];
	}
	
	public void setPiece(int x , int y)
	{
		int test[] = {x,y};
		while(test[0] > 2 || test[1] >2 ||  this.superBoard[xcord][ycord].CurrentBoard[test[0]][test[1]] == 'O' ||  this.superBoard[xcord][ycord].CurrentBoard[test[0]][test[1]] == 'X'  )
		{
			System.out.println("Please pick a legal move");
			Scanner in = new Scanner(System.in);
			test[0] = in.nextInt();
			test[1] = in.nextInt();
	
		}
		if(turn%2 == 0)
		{
			this.superBoard[xcord][ycord].CurrentBoard[test[0]][test[1]] = 'X';
			this.turn++;
		}
		else
		{

			this.superBoard[xcord][ycord].CurrentBoard[test[0]][test[1]] = 'O';
			this.turn++;
		}
		xcord = test[0];
		ycord = test[1];

	}

	//fuck this method it took up most of my time 
	public NineBoard[] possibleMoves() throws CloneNotSupportedException
	{
		
		Board[] almostchildren = this.superBoard[xcord][ycord].PossibleMoves(this.turn,this.superBoard[xcord][ycord].emptySpaces());
		
		NineBoard[] children = new NineBoard[almostchildren.length];
		
		for(int i = 0; i< almostchildren.length; i++)
		{
			children[i] = this.clone();
			children[i].setABoard(xcord, ycord, almostchildren[i]);
			children[i].xcord = almostchildren[i].lastmovex;
			children[i].ycord = almostchildren[i].lastmovey;
			
			children[i].turn++;
		}
		
		return children;
		
	}

	//these methods are a little redunant, they just return the score
	public int totalScoreX()
	{
		int score = 0;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j <3;j++)
			{
				score+= this.superBoard[i][j].PlayerXscore();
			}
		}
		return score;
	}
	
	public int totalScoreO()
	{
		int score = 0;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j <3;j++)
			{
				score+= this.superBoard[i][j].PlayerOscore();
			}
		}
		return score;
	}
	
	public int stateScore()
	{
		if(this.totalScoreX() > 999)
		{
			return this.totalScoreX();
		}
		if(this.totalScoreO() > 999)
		{
			return  this.totalScoreO();
		}
		else
		{
			return this.totalScoreX() - this.totalScoreO()  ;
		}
	}
}
