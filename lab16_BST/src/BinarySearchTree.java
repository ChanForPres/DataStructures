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
            myRoot = add(myRoot, key);
        }
    }

    private TreeNode add(TreeNode t, T key) {
        if (t == null) {
            return new TreeNode(key);
        } else if (key.compareTo(t.myItem) < 0) {
            t.myLeft = add(t.myLeft, key);
            return t;
        } else {
            t.myRight = add(t.myRight, key);
            return t;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<String> t = new BinarySearchTree<>();
        t.add("C");
        t.add("A");
        t.add("B");
        t.add("E");
        t.add("D");
        t.print();
    }
}
