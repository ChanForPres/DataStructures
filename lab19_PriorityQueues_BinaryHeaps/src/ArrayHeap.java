import java.util.ArrayList;

/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value. Why do it this way? It
 * will be useful later on in the class...
 */

public class ArrayHeap<T> {
	private ArrayList<Node> contents = new ArrayList<Node>();

	/**
	 * Inserts an item with the given priority value. This is enqueue, or offer.
	 */
	public void insert(T item, double priority) {
        Node rootNode = this.getNode(1);
        if (rootNode != null) {
            insertInSub(1, item, priority);
        } else {
            Node newRoot = new Node(item, priority);
            this.setNode(1, newRoot);
        }
    }

    // Recursive method to insert
    private void insertInSub(int index, T item, double priority) {
        Node subRootNode = this.getNode(index);
        int leftIndex = this.getLeftOf(index);
        // if left is not empty, getRight
        if (getNode(leftIndex) != null) {
            int rightIndex = this.getRightOf(index);
            if (getNode(rightIndex) != null) {
                insertInSub(leftIndex, item, priority);
            } else {
                Node newRight = new Node(item, priority);
                this.setNode(rightIndex, newRight);
                this.bubbleUp(rightIndex);
            }
        }
        // if left is empty, temporarily place the new item there
        else {
            Node newLeft = new Node(item, priority);
            this.setNode(leftIndex, newLeft);
            // Compare if this newly created node is bigger than its parent
            this.bubbleUp(leftIndex);
        }
    }

    /**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {
		// TODO Complete this method!
		return null;
	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
		// TODO Complete this method!
		return null;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		Node rootNode = getNode(1);
        if (rootNode != null) {
            changePriorHelper(1, item, priority);
        } else {
            throw new IllegalStateException("The tree is not constructed");
        }
	}

    private void changePriorHelper(int index, T item, double priority) {
        Node subTreeRoot = getNode(index);
        int leftIdx = this.getLeftOf(index);
        int rightIdx = this.getRightOf(index);
        // base case
        if (subTreeRoot.myItem.equals(item)) {
            subTreeRoot.myPriority = priority;
            this.bubbleDown(1);
            return;
        } else if (this.getNode(leftIdx) != null) {
            changePriorHelper(leftIdx, item, priority);
        } else if (this.getNode(rightIdx) != null) {
            changePriorHelper(rightIdx, item, priority);
        }
    }

    /**
	 * Prints out the heap sideways.
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
	 * Swap the nodes at the two indices.
	 */
	private void swap(int index1, int index2) {
		Node node1 = getNode(index1);
		Node node2 = getNode(index2);
		this.contents.set(index1, node2);
		this.contents.set(index2, node1);
	}

	/**
	 * Returns the index of the node to the left of the node at i.
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
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
        // Compare if this newly created node is bigger than its parent, if so, swap
        Node curNode = this.getNode(index);
        int parentIdx = this.getParentOf(index);
        Node parentNode = this.getNode(parentIdx);
        if (curNode.myPriority > parentNode.myPriority) {
            this.swap(index, parentIdx);
        }
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
        // Compare if the current node is smaller than any of its children
        // Figure out even smaller one of the left/right and swap
        Node curNode = this.getNode(index);
        int leftIdx = this.getLeftOf(index);
        int rightIdx = this.getRightOf(index);
        Node leftNode = this.getNode(leftIdx);
        Node rightNode = this.getNode(rightIdx);
        if (leftNode == null && rightNode == null) {
            return;
        }
        else if (curNode.myPriority > leftNode.myPriority && curNode.myPriority > rightNode.myPriority) {
            return;
        }
        // bubble down to the left
        else if (curNode.myPriority < leftNode.myPriority || curNode.myPriority < rightNode.myPriority) {
            if (leftNode.myPriority < rightNode.myPriority) {
                swap(rightIdx, index);
                bubbleDown(rightIdx);
            }
            // bubble down to the right
            else {
                swap(leftIdx, index);
                bubbleDown(leftIdx);
            }
        }
	}

	/**
	 * Returns the index of the node with smaller priority. Precondition: Not
	 * both of the nodes are null.
	 */
	private int min(int index1, int index2) {
		Node node1 = getNode(index1);
		Node node2 = getNode(index2);
		if (node1 == null) {
			return index2;
		} else if (node2 == null) {
			return index1;
		} else if (node1.myPriority < node2.myPriority) {
			return index1;
		} else {
			return index2;
		}
	}

	public class Node {
		private T myItem;
		private double myPriority;

		private Node(T item, double priority) {
			myItem = item;
			myPriority = priority;
		}

		public T item() {
			return myItem;
		}

		public double priority() {
			return myPriority;
		}

		@Override
		public String toString() {
			return item().toString() + ", " + priority();
		}
	}

	public static void main(String[] args) {
		ArrayHeap<String> heap = new ArrayHeap<String>();
		heap.insert("c", 3);
        heap.insert("i", 9);
		heap.insert("d", 4);
		heap.insert("a", 1);
        heap.insert("g", 7);
		/*heap.insert("h", 8);
		heap.insert("e", 5);
		heap.insert("b", 2);
		heap.insert("c", 3);
		heap.insert("d", 4);*/
        heap.changePriority("i", 2);
		System.out.println(heap);
	}

}
