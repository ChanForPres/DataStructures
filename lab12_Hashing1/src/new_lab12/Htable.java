import java.util.NoSuchElementException;

public class Htable {
    // value only
    // [0:value],

    private String vals[]; // key is index
    public Htable(int size) {
        this.vals =  new String[size];
    }

    public Object getVal(int key) {
        // call hash

        return this.vals[key];
    }

    private int hash(String inp) throws HashException{
        try { // toString -> getLength%this.vals.length ->
            // 5%5 = 0, 1%5=1, 3%5=3
            int new_key = inp.length()% this.vals.length; // has to be < arr_len
            return new_key;
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return -1; //fail
    }

    public int addKey(String inp) {
        // call hash func
        try {
            int index = hash(inp);
            this.vals[index] =inp;
            return index;
        }  catch (Exception ex) {
            System.out.println(ex);
        }
        throw new NoSuchElementException("AddKey FAIL");

    }

    public class HashException extends Exception {
        public HashException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static void main(String args[]) {
        //Htable table = new Htable(5);
        Htable h1 = new Htable(5);
        //HashTest.Pair p1 = new HashTest.Pair(10);
        //HashTest ht1 = new HashTest(5);
        String s1 = "1";
        Double d1 = 1.1;
        Boolean b1 = false;

        int key1 = h1.addKey(s1); // [1:code]
        int key2 =h1.addKey(d1.toString()); // [1:code]
        int key3 =h1.addKey(b1.toString()); // [1:code]

        System.out.println(h1.getVal(key1));
        System.out.println(h1.getVal(key2));
        System.out.println(h1.getVal(key3));

    }
}
