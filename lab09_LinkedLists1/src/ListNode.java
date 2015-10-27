import java.util.List;

/**
 * Created by EunjinCho on 10/13/15.
 */


public class ListNode {

    private Object myItem;
    private ListNode myNext;

    public ListNode(Object item, ListNode next) {
        myItem = item;
        myNext = next;
    }
    public ListNode(Object item) {
        this(item, null);
    }

    public Object get(int position) {
        ListNode curItem = this;
        int curNum = 0;
        if (position > size(this) || position < 0) {
            throw new IllegalArgumentException("incorrect position");
        }

        while (curNum < position) {
            curItem = curItem.next();
            curNum++;
        }
        return curItem.item();
    }

    public int size(ListNode listNode0) {
        int range = 0;
        while (listNode0 != null) {
            range++;
            listNode0 = listNode0.next();
        }
        return range;
    }

    public Object item() {
        return myItem;
    }
    public ListNode next() {
        return myNext;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(2, listNode1);
        ListNode listNode3 = new ListNode(1, listNode2);
        while (listNode3 != null) {
            System.out.println(listNode3.item());
            listNode3 = listNode3.next();
        }

    }


}

