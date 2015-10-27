/**
 * Created by EunjinCho on 9/24/15.
 */
import java.util.*;

public class AddingMachine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean justStarting = true;
        int total = 0;
        int subtotal = 0;
        int prevNum = 1;
        while (justStarting) {
            int k;
            k = scanner.nextInt();
            if (k != 0) {
                subtotal += k;
                prevNum = k;
                k = scanner.nextInt();
                subtotal += k;
            }
            if (k == 0) {
                System.out.println("subtotal " + subtotal);
                total += subtotal;
                subtotal = 0;
                prevNum = k;
                k = scanner.nextInt();
                subtotal += k;
            }
            if (k == 0 && prevNum == 0) {
                System.out.println("total " + total);
                return;
            }
        }
    }
}

