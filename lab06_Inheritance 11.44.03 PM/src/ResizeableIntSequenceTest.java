import junit.framework.TestCase;

/**
 * Created by EunjinCho on 10/2/15.
 */
public class ResizeableIntSequenceTest extends TestCase {

    public void testAdd() throws Exception {
        ResizeableIntSequence a = new ResizeableIntSequence(4);
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        assertEquals("1 2 3 4 5 ", a.toString());
    }

    public void testInsert() throws Exception {
        ResizeableIntSequence a = new ResizeableIntSequence(5);
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        a.insert(3, 10);
        assertEquals("1 2 3 10 4 5 ", a.toString());
    }

    public void testRemove() throws Exception {
        ResizeableIntSequence a = new ResizeableIntSequence(20);
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        a.remove(3);
        assertEquals("1 2 3 5 0 0 0 0 ", a.toString());

    }

}
