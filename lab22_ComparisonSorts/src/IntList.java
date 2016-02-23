import java.awt.*;

public class IntList {

	ListNode myHead;
	ListNode myTail;
	int mySize;

	public IntList() {
		myHead = null;
		myTail = null;
		mySize = 0;
	}

	/**
	 * Constructs an IntList with one node. Head and tail are the same.
	 */
	public IntList(ListNode head) {
		myHead = myTail = head;
		mySize = 1;
	}

	/**
	 * Returns true if this list is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return mySize == 0;
	}

	/**
	 * Adds a new node with the given value to the front of this list.
	 */
	public void addToFront(int k) {
		if (myHead == null) {
			myHead = myTail = new ListNode(k);
		} else {
			myHead = new ListNode(k, null, myHead);
			myHead.myNext.myPrev = myHead;
		}
		mySize++;
	}

	/**
	 * Adds a new node with the given value to the end of this list.
	 */
	public void addToEnd(int k) {
		if (myHead == null) {
			myHead = myTail = new ListNode(k);
		} else {
			myTail.myNext = new ListNode(k, myTail, null);
			myTail = myTail.myNext;
		}
		mySize++;
	}

	/**
	 * Attaches the input list to the end of this list.
	 */
	public void append(IntList list) {
		if (list.isEmpty()) {
			return;
		}
		if (isEmpty()) {
			myHead = list.myHead;
			myTail = list.myTail;
			mySize = list.mySize;
			return;
		}
		myTail.myNext = list.myHead;
		list.myHead.myPrev = myTail;
		myTail = list.myTail;
		mySize += list.mySize;
	}

	/**
	 * Removes the node reference by p from this list.
	 */
	private void remove(ListNode p) {
		if (myHead == myTail) {
			myHead = myTail = null;
		} else if (p == myHead) {
			myHead = myHead.myNext;
			myHead.myPrev = null;
		} else if (p == myTail) {
			myTail = myTail.myPrev;
			myTail.myNext = null;
		} else {
			p.myNext.myPrev = p.myPrev;
			p.myPrev.myNext = p.myNext;
		}
	}

	@Override
	public String toString() {
		String s = "";
		for (ListNode p = myHead; p != null; p = p.myNext) {
			s = s + p.myItem + " ";
		}
		return s;
	}

	private class ListNode {

		int myItem;
		ListNode myPrev;
		ListNode myNext;

		public ListNode(int k) {
			myItem = k;
			myPrev = myNext = null;
		}

		public ListNode(int k, ListNode prev, ListNode next) {
			myItem = k;
			myPrev = prev;
			myNext = next;
		}

        public void setLinkNext(ListNode next) {
            myNext = next;
        }

        public void setLinkPrev(ListNode prev) {
            myPrev = prev;
        }

        public ListNode getLinkNext() {
            return myNext;
        }

        public ListNode getLinkPrev() {
            return myPrev;
        }


	}

	/**
	 * Returns the result of sorting the values in this list using the insertion
	 * sort algorithm. This list is no longer usable after this operation; you
	 * have to use the returned list.
	 */
	public IntList insertionSort() {
		ListNode soFar = null;
		for (ListNode current = myHead; current != null; current = current.myNext) {
			soFar = insert(current, soFar);
		}
        //System.out.println("soFar1: " + soFar.myItem);
        //System.out.println("soFar2: " + soFar.myNext.myItem);
        //System.out.println("soFar3: " + soFar.myNext.myNext.myItem);
		return new IntList(soFar);
	}

	/**
	 * Inserts the node p into the list headed by head so that the node values
	 * are in increasing order.
     * The first p items are in order..?
	 */
	private ListNode insert(ListNode current, ListNode insertionPointer) {
        ListNode prev;
        for (prev = current; prev != null && prev.getLinkPrev() != null; prev = prev.getLinkPrev()) {
            if (prev.myItem < prev.getLinkPrev().myItem) {
                // excluding the current itself
                insertionPointer = swap(prev, prev.getLinkPrev());
            }
        }

        System.out.println();
        return insertionPointer;
	}

    public ListNode swap(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null) {
            throw new IllegalArgumentException("The nodes should be something");
        }

        if (node1 == node2) {
            return node1;
        }

        // make sure node1 -> node2
        if (node1.getLinkPrev() == node2) {
            System.out.println("node1.getLinkPrev() == node2");
            ListNode tmp = node2;
            node2 = node1;
            node1 = tmp;
        }

        // node1 -> node2
        System.out.println("swapping");
        ListNode node1Prev = node1.getLinkPrev();
        ListNode node1Next = node1.getLinkNext();
        ListNode node2Prev = node2.getLinkPrev();
        ListNode node2Next = node2.getLinkNext();

        // 1) node1 => node2Next
        node1.setLinkNext(node2Next);
        System.out.println("node1: "+node1.myItem);
        System.out.println("node2: "+node2.myItem);
        if(node2Next != null) {
            System.out.println("node2Next, "+node2Next.myItem+ " is not null");
            System.out.println("node2Next: " + node2Next.myItem + " is next to node1 " + node1.myItem);
            // 2) node1 <- node2Next
            node2Next.setLinkPrev(node1);
        }

