import java.util.ArrayList;

/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value. Why do it this way? It
 * will be useful later on in the class...
 * Binary Min heap
 */

public class ArrayHeap<T> {
    protected ArrayList<Node> contents = new ArrayList<Node>();
    int size = 0;

    /**
     * Inserts an item with the given priority value. This is enqueue, or offer.
     */
    public void insert(T item, double priority, T pred) {
        Node rootNode = this.getNode(1);
        if (rootNode != null) {
            insertInSub(1, item, priority, pred);
        } else {
            Node newRoot = new Node(item, priority, pred);
            this.setNode(1, newRoot);
            size++;
        }
    }

    // Recursive method to insert
    private void insertInSub(int index, T item, double priority, T pred) {
        Node subRootNode = this.getNode(index);
        int leftIndex = this.getLeftOf(index);
        // if left is not empty, getRight
        if (getNode(leftIndex) != null) {
            int rightIndex = this.getRightOf(index);
            if (getNode(rightIndex) != null) {
                insertInSub(leftIndex, item, priority, pred);
            } else {
                Node newRight = new Node(item, priority, pred);
                this.setNode(rightIndex, newRight);
                size++;
                this.bubbleUp(rightIndex);
            }
        }
        // if left is empty, temporarily place the new item there
        else {
            Node newLeft = new Node(item, priority, pred);
            this.setNode(leftIndex, newLeft);
            size++;
            // Compare if this newly created node is bigger than its parent
            this.bubbleUp(leftIndex);
        }
    }

    /**
     * Returns the Node with the largest priority value, but does not remove it
     * from the heap.
     */
    public Node peek() {
        Node toRtn = this.getNode(1);
        return toRtn;
    }


    /**
     * Change the node in this heap with the given item to have the given
     * priority. For this method only, you can assume the heap will not have two
     * nodes with the same item. Check for item equality with .equals(), not ==
     */
    // problem when its (2, 10, 0)
    public void changePriority(T item, double priority, T pred) {
        Node rootNode = getNode(1);
        if (rootNode != null) {
            changePriorHelper(1, item, priority, pred);
        } else {
            throw new IllegalStateException("The tree is not constructed");
        }
    }

