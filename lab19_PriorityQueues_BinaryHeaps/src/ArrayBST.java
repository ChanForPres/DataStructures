import java.util.ArrayList;

public class ArrayBST {

    // contents is the array that trees will be saved
    private ArrayList<Node> contents = new ArrayList<Node>();

    public void insert(int value) {
        Node root = this.getNode(1);
        if (root != null) {
			insertInSubtree(1, value);
        } else {
			Node newNode = new Node(value);
            this.setNode(1, newNode);
        }
    }

	/**
	 * IF root is not null
	 * Inserts a value into this subtree according to the BST invariants. This
	 * won't work until you complete the missing methods.
	 */
	private void insertInSubtree(int subTreeRootIndex, int value) {
		Node subTreeRoot = getNode(subTreeRootIndex);
		// is it already here in the tree?
		if (subTreeRoot.value() == value) {
			return;
		}
		// should it go in the left subtree?
		else if (value < subTreeRoot.value()) {

			// find the appropriate index to be placed
			int left = getLeftOf(subTreeRootIndex);
			// if that place is not empty
			// find another one
			if (getNode(left) != null) {
				insertInSubtree(left, value);
			}
			// if it's empty, set
			else {
				setLeft(subTreeRootIndex, new Node(value));
			}
		}
		// should it go in the right subtree?
		else {
			int right = getRightOf(subTreeRootIndex);

			if (getNode(right) != null) {
				insertInSubtree(right, value);
			} else {
				setRight(subTreeRootIndex, new Node(value));
			}
		}
	}

	/**
	 * Used to print out the tree sideways.
	 */
	@Override
	public String toString() {
		return toStringHelper(1, "");
	}

	/* Recursive helper method for toString. */
	private String toStringHelper(int index, String soFar) {
		if (getNode(index) == null) {
			return "";
		} else {
			String toReturn = "";
			int rightChild = getRightOf(index);
			toReturn += toStringHelper(rightChild, "        " + soFar);
			if (getNode(rightChild) != null) {
				toReturn += soFar + "    /";
			}
			toReturn += "\n" + soFar + getNode(index) + "\n";
			int leftChild = getLeftOf(index);
			if (getNode(leftChild) != null) {
				toReturn += soFar + "    \\";
			}
			toReturn += toStringHelper(leftChild, "        " + soFar);
			return toReturn;
		}
	}

    private Node getNode(int index) {
        if (index >= contents.size()) {
            return null;
        } else {
            return contents.get(index);
        }
    }

    private void setNode(int index, Node n) {
		// In the case that the ArrayList is not big enough
		// add null elements until it is the right size
        while (index + 1 >= contents.size()) {
            contents.add(null);
        }
        contents.set(index, n);
    }

	/**
	 * Returns the index of the node to the left of the node at i.
     * left means small and is at index 2n
	 */
	private int getLeftOf(int i) {
        return 2*i;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		return (2*i)+1;
	}

	/**
	 * Returns the index of the node that is the parent of the node at i.
	 */
	private int getParentOf(int i) {
        // if left child
		return i/2;
	}


	/**
	 * Adds the given node as a left child of the node at the given index.
	 */
	private void setLeft(int index, Node n) {
		this.setNode(index*2, n);
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
        this.setNode(index*2+1, n);
	}


    /**
     * Contains method that run time proportional to the height of the tree
     */
	public boolean contains (int value) {
        Node root = this.getNode(1);
        if (root != null) {
            return containsHelper(1, value);
        }
        // if there's nothing
        else {
            return false;
        }
    }

    private boolean containsHelper(int index, int value) {
        Node subTreeRoot = getNode(index);
        // found
        if (subTreeRoot.value() == value) {
            return true;
        }
        // left
        else if (value < subTreeRoot.value()) {
            int left = getLeftOf(index);
            if (getNode(left) != null) {
                return containsHelper(left, value);
            } else {
                return false;
            }
        }
        // right
        else {
            int right = getRightOf(index);
            if (getNode(right) != null) {
                return containsHelper(right, value);
            } else {
                return false;
            }
        }
    }


	public class Node {
        private int myValue;

		private Node(int value) {
			myValue = value;
        }

		public int value() {
			return myValue;
		}

		@Override
		public String toString() {
			return Integer.toString(myValue);
		}
    }


    public static void main(String[] args) {
        ArrayBST bst = new ArrayBST();
        bst.insert(9);
        bst.insert(8);
        bst.insert(7);
        bst.insert(6);
        bst.insert(11);
        bst.insert(10);
		System.out.println(bst);
        System.out.println(bst.contains(10)); //true
        System.out.println(bst.contains(6)); //true
        System.out.println(bst.contains(13)); //false
        System.out.println(bst.contains(3)); //false
    }


}
