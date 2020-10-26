/* Kristina McChesney 
 * CSC 421 
 * Fall 2019
 * Problem Set 4
 * Longest Common Subsequence Of Two Strings Problem
 * 
 * Exercise 15.4-1:
 * Determine an LCS of (1, 0, 0, 1, 0, 1, 0, 1) and (0, 1, 0, 1, 1, 0, 1, 1, 0).
 * 
 * Answer: the length of the longest common sequence is 6
 * 
 */

public class LCS {

	// method takes 2 strings and returns the length of their longest common subsequence
	public static int findLCSLength (String X, String Y) {

		int m = X.length();
		int n = Y.length();

		int[][] cTable = new int[m+1][n+1];

		for(int i=0; i<=m; i++)
			cTable[i][0] = 0;
		for(int j=0; j<=n; j++)
			cTable[0][j] = 0;

		for(int i=1; i<m+1; i++){
			for(int j=1; j<n+1; j++){
				if(X.charAt(i-1) == Y.charAt(j-1)){
					cTable[i][j] = cTable[i-1][j-1]+1;	
				}else{
					cTable[i][j] = Math.max(cTable[i-1][j], cTable[i][j-1]);
				}
			}
		}
		return cTable[m][n];
	}

	public static void main(String[] args) {
		
//		String xString = "10010101";
//		String yString = "010110110";

		String xString = "ABCBDAB";
		String yString = "BDCABA";
		
		System.out.print("The longest common sequence of strings: "+xString+ " and "+yString+" is ");
		int result = findLCSLength(xString,yString);
		System.out.println(result);

	}

}
