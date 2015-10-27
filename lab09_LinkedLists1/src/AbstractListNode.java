import sun.invoke.empty.Empty;

/**
 * Created by EunjinCho on 10/13/15.
 */


abstract public class AbstractListNode {

    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Object get(int position);
    abstract public String toString();
    abstract public boolean equals(Object node);
}

class NonemptyListNode extends AbstractListNode {

    private Object myItem;
    private AbstractListNode myNext;
    private int sizeNum = 0;

    public NonemptyListNode (Object item, AbstractListNode next) {
        myItem = item;
        if (next == null) {
            myNext = new EmptyListNode();
        } else {
            myNext = next;
        }
    }

    public NonemptyListNode (Object item) {
        this (item, new EmptyListNode());
    }

    public Object item() {
        return myItem;
    }

    public AbstractListNode next() {
        return myNext;
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        if (this.isEmpty()) {
            return 0;
        }
        return 1+this.next().size();
    }

    public Object get(int position) {
        NonemptyListNode curItem = this;
        int curNum = 0;
        if (position > this.size() || position < 0) {
            throw new IllegalArgumentException("incorrect position");
        }
        while (curNum < position) {
            if (curItem.next().isEmpty()) {
                return curItem.next();
            }
            curItem = (NonemptyListNode) curItem.next();
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
        int nodeSize = ((NonemptyListNode)node).size();
        if (curList instanceof NonemptyListNode) {
            if (curList.size() == nodeSize) {
                for (int i = 0; i < nodeSize; i++) {
                    if (curList.get(i)!= (((NonemptyListNode) node).get(i))) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }
}

class EmptyListNode extends AbstractListNode {

    public EmptyListNode() {

    }

    public Object item() {
        throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }

    public AbstractListNode next() {
        throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }

    public boolean isEmpty() {
        return true;
    }

    public int size() {
        int toRtn = 0;
        return toRtn;
    }

    public Object get(int position) {
        return this;
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
}
