import junit.framework.TestCase;

/**
 * Created by EunjinCho on 2/12/16.
 */
public class DictionaryTest extends TestCase {
    public void testInsert() {
        Dictionary dict = new Dictionary();
        dict.insert("dog");
        dict.insert("dot");
        dict.insert("angel");
        dict.printKeys();
    }

    public void testAddDefinition() {
        Dictionary dict1 = new Dictionary();
        dict1.insert("dog");
        dict1.insert("dot");
        dict1.insert("angel");
        dict1.addDefinition("dog", "My darling");
        assertEquals(dict1.lookupDefinition("dog"), "My darling");
    }

}