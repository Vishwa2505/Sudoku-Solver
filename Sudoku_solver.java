package sudoku;
import java.util.*;

public class Sudoku_solver {
	private static final int SIZE=9;
	private static final int EMPTY=0;

	public static void main(String[] args) {
		int[][] board=new int[SIZE][SIZE];
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the Sudoku puzzle(9x9 grid):");
		System.out.println("Enter 0 for empty cells.");
		System.out.println("Enter numbers row by row, separated by spaces:");
		
		
		for(int i=0;i<SIZE;i++)
		{
			System.out.print("Row "+(i+1)+": ");
			String[] row=scanner.nextLine().trim().split("\\s");
			for(int j=0;j<SIZE;j++)
			{
				board[i][j]=Integer.parseInt(row[j]);
			}
		}		
		System.out.println("\nInput Sudoku:\n");
		printBoard(board);
		
		if(solveSudoku(board))
		{
			System.out.println("\nSolution:\n");
			printBoard(board);
		}
		else
		{
			System.out.println("\nNo Solutionn exists for this Sudoku puzzle.");
		}
		scanner.close();	
	}
	private static void printBoard(int[][] board)
	{
		for(int i=0;i<SIZE;i++)
		{
			if(i%3==0 && i!=0)
			{
				System.out.println("-----------------------");
			}
			for(int j=0;j<SIZE;j++)
			{
				if(j%3==0 && j!=0)
				{
					System.out.print("| ");
				}
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	private static boolean solveSudoku(int[][] board)
	{
		for(int row=0;row<SIZE;row++)
		{
			for(int col=0;col<SIZE;col++)
			{
				if(board[row][col]==EMPTY)
				{
					for(int num=1;num<=SIZE;num++)
					{
						if(isValid(board,row,col,num))
						{
							board[row][col]=num;
							//Reccursivel solve 
							if(solveSudoku(board))
							{
								return true;
							}
							else
								board[row][col]=EMPTY;
						}
					}

					return false;
				}
				//If no number works,backtrack
				//return false;
			}
		}
		return true;
	}
	
	private static boolean isValid(int[][] board,int row,int col,int num)
	{
		//Check Row
		for(int j=0;j<SIZE;j++)
		{
			if(board[row][j]==num)
				return false;
		}
		
		//Check Column
		for(int i=0;i<SIZE;i++)
		{
			if(board[i][col]==num)
				return false;
		}
		
		//check 3x3 block
		
		int boxRow=row-row%3;
		int boxCol=col-col%3;
		for(int i=boxRow;i<boxRow+3;i++)
		{
			for(int j=boxCol;j<boxCol+3;j++)
			{
				if(board[i][j]==num)
					return false;
			}
		}
		return true;
	}

}
