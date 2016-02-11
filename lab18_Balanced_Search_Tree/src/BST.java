

import java.util.*;

public class BST {
    BSTNode myRoot;
    static Iterator iterator;

    public BST(LinkedList list) {
        myRoot = linkedListToTree(list.iterator(), list.size());
    }


    /**
     * Create nodes bottom-up, and assign them to its parents
     * Enables us to access the list in its order
     * at the same time as creating tree nodes
      * @param iter : iterator of the linked list
     * @param n : length of the linked list
     * @return
     */
    private BSTNode linkedListToTree (Iterator iter, int n) {
        if (!iter.hasNext()) {
            return null;
        }
        iterator = iter;
        return sortedListToBST(0, n-1);

    }

    // Build tree bottom-up
    // e.g. list = (1, 2, 5)
    private BSTNode sortedListToBST(int start, int end) {
        // base case
        if (start > end) {
            return null;
        }
        int mid = (start+end) / 2;
        // separate the left part of the linked list based on mid
        // and recursively call until we hit the very left of the given boundary
        BSTNode leftChild = sortedListToBST(start, mid-1);
        // if we hit the very left and return null
        // we set root to be the next item in the list (iterator.next())
        BSTNode root = new BSTNode(iterator.next());
        // and set leftChild to be root.myLeft
        root.myLeft = leftChild;
        // find the remaining rightChild
        // recursively call until we hit the very right of the given boundary
        BSTNode rightChild = sortedListToBST(mid+1, end);
        // set the rightChild to be the root.myRight
        root.myRight = rightChild;
        return root;
    }

    public Object getMyItem() {
        return myRoot.myItem;
    }

    public Object getMyLeft() {
        return myRoot.myLeft.myItem;
    }

    public Object getMyRight() {
        return myRoot.myRight.myItem;
    }

    private class BSTNode {
        Object myItem;
        BSTNode myLeft;
        BSTNode myRight;

        BSTNode(Object x) {
            myItem = x;
        }
    }
}
