import junit.framework.TestCase;

/**
 * Created by EunjinCho on 10/23/15.
 */
public class MyHashMapTest extends TestCase {

    public void testConstructor() {
        MyHashMap<String, String> myHashMap = new MyHashMap<String, String>();
        assertEquals(30, myHashMap.capacity());
        MyHashMap<String, String> myHashMap1 = new MyHashMap<String, String>(35);
        assertEquals(35, myHashMap1.capacity());
        MyHashMap<String, String> myHashMap2 = new MyHashMap<String, String>(35, 0.75);
        assertEquals(35, myHashMap2.capacity());
    }


    public void testContainsKey() {
        MyHashMap<String, String> myHashMap = new MyHashMap<String, String >();
        myHashMap.put("Jinny", "23");
        myHashMap.put("Scott", "25");
        myHashMap.put("Phil", "25");
        myHashMap.put("Sarah", "22");
        assertTrue(myHashMap.containsKey("Scott"));
        assertFalse(myHashMap.containsKey("Sasha"));
    }

    public void testContainsValue() {
        MyHashMap<String, String> myHashMap = new MyHashMap<String, String >();
        myHashMap.put("Jinny", "23");
        myHashMap.put("Scott", "25");
        myHashMap.put("Phil", "25");
        myHashMap.put("Sarah", "22");
        myHashMap.remove("Phil");
        System.out.println("Scott: " + myHashMap.containsKey("Scott"));
        System.out.println("Sarah: " + myHashMap.containsKey("Sarah"));
        assertFalse(myHashMap.containsKey("Phil"));
        assertTrue(myHashMap.containsValue("25"));
        assertFalse(myHashMap.containsKey("21"));
    }


    // This actually tests all get, put, expand methods at once
    public void testPut() {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>(5, 0.5);
        myHashMap.put("Momo", 3);
        myHashMap.put("Haru", 4);
        myHashMap.put("Jinny", 23);
        assertEquals(10, myHashMap.capacity());
        myHashMap.put("Momo", 4);
        if (myHashMap.get("Momo") instanceof Integer) {
            System.out.println("Momo's value: " + myHashMap.get("Momo"));
        }

        //assertEquals((Integer)4, myHashMap.get("Momo"));

        //assertTrue(myHashMap.containsValue(3));
    }

}