import java.util.Scanner;

public class test {
	
	public static void main(String[] args) throws CloneNotSupportedException
	{

		

		System.out.println("Do you want to play TicTacToe (y/n)");
		Scanner scn = new Scanner(System.in);
		String test = scn.nextLine();
		if(test.compareToIgnoreCase("Y") == 0)
		{
			TicTacToe lets = new TicTacToe();
			lets.PlayGame();
		}
		else 
		{
			System.out.println("Do you want to play Ultimate TicTacToe? (y/n)");
			test = scn.nextLine();
			if(test.compareToIgnoreCase("Y") == 0)
			{
				NineTacToe lets = new NineTacToe();
				lets.PlayGame();
			}
						
		}
		System.out.println("I hoped you enjoyed playing againts my AI genni!");
	

		
		

	}

}
