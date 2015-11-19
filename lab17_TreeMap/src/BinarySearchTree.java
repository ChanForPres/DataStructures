/**
 * Created by EunjinCho on 11/9/15.
 */


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    public boolean contains(T key) {
        TreeNode t = this.myRoot;
        return contains(t, key);
    }

    private boolean contains (TreeNode t, T key) {
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
            myRoot = add(myRoot, key, thisSize);
        }
    }

    private TreeNode add(TreeNode t, T key, int thisSize) {
        if (t == null) {
            TreeNode rtnNode = new TreeNode(key);
            rtnNode.mySize = thisSize;
            return rtnNode;
        } else if (key.compareTo(t.myItem) < 0) {
            thisSize += 1;
            t.myLeft = add(t.myLeft, key, thisSize);
            return t;
        } else {
            thisSize += 1;
            t.myRight = add(t.myRight, key, thisSize);
            return t;
        }
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
        t.print();
    }
}