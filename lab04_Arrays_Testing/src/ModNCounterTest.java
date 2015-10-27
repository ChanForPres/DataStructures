import junit.framework.TestCase;

/**
 * Created by EunjinCho on 9/25/15.
 */
public class ModNCounterTest extends TestCase {

    public void testConstructor() {
        int n = 3;
        ModNCounter c = new ModNCounter(n);
        assertTrue (c.value() == 0);
    }

    public void testIncrement() throws Exception {
        int n = 3;
        ModNCounter c = new ModNCounter(n);
        c.increment();
        assertTrue(c.value() == 1);
        c.increment();
        assertTrue(c.value() == 2);
        c.increment();
        assertTrue (c.value() == 0);
    }

    public void testReset() throws Exception {
        int n = 3;
        ModNCounter c = new ModNCounter(n);
        c.increment();
        c.reset();
        assertTrue (c.value() == 0);
    }

}