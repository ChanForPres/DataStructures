import junit.framework.TestCase;

/**
 * Created by EunjinCho on 10/15/15.
 */
public class AbstractListNodeTest extends TestCase {

    public void testSize() {
        EmptyListNode emptyList = new EmptyListNode();
        assertEquals(0, emptyList.size());

        NonemptyListNode listNode0 = new NonemptyListNode(3, emptyList);
        NonemptyListNode listNode1 = new NonemptyListNode(2, listNode0);
        NonemptyListNode listNode2 = new NonemptyListNode(1, listNode1);
        assertEquals(3, listNode2.size());
    }

    public void testGet() {
        EmptyListNode emptyList = new EmptyListNode();
        NonemptyListNode listNode0 = new NonemptyListNode(3, emptyList);
        NonemptyListNode listNode1 = new NonemptyListNode(2, listNode0);
        NonemptyListNode listNode2 = new NonemptyListNode(1, listNode1);
        assertEquals(2, listNode2.get(1));
        assertEquals(3, listNode2.get(2));
        assertEquals(emptyList.toString(), listNode2.get(3).toString());
    }

    public void testToString() {
        EmptyListNode emptyList = new EmptyListNode();
        NonemptyListNode listNode0 = new NonemptyListNode(3, emptyList);
        NonemptyListNode listNode1 = new NonemptyListNode(2, listNode0);
        NonemptyListNode listNode2 = new NonemptyListNode(1, listNode1);
        assertEquals("( )", emptyList.toString());
        assertEquals("( 1 2 3 )", listNode2.toString());
    }

    public void testEquals() {
        EmptyListNode emptyList1 = new EmptyListNode();
        NonemptyListNode listNode1_0 = new NonemptyListNode(3, emptyList1);
        NonemptyListNode listNode1_1 = new NonemptyListNode(2, listNode1_0);
        NonemptyListNode listNode1_2 = new NonemptyListNode(1, listNode1_1);

        EmptyListNode emptyList2 = new EmptyListNode();
        NonemptyListNode listNode2_0 = new NonemptyListNode(3, emptyList1);
        NonemptyListNode listNode2_1 = new NonemptyListNode(2, listNode2_0);
        NonemptyListNode listNode2_2 = new NonemptyListNode(1, listNode2_1);

        EmptyListNode emptyList3 = new EmptyListNode();
        NonemptyListNode listNode3_0 = new NonemptyListNode(3, emptyList3);
        NonemptyListNode listNode3_1 = new NonemptyListNode(1, listNode3_0);
        NonemptyListNode listNode3_2 = new NonemptyListNode(1, listNode3_1);

        assertFalse(listNode1_2.equals(listNode3_2));
        assertTrue(listNode1_2.equals(listNode2_2));
        assertTrue(emptyList1.equals(emptyList2));
    }
}