/**
 * Created by EunjinCho on 10/10/15.
 */

import java.util.Iterator;

/**
 * Created by EunjinCho on 10/1/15.
 */


public class Sequence <T> implements Iterable<T> {

    // instance variables
    protected T[] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        myValues = (T[]) new Object[capacity];
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

    public T elementAt(int pos) {
        if (pos < 0 || pos > myValues.length) {
            System.err.println("Invalid position to look at");
            System.exit(1);
        }
        return myValues[pos];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(T toBeAdded) {
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
    public void insert (int pos, T newInt) {
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
                    //myValues[myValues.length-1] = (T) new Object();
                    break;
                }
                myValues[i] = myValues[i + 1];
            }
            myCount--;
        }
    }

    public boolean contains(T k) {
        for (int i = 0; i < myCount; i++) {
            if (myValues[i] == k) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new SequenceIterator();
    }

    public class SequenceIterator implements Iterator<T> {
        int curIndex;
        int sequenceSize;

        public SequenceIterator() {
            curIndex = 0;
            sequenceSize = myValues.length;
        }

        @Override
        public boolean hasNext() {
            if (curIndex < sequenceSize) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            T toRtn = myValues[curIndex];
            curIndex++;
            return toRtn;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Sequence<Integer> seq = new Sequence(5);
        seq.insert(0, 0);
        seq.insert(1, 1);
        seq.insert(2, 2);
        seq.insert(3, 3);
        seq.insert(4, 4);
        for (Integer oneInt: seq) {
            System.out.println(oneInt);
        }
    }


}


