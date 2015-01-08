import java.lang.Math;
import java.util.Random;


/**
 * @author Simon Aristizabal
 *
 */
public class Sudoku {

	public char[][] table;
	
	/**
	 * Creates a Sudoku game with a random generated table.
	 * 
	 * The table generated is not necessarily solvable for testing purposes.
	 */
	public Sudoku()
	{
		setTable();
	}
	
	/**
	 * Accepts a 9 by 9 two-dimensional array to create a new Sudoku table.
	 * 
	 * @param t
	 *   Two-dimensional array, must be 9x9.
	 * 
	 */
	public Sudoku(char[][] t)
	{
		this.table = t;
	}
	
	
	/**
	 * Inserts value into the passed coordinates.
	 * @param x
	 * 	  X coordinate
	 * @param y
	 *    Y coordinate
	 * @param value
	 *    Value to be placed in (x,y)
	 */
	public void setValue(int x, int y, int value)
	{
		if (table[y][x] == ' ')
			table[y][x] = mapNumber(value);
	}
	
	/**
	 * Generates the new table.
	 */
	private void setTable()
	{
		table = new char[9][9];
		generateNumbers();
	}
	
	/**
	 * Randomly generates the numbers to be placed in the table.
	 * The number of numbers generated can be changed for testing purposes.
	 */
	private void generateNumbers()
	{
		//int range = 30;
		//int numberOfGenerations = (int) (Math.random()*range) + 15;
		Random rand = new Random();
		int numberOfGenerations = rand.nextInt((40 - 30)+1)+30;
		int x = 0, y = 0;
		int value;
		
		emptyTable(table);
		
		while (numberOfGenerations > 0)
		{
			x = (int) (Math.random()*9);
			y = (int) (Math.random()*9);
			value = (int) (Math.random()*9)+1;
			
			if (checkCoordinate(x,y,value))
			{
				setValue(x,y,value);
				numberOfGenerations--;
			}
		}
	}
	
	/**
	 * Clears all the slots in the table with a default character ' '
	 * @param t
	 *    The table to be cleared.
	 * @return
	 *    The modified table.
	 */
	private static char[][] emptyTable(char[][] t)
	{
		for (int i = 0; i<t.length; i++)
		{
			for (int j = 0; j<t[i].length; j++)
			{
				t[i][j] = ' ';
			}
		} 
		return t;
	}
	
	/**
	 * Returns the requested quadrant by the passed number from 1 to 9
	 * @param quadrantNumber
	 *    Requested quadrant.
	 * @return
	 *    Quadrant from the original table.
	 */
	private char[][] mapQuadrants(int quadrantNumber)
	{
		char[][] output = new char[3][3];
		int arrayIndexX = 0;
		int arrayIndexY = 0;
		
		// Quadrant 1
		switch (quadrantNumber)
		{
		case 1:
			for (int i = 0; i<3; i++)
			{
				for (int j = 0; j<3; j++)
				{
					output[i][j] = table[i][j];
				}
			}
			break;
		
		// Quadrant 2
		case 2:
			for (int i = 0; i<3; i++) 
			{
				for (int j = 3; j<6; j++) 
				{
					output[i][arrayIndexX] = table[i][j];
					arrayIndexX++;
				}
				arrayIndexX = 0;
			}
			break;
		
		// Quadrant 3
		case 3:
			for (int i = 0; i<3; i++) 
			{
				for (int j = 6; j<9; j++) 
				{
					output[i][arrayIndexX] = table[i][j];
					arrayIndexX++;
				}
				arrayIndexX = 0;
			}
			break;
		
		// Quadrant 4
		case 4:
			for (int i = 3; i<6; i++) 
			{
				for (int j = 0; j<3; j++) 
				{
					output[arrayIndexY][j] = table[i][j];
				}
				arrayIndexY++;
			}
			break;
			
		// Quadrant 5
		case 5:
			for (int i = 3; i<6; i++) 
			{
				for (int j = 3; j<6; j++)
				{
					output[arrayIndexY][arrayIndexX] = table[i][j];
					arrayIndexX++;
				}
				arrayIndexX = 0;
				arrayIndexY++;
			}
			break;
		
		// Quadrant 6
		case 6:
			for (int i = 3; i<6; i++)
			{
				for (int j = 6; j<9; j++) 
				{
					output[arrayIndexY][arrayIndexX] = table[i][j];
					arrayIndexX++;
				}
				arrayIndexX = 0;
				arrayIndexY++;
			}
			break;
		
		// Quadrant 7
		case 7:
			for (int i = 6; i<9; i++)
			{
				for (int j = 0; j<3; j++)
				{
					output[arrayIndexY][j] = table[i][j];
				}
				arrayIndexY++;
			}
			break;
		
		// Quadrant 8
		case 8:
			for (int i = 6; i<9; i++)
			{
				for (int j = 3; j<6; j++)
				{
					output[arrayIndexY][arrayIndexX] = table[i][j];
					arrayIndexX++;
				}
				arrayIndexX = 0;
				arrayIndexY++;
			}
			break;
		
		// Quadrant 9
		case 9:
			for (int i = 6; i<9; i++)
			{
				for (int j = 6; j<9; j++)
				{
					output[arrayIndexY][arrayIndexX] = table[i][j];
					arrayIndexX++;
				}
				arrayIndexX = 0;
				arrayIndexY++;
			}
			break;
		default:
			System.out.println("Invalid quadrant request!");
		}
		return output;
	}
	
