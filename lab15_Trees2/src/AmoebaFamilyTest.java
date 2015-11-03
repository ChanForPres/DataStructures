import junit.framework.TestCase;

/**
 * Created by EunjinCho on 10/28/15.
 */
public class AmoebaFamilyTest extends TestCase {
    public void testHeight() {
        AmoebaFamily family = new AmoebaFamily("Amos McCoy");
        assertEquals(1, family.height());
        family.addChild("Amos McCoy", "mom/dad");
        family.addChild("Amos McCoy", "auntie");
        family.addChild("mom/dad", "me");
        family.addChild("mom/dad", "Fred");
        family.addChild("mom/dad", "Wilma");
        family.addChild("me", "Mike");
        family.addChild("me", "Homer");
        family.addChild("me", "Marge");
        family.addChild("me", "Katie");
        family.addChild("Mike", "Bart");
        family.addChild("Mike", "Lisa");
        family.addChild("Marge", "Bill");
        family.addChild("Marge", "Hilary");
        System.out.println(family.height());
        assertEquals(5, family.height());
    }
}