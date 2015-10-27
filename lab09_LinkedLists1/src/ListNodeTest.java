import junit.framework.TestCase;

/**
 * Created by EunjinCho on 10/15/15.
 */

public class ListNodeTest extends TestCase {

    public void testGet() {
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(2, listNode1);
        ListNode listNode3 = new ListNode(1, listNode2);
        assertEquals(3, listNode3.get(2));
        assertEquals(2, listNode3.get(1));
        try {
            listNode3.get(4);
            fail("incorrect position");
        } catch (IllegalArgumentException e) {

        }
    }
}