	/**
	 * For the sake of representing the values in the slots of the table, the numbers passed
	 * as value will be converted to its character representation by this method. 
	 * Numbers must be from 1 to 9, other inputs will yield an error.
	 * @param value
	 *     The integer value of the number to be placed in the table.
	 * @return
	 *     The character representation of the number passed.
	 */
	private char mapNumber(int value)
	{
		char output = '0';
		switch (value)
		{
		case 1:
			output = '1';
			break;
		case 2:
			output = '2';
			break;
		case 3:
			output = '3';
			break;
		case 4:
			output = '4';
			break;
		case 5:
			output = '5';
			break;
		case 6:
			output = '6';
			break;
		case 7:
			output = '7';
			break;
		case 8:
			output = '8';
			break;
		case 9:
			output = '9';
			break;
		default:
			System.out.println("Wrong input value!");
		}
		return output;
	}
	
	
	/**
	 * Checks if the vertical line on the table contains the passed value.
	 * @param x
	 *   The x coordinate to check vertically.
	 * @param value
	 *   The number to be checked vertically.
	 * @return
	 * 	 True if the value is not found,
	 *   False if the value is found.
	 */
	private boolean checkVertical(int x, int value)
	{
		boolean output = true;
		char charValue= mapNumber(value);
		
		for(int i = 0; i<9; i++)
		{
			if (table[i][x] == charValue)
			{	
				output = false;
				break;
			}
		}
		
		return output;
	}
	
	/**
	 * Checks if the horizontal line on the table contains the passed value.
	 * @param y
	 *   The y coordinate to check horizontally.
	 * @param value
	 *   The number to be checked horizontally.
	 * @return
	 * 	 True if the value is not found,
	 *   False if the value is found.
	 */
	private boolean checkHorizontal(int y, int value)
	{
		boolean output = true;
		char charValue= mapNumber(value);
		
		for(int i = 0; i<table[y].length; i++)
		{
			if (table[y][i] == charValue)
			{	
				output = false;
				break;
			}
		}
		
		return output;
	}
	
	
	/**
	 * Checks if the quadrant on the coordinate passed contains the value
	 * @param x
	 *    Coordinate
	 * @param y
	 *    Coordinate
	 * @param value
	 *    to be checked
	 * @return
	 *   True if value not found,
	 *   False if value is found.
	 */
	private boolean checkQuadrant(int x, int y, int value)
	{
		boolean output = true;
		char charValue = mapNumber(value);
		char[][] quadrant = new char[3][3];
		
		if (x >= 0 && x < 3 && y >= 0 && y < 3)
		{
			quadrant = mapQuadrants(1);
		}
		else if (x >= 3 && x < 6 && y >= 0 && y < 3)
		{
			quadrant = mapQuadrants(2);
			x -= 3;
		}
		else if (x >= 6 && x < 9 && y >= 0 && y < 3)
		{
			quadrant = mapQuadrants(3);
			x -=6;
		}
		else if (x >= 0 && x < 3 && y >= 3 && y < 6)
		{
			quadrant = mapQuadrants(4);
			y -= 3;
		}
		else if (x >= 3 && x < 6 && y >= 3 && y < 6)
		{
			quadrant = mapQuadrants(5);
			x -= 3;
			y -= 3;
		}
		else if (x >= 6 && x < 9 && y >= 3 && y < 6)
		{
			quadrant = mapQuadrants(6);
			x -= 6;
			y -= 3;
		}
		else if (x >= 0 && x < 3 && y >= 6 && y < 9)
		{
			quadrant = mapQuadrants(7);
			y -= 6;
		}
		else if (x >= 3 && x < 6 && y >= 6 && y < 9)
		{
			quadrant = mapQuadrants(8);
			y -= 6;
			x -= 3;
		}
		else if (x >= 6 && x < 9 && y >= 6 && y < 9)
		{
			quadrant = mapQuadrants(9);
			x -= 6;
			y -= 6;
		}
		
		if (quadrant[y][x] != charValue);
		{
			for (int i = 0; i<quadrant.length; i++)
			{
				for (int j = 0; j<quadrant[i].length; j++)
				{
					if (quadrant[j][i] == charValue)
					{	
						output = false;
						break;
					}
				}
				if (!output) break;
			}
		}
		
		return output;
	}
	
