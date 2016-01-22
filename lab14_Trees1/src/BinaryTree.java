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
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b"),  new TreeNode("c", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null));
    }

    public void fillSampleTree3() {
        myRoot = new TreeNode("A", new TreeNode("B"), new TreeNode("C"));
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

    public static void main(String[] args) {
        /*BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        t.fillSampleTree1();
        print(t, "sample tree 1");
        t.fillSampleTree2();
        print(t, "sample tree 2");*/

        BinaryTree t3 = new BinaryTree();
        t3.fillSampleTree3();
        //print(t3, "sample tree 3");
        System.out.println(t3.height());
        //System.out.println(t3.isCompletelyBalanced());
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
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

        // Computes the height of the binary tree
        private int height() {
            // for a leaf node
            if (this.myLeft == null && this.myRight == null) {
                return 1;
            }
            // if it contains at least one child
            else {
                int bestSoFar = 1;
                // if it only contains right child
                if (this.myLeft == null) {
                    bestSoFar = Math.max(this.myRight.height()+1, bestSoFar);
                }
                // if it only contains left child
                else if (this.myRight == null) {
                    bestSoFar = Math.max(this.myLeft.height()+1, bestSoFar);
                }
                // if it contains both left and right child
                else {
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
    }
}
