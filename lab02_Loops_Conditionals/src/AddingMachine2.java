/**
 * Created by EunjinCho on 1/1/16.
 */
import java.util.*;

public class AddingMachine2 {
    // I wanted to test the nested two loops

    public static void main (String [ ] args) {
        Scanner scanner = new Scanner (System.in);
        boolean justStarting = true;
        int total = 0;
        int subtotal = 0;
        int prevNum = 1;
        while (true) {
            int k;
            k = scanner.nextInt();
            while (k != 0) {
                subtotal += k;
                prevNum = k;
                k = scanner.nextInt();
            }
            // For two consecutive zeroes
            // To exit the main, execute a return
            if (prevNum == 0) {
                System.out.println("total " + total);
                return;
            }

            // For one zero value, add subtotal to total
            // and reset subtotal to zero
            else {
                System.out.println("subtotal " + subtotal);
                total += subtotal;
                subtotal = 0;
                prevNum = k;
                k = scanner.nextInt();
            }
        }
    }
}