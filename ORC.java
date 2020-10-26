/* Kristina McChesney 
 * CSC 421 
 * Fall 2019
 * Problem Set 4
 * Bottom-Up Algorithm for the Optimal Rod Cutting Problem
 */

public class ORC {

	private static int findORC(int[] p, int n){
		int[] revenues = new int[n+1];
		revenues[0] = 0;
		int q;
		for(int j=1; j<=n; j++){
			q = Integer.MIN_VALUE;
			for(int i=0; i<j; i++){
				q = Math.max(q,p[i]+revenues[j-i-1]);
			}
			revenues[j] = q;
		}		
		return revenues[n];
	}

	public static void main(String[] args) {
		int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
		int length = prices.length;

		System.out.println("Optimal profits:");
		for (int i=1;i<=length; i++){
			int maxRevenue = findORC(prices,i);
			System.out.println("  for length "+i+" is "+maxRevenue);
		}
	}
}
