import sun.invoke.empty.Empty;

import java.util.NoSuchElementException;

/**
 * Created by EunjinCho on 10/13/15.
 */


abstract public class AbstractListNode {
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public void setItem(Comparable newItem);
    abstract public void setNext(AbstractListNode newNext);
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int position);
    abstract public String toString();
    abstract public boolean equals(Object node);
    public abstract AbstractListNode add(Comparable c);
    public abstract AbstractListNode append(AbstractListNode list);


    public Comparable smallest() {
        if (isEmpty()) {
            throw new NoSuchElementException("Can't find smallest in empty list.");
        }
        return smallestHelper(this.item());
    }

    public Comparable smallestHelper(Comparable smallestSoFar) {
        AbstractListNode curList = this;
        if (curList.isEmpty()) {
            return smallestSoFar;
        } else {
            return this.next().smallestHelper(min(smallestSoFar, this.item()));
        }
    }

    // Useful *****
    public static Comparable min(Comparable c1, Comparable c2) {
        if (c1.compareTo(c2) < 0) {
            return c1;
        } else {
            return c2;
        }
    }

    public abstract AbstractListNode reverse();

    abstract public AbstractListNode appendInPlace(AbstractListNode list2);

    /*
     Remove an item at a given index in the linked list
     */
    public void remove(int index) {
        AbstractListNode curList = this;
        int i = 0;
        int j = 0;

        if (index == this.size() - 1) {
            while (i < index-1) {
                curList = curList.next();
                i++;
            }
            curList.setNext(new EmptyListNode());
        } else if (index != this.size() - 1) {
            while (i < index) {
                curList = curList.next();
                i++;
            }
            while (!(curList.next().isEmpty())) {
                curList.setItem(curList.get(j + 1));
                curList = curList.next();
            }
            AbstractListNode curList2 = this;
            while (j < this.size() - 2) {
                curList2 = curList2.next();
                j++;
            }
            curList2.setNext(new EmptyListNode());
        }
    }


    public static AbstractListNode merge(AbstractListNode aList, AbstractListNode bList) {
        // Handling all the empty list cases
        if (aList.isEmpty()) {
            return bList;
        } else if (bList.isEmpty()) {
            return aList;
        }
        // For any non-empty lists
        else {
            // Check if one list is totally smaller than another
            Comparable checkMinMacro = min(aList.get(aList.size() - 1), bList.item());
            if (checkMinMacro == aList.get(aList.size() - 1)) {
                aList = aList.append(bList);
                return aList;
            } else {
                AbstractListNode head;
                AbstractListNode temp;
                Comparable minOfA1B1 = min(aList.item(), bList.item());

                if (minOfA1B1 == aList.item()) {
                    head = aList;
                    System.out.println();
                } else {
                    head = bList;
                    bList = aList;
                    aList = head;
                    System.out.println();
                }
                while (!(aList.next().isEmpty()) && !(bList.isEmpty())) {
                    Comparable minOfA2B1 = min(aList.next().item(), bList.item());
                    if (minOfA2B1 == aList.next().item()) {
                        aList = aList.next();
                    } else {
                        temp = aList.next();
                        aList.setNext(bList);
                        bList = temp;
                    }
                }
                if (aList.next().isEmpty()) {
                    aList.setNext(bList);
                }
                return head;
            }
        }
    }
}

class NonemptyListNode extends AbstractListNode {

    private Comparable myItem;
    private AbstractListNode myNext;
    private int sizeNum = 0;

    public NonemptyListNode(Comparable item, AbstractListNode next) {
        myItem = item;
        if (next == null) {
            myNext = new EmptyListNode();
        } else {
            myNext = next;
        }
    }

    public NonemptyListNode(Comparable item) {
        this(item, new EmptyListNode());
    }

    public Comparable item() {
        return myItem;
    }

    public AbstractListNode next() {
        return myNext;
    }

    public void setItem(Comparable newItem) {
        if (!this.isEmpty()) {
            myItem = newItem;
        }
    }

    public void setNext(AbstractListNode newNext) {
        if (!this.isEmpty()) {
            myNext = newNext;
        }
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        if (this.isEmpty()) {
            return 0;
        }
        return 1 + this.next().size();
    }

    public Comparable get(int position) {
        AbstractListNode curItem = this;
        int curNum = 0;
        if (position > this.size() || position < 0) {
            throw new IllegalArgumentException("incorrect position");
        }
        while (curNum < position) {
            if (curItem.isEmpty()) {
                return curItem.item();
            }
            curItem = curItem.next();
            curNum++;
        }
        return curItem.item();
    }

