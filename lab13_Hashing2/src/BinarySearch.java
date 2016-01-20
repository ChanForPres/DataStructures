import java.util.HashMap;
import java.util.LinkedList;

public class BinarySearch {

	public static void main (String[] args) {
		int[] values = new int[31];
		// smallest value of number of buckets, 3,
		// for which the total number of comparisons to find all 31 values in the hash table is less
		MyHashMap<Integer, Integer> hashMap = new MyHashMap<>(3);
		/*for (int k = 0; k < 31; k++) {
			values[k] = k+1;
		}*/
		totalComparisons = 0;
		/*for (int key = 1; key <= 31; key++) {
			search(key, values);
		}*/

		for (int k = 0; k < 31; k++) {
			hashMap.put(k, k);
		}

		for (int key = 1; key <= 31; key++) {
			searchH(hashMap, key);
		}
		//System.out.println ("totalComparisons: " + totalComparisons);
	}
	
	static int totalComparisons;

	public static boolean search (int key, int[] values) {
		int leftIndex = 0;
		int rightIndex = values.length - 1;
		while (leftIndex <= rightIndex) {
			int middleIndex = (leftIndex+rightIndex) / 2;
			if (values[middleIndex] == key) {	    // a comparison
				totalComparisons += 1;
				return true;
			} else if (values[middleIndex] < key) {	// another comparison
				totalComparisons += 2;
				leftIndex = middleIndex+1;
			} else {
				totalComparisons += 2;
				rightIndex = middleIndex-1;
			}
		}
		return false;
	}


	public static boolean searchH (MyHashMap<Integer, Integer> hashMap, int key) {
		return hashMap.containsKey(key);
	}

}
