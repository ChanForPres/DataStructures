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

    public static void main(String[] args) {
        BinarySearchTree<String> t = new BinarySearchTree<>();
        t.myRoot = t.new TreeNode("a", t.new TreeNode("b"), t.new TreeNode("c"));
        t.contains("c");
    }
}
