/**
 * Created by EunjinCho on 9/24/15.
 */
import java.util.*;

public class AddingMachine {
    // Given user input, one per line
    // A nonzero value should be added into a subtotal
    // A zero value should print the subtotal and reset subtotal to zero
    // Two consecutive zeroes should print the total of all values and terminate

    // This only uses the single loop

    public static void main(String[] args) {
        // Scanner class to read user input
        Scanner scanner = new Scanner(System.in);
        boolean justStarting = true;
        int total = 0;
        int subtotal = 0;
        // To check two consecutive zeroes
        int prevNum = 1;

        while (justStarting) {
            int k;
            k = scanner.nextInt();
            // For non-zero values, keep adding up
            if (k != 0) {
                subtotal += k;
                prevNum = k;
                k = scanner.nextInt();
                subtotal += k;
            }
            // For one zero value, add subtotal to total
            // and reset subtotal to zero
            if (k == 0) {
                System.out.println("subtotal " + subtotal);
                total += subtotal;
                subtotal = 0;
                prevNum = k;
                k = scanner.nextInt();
            }
            // For two consecutive zeroes
            // To exit the main, execute a return
            if (k == 0 && prevNum == 0) {
                System.out.println("total " + total);
                return;
            }
        }
    }
}

