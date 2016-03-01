import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {

        // count the total number of each element
        int[] counts = new int[10];
        for (int i = 0; i < arr.length; i++) {
            counts[arr[i]]++;
        }

        // think about the index
        int[] starts = new int[10];
        for (int j = -1; j < counts.length - 1; j++) {
            if (j == -1) {
                starts[j+1] = 0;
            } else {
                starts[j+1] = counts[j] + starts[j];
            }
        }

        // spread
        int [] sorted = new int[arr.length];
        for (int w = 0; w < arr.length; w++) {
            sorted[starts[arr[w]]] = arr[w];
            starts[arr[w]]++;
        }

        for (int a = 0; a < sorted.length; a++) {
            arr[a] = sorted[a];
        }
	}


    /**
	 * Sorts the given array using MSD radix sort.
     *
	 */
	public static void MSDRadixSort(int[] arr) {
		int maxDigit = mostDigitsIn(arr);
        System.out.println(maxDigit);
		MSDRadixSortFromDigitInBounds(arr, maxDigit, 0, arr.length);
	}

	/**
	 * Radix sorts the input array only between the indices start and end. Only
	 * considers digits from the input digit on down. This method is recursive.
	 */
	public static void MSDRadixSortFromDigitInBounds(int[] arr, int digit, int begin, int end) {
        System.out.println("begin: " + begin + " end: " + end);

        // base case: if end and begin are next to each other or the same
        if (end <= begin+1) {
            return;
        }// base case: when two numbers are the same
        else if (arr[begin] == arr[end-1] && end <= begin+2) {
            return;
        }
        else if (arr[begin] == arr[end-1] && arr[begin+1] == arr[end-1] && end <= begin+3) {
            return;
        } else if (digit < 0) {
            return;
        }
        else{
            int[] bounds = countingSortByDigitInBounds(arr, digit, begin, end);
            System.out.println("FINAL arr: " + Arrays.toString(arr));

            for (int i = 0; i < bounds.length; i+=2) {
                //System.out.println(Arrays.toString(Arrays.copyOfRange(arr, bounds[i], bounds[i+1]+1)));
                MSDRadixSortFromDigitInBounds(arr, digit-1, bounds[i], bounds[i+1]+1);
            }
        }
    }

    public static int getNthDigit(int number, int base, int n) {
        return (int) ((number / Math.pow(base, n)) % base);
    }

	/**
	 * A helper method for radix sort. Modifies arr to be sorted according to
	 * digit. Only sorts the portion of the arr between the indices start
	 * (inclusive) and end (exclusive).
	 * 
	 * Does NOT return the sorted array. Returns an array containing the
	 * boundary of each same-digit bucket in the array. This will be useful for
	 * radix sort.
	 */
	private static int[] countingSortByDigitInBounds(int[] arr, int digit,
			int begin, int end) {
        System.out.println("HEREHEREHERE begin: " + begin + " end: " + end);

        // count frequencies
        int[] counts = new int[10];
        for (int i = begin; i < end; i++) {
            //System.out.println("arr[i]: " + arr[i] + " digit: " + digit + " arr[i] / (int) Math.pow(10, digit): "+arr[i] / (int) Math.pow(10, digit));
            int numDigit = getNthDigit(arr[i], 10, digit);
            System.out.println("arr[i]: " + arr[i] + " numDigit: " + numDigit);
            counts[numDigit]++;
        }
        System.out.println("count: " + Arrays.toString(counts));


        // find start index
        int[] starts = new int[10];
        for (int j = -1; j < counts.length - 1; j++) {
            if (j == - 1) {
                starts[j + 1] = 0;
            } else {
                starts[j + 1] = counts[j] + starts[j];
            }
        }
        System.out.println("starts: " + Arrays.toString(starts));


        int[] temp = new int[arr.length];
        for (int t = 0; t < arr.length; t++) {
            temp[t] = arr[t];
        }

        // sort the arr
        for (int w = begin; w < end; w++) {
            int numDigit = getNthDigit(temp[w], 10, digit);
            int toPlace = begin + starts[numDigit];
            arr[toPlace] = temp[w];
            starts[numDigit]++;
        }


        // count the variety of numbers
        int variety = 0;
        HashSet<Integer> varietySet = new HashSet<>();
        for (int v = begin; v < end; v++) {
            int numDigit = getNthDigit(arr[v], 10, digit);
            if (!varietySet.contains(numDigit)) {
                variety++;
                varietySet.add(numDigit);
            }
        }

        // Create boundaries
        /*int k = begin;
        int assign = 0;
        int[] bounds = new int[variety * 2];
        HashSet<Integer> boundCheck = new HashSet<>();
        while (assign != end) {
            int numDigit = arr[k] / (int) Math.pow(10, digit);
            if (k == begin) {

            }
            if (!boundCheck.contains(numDigit)) {
                bounds[k] = assign;
                k++;
            } else {
                while (boundCheck.contains(numDigit)) {
                    assign++;
                }
            }
        }
        return bounds;*/

        // create boundaries
        HashSet<Integer> boundCheck = new HashSet<>();
        System.out.println("variety: " + variety);
        System.out.println("maxDigit: " + digit);
        int[] bound = new int[variety*2];
        int bi = 0;
        int boundIdx = 0;
        for (int k = begin; k < end; k++) {
            System.out.println("k: " + k);
            int numDigit = getNthDigit(arr[k], 10, digit);
            if (bi == 0) {
                bound[0] = begin;
                System.out.println("bound[0]: " + begin + " arr[k]: " + arr[k] + " numDigit: " + numDigit);
                boundIdx++;
                bi++;
                boundCheck.add(numDigit);
            } else if (!boundCheck.contains(numDigit) && bi != begin) {
                System.out.println("boundIdx: " + boundIdx + " k-1: " + (k-1) + " numDigit: " + numDigit + " arr[k]: " + arr[k]);
                bound[boundIdx] = k-1;
                boundIdx++;
                bi++;
                bound[boundIdx] = k;
                System.out.println("boundIdx: " + boundIdx + " k: " + (k) + " numDigit: " + numDigit+ " arr[k]: " + arr[k]);
                boundCheck.add(numDigit);
                boundIdx++;
                bi++;
                System.out.println("bi: " + bi);
            } if (bi == bound.length-1) {
                System.out.println("boundIdx: " + boundIdx + " k: " + k);
                bound[boundIdx] = k;
            }
        }
        if (bound[bound.length-1] == 0) {
            bound[bound.length-1] = end-begin-1;
        }

        System.out.println("bounds: " + Arrays.toString(bound));
        return bound;

	}

	/**
	 * Returns the highest number of digits that any integer in arr happens to
	 * have.
	 */
	private static int mostDigitsIn(int[] arr) {
		int maxDigitsSoFar = 0;
		for (int num : arr) {
            int numDigits = (int) (Math.log10(num));
			if (numDigits > maxDigitsSoFar) {
				maxDigitsSoFar = numDigits;
			}
		}
		return maxDigitsSoFar;
	}

	/**
	 * Returns a random integer between 0 and 9999.
	 */
	private static int randomInt() {
		return (int) (10000 * Math.random());
	}

	/**
	 * Returns a random integer between 0 and 9.
	 */
	private static int randomDigit() {
		return (int) (10 * Math.random());
	}

	/**
	 * Runs some very basic tests of counting sort and radix sort.
	 */
	public static void main(String[] args) {
		/*int[] arr1 = new int[20];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = randomDigit();
		}
		System.out.println("Original array: " + Arrays.toString(arr1));
		countingSort(arr1);
		if (arr1 != null) {
			System.out.println("Should be sorted: " + Arrays.toString(arr1));
		}*/

		/*int[] arr2 = new int[6];
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = randomDigit();
		}
        arr2[0] = 20;
        arr2[1] = 5;
        arr2[2] = 3;
        arr2[3] = 10;
        arr2[0] = 44;
        arr2[1] = 269;
        arr2[2] = 330;
        arr2[3] = 3900;
        arr2[4] = 1000;
        arr2[5] = 1040;
        //arr2[6] = 3900;
        System.out.println("Original array: " + Arrays.toString(arr2));
        try {
            MSDRadixSort(arr2);
        } catch (StackOverflowError e) {
            System.err.println("StackOverFlow");
        }
		System.out.println("Should be sorted: " + Arrays.toString(arr2));*/

		int[] arr3 = new int[30];
		for (int i = 0; i < arr3.length; i++) {
			arr3[i] = randomInt();
		}
		System.out.println("Original array: " + Arrays.toString(arr3));
        try {
            MSDRadixSort(arr3);
        } catch (StackOverflowError e) {
            System.err.println("StackOverFlow");
        }
		System.out.println("Should be sorted: " + Arrays.toString(arr3));
	}
}
