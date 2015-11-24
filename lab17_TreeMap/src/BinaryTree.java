import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BinaryTree<T extends Comparable<T>> implements Iterable<T> {

    protected TreeNode myRoot;
    protected int preIndex = 0;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }

    /**
     * @param pre: contains the objects in a preorder traversal (root-left-right)
     * @param in: contains the objects in an inorder traversal (left-root-right)
     */
    public BinaryTree(ArrayList<T> in, ArrayList<T> pre) {
        if (pre.isEmpty() || in.isEmpty()) {
            System.err.println("Both arrayList should not be empty");
            throw new IllegalArgumentException("Both arrayList should not be empty");
        } else {
            myRoot = BinaryTreeHelper(in, pre, 0, in.size()-1);
        }
    }

    private TreeNode BinaryTreeHelper(ArrayList<T> in, ArrayList<T> pre, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }
        TreeNode tNode = new TreeNode(pre.get(preIndex++));

        // If this node has no children, terminates
        if (inStart == inEnd) {
            return tNode;
        }

        int inIndex = search(in, inStart, inEnd, tNode.myItem);

        tNode.myLeft = BinaryTreeHelper(in, pre, inStart, inIndex-1);
        tNode.myRight = BinaryTreeHelper(in, pre, inIndex+1, inEnd);
        return tNode;
    }

    private int search(ArrayList<T> in, int inStart, int inEnd, T myRootItem) {

        int j = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (in.get(i) == myRootItem) {
                j = i;
            }
        }
        System.out.println("J: "+j);
        return j;
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

    public void print(int i) {
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
        print(s, "sample tree 1");**/
        //BinaryTree<String> r = fillSampleTree2();
        //r.print();
        ArrayList<String> pre = new ArrayList<>();
        pre.add("A");
        pre.add("B");
        pre.add("C");
        pre.add("D");
        pre.add("E");
        pre.add("F");
        ArrayList<String> in = new ArrayList<>();
        in.add("B");
        in.add("A");
        in.add("E");
        in.add("D");
        in.add("F");
        in.add("C");
        BinaryTree<String> t = new BinaryTree<>(in, pre);
        t.print(0);

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

    public class TreeNode extends BinarySearchTree<T> {

        public T myItem;
        public TreeNode myLeft;
        public TreeNode myRight;
        public int mySize;
        public int myRank;

        public TreeNode(T item) {
            myItem = item;
            myLeft = myRight = null;
            mySize = 0;
            myRank = 0;
        }

        public TreeNode(T item, TreeNode left, TreeNode right) {
            myItem = item;
            myLeft = left;
            myRight = right;
            mySize = 0;
            myRank = 0;
        }

        public void printPreorder() {
            System.out.print(myItem + " ");
            if (myLeft != null) {
                myLeft.printPreorder();
            }
            if (myRight != null) {
                myRight.printPreorder();
            }
        }

        public void printInorder() {
            if (myLeft != null) {
                myLeft.printInorder();
            }
            System.out.print(myItem + " ");
            if (myRight != null) {
                myRight.printInorder();
            }
        }

        private static final String indent1 = "    ";

        public void print(int indent) {
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