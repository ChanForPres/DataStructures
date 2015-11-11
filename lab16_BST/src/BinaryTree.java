import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BinaryTree<T> implements Iterable<T> {

    protected TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }

    /**
     * @param a1: contains the objects in a preorder traversal (root-left-right)
     * @param a2: contains the objects in an inorder traversal (left-root-right)
     */
    public BinaryTree(ArrayList<T> a1, ArrayList<T> a2) {
        Stack<TreeNode> a1stack = new Stack<>();
        if (a1.isEmpty() || a2.isEmpty()) {
            System.err.println("both arraylist should not be empty");
            throw new IllegalArgumentException("both arraylist should not be empty");
        } else {
            BinaryTreeHelper(a1, a2, 0, null);
        }
    }

    public void BinaryTreeHelper(ArrayList<T> a1, ArrayList<T> a2, int n, TreeNode thisRoot) {
        myRoot = new TreeNode((T) a1.get(n));
        int j = 0;
        while(a2.get(j) != a1.get(n)) {
            j++;
        }
        if (a2.get(j) == a1.get(n)) {
            int inIndex = j;
        }

        ArrayList<T> a2_sub_left = new ArrayList<T>(a2.subList(0, j));
        ArrayList<T> a2_sub_right = new ArrayList<>(a2.subList(j+1, a2.size()-1));
        BinaryTreeHelper(a1, a2_sub_left, n+1, myRoot.myLeft);
        BinaryTreeHelper(a1, a2_sub_right, n+1, myRoot.myRight);
    }

    // Print the values in the tree in preorder: root value first,
    // then values in the left subtree (in preorder), then values
    // in the right subtree (in preorder).
    public void printPreorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printPreorder();
            System.out.println();
        }
    }

    // Print the values in the tree in inorder: values in the left
    // subtree first (in inorder), then the root value, then values
    // in the right subtree (in inorder).
    public void printInorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printInorder();
            System.out.println();
        }
    }

    public void print() {
        if (myRoot != null) {
            myRoot.print(1);
        }
    }

    public static BinaryTree<String> fillSampleTree1() {
        BinaryTree<String> t = new BinaryTree<String>();
        t.myRoot = t.new TreeNode("a", t.new TreeNode("b"), t.new TreeNode("c"));
        return t;
    }

    public static BinaryTree<String> fillSampleTree2() {
        BinaryTree<String> t = new BinaryTree<String>();
        t.myRoot = t.new TreeNode("a", t.new TreeNode("b", t.new TreeNode("d", t.new TreeNode("e"), t.new TreeNode("f")), null), t.new TreeNode("c"));
        return t;
    }

    public static void main(String[] args) {
        /**BinaryTree<String> t = new BinaryTree<String>();
        print(t, "the empty tree");
        BinaryTree<String> s = fillSampleTree1();
        print(s, "sample tree 1");
        BinaryTree<String> r = fillSampleTree2();
        print(r, "sample tree 2");**/
        ArrayList<String> a1 = new ArrayList<>();
        a1.add("A");
        a1.add("B");
        a1.add("C");
        a1.add("D");
        a1.add("E");
        a1.add("F");
        ArrayList<String> a2 = new ArrayList<>();
        a2.add("B");
        a2.add("A");
        a2.add("E");
        a2.add("D");
        a2.add("F");
        a2.add("C");
        BinaryTree<String> t = new BinaryTree<>(a1, a2);
        print(t, "tree2");

    }

    protected static void print(BinaryTree<?> t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    // Method for the BinaryTree class
    public Iterator<T> iterator(){
        return new InorderIterator();
    }

    // Inner class inside of the BinaryTree class.
    // Also, it uses java.util.Iterator and java.util.Stack.
    private class InorderIterator implements Iterator<T> {
        private Stack<TreeNode> nodeStack;
        private TreeNode currentNode;

        public InorderIterator() {
            nodeStack = new Stack<TreeNode>();
            currentNode = myRoot;
        }

        public boolean hasNext() {
            return !nodeStack.isEmpty() || (currentNode != null);
        }

        public T next() {
            TreeNode nextNode = null;

            // find leftmost node with no left child
            while (currentNode != null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.myLeft;
            }

            // get leftmost node, then move to its right subtree
            if (!nodeStack.isEmpty()) {
                nextNode = nodeStack.pop();
                assert nextNode != null;    // since nodeStack was not empty before the pop
                currentNode = nextNode.myRight;
            } else {
                throw new NoSuchElementException();
            }

            return nextNode.myItem; 
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    protected class TreeNode {

        public T myItem;
        public TreeNode myLeft;
        public TreeNode myRight;

        public TreeNode(T item) {
            myItem = item;
            myLeft = myRight = null;
        }

        public TreeNode(T item, TreeNode left, TreeNode right) {
            myItem = item;
            myLeft = left;
            myRight = right;
        }

        private void printPreorder() {
            System.out.print(myItem + " ");
            if (myLeft != null) {
                myLeft.printPreorder();
            }
            if (myRight != null) {
                myRight.printPreorder();
            }
        }

        private void printInorder() {
            if (myLeft != null) {
                myLeft.printInorder();
            }
            System.out.print(myItem + " ");
            if (myRight != null) {
                myRight.printInorder();
            }
        }

        private static final String indent1 = "    ";

        private void print(int indent) {
            if (myRight != null) {
                myRight.print(indent+1);
            }
            println (myItem, indent);
            if (myLeft != null) {
                myLeft.print(indent+1);
            }
        }

        private void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }
    }
}