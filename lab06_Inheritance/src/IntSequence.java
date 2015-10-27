/**
 * Created by EunjinCho on 10/1/15.
 */


public class IntSequence {

    // instance variables
    protected int[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public IntSequence(int capacity) {
        myValues = new int[capacity];
        myCount = 0;
    }

    public boolean isEmpty() {
        if (myCount == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return myCount;
    }

    public int elementAt(int pos) {
        if (pos < 0 || pos > myValues.length) {
            System.err.println("Invalid position to look at");
            System.exit(1);
        }
        return myValues[pos];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int toBeAdded) {
        if (myValues.length == myCount) {
            System.err.println("No room to add an element");
            System.exit(1);
        } else {
            myValues[myCount] = toBeAdded;
            myCount++;
        }
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert (int pos, int newInt) {
        if (pos < 0 || pos >= myValues.length) {
            System.err.println("Invalid place to insert");
            System.exit(1);
        }
        for (int i = myValues.length-1; i> pos; i--) {
            myValues[i] = myValues[i-1];
        }
        myValues[pos] = newInt;

    }

    // other methods go here
    public String toString() {
        String toRet = new String("");
        for (int i = 0; i < myValues.length; i++) {
            toRet +=  myValues[i] + " ";
        }
        return toRet;
    }

    public void remove(int pos) {
        if (pos < 0 || pos >= myValues.length) {
            System.err.println("Invalid place to remove");
            System.exit(1);

        } else {
            for (int i = pos; i < myValues.length; i++) {
                if (i == myValues.length-1) {
                    myValues[myValues.length-1] = 0;
                    break;
                }
                myValues[i] = myValues[i + 1];
            }
            myCount--;
        }
    }

    public boolean contains(int k) {
        for (int i = 0; i < myCount; i++) {
            if (myValues[i] == k) {
                return true;
            }
        }
        return false;
    }
}

