/* Kristina McChesney 
 * CSC 421 
 * Fall 2019
 * Final Exam
 * Maximum Profit Problem
 * 
 * Maximum profit values are computed in current program starting from the top left-most index, going down to the last index table[m][m].
 * The total profits table is filled up from top to bottom and the legal steps in the path are to the right, down and diagonally down and right, 
 * therefore every value in the totalProfits table computed traversing back: to the left, up and diagonally up and left. 
 * Exceptions are the first value at index table[0][0], left-most and top-most indices.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MPP {

	private static int computeMaxProfit(int[][] profits, int m){

		int[][] totalProfits = new int[m][m];

		// to compute max profits, we start at the top left corner of the table
		for (int i=0; i<m; i++) { // rows
			for (int j=0; j<m; j++) { // indices of the line
				if (i==0 && j==0) { // the first value is max profit
					totalProfits[0][0] = profits[0][0];
				} else if (i==0) { // if it's the top line of the table, we can only compare to the values on the left
					totalProfits[i][j] = profits[i][j] + totalProfits[i][j-1]; 
				} else if (j==0) { // if it's the most-left row of the table, we can only go up
					totalProfits[i][j] = profits[i][j] + totalProfits[i-1][j]; 
				}
				else { // all other cases
					totalProfits[i][j] = profits[i][j] + 
							Math.max(totalProfits[i][j-1], 
									Math.max(totalProfits[i-1][j-1], totalProfits[i-1][j]));
				}
			}
		}

		// print total profits after filling up the second table
		System.out.println("\nThe result table with profits computed\n");
		for (int k=0; k<m; k++){
			for (int l=0; l<m; l++){
				System.out.print(totalProfits[k][l]+" ");
			}
			System.out.println();
		}

		return totalProfits[m-1][m-1];
	}

	public static void main(String[] args) throws IOException {

		// read the file with values separated by space
		File file = new File("data/mpptable.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 

		// first line of the file is an integer which shows both width & height of the table
		String dimensions; 		
		dimensions = br.readLine();
		int m = Integer.parseInt(dimensions);

		// create a table to store data
		int[][] profitsMatrix = new int[m][m]; 

		// read the file and fill up the table
		String line;
		// traverse rows
		for (int i=0; i<m; i++){ 
			line = br.readLine();
			// values in the line separated by spaces
			String[] profits = line.split(" ");
			// go through indices of the line
			for(int j=0; j<m; j++){ 
				profitsMatrix[i][j] = Integer.parseInt(profits[j]);
			} 
		}

		// print table
		System.out.println("Given profits table\n");
		for (int k=0; k<m; k++){
			for (int l=0; l<m; l++){
				System.out.print(profitsMatrix[k][l]+" ");
			}
			System.out.println();
		}

		System.out.println("\nMaximum possible profit: "+computeMaxProfit(profitsMatrix,m));

	}

}
