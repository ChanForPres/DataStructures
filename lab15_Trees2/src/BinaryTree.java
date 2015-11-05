import java.util.*;

public class BinaryTree {

    private TreeNode myRoot;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
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


    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("a"));
    }


    public void fillSampleTree2() {
        TreeNode treenode = new TreeNode("a");
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("a"));
    }


    public void fillSampleTree3() {
        myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C", new TreeNode("D"), new TreeNode("E")));
    }


    public int height() {
        if (myRoot == null) {
            return 0;
        } else {
            return myRoot.height();
        }
    }


    public boolean isCompletelyBalanced() {
        if (myRoot == null) {
            return true; // Empty tree
        } else if (myRoot.myRight == null && myRoot.myLeft == null) {
            return true; // Only leaf
        } else {
            return myRoot.isCompletelyBalanced();
        }
    }


    public void print() {
        if (myRoot != null) {
            myRoot.print(1);
        }
    }

    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n) {
        if (n == 0) {
            myRoot = new TreeNode(0);
            return myRoot;
        } else if (n == 1) {
            myRoot = new TreeNode(1);
            return myRoot;
        } else {
            myRoot = new TreeNode(add(fibTreeHelper(n-1), fibTreeHelper(n-2)), fibTreeHelper(n-1), fibTreeHelper(n-2));
            return myRoot;
        }
    }

    private Object add(TreeNode myLeft, TreeNode myRight) {
        Integer leftItem = (int) myLeft.myItem;
        Integer rightItem = (int) myRight.myItem;
        return leftItem + rightItem;
    }


    public static BinaryTree exprTree(String s) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.exprTreeHelper(s);
        return result;
    }

    // Return the tree corresponding to the given arithmetic expression.
    // The expression is legal, fully parenthesized, contains no blanks,
    // and involves only the operations + and *.
    private TreeNode exprTreeHelper(String expr) {

        // Base case
        if (expr.charAt(0) != '(') {
            if (Character.isLetter(expr.charAt(0))) {
                return new TreeNode(expr.charAt(0));
            } else {
                return new TreeNode(new Integer(Character.getNumericValue(expr.charAt(0))));
            }
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
                if (expr.charAt(k) == '(') {
                    nesting++;
                } else if (expr.charAt(k) == ')') {
                    nesting--;
                } else if (nesting == 0 && (expr.charAt(k) == '+'||expr.charAt(k) == '*')) {
                    opPos = k;
                }
            }
            String opnd1 = expr.substring(1, opPos);
            String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
            String op = expr.substring(opPos, opPos + 1);
            System.out.println("expression = " + expr);
            System.out.println("operand 1  = " + opnd1);
            System.out.println("operator   = " + op);
            System.out.println("operand 2  = " + opnd2);
            System.out.println();

            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2));
        }
    }

    public void optimize() {
        optimizeHelper(this.myRoot);
    }

    private void optimizeHelper(TreeNode myRoot) {
        System.out.println("myRoot: "+myRoot.myItem);

        if (myRoot.myLeft != null) {
            optimizeHelper(myRoot.myLeft);
        } if (myRoot.myRight != null) {
            // Regardless myRoot.myLeft != null check, this statement will also be executed
            // This is why it is if() statement instead of else if()
            optimizeHelper(myRoot.myRight);
        } if (myRoot.myItem instanceof String) {
            if (myRoot.myLeft.myItem instanceof Integer && myRoot.myRight.myItem instanceof Integer) {
                String op = (String) myRoot.myItem;
                Integer left_comp = (Integer)myRoot.myLeft.myItem;
                Integer right_comp = (Integer)myRoot.myRight.myItem;
                if (op.charAt(0) == '+') {
                    myRoot.myItem = new Integer(left_comp+right_comp);
                } else if (op.charAt(0) == '*') {
                    myRoot.myItem = new Integer(left_comp*right_comp);
                }
                myRoot.myRight = null;
                myRoot.myLeft = null;
                return;
            }
        }
    }


    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        //t = fibTree(0);
        //t.print();
        //t = fibTree(1);
        //t.print();
        //t = fibTree(2);
        //t.print();
        //t = fibTree(5);
        //t.print();
        t = exprTree("((a+(5*(9+1)))+(6*5))");
        t.optimize();
        t.print();


        //print(t, "the empty tree");
        //t.fillSampleTree1();
        //print(t, "sample tree 1");
        //t.fillSampleTree2();
        //print(t, "sample tree 2");
        //t.check();
        //BinaryTree t3 = new BinaryTree();
        //t3.fillSampleTree3();
        //t3.print();
        //print(t3, "sample tree 3");
        //System.out.println(t3.height());
        //System.out.println(t3.isCompletelyBalanced());
    }



    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }


    private ArrayList alreadySeen;
    public boolean check() {
        alreadySeen = new ArrayList();
        try {
            isOK(myRoot);
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    // I have two different kinds of solution
    // 1. Using TreeIterator that implements Iterator<TreeNode>, traverses the tree and checks multiplicity
    // 2. Using recursion, checks if there's any multiplicity
    private void isOK(TreeNode t) throws IllegalStateException {
        /*Iterator<TreeNode> iter = new TreeIterator();
        System.out.println("alreadySeen: "+alreadySeen);
        if (iter.hasNext()) {
            TreeNode myNext = iter.next();
            System.out.println("myNext: "+myNext.myItem.toString());
            if (alreadySeen.contains(myNext)) {
                throw new IllegalStateException("no node appears more than once in the traversal");
            } else {
                alreadySeen.add(myNext);
            }
        }*/
        alreadySeen.add(t);
        if (alreadySeen.contains(t)) {
            System.err.println("no node appears more than once in the traversal");
            throw new IllegalStateException("no node appears more than once in the traversal");
        } else {
            if (t.myLeft != null) {
                alreadySeen.add(t.myLeft);
                isOK(t.myLeft);
            } else if (t.myRight != null) {
                alreadySeen.add(t.myRight);
                isOK(t.myRight);
            }
        }
    }

    public class TreeIterator implements Iterator<TreeNode> {

        private Queue fringe = new LinkedList<>();

        public TreeIterator() {
            if (myRoot != null) {
                fringe.add(myRoot);
            }
        }

        @Override
        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        @Override
        public TreeNode next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the tree");
            } else {
                TreeNode thisRoot = (TreeNode) fringe.remove();

                if (thisRoot.myLeft != null) {
                    fringe.add(thisRoot.myLeft);
                } else if (thisRoot.myRight != null) {
                    fringe.add(thisRoot.myRight);
                }

                return thisRoot;
            }
        }
    }


    private static class TreeNode {

        public Object myItem; // Root
        public TreeNode myLeft;
        public TreeNode myRight;

        public TreeNode(Object obj) {
            myItem = obj;
            myLeft = myRight = null;
        }


        public TreeNode(Object obj, TreeNode left, TreeNode right) {
            myItem = obj;
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


        private int height() {
            if (this.myLeft == null && this.myRight == null) {
                return 1;
            } else {
                int bestSoFar = 1;
                if (this.myRight != null) {
                    bestSoFar = Math.max(this.myRight.height()+1, bestSoFar);
                } else if (this.myLeft != null) {
                    bestSoFar = Math.max(this.myLeft.height()+1, bestSoFar);
                } else {
                    bestSoFar = Math.max(this.myLeft.height()+1, this.myRight.height()+1);
                }
                return bestSoFar;
            }
        }


        private boolean isCompletelyBalanced() {
            if (myLeft.height() == myRight.height()) {
                if ((myLeft.height() == 1) && (myRight.height()==1)) {
                    return true;
                } else {
                    return (myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced());
                }
            } else {
                return false;
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


        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }

    }
}
