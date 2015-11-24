import junit.framework.TestCase;

/**
 * Created by EunjinCho on 11/24/15.
 */
public class MyTreeMapTest extends TestCase {

    public void testContainsKey() throws Exception {
        MyTreeMap<String, String> phoneBook = new MyTreeMap<>();
        phoneBook.put("Jane", "111-222-3333");
        phoneBook.put("Alice", "111-222-4444");
        phoneBook.put("Tom", "111-222-5555");
        phoneBook.put("Zack", "111-222-6666");
        assertTrue(phoneBook.containsKey("Zack"));
    }

    public void testPut() throws Exception {
        MyTreeMap<String, String> phoneBook = new MyTreeMap<>();
        phoneBook.put("Jane", "111-222-3333");
        phoneBook.put("Alice", "111-222-4444");
        phoneBook.put("Zack", "111-222-5555");

    }

    /*public void testFindKVPair() throws Exception {

    }

    public void testRemove() throws Exception {

    }

    public void testGet() throws Exception {

    }*/
}