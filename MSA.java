/* Kristina McChesney 
 * CSC 421 
 * Fall 2019
 * Problem Set 3
 * Maximum Subarray Problem
 */

import java.util.Random;

public class MSA {

	// find max crossing subarray
	private static int[] findMaxCrossingSubarray(int[] A, int low, int mid, int high) {

		//array is used to return values: A[0]=maxLeft; A[1]=maxRight; A[2]leftSum+rightSum
		int[] result = new int[3]; 
		int maxLeft, maxRight;

		int leftSum = Integer.MIN_VALUE;
		int sum = 0;
		for (int i=mid; i>=low; i--) {
			sum = sum+A[i];
			if (sum > leftSum) {
				leftSum = sum;
				maxLeft = i;
				result[0]=maxLeft;
			}			
		}
		int rightSum = Integer.MIN_VALUE;
		sum = 0;
		for (int j=mid+1; j<=high; j++){
			sum = sum+A[j];
			if (sum > rightSum) {
				rightSum = sum;
				maxRight = j;
				result[1]=maxRight;
			}
		}		
		result[2]=leftSum+rightSum;
		return result;
	}

	// find max subarray
	private static int[] findMaxSubarray(int[] A, int low, int high) {
		// method returns an array of index of the low value, index of a high value and total sum of a subarray
		int[] result = new int[3]; 
		// base case
		if (high == low) {
			result[0]=low;
			result[1]=high;
			result[2]=A[low];
			return result;
		} else {
			int mid = (low+high)/2; 
			int[] leftSubarray = findMaxSubarray(A,low,mid); // A[0]=left-low; A[1]=left-high; A[2]=left-sum
			int[] rightSubarray = findMaxSubarray(A,mid+1,high); // A[0]=right-low; A[1]=right-high; A[2]=right-sum 
			int[] crossingSubarray = findMaxCrossingSubarray(A,low,mid,high); // A[0]=cross-low; A[1]=cross-high; A[2]=cross-sum

			// sum of a subarray is stored at index 2 of the returned array
			if ((leftSubarray[2] >= rightSubarray[2]) 
					&& (leftSubarray[2] >= crossingSubarray[2])) 
				return (leftSubarray);
			else if ((rightSubarray[2] >= leftSubarray[2]) 
					&& (rightSubarray[2] >= crossingSubarray[2]))
				return (rightSubarray);
			else 
				return (crossingSubarray);
		} 
	}

	// makes a random array; need an array size and integers limit as input
	private static int[] getRandomArray(int sizeIn, int minIn, int maxIn) {

		Random rd = new Random();
		int[] array = new int[sizeIn];

		for (int i=0; i<array.length; i++) {
			array[i] = rd.nextInt ((maxIn-minIn) + 1) + minIn;
		}	
		return array;
	}

	public static void main(String[] args) {

		int[] randomArray = getRandomArray(16, -32, 32); 
//		int[] randomArray = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};

		int[] forOutput = findMaxSubarray(randomArray, 0, randomArray.length-1);

		System.out.print("Random array: \n");
		for (int i=0; i<randomArray.length; i++) {
			System.out.print(randomArray[i]+" ");
		}
		System.out.print("\n\n");

		System.out.println("Index of the 1st element: "+forOutput[0]);
		System.out.println("Index of the 2nd element: "+forOutput[1]);
		System.out.println("Maximum sum os a subarray: "+forOutput[2]);
	}
}

