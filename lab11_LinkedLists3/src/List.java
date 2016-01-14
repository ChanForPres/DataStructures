import java.util.Iterator;
import java.util.Objects;

/**
 * Created by EunjinCho on 10/20/15.
 */


public class List implements Iterable {

    private ListNode myHead;
    private int mySize;
    private ListNode myTail;

    public List() {
        myHead = null;
        mySize = 0;
        myTail = null;
    }

    public boolean isEmpty() {
        return myHead == null;
    }

    @Override
    public Iterator iterator() {
        return new ElementIterator();
    }

    public class ElementIterator implements Iterator {

        int curIndex;
        int sequenceSize;

        public ElementIterator() {
            curIndex = 0;
            sequenceSize = size();
        }

        @Override
        public boolean hasNext() {
            return (curIndex<sequenceSize);
        }

        @Override
        public Object next() {
            Object toRtn = get(curIndex);
            curIndex++;
            return toRtn;
        }
    }

    private static class ListNode {

        private Object myItem;
        private ListNode myNext;

        private ListNode (Object item, ListNode next) {
            myItem = item;
            myNext = next;
        }

        private ListNode (Object item) {
            myItem = item;
            myNext = null;
        }

    }

    public String toString() {
        String rtn = "( ";
        for (ListNode p = myHead; p != null; p = p.myNext) {
            rtn = rtn + p.myItem + " ";
        }
        return rtn + ")";
    }

    /*
    // Return the number of items in this list ("length" in Scheme).
    public int size() {
        int rtn = 0;
        for (ListNode p = myHead; p != null; p = p.myNext) {
            rtn++;
        }
        return rtn;
    }
    */

    public int size() {
        return mySize;
    }

    public boolean isOK() {
        // check if mySize stored the right value
        int realSize = 0;
        for (ListNode p = myHead; p != null; p = p.myNext) {
            realSize++;
        }

        // check if all myItem objects are non-null
        int checkNull = 0;
        boolean checkNullBool;
        ListNode head = myHead;
        while (head != null) {
            head = head.myNext;
            checkNull++;
        }
        if (checkNull == realSize) {
            checkNullBool = true;
        } else {
            checkNullBool = false;
        }

        // either both myHead&myTail are null
        // or myTail is a reference to the last node in the list
        // whose first node is the node that myHead refers to
        boolean headTailBool;
        ListNode head2 = myHead;
        while (head2.myNext != null) {
            head2 = head2.myNext;
        }
        if (myHead == null && myTail == null) {
            headTailBool = true;
        } else if (head2.myItem == myTail) {
            headTailBool = true;
        } else {
            headTailBool = false;
        }

        if ((realSize == mySize) && (checkNullBool == true) && (headTailBool == true)) {
            return true;
        } else {
            return false;
        }
    }

    // Return true if the list contains the object
    public boolean contains (Object obj) {
        for (ListNode p = myHead; p != null; p = p.myNext) {
            if (obj.equals(p.myItem)) {
                return true;
            }
        }
        return false;
    }

    // Returns the element at the given position in this list.
    public Object get (int pos) {
        if (pos < 0) {
            throw new IllegalArgumentException (
                    "Argument to get must be at least 0.");
        }
        if (pos >= size()) {
            throw new IllegalArgumentException ("Argument to get is too large.");
        }
        int k = 0;
        for (ListNode p = myHead; p != null; p = p.myNext) {
            if (k == pos) {
                return p.myItem;
            }
            k++;
        }
        return null;
    }

    /*
    public void addToFront (Object obj) {
        myHead = new ListNode(obj, myHead);
    }
    */

    public void addToFront(Object obj) {
        myHead = new ListNode(obj, myHead);
        myTail = myHead;
        mySize++;
    }

    public boolean equals (Object obj) {
        return (this.toString().equals(obj.toString()));
    }

    /*
    // Old version without references
    public void add(Object x) {
        if (this.isEmpty()) {
            this.myHead = new ListNode(x);
            return;
        } else {
            ListNode p = myHead;
            while (p != null) {
                if (p.myNext == null) {
                    p.myNext = new ListNode(x);
                    break;
                }
                p = p.myNext;
            }
        }
    }
    */

