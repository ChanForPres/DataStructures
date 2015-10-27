import junit.framework.TestCase;

/**
 * Created by EunjinCho on 10/1/15.
 */
public class ModNCounterTest extends TestCase {
    public void testIncrement() throws Exception {
        ModNCounter modCounter = new ModNCounter(3);
        modCounter.increment();
        assertEquals(1, modCounter.value());
        modCounter.reset();
        modCounter.increment();
        assertEquals(1, modCounter.value());

        ModNCounter modCounter2 = new ModNCounter(3);
        modCounter2.increment();
        modCounter2.increment();
        modCounter2.increment();
        modCounter2.increment();
        assertEquals(1, modCounter2.value()); // prints 1
    }
}