/* Kristina McChesney 
 * CSC 421 
 * Fall 2019
 * Problem Set 5
 * 
 * Algorithm to determine the lowest-cost seam 
 * 
 * (a) Show that the number of such possible seams grows at least exponentially in m, assuming that n > 1. 
 * 
 * For the pixel q[i,j] we have at least 2 (up to 3) possible values, depending on where it's located in the array: 
 * q[i-1][j], q[i-1][j+1], and q[i-1][j-1].
 * If we use recurrence relation, the choice for the each next pixel would grow exponentially:
 * if n>1
 * T(n) >= 2T(n-1) >= 2*n*2^n-2 = n*2^n-1 = O(2^n)
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Seam {

	public static void seam(int[][] pTable, int m, int n) {

		// copy the first row of the old table to a new table without change
		int[][] seamTable = new int[m][n];
		for (int i=0; i<n; i++){
			seamTable[0][i] = pTable[0][i];
		}		

		// fill up the second table from top, to bottom
		for (int i=1; i<m; i++){ // outer loop goes through rows
			for (int j=0; j<n; j++){ // inner loop goes through the indices of the row
				// add the value from the main table and minimum value from the adjacent pixels
				seamTable[i][j] = pTable[i][j];  
				if (j == 0) {
					seamTable[i][j] += Math.min(seamTable[i-1][j], seamTable[i-1][j+1]); // if the pixel is the left-most in the array
				} else if (j == n-1) { 
					seamTable[i][j] += Math.min(seamTable[i-1][j], seamTable[i-1][j-1]); // if the pixel is the right-most in the array
				} else {
					seamTable[i][j] += Math.min(Math.min(seamTable[i-1][j-1], seamTable[i-1][j]), seamTable[i-1][j+1]); // for all other cases
				}
			}
		}

		// get the last row of the table to determine the smallest value
		int[] array = new int[n];
		for (int i=0; i<n; i++){
			array[i] = seamTable[m-1][i];
		}

		// find the minimum value from the last row
		Arrays.sort(array);

		System.out.println("The lowest-cost seam value: "+array[0]);
	}

	public static void main(String[] args) throws Exception {
 
//		File file = new File("data/disruptionValuesSmall.txt"); 
		File file = new File("data/disruptionValues.txt"); 

		BufferedReader br = new BufferedReader(new FileReader(file)); 

		String dimensions; 		
		dimensions = br.readLine(); // read the first line, which gives the sizes of the table

		String size[] = dimensions.split(" ");
		int m = Integer.parseInt(size[0]); // height
		int n = Integer.parseInt(size[1]); // width

		int[][] image = new int[m][n]; // create a table to store pixels

		// read the file and fill up the table
		String line; 	
		for (int i=0; i<m; i++){
			line = br.readLine();
			String[] pixels = line.split(" ");
			for(int j=0; j<n; j++){
				image[i][j] = Integer.parseInt(pixels[j]);
			} 
		}
		
		seam(image,m,n);
	}
}