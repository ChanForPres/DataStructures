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

    /*public void testPut() throws Exception {
        MyTreeMap<String, String> phoneBook = new MyTreeMap<>();
        phoneBook.put("Jimmy", "111-222-3333");
        phoneBook.put("Andy", "111-222-4444");
        phoneBook.put("Zack", "111-222-5555");
        phoneBook.put("Taylor", "111-222-6666");
        phoneBook.put("Taylor", "111-222-7777");
    }*/

    public void testRemove() throws Exception {
        MyTreeMap<String, String> phoneBook = new MyTreeMap<>();
        phoneBook.put("Jimmy", "111-222-3333");
        phoneBook.put("Andy", "111-222-4444");
        phoneBook.put("Abby", "111-222-5555");
        phoneBook.put("Zack", "111-222-6666");
        phoneBook.put("Taylor", "111-222-7777");
        phoneBook.put("Zonny", "111-222-8888");
        assertTrue(phoneBook.containsKey("Zonny"));
        //phoneBook.remove("Abby");
        //assertFalse(phoneBook.containsKey("Jimmy"));
        //System.out.println("=====phoneBook Change======"+phoneBook.myBST.myRoot.myItem.getKey());
    }

    /*public void testGet() throws Exception {
        MyTreeMap<String, String> phoneBook = new MyTreeMap<>();
        phoneBook.put("Jimmy", "111-222-3333");
        phoneBook.put("Andy", "111-222-4444");
        phoneBook.put("Zack", "111-222-5555");
        phoneBook.put("Taylor", "111-222-6666");
        phoneBook.put("Taylor", "111-222-7777");
        assertEquals("111-222-7777", phoneBook.get("Taylor"));
    }*/
}