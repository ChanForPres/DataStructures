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
    }

    public void testAddDefinition() {
        Dictionary dict1 = new Dictionary();
        dict1.insert("dog");
        dict1.insert("cat");
        dict1.insert("duck");
        dict1.addDefinition("dog", "My darling, such pure and innocent");
    }

}