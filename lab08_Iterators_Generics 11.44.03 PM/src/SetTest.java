import junit.framework.TestCase;

/**
 * Created by EunjinCho on 10/10/15.
 */
public class SetTest extends TestCase {
    public void testInsert() {
        Set s = new Set(5);
        s.insert(0);
        s.insert(2);
        s.insert(3);
        assertTrue(s.member(3));
    }
}