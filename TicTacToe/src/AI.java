import java.lang.Math;

public class AI {
	
	
	
	public AI() 
	{
		
	}
	//Finding the best move for the tictactoe game
	public Board findBestMove(Board currentBoard,boolean isComputerX) throws CloneNotSupportedException
	{
		//construct all the children
		Board[] children = currentBoard.PossibleMoves();
		Board bestMove = children[0].clone();
		int bestVal;
		//if the computer is vs the player
		if(isComputerX)
		{
			for(int i = 0; i < children.length;i++)
			{
				bestVal =  minimax(bestMove,bestMove.turn,false);
				Board chooseBoard = children[i].clone();
				
				int tempmax = minimax(chooseBoard,chooseBoard.turn,false);
				if(tempmax > bestVal)
				{
					bestMove = chooseBoard;
					bestVal = tempmax;
				}
			}
		}
		else
		{
			for(int i = 0; i < children.length;i++)
			{
				bestVal =  minimax(bestMove,bestMove.turn,true);
				Board chooseBoard = children[i].clone();
				
				int tempmax = minimax(chooseBoard,chooseBoard.turn,true);
				if(tempmax < bestVal)
				{
					bestMove = chooseBoard;
					bestVal = tempmax;
				}
			}
		}
		return bestMove;
	}
	//implements the minimax algorithm
	public int minimax(Board board,int depth , boolean max) throws CloneNotSupportedException
	{
		if(board.PlayerOscore() > 999)
		{
			return -1*board.PlayerOscore();
		}
		else if(board.PlayerXscore() > 999)
		{

			return board.PlayerXscore();
		}
		else if(depth > 8)
		{

			return 0;
		}
		
		if(max)
		{
			int bestVal = Integer.MIN_VALUE;
			Board[] children = board.PossibleMoves();
			for(int i = 0; i < children.length; i++)
			{
				int test = minimax(children[i] , children[i].turn, false);
				bestVal = Math.max(test,bestVal);
			}
			return bestVal;
		}
		else
		{
			int bestVal = Integer.MAX_VALUE;
			Board[] children = board.PossibleMoves();
			for(int i = 0; i < children.length; i++)
			{
				int test = minimax(children[i] , children[i].turn, true);
				bestVal = Math.min(test,bestVal);
				
			}
			return bestVal;
		}
	}
	//implements the alphabeta algorithm
	@SuppressWarnings("null")
	public int alphabeta(NineBoard board ,int depth , int alpha, int beta, boolean player ) throws CloneNotSupportedException
	{
		NineBoard[] children = board.possibleMoves();
		if(depth == 0 || board.stateScore() > 999)
		{
			return board.stateScore();
		}		
		else
		{
			if(player)
			{
				for(int i = 0; i < children.length; i++)
				{
					int score = alphabeta(children[i],depth - 1, alpha , beta, false);
					alpha = Math.max(score, alpha);		
					if(alpha >= beta)
					{
						break;
					}
				
					
				}
				return alpha;
			}
			else
			{
				for(int i = 0; i < children.length; i++)
				{
					int score = alphabeta(children[i],depth - 1, alpha , beta, true);
					beta = Math.min(score, beta);		
					if(alpha >= beta)
					{
						break;
					}
					
				}
				return beta;
			}
		}
		
	}
	//finds the best nineboard
	public NineBoard findBestNineBoard(NineBoard currentboard , int depth ,int alpha, int beta , boolean isComputerX) throws CloneNotSupportedException
	{
		NineBoard[] children = currentboard.possibleMoves();
		NineBoard bestMove = children[0].clone();
		int bestVal;
		if(isComputerX)
		{
			for(int i = 0; i < children.length;i++)
			{
				bestVal =  alphabeta(bestMove,alpha,beta,bestMove.turn,false);
				NineBoard chooseBoard = children[i].clone();
				
				int tempmax = alphabeta(chooseBoard,depth,alpha,beta,false);
				if(tempmax > bestVal)
				{
					bestMove = chooseBoard;
					bestVal = tempmax;
				}
			}
		}
		else
		{
			for(int i = 0; i < children.length;i++)
			{
				bestVal =  alphabeta(bestMove,alpha,beta,bestMove.turn,true);
				NineBoard chooseBoard = children[i].clone();
				
				int tempmax = alphabeta(chooseBoard,depth,alpha,beta,true);
				if(tempmax < bestVal)
				{
					bestMove = chooseBoard;
					bestVal = tempmax;
				}
			}
		}
		bestMove.printNineBoard();
		System.out.println(bestMove.xcord + " " + bestMove.ycord);
		return bestMove;
		
	}
	
}
