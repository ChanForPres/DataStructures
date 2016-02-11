

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
        System.out.println("start: "+start+" end: "+end + " mid: "+(start+end) / 2);
        int mid = (start+end) / 2;
        BSTNode leftChild = sortedListToBST(start, mid-1);
        BSTNode root = new BSTNode(iterator.next());
        System.out.println("root: "+root.myItem);
        root.myLeft = leftChild;
        System.out.println("here1");
        BSTNode rightChild = sortedListToBST(mid+1, end);
        System.out.println("here2");
        root.myRight = rightChild;
        System.out.println("here3");
        System.out.println("final root: "+root.myItem);
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
