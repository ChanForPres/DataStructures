import com.sun.deploy.util.StringUtils;
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

    public void testSmallest() {
        EmptyListNode emptyList = new EmptyListNode();
        NonemptyListNode listNode0 = new NonemptyListNode(3, emptyList);
        NonemptyListNode listNode1 = new NonemptyListNode(2, listNode0);
        NonemptyListNode listNode2 = new NonemptyListNode(1, listNode1);
        assertEquals(1, listNode2.smallest());
    }

    public void testAdd() {
        AbstractListNode l1 = new EmptyListNode();
        AbstractListNode l2 = l1.add("a");
        assertEquals("( a )", l2.toString());
        AbstractListNode l3 = l2.add("b");
        assertEquals("( a b )", l3.toString());
        assertEquals("( a )", l2.toString());
        AbstractListNode l4 = l3.add("c");
        assertEquals("( a b c )", l4.toString());
    }

    public void testAppend() {
        AbstractListNode l1 = new EmptyListNode();
        AbstractListNode l2 = l1.add("a");
        AbstractListNode l3 = l2.add("b");
        AbstractListNode l4 = l3.add("e");

        AbstractListNode m1 = new EmptyListNode();
        AbstractListNode m2 = m1.add("c");
        AbstractListNode m3 = m2.add("d");

        // only the list is empty -> should return "( a b )"
        AbstractListNode test1 = new EmptyListNode();
        AbstractListNode test2 = test1.append(l3);
        assertEquals("( a b )", test2.toString());


        // both the list and the argument is empty -> should return "( )"
        AbstractListNode test3 = test1.append(l1);
        assertEquals("( )", test3.toString());


        // only the argument is empty -> should return "( a b )"
        AbstractListNode test4 = l4.append(test1);
        assertEquals("( a b e )", test4.toString());


        // neither are empty -> should return "( a b c d )"
        AbstractListNode test5 = l3.append(m3);
        assertEquals("( a b c d )", test5.toString());

        // neither are empty -> should return "( a b e c d )"
        AbstractListNode test6 = l4.append(m3);
        assertEquals("( a b e c d )", test6.toString());

        // the argument and the list remain the same -> should return "( a b a b )"
        AbstractListNode test7 = l3.append(l3);
        assertEquals("( a b a b )", test7.toString());

    }

    public void testReverse() {
        AbstractListNode l1 = new EmptyListNode();
        assertEquals("( )", l1.reverse().toString());
        AbstractListNode l2 = l1.add("a");
        AbstractListNode l3 = l2.add("b");
        AbstractListNode l4 = l3.add("c");
        assertEquals("( c b a )", l4.reverse().toString());
    }

    public void testSetItem() {
        AbstractListNode l1 = new EmptyListNode();
        AbstractListNode l2 = l1.add("a");
        AbstractListNode l3 = l2.add("b");
        l3.setItem("c");
        assertEquals("( c b )", l3.toString());
    }

    public void testAppendInPlace() {

        AbstractListNode empty1 = new EmptyListNode();
        AbstractListNode empty2 = new EmptyListNode();

        empty1 = empty1.appendInPlace (empty2);

        assertEquals ("( )", empty1.toString());
        assertEquals ("( )", empty2.toString());

        AbstractListNode a = new NonemptyListNode("a");

        a = a.appendInPlace(empty1);

        assertEquals ("( a )", a.toString());
        assertEquals ("( )", empty1.toString());

        AbstractListNode b = new NonemptyListNode("b");
        AbstractListNode c = new NonemptyListNode("c");

        b = b.appendInPlace(c);

        assertEquals ("( b c )", b.toString());
        assertEquals ("( c )", c.toString());
    }

    public void testAppendInPlace2() {
        AbstractListNode list1 = new EmptyListNode();
        AbstractListNode list2 = new NonemptyListNode ("a");

        list1 = list1.appendInPlace(list2);

        assertEquals ("( a )", list1.toString());
        assertEquals ("( a )", list2.toString());

        list2 = list2.appendInPlace(list1);

        //assertEquals ("( a )", list1.toString());
        //assertEquals ("( a a )", list2.toString());
    }

    public void testRemove1() {
        AbstractListNode list2 = new NonemptyListNode("a");
        AbstractListNode list3 = list2.add("b");
        AbstractListNode list4 = list3.add("c");
        AbstractListNode list5 = list4.add("d"); // "( a b c d )"
        list5.remove(3);
        assertEquals("( a b c )", list5.toString());
    }

    public void testRemove2() {
        AbstractListNode list2 = new NonemptyListNode("a");
        AbstractListNode list3 = list2.add("b");
        AbstractListNode list4 = list3.add("c");
        AbstractListNode list5 = list4.add("d");
        AbstractListNode list6 = list5.add("e");// "( a b c d e )"
        list6.remove(4);
        assertEquals("( a b c d )", list5.toString());
    }

    public void testMerge1() {
        AbstractListNode a1 = new EmptyListNode();
        AbstractListNode a2 = a1.add(1);
        AbstractListNode a3 = a2.add(2);
        AbstractListNode a4 = a3.add(3);

        AbstractListNode b1 = new EmptyListNode();
        AbstractListNode b2 = b1.add(4);
        AbstractListNode b3 = b2.add(5);
        AbstractListNode b4 = b3.add(6);

        assertEquals("( 1 2 3 4 5 6 )", a4.merge(a4, b4).toString());

    }

    public void testMerge2() {
        AbstractListNode a1 = new EmptyListNode();
        AbstractListNode a2 = a1.add(1);
        AbstractListNode a3 = a2.add(3);


        AbstractListNode b1 = new EmptyListNode();
        AbstractListNode b2 = b1.add(2);
        AbstractListNode b3 = b2.add(4);

        assertEquals("( 1 2 3 4 )", a3.merge(a3, b3).toString());

    }

    public void testMerge3() {
        AbstractListNode a1 = new EmptyListNode();
        AbstractListNode a2 = a1.add(3);
        AbstractListNode a3 = a2.add(5);
        AbstractListNode a4 = a3.add(6);

        AbstractListNode b1 = new EmptyListNode();
        AbstractListNode b2 = b1.add(2);
        AbstractListNode b3 = b2.add(4);
        AbstractListNode b4 = b3.add(7);

        assertEquals("( 2 3 4 5 6 7 )", a4.merge(a4, b4).toString());

    }

    public void testMerge4() {
        AbstractListNode a1 = new EmptyListNode();
        AbstractListNode a2 = a1.add(1);
        AbstractListNode a3 = a2.add(2);
        AbstractListNode a4 = a3.add(3);

        AbstractListNode b1 = new EmptyListNode();
        AbstractListNode b2 = b1.add(2);
        AbstractListNode b3 = b2.add(3);
        AbstractListNode b4 = b3.add(5);

        assertEquals("( 1 2 2 3 3 5 )", a4.merge(a4, b4).toString());

    }

    public void testMerge5() {
        AbstractListNode a1 = new EmptyListNode();
        AbstractListNode a2 = a1.add(1);
        AbstractListNode a3 = a2.add(3);
        AbstractListNode a4 = a3.add(6);

        AbstractListNode b1 = new EmptyListNode();
        AbstractListNode b2 = b1.add(2);
        AbstractListNode b3 = b2.add(4);
        AbstractListNode b4 = b3.add(5);
        AbstractListNode b5 = b4.add(8);

        assertEquals("( 1 2 3 4 5 6 8 )", a4.merge(a4, b5).toString());

    }
}

