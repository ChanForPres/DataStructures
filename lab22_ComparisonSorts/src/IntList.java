import java.awt.*;
import java.util.zip.ZipEntry;

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
        // for quicksort partitioning
        boolean isOpen;
        boolean lastClosed;

        public ListNode() {
            myPrev = myNext = null;
        }

		public ListNode(int k) {
			myItem = k;
			myPrev = myNext = null;
            lastClosed = false;
            isOpen = false;
		}

		public ListNode(int k, ListNode prev, ListNode next) {
			myItem = k;
			myPrev = prev;
			myNext = next;
            lastClosed = false;
            isOpen = false;
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
                insertionPointer = swap(prev, prev.getLinkPrev());
            }
        }

        return insertionPointer;
	}

    public ListNode swapP(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null) {
            throw new IllegalArgumentException("The nodes should be something");
        }

        if (node1 == node2) {
            return node1;
        }

        // make sure node1 -> node2
        if (node1.getLinkPrev() != null) {
            if (node1.getLinkPrev() == node2) {
                //System.out.println("node1.getLinkPrev() == node2");
                ListNode tmp = node2;
                node2 = node1;
                node1 = tmp;
            }
        }

        // node1 -> node2
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
            System.out.println("node2Prev: " + node2Prev.myItem + " is prev to node1 " + node1.myItem);
            if (node2Prev != null) {
                // 8) node2Prev => node1
                System.out.println("node1: " + node1.myItem + " is next to node1 " + node2Prev.myItem);
                node2Prev.setLinkNext(node1);
            }
            // 9) node2 => node1Next
            node2.setLinkNext(node1Next);
            System.out.println("node1Next: " + node1Next.myItem + " is next to node1 " + node2.myItem);
            if (node1Next != null) {
                // 10) node2 <- node1Next
                System.out.println("node2: " + node2.myItem + " is prev to node1Next " + node1Next.myItem);
                node1Next.setLinkPrev(node2);
            }
        }
        System.out.println("node1.myItem: " + (node1 == node2.myNext.myNext));
        System.out.println("node hereherehere: " + node2.myItem + node2.myNext.myItem + node2.myNext.myNext.myItem +node2.myNext.myNext.myNext.myItem );

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
        // create a new sorted collection
		IntList sorted = new IntList();
		while (myHead != null) {
			int minSoFar = myHead.myItem;
			ListNode minPtr = myHead;
			// Find the node in the list pointed to by myHead
			// whose value is smallest.
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

        IntList quickSort = new IntList();
        for (ListNode l = myHead; l != null; l = l.myNext) {
            quickSort.addToEnd(l.myItem);
        }
        IntList toRtn = recQuicksort(quickSort, quickSort.myHead, quickSort.myTail);
        return toRtn;

        /*ListNode quickSorted = quicksortFail(myHead);

        IntList finalRtn = new IntList();
        for (ListNode l = quickSorted; l != null; l = l.myNext) {
            finalRtn.addToEnd(l.myItem);
        }
        return finalRtn;*/
	}

    public ListNode swap(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null) {
            throw new IllegalArgumentException("The nodes should be something");
        }

        if (node1 == node2) {
            return node1;
        }

        // make sure node1 -> node2
        if (node1.getLinkPrev() != null) {
            if (node1.getLinkPrev() == node2) {
                //System.out.println("node1.getLinkPrev() == node2");
                ListNode tmp = node2;
                node2 = node1;
                node1 = tmp;
            }
        }

        // node1 -> node2
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
            System.out.println("node2Prev: " + node2Prev.myItem + " is prev to node1 " + node1.myItem);
            if (node2Prev != null) {
                // 8) node2Prev => node1
                System.out.println("node1: " + node1.myItem + " is next to node1 " + node2Prev.myItem);
                node2Prev.setLinkNext(node1);
            }
            // 9) node2 => node1Next
            node2.setLinkNext(node1Next);
            System.out.println("node1Next: " + node1Next.myItem + " is next to node1 " + node2.myItem);
            if (node1Next != null) {
                // 10) node2 <- node1Next
                System.out.println("node2: " + node2.myItem + " is prev to node1Next " + node1Next.myItem);
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

    private ListNode quicksortFail(ListNode head) {

        // base case
        if (head == null || head.getLinkNext() == null) {
            return head;
        }

        int divider = head.myItem;
        ListNode cur = head;
        ListNode toRtn = head;
        // Partitioning process around a divider element
        while(cur != null) {
            cur.isOpen = true;
            // if current card is less than the divider
            if (cur.myItem < divider) {
                ListNode leftMostOpen = findLeftMostOpen(toRtn);
                ListNode lastClosedNode = findLastClosed(toRtn);
                if (lastClosedNode != null) {
                    lastClosedNode.lastClosed = false;
                }
                leftMostOpen.lastClosed = true;
                leftMostOpen.isOpen = false;
                toRtn = swap(leftMostOpen, cur);
                cur = leftMostOpen;
            }
            cur = cur.getLinkNext();
        }
        ListNode lastClosedNode = findLastClosed(toRtn);
        ListNode pivotNode = findPivot(head, toRtn.myItem);
        if (lastClosedNode != null) {
            toRtn = swap(lastClosedNode, pivotNode);
        }
        // Finally getting the partitioned list
        ListNode recurse;
        for (recurse = toRtn; recurse != null; recurse = recurse.getLinkPrev()) {
            toRtn = recurse;
        }
        // Now reset to all false to be used in recursion
        for (ListNode l = toRtn; l != null; l = l.getLinkNext()) {
            l.isOpen = false;
            l.lastClosed = false;
        }
        // Split the list into two parts based on the pivot
        ListNode small = toRtn;
        if (small.getLinkNext() != null) {
            while (small.getLinkNext() != pivotNode) {
                if (small == pivotNode) {
                    break;
                }
                small = small.getLinkNext();
            }
        }
        ListNode smallElements = toRtn;
        ListNode largeElements = pivotNode.myNext;
        small.setLinkNext(null);

        // having two parts, recursively split them
        ListNode l1 = quicksortFail(smallElements);
        ListNode l2 = quicksortFail(largeElements);

        // merge together
        IntList merged = merge(l1, l2);

        return merged.myHead;
    }

    private IntList recQuicksort(IntList quickSortList, ListNode head, ListNode tail) {
        System.out.println("quickSortList 111: " + quickSortList);
        System.out.println("head: " + head.myItem);
        System.out.println("tail: " + tail.myItem);

        if (head.myItem == tail.myItem) {
            return new IntList(head);
        }
        int divider = head.myItem;
        ListNode cur = head;
        ListNode toRtn = head;

        // Setting tail
        ListNode tailUsed;
        if (tail.getLinkNext() != null) {
            tailUsed = tail.getLinkNext();
        } else {
            tailUsed = null;
        }

        // partitioning process around a divider element
        while (cur != tailUsed) {
            if (tailUsed != null && cur.myItem == tailUsed.myItem) {
                break;
            }
            if (tailUsed != null) {
                System.out.println("tailUsed: " + tailUsed.myItem);
            }
            System.out.println("divider: " + divider);

            cur.isOpen = true;
            if (cur.myItem < divider) {
                ListNode leftMostOpen = findLeftMostOpen(toRtn);
                ListNode lastClosedNode = findLastClosed(toRtn);
                if (lastClosedNode != null) {
                    lastClosedNode.lastClosed = false;
                }
                leftMostOpen.lastClosed = true;
                leftMostOpen.isOpen = false;
                System.out.println("leftMostOpen: " + leftMostOpen.myItem + " cur: " +cur.myItem + " in list: " + quickSortList);
                toRtn = swap(leftMostOpen, cur);
                System.out.println("quickSortList 222: " + quickSortList);
                System.out.println();

                cur = leftMostOpen;
            }
            cur = cur.getLinkNext();
        }
        ListNode lastClosedNode = findLastClosed(toRtn);
        ListNode pivotNode = findPivot(toRtn, divider);
        if (lastClosedNode != null) {
            toRtn = swap(lastClosedNode, pivotNode);
        }
        for (ListNode past = toRtn; past != null; past = past.getLinkPrev()) {
            toRtn = past;
        }
        quickSortList = convertToIntList(toRtn);
        System.out.println("quickSortList 444: " + quickSortList);
        System.out.println("pivotNode: " +pivotNode.myItem);
        System.out.println();
        IntList l1 = recQuicksort(quickSortList, quickSortList.myHead, pivotNode);
        IntList l2 = recQuicksort(quickSortList, pivotNode, tail);
        l1.addToEnd(pivotNode.myItem);
        quickSortList = merge(l1.myHead, l2.myHead);
        return quickSortList;
    }

    private IntList convertToIntList(ListNode toRtn) {
        IntList finalRtn = new IntList();
        for (ListNode l = toRtn; l != null; l = l.myNext) {
            finalRtn.addToEnd(l.myItem);
        }
        return finalRtn;
    }


    private ListNode findPivot(ListNode quickSortList, int divider) {
        for (ListNode cur = quickSortList; cur != null; cur = cur.myNext) {
            if (cur.myItem == divider) {
                return cur;
            }
        }
        return null;
    }

    private ListNode findLastClosed(ListNode quickSortList) {
        for (ListNode cur = quickSortList; cur != null; cur = cur.getLinkNext()) {
            if (cur.lastClosed) {
                return cur;
            }
        }
        return null;
    }

    private ListNode findLeftMostOpen(ListNode quickSortList) {
        for (ListNode cur = quickSortList; cur != null; cur = cur.getLinkNext()) {
            if (cur.isOpen == true) {
                return cur;
            }
        }
        return quickSortList;
    }

    private ListNode findPivot(IntList quickSortList, int divider) {
        for (ListNode cur = quickSortList.myHead; cur != null; cur = cur.myNext) {
            if (cur.myItem == divider) {
                return cur;
            }
        }
        return null;
    }

    private ListNode findLastClosed(IntList quickSortList) {
        for (ListNode cur = quickSortList.myHead; cur != null; cur = cur.getLinkNext()) {
            if (cur.lastClosed) {
                return cur;
            }
        }
        return null;
    }

    private ListNode findLeftMostOpen(IntList quickSortList) {
        for (ListNode cur = quickSortList.myHead; cur != null; cur = cur.getLinkNext()) {
            if (cur.isOpen == true) {
                return cur;
            }
        }
        return quickSortList.myHead;
    }

    /**
	 * Returns the result of sorting the values in this list using the merge
	 * sort algorithm. This list is no longer usable after this operation.
	 */
	public IntList mergeSort() {
		if (mySize <= 1) {
			return this;
		}
		//IntList oneHalf = new IntList();
		//IntList otherHalf = new IntList();

        ListNode toRtn = mergeSplit(myHead);
        IntList finalRtn = new IntList(toRtn);
        return finalRtn;
    }

    private ListNode mergeSplit(ListNode head) {
        // no element or only one element (base case)
        if (head == null || head.getLinkNext() == null) {
            return head;
        }
        // Divide the linked list into two halves
        else {
            ListNode slow = head;
            ListNode fast = head.getLinkNext();
            while (fast != null) {
                fast = fast.getLinkNext();
                if (fast != null) {
                    slow = slow.getLinkNext();
                    fast = fast.getLinkNext();
                }
            }
            ListNode oneHalf = head;
            ListNode otherHalf = slow.getLinkNext();
            slow.setLinkNext(null);
            // having two parts, recursively split them
            ListNode l1 = mergeSplit(oneHalf);
            ListNode l2 = mergeSplit(otherHalf);

            // merge together
            IntList merged = merge(l1, l2);

            return merged.myHead;
        }
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

		/*values = new IntList();
		System.out.print("Before insertion sort: ");
		for (int k = 0; k < 10; k++) {
			values.addToFront(randomInt());
		}
		System.out.println(values);
		sortedValues = values.insertionSort();
		System.out.print("After insertion sort: ");
		System.out.println(sortedValues);*/

        /*values.addToEnd(5);
        values.addToEnd(4);
        values.addToEnd(3);*/

		values = new IntList();
		System.out.print("Before quicksort: ");
		//for (int k = 0; k < 10; k++) {
        //    values.addToFront(randomInt());
		//}
        //values.addToEnd(1);
        values.addToEnd(5);
        values.addToEnd(3);
        values.addToEnd(2);
        values.addToEnd(7);
		System.out.println(values);
		sortedValues = values.quicksort();
		System.out.print("After quicksort: ");
		System.out.println(sortedValues);

		/*values = new IntList();
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
