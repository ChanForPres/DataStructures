
/**
 * Created by EunjinCho on 10/2/15.
 */

public class TestExceptions {

    public static void main (String [ ] args) {

        try {
            Object obj = null;
            obj.equals("Hello");
        } catch (NullPointerException e) {
            System.out.println ("got null pointer");
        }

        try {
            Object x[] = new String[4];
            x[0] = new Integer(0);
        } catch (ArrayStoreException e) {
            System.out.println ("got illegal array store");
        }

        try {
            Object intObj = new Integer(0);
            String toRtn = "";
            toRtn += (String)intObj;
            System.out.println(toRtn);
        } catch (ClassCastException e) {
            System.out.println ("got illegal class cast");
        }

    }


}
