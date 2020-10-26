/* Kristina McChesney 
 * CSC 421 
 * Fall 2019
 * Problem Set 5
 * Longest Common Subsequence, Second Version
 */

public class LCSv2 {

	// method takes 2 strings and builds up a table
	public static int[][] findLCSLength (String X, String Y) {

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
		return cTable;
	}
	
	// takes the result table and and prints the common subsequence and its length
	public static void printLCS (String X, String Y, int[][] table) {
		int m = X.length();
		int n = Y.length();
		int sequenceLength = table[m][n];

		System.out.println("The longest common subsequence of strings: "+X+ " and "+Y+" is " + sequenceLength);

		char[] result = new char[sequenceLength+1];

		int i = m;
		int j = n;
		while (i > 0 && j > 0){
			if (X.charAt(i-1) == Y.charAt(j-1)){
				result[sequenceLength-1] = X.charAt(i-1);
				i--;
				j--;
				sequenceLength--;
			}
			else if (table[i-1][j] > table[i][j-1])
				i--;
			else
				j--;
		}

		System.out.print("The subsequence is: ");
		for (int k=0; k<result.length; k++){
			System.out.print(result[k]);
		}
	}

	public static void main(String[] args) {

		String xString = "ABCBDAB";
		String yString = "BDCABA";

		int[][] result = findLCSLength(xString,yString);
		printLCS(xString,yString,result);
		
	}
}
