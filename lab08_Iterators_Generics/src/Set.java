/**
 * Created by EunjinCho on 10/10/15.
 */


public class Set {

    // Represent a set of nonnegative ints from 0 to maxElement-1
    // for some initially specified maxElement.

    // contains[k] is true if k is in the set, false if it isn't
    private boolean[] contains;

    // Initialize a set of ints from 0 to maxElement-1.
    public Set (int maxElement) {
        contains = new boolean[maxElement];
    }

    // precondition: 0 <= k < maxElement.
    // postcondition: k is in this set.
    public void insert(int k) {
        if (k < 0 || k > contains.length) {
            return;
        } else {
            contains[k] = true;
        }
    }

    // precondition: 0 <= k < maxElement.
    // postcondition: k is not in this set.
    public void remove(int k) {
        if (k < 0 || k > contains.length) {
            return;
        } else {
            contains[k] = false;
        }
    }

    // precondition: 0 <= k < maxElement
    // Return true if k is in this set, false otherwise.
    public boolean member(int k) {
        if (k < 0 || k > contains.length) {
            System.err.println("Not a valid k");
            System.exit(1);
        }
        if (contains[k] == true) {
            return true;
        } else {
            return false;
        }
    }

    // Return true if this set is empty, false otherwise.
    public boolean isEmpty() {
        for (int i = 0; i < contains.length; i++) {
            if (contains[i] == true) {
                return false;
            }
        }
        return true;
    }

    int nextIndexToReturn;
    int numOfTrues;
    int limitOfTrues;

    public int getNumOfTrues() {
        for (int i = 0; i < contains.length; i++) {
            if (contains[i] == true) {
                numOfTrues++;
            }
        }
        return numOfTrues;
    }

    public void initIterator() {
        nextIndexToReturn = 0;
        limitOfTrues = 0;
        numOfTrues = getNumOfTrues();
    }

    public boolean hasNext() {
        return limitOfTrues < numOfTrues;
    }

    public int next() {
        int valToRtn = -1;
        if (contains[nextIndexToReturn] == true) {
            valToRtn = nextIndexToReturn;
            limitOfTrues++;
        }
        nextIndexToReturn++;
        return valToRtn;
    }

    public static void main(String[] args) {
        Set s = new Set(5);
        s.insert(0);
        s.insert(1);
        s.insert(2);
        s.insert(4);
        s.initIterator();
        while (s.hasNext()) {
            int cur = s.next();
            System.out.println(cur);
        }
    }
}
