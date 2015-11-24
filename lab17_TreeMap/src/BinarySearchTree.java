/**
 * Created by EunjinCho on 11/9/15.
 */


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    public boolean contains(T key) {
        TreeNode t = this.myRoot;
        return contains(t, key);
    }

    public boolean contains (TreeNode t, T key) {
        if (t == null) {
            return false;
        } else if (t.myItem == key) {
            return true;
        } else {
            if (key.compareTo(t.myItem) < 0) {
                contains(t.myLeft, key);
            } else if (key.compareTo(t.myItem) > 0) {
                contains(t.myRight, key);
            }
        }
        return false;
    }

    public void add(T key) {
        if (this.contains(key)) {
            throw new IllegalStateException("key is already in the tree");
        } else {
            int thisSize = 0;
            myRoot = add(myRoot, key);
        }
    }

    public TreeNode add(TreeNode t, T key) {
        if (t == null) {
            TreeNode rtnNode = new TreeNode(key);
            rtnNode.mySize = 1;
            return rtnNode;
        }
        // left subtree
        else if (key.compareTo(t.myItem) < 0) {
            t.mySize += 1;
            t.myLeft = add(t.myLeft, key);
            return t;
        }
        // right subtree
        else {
            t.mySize += 1;
            t.myRight = add(t.myRight, key);
            return t;
        }
    }


    public Comparable kthLargest(int k) {
        return kthLargestHelper(this.myRoot, k).myItem;
    }

    private TreeNode kthLargestHelper(TreeNode t, int k) {
        if (k < 0 || k > this.myRoot.mySize) {
            throw new IllegalArgumentException("k is not a valid number");
        } else {
            //System.out.println("t: " + t.myItem);
            //System.out.println("t.mySize: " + t.mySize);
            if (t.myRank == 0 && (t.myLeft != null || t.myRight != null)) {
                t.myRank = t.mySize - t.myLeft.mySize;
            }
            //System.out.println("t.myRank: "+ t.myRank);
            if (t.myRank == k) {
                return t;
            }
            // Right
            else if (t.myRank > k) {
                if (t.myRight.myRight == null) {
                    t.myRight.myRank = t.mySize - 2;
                }
                return kthLargestHelper(t.myRight, k);
            }
            // Left
            else if (t.myRank < k) {
                t.myLeft.mySize = t.mySize;
                if (t.myLeft.myLeft == null) {
                    t.myLeft.myRank = t.mySize;
                }
                return kthLargestHelper(t.myLeft, k);
            }
        }
        return t;
    }


    public static void main(String[] args) {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        t.add(4);
        t.add(2);
        t.add(1);
        t.add(3);
        t.add(8);
        t.add(6);
        t.add(5);
        t.add(9);
        t.add(7);
        t.kthLargest(5);
        System.out.println(t.kthLargest(3)); // 7
        //System.out.println(t.myRoot.mySize);
        //t.print();
    }

}
