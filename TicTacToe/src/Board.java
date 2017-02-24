import java.util.*;

public class Board implements Cloneable{
	
	char[][] CurrentBoard={{'s','s','s'},{'s','s','s'},{'s','s','s'}};
	int lastmovex=0;
	int lastmovey=0;
	
	boolean HumanPlayerX = false;

	int turn = 0;
	//I need a clone method because java does not handle new objects very gracefully 
	@Override
	protected Board clone() throws CloneNotSupportedException{
		char[][] clone1 = new char[3][3];
		for(int i = 0; i < 3 ;i++)
		{
			clone1[i] = this.CurrentBoard[i].clone();
		}
		int clone2 = this.turn;
		
		Board clone = new Board(clone1,clone2);
		
		return clone;
	}
	
	public Board(char[][] in, int num)
	{
		this.CurrentBoard = in;
		this.turn = num;
	}
	
	public Board(char[][] in)
	{
		this.CurrentBoard = in;
	}
	
	public Board()
	{
		char[][] currentBoard = {{'s','s','s'},{'s','s','s'},{'s','s','s'}};
		CurrentBoard = currentBoard;
	}
	//helper method for score
	public int scoreconverter(int cont, int scount)
	{
		if(cont == 1 && scount == 2)
		{
			return 1;
		}
		else if(cont == 2 && scount == 1)
		{
			return 10;
		}
		else if(cont == 3)
		{
	
			return 1000;
		}
		else
			return 0;
		
	}
	//return player x score
	public int PlayerXscore()
	{
		//the score is determined by how many empty squares are in the row or column or diag
		int score = 0;
		for(int i = 0; i < 3; i++)
		{
			int counter = 0; // counts the number of X square
			int scounter = 0; // counts the number of empty squares
			//count the scores based on the rows
			for(int j = 0; j < 3 ; j++)
			{
				if(this.CurrentBoard[i][j] == 'X')
				{
					counter++;
				}
				else if(this.CurrentBoard[i][j] == 's')
				{
					scounter++;
				}
			}
			score += scoreconverter(counter,scounter);	
		
		}
		//count the score for all the column
		for(int j = 0 ; j < 3 ; j++)
		{
			int counter = 0;
			int scounter = 0;
			for(int i = 0; i < 3 ; i++)
			{
				if(this.CurrentBoard[i][j] == 'X')
				{
					counter++;
				}
				else if (this.CurrentBoard[i][j] == 's')
				{
					scounter++;
				}
			}
			score += scoreconverter(counter,scounter);
		}

		int counter = 0;
		int scounter = 0;
		//count up the score for the diagonal
		for(int i = 0; i < 3 ; i++ )
		{
			
			
			if(this.CurrentBoard[i][i] == 'X')
			{
				counter++;
	
			}
			else if(this.CurrentBoard[i][i] == 's')
			{
				scounter++;
			}
			
			score += scoreconverter(counter ,scounter);
		}
		counter = 0;
		scounter = 0;
		for(int i = 0; i < 3 ; i++ )
		{
			
			
			if(this.CurrentBoard[i][2-i] == 'X')
			{
				counter++;
	
			}
			else if(this.CurrentBoard[i][2-i] == 's')
			{
				scounter++;
			}
			
			score += scoreconverter(counter ,scounter);
		}
		
		return score;
	}
	//returns playerOscore
	public int PlayerOscore()
	{
		//the score is determined by how many empty squares are in the row or column or diag
				int score = 0;
				
				for(int i = 0; i < 3; i++)
				{
					int counter = 0; // counts the number of X square
					int scounter = 0; // counts the number of empty squares
					//count the scores based on the rows
					for(int j = 0; j <3 ; j++)
					{
						if(this.CurrentBoard[i][j] == 'O')
						{
							counter++;
						}
						else if(this.CurrentBoard[i][j] == 's')
						{
							scounter++;
						}
					}
					
					score += scoreconverter(counter,scounter);	
				
				}
				//count the score for all the column
				for(int j = 0 ; j < 3 ; j++)
				{
					int counter = 0;
					int scounter = 0;
					for(int i = 0; i < 3 ; i++)
					{
						if(this.CurrentBoard[i][j] == 'O')
						{
							counter++;
						}
						else if (this.CurrentBoard[i][j] == 's')
						{
							scounter++;
						}
					}
					score += scoreconverter(counter,scounter);
				}

				int counter = 0;
				int scounter = 0;
				//count up the score for the diagonal
				for(int i = 0; i < 3 ; i++ )
				{
					
					
					if(this.CurrentBoard[i][i] == 'O')
					{
						counter++;
			
					}
					else if(this.CurrentBoard[i][i] == 's')
					{
						scounter++;
					}
					
					score += scoreconverter(counter ,scounter);
				}
				counter = 0;
				scounter = 0;
				for(int i = 0; i < 3 ; i++ )
				{
					
					
					if(this.CurrentBoard[i][2-i] == 'O')
					{
						counter++;
			
					}
					else if(this.CurrentBoard[i][2-i] == 's')
					{
						scounter++;
					}
					
					score += scoreconverter(counter ,scounter);
				}
				return score;
	}
	//debugging help
	public void printBoard()
	{
		for(int i = 0;i<3;i++)
		{
			for(int j = 0; j < 3 ; j++)
			{
				if(this.CurrentBoard[i][j]=='X' || this.CurrentBoard[i][j] == 'O')
				{
					System.err.print(CurrentBoard[i][j]+" " );
				}
				else
				{
					System.err.print("- " );
				}		
			}
			System.err.println();
		}
	}

