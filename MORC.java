/* Kristina McChesney 
 * CSC 421 
 * Fall 2019
 * Problem Set 4
 * Memoized Algorithm for the Optimal Rod Cutting Problem
 */

public class MORC {
	
	// main method, fills up an array with "unknown" values and calls a helper method
	private static int MemoizedOCR(int[] p, int n){
		int[] revenues = new int[n+1];
		for(int i=0; i<n; i++)
			revenues[i] = Integer.MIN_VALUE;
		return MOCR_Aux(p, n, revenues);	
	}

	// check if the value already exists, if not - computes new value and adds to the array
	private static int MOCR_Aux(int[] p, int n, int[] r){
		if(r[n] > 0) 
			return r[n];	
		int q;
		if(n == 0){
			q = 0;
		}else{ 
			q = Integer.MIN_VALUE;
			for(int i=0; i<n; i++){
				q = Math.max(q, p[i] + MOCR_Aux(p,n-i-1,r));
			}
		}
		r[n] = q;
		return q;	
	}	
	
	public static void main(String[] args) {
		int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
		int length = prices.length;	
		
		System.out.println("Optimal profits:");
		for (int i=1;i<=length; i++){
			int maxRevenue = MemoizedOCR(prices,i);
			System.out.println("  for length "+i+" is "+maxRevenue);
		}
		
	}
}
