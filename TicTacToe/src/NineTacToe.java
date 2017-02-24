import java.util.Scanner;

public class NineTacToe {
	NineBoard game;
	AI genni = new AI(); // say hello to genni
	boolean HumanPlayerX;

	//constructer for the game board
	public NineTacToe() throws CloneNotSupportedException
	{
		game = new NineBoard();
	}
	
	//setters
	public void setPiece(int i, int j , int a, int b)
	{
		game.setPiece(i, j, b, b);
	}
	
	public void setPiece(int a , int b)
	{
		game.setPiece(a, b);
	}
	
	//prompts the user for input
	public void pickPiece()
	{
		Scanner ne = new Scanner(System.in);
		
		int x = ne.nextInt();
		int y = ne.nextInt();
		
		game.setPiece(x, y);
	}
	
	//prompts the user for input for the first turn
	public void pickPiece1st()
	{
		Scanner ne = new Scanner(System.in);
		
		int i = ne.nextInt();
		int j =ne.nextInt();
		int x= ne.nextInt();
		int y= ne.nextInt();
		
		game.setPiece(i, j,x,y);

	}
	//begins the game
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
		
		//who is playing as x
		if(this.HumanPlayerX == true)
		{
			while(!hasWon())
			{
				if(this.game.turn%2 == 0)
				{
					//the first player has to pick which square he begins in
					if(this.game.turn == 0)
					{
						pickPiece1st();
					}
					else
					{
						pickPiece();
					}
				}
				else
				{
					this.game = genni.findBestNineBoard(this.game, 7, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
				}
			}
		}
		else
		{
			while(!hasWon())
			{
				if(this.game.turn%2 == 0)
				{
					
					this.game = genni.findBestNineBoard(this.game, 7, Integer.MIN_VALUE, Integer.MAX_VALUE, true);	
					
				}
				else
				{
					pickPiece();
				}
			}
		}
		
			
	}
	//checks to see if the game has won
	public boolean hasWon()
	{
		if(this.game.totalScoreX()  >999)
		{
			System.out.println("X has won, Congrats!");
			return true;
		}
		else if (this.game.totalScoreO() > 999)
		{
			System.out.println("O has won, Congrats!");
			return true;					
		}
		else if(this.game.turn > (9*9 -1))
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
		
	
	

}