	//This method will put all possible moves in the childrens list.
	public Board[] PossibleMoves() throws CloneNotSupportedException		
	{
		Board[] children =  new Board[9 - this.turn];
		int counter = 0;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j< 3; j++)
			{
				if(this.CurrentBoard[i][j] == 's')
				{
					if(this.turn%2 == 0)
					{
						children[counter] = this.clone();
						children[counter].turn++;
						children[counter].CurrentBoard[i][j] = 'X';
						children[counter].lastmovex =i;
						children[counter].lastmovey =j;
						counter++;
					}
					else
					{
						children[counter] = this.clone();
						children[counter].turn++;
						children[counter].CurrentBoard[i][j] = 'O';
						children[counter].lastmovex = i;
						children[counter].lastmovey = j;
						counter++;
						
					}
				}
			}
		}
		return children;		
	}
	//this method helps with constructing the nineboards
	public Board[] PossibleMoves(int trn,int emptysq) throws CloneNotSupportedException
	{ 
		Board[] children =  new Board[emptysq];
		int counter = 0;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j< 3; j++)
			{
				if(this.CurrentBoard[i][j] == 's')
				{
					if(trn%2 == 0)
					{
						children[counter] = this.clone();
						children[counter].CurrentBoard[i][j] = 'X';
						children[counter].lastmovex =i;
						children[counter].lastmovey =j;
						counter++;
					}
					else
					{
						children[counter] = this.clone();
						children[counter].CurrentBoard[i][j] = 'O';
						children[counter].lastmovex = i;
						children[counter].lastmovey = j;
						counter++;
						
					}
				}
			}
		}
		return children;		
	}
	//checks the number of empty spaces on the board, it also helps with the 9 board
	public int emptySpaces()
	{
		//the score is determined by how many empty squares are in the row or column or diag
				int scounter = 0; // counts the number of empty squares
				for(int i = 0; i < 3; i++)
				{
					
					//count the scores based on the rows
					for(int j = 0; j < 3 ; j++)
					{
					
						if(this.CurrentBoard[i][j] == 's')
						{
							scounter++;				
						
						}
					}
				}
				return scounter;
	}


}
