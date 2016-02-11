import junit.framework.TestCase;

import java.util.LinkedList;

/**
 * Created by EunjinCho on 2/5/16.
 */
public class BSTTest extends TestCase {
    public void testBST() {
        LinkedList<Integer> linkedList1 = new LinkedList<>();
        linkedList1.add(1);
        linkedList1.add(2);
        linkedList1.add(5);
        BST newBST = new BST(linkedList1);
        assertEquals(2, newBST.getMyItem());
        assertEquals(1, newBST.getMyLeft());
        assertEquals(5, newBST.getMyRight());
    }
}
