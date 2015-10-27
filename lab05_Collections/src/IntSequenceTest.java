import junit.framework.TestCase;

/**
 * Created by EunjinCho on 10/1/15.
 */
public class IntSequenceTest extends TestCase {

    public void testAdd() throws Exception {
        IntSequence intSeq1 = new IntSequence(10);
        assertTrue(intSeq1.isEmpty());
        intSeq1.add(6);
        intSeq1.add(0);
        assertEquals(2, intSeq1.size());
        intSeq1.add(-11);
        assertEquals(-11, intSeq1.elementAt(2));
        intSeq1.add(42);
        assertEquals(4, intSeq1.size());
        intSeq1.add(-33);
        assertEquals(-33, intSeq1.elementAt(4));
        assertEquals("6 0 -11 42 -33 0 0 0 0 0 ", intSeq1.toString());
    }

    public void testRemove() throws Exception {
        IntSequence intSeq2 = new IntSequence(10);
        intSeq2.add(3);
        intSeq2.add(-7);
        intSeq2.add(42);
        intSeq2.add(-11);
        intSeq2.add(0);
        intSeq2.add(6);
        intSeq2.add(9);
        intSeq2.remove(2);
        assertEquals("3 -7 -11 0 6 9 0 0 0 0 ", intSeq2.toString());
        intSeq2.add(0);
        intSeq2.add(200);
        intSeq2.add(-22);
        intSeq2.add(30);
        assertEquals("3 -7 -11 0 6 9 0 200 -22 30 ", intSeq2.toString());
        intSeq2.remove(9);
        assertEquals("3 -7 -11 0 6 9 0 200 -22 0 ", intSeq2.toString());
    }

    public void testInsert() throws Exception {
        IntSequence intSeq2 = new IntSequence(5);
        intSeq2.add(3);
        intSeq2.add(-7);
        intSeq2.add(42);
        intSeq2.add(-11);
        intSeq2.add(10);
        intSeq2.insert(2, -9);
        assertEquals("3 -7 -9 42 -11 ", intSeq2.toString());
        assertTrue(intSeq2.contains(3));
    }

}