	/**
	 * Check if the value can be placed in the coordinates given.
	 * @param x
	 *   Coordinate
	 * @param y
	 *   Coordinate
	 * @param value
	 *   to be checked
	 * @return
	 *   True if value can be placed,
	 *   False If value cannot be placed.
	 */
	protected boolean checkCoordinate(int x, int y, int value)
	{
		boolean output = false;
		if (table[y][x] == ' ' && checkHorizontal(y,value) && checkVertical(x,value) && checkQuadrant(x,y,value))
		{
			output = true;
		}
		
		return output;
	}
	
	
	/**
	 * Prints the table passed with coordinates to make it easy for the user.
	 * @param table
	 *   to be printed.
	 */
	public static void printTable(char[][] table)
	{
		int xCoord = 0;
		
		System.out.println("     0   1   2    3   4   5    6   7   8  ");
		System.out.println("   ---------------------------------------");
		for (int i = 0; i<table.length; i++)
		{
			System.out.print(" " + xCoord);
			xCoord++;
			
			for (int j = 0; j<table[i].length; j++)
			{
				if (j % 3 == 0 && j > 0)
				{
					System.out.print(" || " + table[i][j]);
				}
				else
					System.out.print(" | " + table[i][j]);
			}
			System.out.println(" |");
			
			if ((i+1) % 3 == 0 && i > 0 && i+1 != 9)
			{
				System.out.println("   =======================================");
			}
			else
				System.out.println("   ---------------------------------------");
		}
	}
	
	/**
	 * Adds the passed value into the coordinate only if that coordinate is empty.
	 * @param x
	 *   Coordinate
	 * @param y
	 *   Coordinate
	 * @param value
	 *   to be placed
	 * @return
	 *   True if successfully placed,
	 *   False if not successful
	 */
	public boolean addValueToCoord(int x, int y, int value)
	{
		boolean output = false;
		
		if (table[y][x] != ' ')
		{
			output = false;
		}
		else
		{
			setValue(x,y,value);
			output = true;
		}
		
		return output;
	}
	
	/**
	 * Solves this Sudoku table by using backtracking algorithm.
	 * Source: {@link http://www.geeksforgeeks.org/backtracking-set-7-suduku/} 
	 * @return true if this Sudoku was solved, false otherwise.
	 */
	public boolean solve()
	{
		int x = 0, y = 0;
		boolean location = false;
		char[][] trialTable = this.table;
		
		for (x = 0; x < trialTable.length; x++)
		{
			for (y = 0; y < trialTable[x].length; y++)
			{
				if (checkLocation(x,y))
				{
					location = true;
					break;
				}
			}	
			if (location) break;
		}
		if (!location) return true;
		
		for (int i = 1; i<10; i++)
		{
			if (checkCoordinate(x,y,i))
			{
				setValue(x,y,i);
				if (this.solve()) return true;
				
				trialTable[y][x] = ' ';
			}
		}
		return false;
		
	}
	
	/**
	 * Check the location for empty value.
	 * @param x
	 *  Coordinate
	 * @param y
	 *  Coordinate
	 * @return
	 *  True if coordinate is empty,
	 *  False if not empty.
	 */
	private boolean checkLocation(int x,int y)
	{
		if (table[y][x] == ' ')
			return true;
		return false;
	}
	
}
