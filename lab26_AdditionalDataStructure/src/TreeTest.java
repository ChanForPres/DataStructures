/**
 * Created by EunjinCho on 2016. 3. 5..
 */

import java.util.*;

public class TreeTest {


    public static void main(String[] args) {
        HashMap<String, Integer> edgeT = new HashMap<>();
        edgeT.put("A", 5);
        edgeT.put("B", 3);
        edgeT.put("C", 8);
        edgeT.put("D", 5);
        edgeT.put("E", 2);

        System.out.println(edgeT.entrySet());

        /*Iterator i = Eset.iterator();

        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }*/
    }
}