    public void add(Object x) {
        // Destructive add method
        if (this.isEmpty()) {
            ListNode newX = new ListNode(x);
            this.myHead = newX;
            mySize++;
            this.myTail = newX;
            return;
        } else {
            ListNode t = this.myTail;
            ListNode newX = new ListNode(x);
            t.myNext = newX;
            this.myTail = newX;
            mySize++;
        }
    }

    /*
    // Old version without references
    public void appendInPlace (List l) {
        if (this.isEmpty()) {
            this.myHead = l.myHead;
            return;
        } else {
            ListNode p = myHead;
            while (p != null) {
                if (p.myNext == null) {
                    p.myNext = l.myHead;
                    break;
                }
                p = p.myNext;
            }
        }
    }*/

    public void appendInPlace(List l) {
        if (this.isEmpty()) {
            this.myHead = l.myHead;
            this.myTail = l.myTail;
            mySize += l.mySize;
            return;
        } else {
            ListNode t = this.myTail;
            t.myNext = l.myHead;
            if (l.isEmpty()) {
                this.myTail = this.myTail;
            } else {
                this.myTail = l.myTail;
            }
            mySize += l.size();
        }
    }

    public void remove(Object obj) {
        for (ListNode p = myHead; p.myNext != null; p = p.myNext) {
            // if the next item is the obj that we are looking for
            if (p.myNext.myItem.equals(obj)) {
                // if the obj is located at the tail
                if (p.myNext.myNext == null) {
                    p.myNext = null;
                    break;
                } else {
                    // Shifts all the values by one
                    for (ListNode p2 = p.myNext; p2.myNext != null; p2 = p2.myNext) {
                        p2.myItem = p2.myNext.myItem;
                    }
                    // Handles the last item.
                    // Since all the myItems are shifted, we need to take care of the last item that is useless
                    for (ListNode p3 = p.myNext; p3.myNext != null; p3 = p3.myNext) {
                        if (p3.myNext.myNext == null) {
                            p3.myNext = null;
                            break;
                        }
                    }
                }
            }
            // Double check
            else if (p.myItem.equals(obj)) {
                for (ListNode p2 = p; p2.myNext != null; p2 = p2.myNext) {
                    p2.myItem = p2.myNext.myItem;
                }
                for (ListNode p3 = p.myNext; p3.myNext != null; p3 = p3.myNext) {
                    if (p3.myNext.myNext == null) {
                        p3.myNext = null;
                        break;
                    }
                }
            }
        }
    }

    public void doubleInPlace() {
        ListNode temp;
        for (ListNode p = myHead; p != null; p = p.myNext.myNext) {
            temp = p.myNext;
            p.myNext = new ListNode(p.myItem);
            p.myNext.myNext = temp;
        }
    }

    // Helpful printing method
    public static void printAllNodes(ListNode listNode) {
        for (ListNode p = listNode; p != null; p = p.myNext) {
            System.out.print("previous node: " + p.myItem.toString() + " -- ");
        }
        System.out.println();
    }

    public void reverse() {
        //this.myHead = recursiveReverseHelper(this.myHead, null);
        this.myHead = iterativeReverseHelper(this.myHead);
    }


    // Recursive way of reversing the linked list
    private static ListNode recursiveReverseHelper(ListNode currentNode, ListNode previousNode) {
        if (currentNode == null) {
            return previousNode;
        } else {
            ListNode nextNode = currentNode.myNext;
            currentNode.myNext = previousNode;
            previousNode = currentNode;
            return recursiveReverseHelper(nextNode, previousNode);
        }
    }




    // Iterative way of reversing the linked list
    private static ListNode iterativeReverseHelper(ListNode head) {
        ListNode p, soFar;
        // p plays the role of currentNode in the previous version.
        for (p = head, soFar = null; p != null;) {
            ListNode nextNode = p.myNext;
            p.myNext = soFar;
            soFar = p;
            p = nextNode;
        }
        return soFar;
    }

}
