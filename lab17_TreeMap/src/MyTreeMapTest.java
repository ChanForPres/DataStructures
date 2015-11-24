import junit.framework.TestCase;

/**
 * Created by EunjinCho on 11/24/15.
 */

import junit.framework.TestCase;

/**
 * Created by EunjinCho on 11/24/15.
 */
public class MyTreeMapTest extends TestCase {

    public void testContainsKey() throws Exception {
        MyTreeMap<String, String> phoneBook = new MyTreeMap<>();
        phoneBook.put("Jane", "111-222-3333");
        phoneBook.put("Alice", "111-222-4444");
        phoneBook.put("Zora", "111-222-5555");
        System.out.println(((MyTreeMap.KVPair)phoneBook.myBST.myRoot.myItem).getKey());
        System.out.println(((MyTreeMap.KVPair)phoneBook.myBST.myRoot.myLeft.myItem).getKey());
        System.out.println(((MyTreeMap.KVPair)phoneBook.myBST.myRoot.myRight.myItem).getKey());
        System.out.println("========TEST BEGINS========");
        //assertTrue(phoneBook.containsKey("Alice"));

    }

    /*public void testPut() throws Exception {

    }

    public void testFindKVPair() throws Exception {

    }

    public void testRemove() throws Exception {

    }

    public void testGet() throws Exception {

    }*/
}