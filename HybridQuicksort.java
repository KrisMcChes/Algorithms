/* Kristina McChesney
 * CSC 421 
 * Fall 2019
 * Problem Set 3
 * Hybrid of QuickSort and InsertionSort 
 * 
 * Exercise 7.4-5
 * How should we pick k, both in theory and in practice? 
 * 
 * Insertion Sort takes O(n^2) time, while Quick Sort - n lg(n).
 * The hybrid algorithm runs in O(nk + n lg(n/k)) time.
 * Therefore, value k has to be such that O(nk + n lg(n/k)) = O(n lg(n))
 * 
 * In practice size of a k depends on the computer architecture. Since a small array can be stored in one page of a memory.
 * 
 */

import java.util.Random;

public class HybridQuicksort {

	// sorts subarray only if it has more than k elements
	private static void quickSortModified(int[] A, int p, int r, int k) {
		if(p < r) {
			if((r-p+1) > k) {
				int q = partition(A,p,r);
				quickSortModified(A,p,q-1,k);
				quickSortModified(A,q+1,r,k);
			}
		}
	}

	private static int partition(int[] A, int p, int r) {
		int pivot = A[r];
		int i = (p-1);
		for (int j=p; j<r; j++){
			if (A[j] < pivot) {
				i++;
				exchange(A,i,j);
			}
		}
		exchange(A,i+1,r);
		return i+1;
	} 

	// swaps elements in the array
	private static void exchange(int[] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

	// regular insertion sort
	private static int[] insertionSort (int[] A) {
		for (int j=1; j<A.length; j++) {
			int key = A[j];
			int i = j-1;
			while (i>=0 && A[i] > key) {
				A[i+1] = A[i];
				i -= 1;
			}
			A[i+1] = key;
		}
		return A;
	}

	// makes a random array; need an array size and integers limit as input
	private static int[] getRandomArray(int sizeIn, int minIn, int maxIn) {

		Random rd = new Random();
		int[] array = new int[sizeIn];

		for (int i=0; i<array.length; i++) {
			array[i] = rd.nextInt(); 
		}	
		return array;
	}

	// master sorting method that calls the other; k-size of an array, such that quickSort will return if the array is smaller than k
	public static void hybridSort (int[] A, int k) {

		quickSortModified(A,0,A.length-1,k);
		insertionSort(A);

	}

	public static void main(String[] args) {

		int min = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;
		int arrayLength = 1024;

		int[] randomArray = getRandomArray(arrayLength, min, max);

		hybridSort(randomArray, 100);

		System.out.println("Sorted array of a length "+arrayLength);

		for (int i=0; i<randomArray.length; i++) {
			System.out.println(randomArray[i]);

		}	
	}
}
