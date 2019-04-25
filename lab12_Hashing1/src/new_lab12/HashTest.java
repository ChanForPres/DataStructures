import java.util.ArrayList;

public class HashTest{
    // (5, "Clancy")
    private Pair hTable[]; // [key][val]=> [1,2],[2,3],[3,4]

    public HashTest(int init_size) {
        this.hTable = new Pair[init_size];
    }

    public Pair.Val getVal(Pair.Key inp_key) {
        // ret corresponding val

        for (int i = 0; i< this.hTable.length;i++) {
            // check if key exists
            if (hTable[i].local_key== inp_key) {
                // get val
                break;
            }
        }
        return null;
    }

    public boolean setKey(Pair.Key inp_key) {

        return false;
    }

    // wrapper class
    protected static class Pair {
        // key
        Key local_key = null;
        // val
        Val local_val = null;
        Pair(Key new_key) {
            this.local_key =new_key;
        }


        private static class Key<S,T> {

            public Key(S new_k) {
                this.key = new_k;
                this.value = new Val(new_k);
            }

            private S key;
            private Val value;
        }

        //
        private static class Val<T> {
            // use hash function
            public int easy_hash(T inp) {
                return inp.hashCode();
            }
            public String uni_hash(T inp) {
                return String.valueOf(inp);
            }
            public Val(T inp) {
                this.value = uni_hash(inp);
            }

            private String value;
        }
    }




    /*
    Linear Probing: Store the colliding keys elsewhere in the array,
    potentially in the next open array space. We will encounter it later in this lab.

    Chaining: An easier, more common solution is to store all the keys
    with a given hash value together in a collection of their own, such as a linked list.
     This collection is often called a bucket.

     */
    private void collideProbe() {}
    private void collideChain() {}


    public static void main(String args[]) {
        //Key<Integer,Integer> k1 = new Key<>(1);
        //Key<Object,Integer> k2 = new Key<>("2");
        //Key<Object,Object> k3 = new Key<>(k2);
        Htable h1 = new Htable(5);
        //Pair p1 = new Pair(10);
        HashTest ht1 = new HashTest(5);
        //ht1.add(1); // [1:code]
        //ht1.get(1); // code

    }
}