    private void changePriorHelper(int index, T item, double priority, T pred) {
        Node subTreeRoot = getNode(index);
        int leftIdx = this.getLeftOf(index);
        int rightIdx = this.getRightOf(index);
        // base case
        if (subTreeRoot.item() == null) {
            return;
        } else {
            if (this.getNode(leftIdx) != null) {
                if (this.getNode(leftIdx).myItem.equals(item)) {
                    this.getNode(leftIdx).myPriority = priority;
                    this.getNode(leftIdx).myPred = pred;
                    this.bubbleDown(1);
                    return;
                } else {
                    changePriorHelper(leftIdx, item, priority, pred);
                }
            } if (this.getNode(rightIdx) != null) {
                if (this.getNode(rightIdx).myItem.equals(item)) {
                    this.getNode(rightIdx).myPriority = priority;
                    this.getNode(rightIdx).myPred = pred;
                    this.bubbleDown(1);
                    return;
                } else {
                    changePriorHelper(rightIdx, item, priority, pred);
                }
            }
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

    public Node getNode(int index) {
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

    private int getSize() {
        return size;
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
        if (curNode.priority() < parentNode.priority()) {
            this.swap(index, parentIdx);
        }
    }


    /**
     * Returns the Node with the largest priority value, and removes it from
     * the heap. This is dequeue, or poll.
     */
    public Node removeMin() {
        Node toRtn = this.peek();
        swap(1, size);
        this.contents.remove(size);
        size--;
        this.bubbleDown(1);
        return toRtn;
    }


    /**
     * Bubbles down the node currently at the given index.
     */
    private void bubbleDown(int index) {
        // Compare if the current node is smaller than any of its children
        // Figure out even smaller one of the left/right and swap
        while (hasLeftChild(index)) {
            int left = getLeftOf(index);
            int right = getRightOf(index);
            Node curNode = this.getNode(index);
            Node leftNode = this.getNode(left);
            Node rightNode = this.getNode(right);
            Node child = leftNode;
            int childIdx = left;
            if (hasRightChild(index) && rightNode.priority() < leftNode.priority()) {
                child = rightNode;
                childIdx = right;
            }
            if (curNode.priority() > child.priority()) {
                swap(index, childIdx);
                index = childIdx;
            } else {
                break;
            }
        }
    }

    private boolean hasRightChild(int index) {
        return getRightOf(index) <= size;
    }

    private boolean hasLeftChild(int index) {
        return getLeftOf(index) <= size;
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
        } else if (node1.priority() < node2.priority()) {
            return index1;
        } else {
            return index2;
        }
    }

    public boolean contains(T myEndVertex) {
        Node rootNode = this.getNode(1);
        if (rootNode != null) {
            return containsHelper(1, myEndVertex);
        } else {
            throw new IllegalStateException("The tree is not constructed");
        }
    }

    private boolean containsHelper(int index, T myEndVertex) {
        Node subTreeRoot = getNode(index);
        int leftIdx = this.getLeftOf(index);
        int rightIdx = this.getRightOf(index);
        // base case
        if (subTreeRoot.myItem == null) {
            return false;
        } else {
            if (this.getNode(leftIdx) != null) {
                Node leftNode = getNode(leftIdx);
                if (leftNode.myItem.equals(myEndVertex)) {
                    return true;
                } else {
                    containsHelper(leftIdx, myEndVertex);
                }
            }
            if (this.getNode(rightIdx) != null) {
                Node rightNode = getNode(rightIdx);
                if (rightNode.myItem.equals(myEndVertex)) {
                    return true;
                } else {
                    containsHelper(rightIdx, myEndVertex);
                }
            }
        }
        return false;
    }

    /**
     * Find the distance for a given item
     */
    public double find(T item) {
        Node rootNode = this.getNode(1);
        if (rootNode != null) {
            return findHelper(1, item);
        } else {
            throw new IllegalStateException("The tree is not constructed");
        }
    }

    private double findHelper(int index, T item) {
        double inf = Double.POSITIVE_INFINITY;
        Node subTreeRoot = getNode(index);
        int leftIdx = this.getLeftOf(index);
        int rightIdx = this.getRightOf(index);
        // base case
        if (subTreeRoot.myItem == null) {
            throw new IllegalStateException("we couldn't find the item");
        } else {
            if (this.getNode(leftIdx) != null) {
                Node leftNode = getNode(leftIdx);
                if (leftNode.myItem.equals(item)) {
                    return leftNode.myPriority;
                } else {
                    containsHelper(leftIdx, item);
                }
            }
            if (this.getNode(rightIdx) != null) {
                Node rightNode = getNode(rightIdx);
                if (rightNode.myItem.equals(item)) {
                    return rightNode.myPriority;
                } else {
                    containsHelper(rightIdx, item);
                }
            }
        }
        return inf;
    }


    public class Node {
        private T myItem;
        private double myPriority;
        private T myPred;

        private Node(T item, double priority, T pred) {
            myItem = item;
            myPriority = priority;
            myPred = pred;
        }

        public T item() {
            return myItem;
        }

        public double priority() {
            return myPriority;
        }

        public T predecessor() {
            return myPred;
        }

        @Override
        public String toString() {
            return item().toString() + ", " + priority();
        }
    }

    public static void main(String[] args) {
        ArrayHeap<String> heap = new ArrayHeap<String>();
        heap.insert("c", 3, null);
        heap.insert("i", 9, null);
        heap.insert("d", 4, null);
        heap.insert("a", 1, null);
        heap.insert("g", 7, null);
        heap.changePriority("i", 2, null);
        System.out.println(heap);
        //heap.heapSort();
		/*heap.insert("h", 8);
		heap.insert("e", 5);
		heap.insert("b", 2);
		heap.insert("c", 3);
		heap.insert("d", 4);*/

    }

    /**
     * heap sort
     */
    public void heapSort() {
        ArrayList <Node> toRtn = new ArrayList<>();
        int size = this.contents.size() - 2;
        for (int i = 0; i < size; i++) {
            toRtn.add(this.removeMin());
        }
        for (int j = 0; j < size; j++) {
            System.out.println(toRtn.get(j));
        }
    }

}