        // 3) node1Prev <- node2
        node2.setLinkPrev(node1Prev);
        if (node1Prev != null) {
            // 4) node1Prev => node2
            System.out.println("node1Prev, "+node1Prev.myItem+ " is not null");
            System.out.println("node1Prev: " + node1Prev.myItem + " is prev to node2 " + node2.myItem);
            node1Prev.setLinkNext(node2);
        }

        // if node1 and node2 is right next to each other
        if (node1.myItem == node2Prev.myItem) {
            System.out.println("node1 and node2 are right next to each other");
            // 5) node2 <- node1
            node1.setLinkPrev(node2);
            System.out.println("node2: " + node2.myItem + " is prev to node1 " + node1.myItem);
            // 6) node2 => node1
            node2.setLinkNext(node1);
        } else {
            // 7) node2Prev <- node1
            node1.setLinkPrev(node2Prev);
            if (node2Prev != null) {
                // 8) node2Prev => node1
                System.out.println("node2Prev: " + node2Prev.myItem + " is prev to node1 " + node1.myItem);
                node2Prev.setLinkNext(node1);
            }
            // 9) node2 => node1Next
            node2.setLinkNext(node1Next);
            if (node1Next != null) {
                // 10) node2 <- node1Next
                System.out.println("node1Next: " + node1Next.myItem + " is next to node1 " + node2.myItem);
                node1Next.setLinkPrev(node2);
            }
        }

        if (node1 == myHead) {
            myHead = node2;
        } else if (node2 == myHead) {
            myHead = node1;
        }
        return node2;
    }

	/**
	 * Sorts this list using the selection sort algorithm.
	 */
	public void selectionSort() {
		IntList sorted = new IntList();
		while (myHead != null) {
			int minSoFar = myHead.myItem;
			ListNode minPtr = myHead;
			// Find the node in the list pointed to by myHead
			// whose value is largest.
			for (ListNode p = myHead; p != null; p = p.myNext) {
				if (p.myItem < minSoFar) {
					minSoFar = p.myItem;
					minPtr = p;
				}
			}
			sorted.addToEnd(minSoFar);
			remove(minPtr);
		}
		myHead = sorted.myHead;
	}

	/**
	 * Returns the result of sorting the values in this list using the Quicksort
	 * algorithm. This list is no longer usable after this operation.
	 */
	public IntList quicksort() {
		if (mySize <= 1) {
			return this;
		}
		// Assume first element is the divider.
		IntList smallElements = new IntList();
		IntList largeElements = new IntList();
		int divider = myHead.myItem;
		// TODO your code here!

		return null;
	}

	/**
	 * Returns the result of sorting the values in this list using the merge
	 * sort algorithm. This list is no longer usable after this operation.
	 */
	public IntList mergeSort() {
		if (mySize <= 1) {
			return this;
		}
		IntList oneHalf = new IntList();
		IntList otherHalf = new IntList();
		// TODO your code here!
		return null;
	}

	/**
	 * Returns the result of merging the two sorted lists / represented by list1
	 * and list2.
	 */
	private static IntList merge(ListNode list1, ListNode list2) {
		IntList rtn = new IntList();
		while (list1 != null && list2 != null) {
			if (list1.myItem < list2.myItem) {
				rtn.addToEnd(list1.myItem);
				list1 = list1.myNext;
			} else {
				rtn.addToEnd(list2.myItem);
				list2 = list2.myNext;
			}
		}
		while (list1 != null) {
			rtn.addToEnd(list1.myItem);
			list1 = list1.myNext;
		}
		while (list2 != null) {
			rtn.addToEnd(list2.myItem);
			list2 = list2.myNext;
		}
		return rtn;
	}

	/**
	 * Returns a random integer between 0 and 99.
	 */
	private static int randomInt() {
		return (int) (100 * Math.random());
	}

	public static void main(String[] args) {
		IntList values;
		IntList sortedValues;
		/*values = new IntList();
		System.out.print("Before selection sort: ");
		for (int k = 0; k < 10; k++) {
			values.addToFront(randomInt());
		}
		System.out.println(values);
		values.selectionSort();
		System.out.print("After selection sort: ");
		System.out.println(values);*/

		values = new IntList();
		System.out.print("Before insertion sort: ");
		for (int k = 0; k < 10; k++) {
			values.addToFront(randomInt());
		}
		System.out.println(values);
		sortedValues = values.insertionSort();
		System.out.print("After insertion sort: ");
		System.out.println(sortedValues);
        
        /*values.addToEnd(5);
        values.addToEnd(4);
        values.addToEnd(3);*/

		/*values = new IntList();
		System.out.print("Before quicksort: ");
		for (int k = 0; k < 10; k++) {
			values.addToFront(randomInt());
		}
		System.out.println(values);
		sortedValues = values.quicksort();
		System.out.print("After quicksort: ");
		System.out.println(sortedValues);

		values = new IntList();
		System.out.print("Before merge sort: ");
		for (int k = 0; k < 10; k++) {
			values.addToFront(randomInt());
		}
		System.out.println(values);
		sortedValues = values.mergeSort();
		System.out.print("After merge sort: ");
		System.out.println(sortedValues);*/
	}
}
