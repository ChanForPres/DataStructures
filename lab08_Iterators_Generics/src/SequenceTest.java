import junit.framework.TestCase;

/**
 * Created by EunjinCho on 10/10/15.
 */

public class SequenceTest extends TestCase {

    public void testIsEmpty() throws Exception {
        Sequence<Integer> newSequence = new Sequence<Integer>(4);
        assertTrue(newSequence.isEmpty());
    }

    public void testSize() throws Exception {
        Sequence<String> newSequence = new Sequence<String>(4);
        newSequence.add("A");
        newSequence.add("B");
        newSequence.add("C");
        assertEquals(3, newSequence.size());
    }

    public void testElementAt() throws Exception {
        Sequence<String> newSequence = new Sequence<String>(4);
        newSequence.add("A");
        newSequence.add("B");
        newSequence.add("C");
        assertEquals("B", newSequence.elementAt(1));
    }

    public void testInsert() throws Exception {
        Sequence<Integer> newSequence = new Sequence<Integer>(4);
        newSequence.add(1);
        newSequence.add(2);
        newSequence.add(3);
        newSequence.insert(1, 5);
        assertEquals("1 5 2 3 ", newSequence.toString());
    }


    public void testRemove() throws Exception {
        Sequence<String> newSequence = new Sequence<String>(4);
        newSequence.add("A");
        newSequence.add("B");
        newSequence.add("C");
        newSequence.remove(2);
        assertEquals("A B null null ", newSequence.toString());

    }

}