    public String toString() {
        String strToRtn = "( ";
        NonemptyListNode curNode = this;
        for (int i = 0; i < this.size(); i++) {
            if (curNode.next().isEmpty()) {
                strToRtn += curNode.item();
                strToRtn += " )";
                break;
            }
            strToRtn += curNode.item();
            strToRtn += " ";
            curNode = (NonemptyListNode) curNode.next();
        }
        return strToRtn;
    }


    public boolean equals(Object node) {
        NonemptyListNode curList = this;
        int nodeSize = ((NonemptyListNode) node).size();
        if (curList instanceof NonemptyListNode) {
            if (curList.size() == nodeSize) {
                for (int i = 0; i < nodeSize; i++) {
                    if (curList.get(i) != (((NonemptyListNode) node).get(i))) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public AbstractListNode add(Comparable c) {
        // Recursive method
        AbstractListNode curList = this;
        NonemptyListNode listToRtn = new NonemptyListNode(curList.item(), curList.next().add(c));
        return listToRtn;
    }

    public AbstractListNode append(AbstractListNode listToAppend) {
        // Concatenating the two lists this + listToAppend
        AbstractListNode curList = this;
        AbstractListNode listToRtn;
        if (listToAppend.isEmpty()) {
            listToRtn = new NonemptyListNode(curList.item(), curList.next());
        } else {
            // if listToAppend is not empty
            // created a new list called listToRtn
            listToRtn = new EmptyListNode();

            // Using a while loop to traverse everything in the curList
            while (!curList.isEmpty()) {
                listToRtn = listToRtn.add(curList.item());
                curList = curList.next();
            }
            while (!listToAppend.isEmpty()) {
                listToRtn = listToRtn.add(listToAppend.item());
                listToAppend = listToAppend.next();
            }
        }
        return listToRtn;
    }

    // Another version of append using recursion
    // It recursively traverses through list1 and then the last item in list1 points to list2
    /*
    public AbstractListNode append(AbstractListNode list) {
        return appendHelper(this, list);
    }

    private static AbstractListNode appendHelper(AbstractListNode list1, AbstractListNode list2) {
        if (list1.isEmpty()) {
            return list2;
        } else {
            return new NonemptyListNode(list1.item(), appendHelper(list1.next(), list2));
        }
    }*/

    public AbstractListNode reverse() {
        // return a new list (non-destructive) with a reversed order
        AbstractListNode curList = this;
        AbstractListNode listToRtn = new EmptyListNode();
        AbstractListNode tempNextList;

        for (int i = curList.size()-1; i >= 0; i--) {
            listToRtn = listToRtn.add(curList.get(i));
        }

        return listToRtn;
    }


    // ==================== Destructive Methods ====================
    public AbstractListNode appendInPlace(AbstractListNode list2) {
        AbstractListNode curList = this;
        while (!curList.next().isEmpty()) {
            curList = curList.next();
        }
        curList.setNext(list2);
        return curList;
    }
}


class EmptyListNode extends AbstractListNode {

    public EmptyListNode() {

    }

    public Comparable item() {
        throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }

    public AbstractListNode next() {
        throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }

    public void setItem(Comparable newItem) {
        throw new IllegalArgumentException ("There is no 'item' value to set in EmptyListNode.");
    }

    public void setNext(AbstractListNode newNext) {
        throw new IllegalArgumentException ("No elements to set as follow an EmptyListNode.");
    }

    public boolean isEmpty() {
        return true;
    }

    public int size() {
        int toRtn = 0;
        return toRtn;
    }

    public Comparable get(int position) {
        return this.item();
    }

    public String toString() {
        return "( )";
    }

    public boolean equals(Object node) {
        EmptyListNode curEmptyList = this;
        if (node instanceof EmptyListNode) {
            if (curEmptyList.size() == ((EmptyListNode) node).size()) {
                return true;
            }
        }
        return false;
    }


    public AbstractListNode add(Comparable c) {
        NonemptyListNode newListToRtn = new NonemptyListNode(c);
        //System.out.println("NewListToRtn: " + newListToRtn);
        return newListToRtn;
    }

    public AbstractListNode append(AbstractListNode list) {
        AbstractListNode curList = this;
        AbstractListNode newListToRtn = null;
        if (!list.isEmpty()) {
            newListToRtn = new NonemptyListNode(list.item(), append(list.next()));
        } else {
            newListToRtn = new EmptyListNode();
        }
        return newListToRtn;
    }

    public AbstractListNode reverse() {
        AbstractListNode newListToRtn = new EmptyListNode();
        return newListToRtn;
    }

    // ==================== Destructive Methods ====================
    public AbstractListNode appendInPlace(AbstractListNode list2) {
        return list2;
    }
}
