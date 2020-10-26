/* Kristina McChesney 
 * CSC 421 
 * Fall 2019
 * Problem Set 5
 * Minimum Edit Distance (MED) between two strings
 */

public class MED {

	// compute the minimum edit distance
	public static int findEditDistance(String x, String y) {
		
		int m = x.length();
		int n = y.length();
		
		int[][] dTable = new int[m+1][n+1];
		
		for (int i=0; i<=m; i++){
			for (int j=0; j<=n; j++){
				if (i == 0)
					dTable[i][j] = j;
				else if (j == 0) 
					dTable[i][j] = i;
				else if (x.charAt(i-1) == y.charAt(j-1))
					dTable[i][j] = dTable[i-1][j-1];
				else
					dTable[i][j] = Math.min(Math.min(
									dTable[i][j-1], dTable[i-1][j]),
									dTable[i-1][j-1])
									+ 1;	
			}
		}		
		return dTable[m][n];
	}
	
	public static void main(String[] args) {
		
		String firstString = "depaul";
		String secondString = "declaw"; 
		
		int distance = findEditDistance(firstString,secondString);
		
		System.out.println("First string: " + firstString);
		System.out.println("Second string: " + secondString);
		
		System.out.println("Minimum distance needed to edit the string: " + distance);
			
	}
}
