import junit.framework.TestCase;

/**
 * Created by EunjinCho on 11/24/15.
 */

import junit.framework.TestCase;

/**
 * Created by EunjinCho on 11/24/15.
 */
public class MyTreeMapTest extends TestCase {

    /*public void testContainsKey() throws Exception {
        MyTreeMap<String, String> phoneBook = new MyTreeMap<>();
        phoneBook.put("Jane", "111-222-3333");
        phoneBook.put("Alice", "111-222-4444");
        phoneBook.put("Zora", "111-222-5555");
        assertFalse(phoneBook.containsKey("Zara"));
        assertTrue(phoneBook.containsKey("Zora"));

    }*/

    public void testPut() throws Exception {
        MyTreeMap<String, String> phoneBook = new MyTreeMap<>();
        phoneBook.put("Jimmy", "111-222-3333");
        phoneBook.put("Andy", "111-222-4444");
        phoneBook.put("Zack", "111-222-5555");
        phoneBook.put("Taylor", "111-222-6666");
        phoneBook.put("Taylor", "111-222-7777");
    }

    /*public void testFindKVPair() throws Exception {

    }

    public void testRemove() throws Exception {

    }

    public void testGet() throws Exception {

    }*/
}