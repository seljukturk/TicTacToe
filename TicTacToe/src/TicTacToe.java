import java.util.Scanner;

public class TicTacToe {

	Board Board = new Board();
	int turn = 0;
	AI genni;
	public TicTacToe()
	{
		
		char[][] temp = {{'s','s','s'},{'s','s','s'},{'s','s','s'}};	
		this.Board = new Board(temp ,0);
		genni = new AI();
			
	}
	
	boolean HumanPlayerX = false;

	
	public boolean hasWon()
	{
		if(this.Board.PlayerXscore()  >999)
		{
			System.out.println("X has won, Congrats!");
			return true;
		}
		else if (this.Board.PlayerOscore() > 999)
		{
			System.out.println("O has won, Congrats!");
			return true;					
		}
		else if(this.Board.turn > 8)
		{
			System.out.println("Its a draw!");
			return true;
			
		}
		else
		{
		//	System.out.println("Player X has a score of " + PlayerXscore());
		//	System.out.println("Player O has a score of " + PlayerOscore());
			return false;
		}
	}
	
	public int[] pickpiece(int i, int j)
	{
		int[] test = {i , j};
		while( test[0] > 2 || test[1] > 2 ||this.Board.CurrentBoard[test[0]][test[1]] == 'O' || this.Board.CurrentBoard[test[0]][test[1]] == 'X'  )
		{
			System.out.println("Please pick a legal move");
			Scanner in = new Scanner(System.in);
			test[0] = in.nextInt();
			test[1] = in.nextInt();
			
		}
		return test;
	}
	
	public void setpieceX(int i, int j)
	{
		int [] legal = pickpiece(i,j);
	
		this.Board.CurrentBoard[legal[0]][legal[1]] = 'X';
		this.Board.lastmovex = legal[0];//this will become useful in the 9 board aspect of this project
		this.Board.lastmovey = legal[1];
		
		this.turn++;
	}
	
	public void setpieceO(int i , int j)
	{
		int [] legal = pickpiece(i,j);

		this.Board.CurrentBoard[legal[0]][legal[1]] = 'O';
		this.Board.lastmovex = legal[0];//9 board utility
		this.Board.lastmovey = legal[1];
		
		this.turn++;
	}
	
	public void PlayGame() throws CloneNotSupportedException
	{
		System.out.println("Do you want to play as x/o?");
		Scanner in = new Scanner(System.in);
		String out = in.next();
		
		if(out.compareToIgnoreCase("X") == 0)
		{
			System.out.println("Your playing as X");
			HumanPlayerX = true;
		}
		else if(out.compareToIgnoreCase("O") == 0)
		{
			System.out.println("Your playing as O");
			HumanPlayerX = false;
		}
		else
		{
			System.out.println("Please enter an appropiate answer, I worked really hard on this!");
			PlayGame(); // current bug will try to fix later
		}
		
		if(this.HumanPlayerX == true)
		{
			while(!hasWon())
			{
				//this implies that it is X's turn
				if(this.Board.turn%2 == 0)
				{
					
					humanChooserX(); // humans turn
					this.Board.printBoard();
		//			System.out.println("Prev move:" + this.Board.lastmovex + "," + this.Board.lastmovey );

					this.Board.turn++;
				}
				else
				{
					//let the computer choose
					this.Board = genni.findBestMove(this.Board , !HumanPlayerX);  // this is currently a place holder untill i get my ai running
					this.Board.printBoard();
				//	System.out.println("Prev move:" + this.Board.lastmovex + "," + this.Board.lastmovey );

					
					
				}
				
			}
			
		}
		else
		{
			while(!hasWon())
			{
				//this implies that it is X's turn
				if(this.Board.turn%2 == 0)
				{
					this.Board = genni.findBestMove(this.Board , !HumanPlayerX);  // genni the ai chooses
					this.Board.printBoard();
	//				System.out.println("Prev move:" + this.Board.lastmovex + "," + this.Board.lastmovey );
				}
				else
				{
					//let the computer choose
					
					humanChooserO(); // this is currently a place holder untill i get my ai running
					this.Board.printBoard();
	//				System.out.println("Prev move:" + this.Board.lastmovex + "," + this.Board.lastmovey );
					this.Board.turn++;

				}
			
			}
		}
		
		
		
	}
	
	//this method will parse information from the user into a nice 
	public void humanChooserX()
	{
		Scanner ne = new Scanner(System.in);
		int x = ne.nextInt();
		int y = ne.nextInt();
		

		setpieceX(x,y);
					
	}
	
	public void humanChooserO()
	{
		Scanner ne = new Scanner(System.in);
		int x = ne.nextInt();
		int y = ne.nextInt();
		
	
		setpieceO(x,y);
		
		
	}

}
