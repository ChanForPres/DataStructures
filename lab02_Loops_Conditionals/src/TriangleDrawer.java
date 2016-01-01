/**
 * Created by EunjinCho on 9/23/15.
 */

public class TriangleDrawer {
    // Creates a triangle using asterisks
    // Asterisks increment by one each line up to 10 asterisks

    public static void main (String [ ] args) {

        int col = 0;
        int row = 0;
        int SIZE = 10;

        // Continue increment until row contains 10 asterisks
        while (row < SIZE) {
            while (col <= row) {
                System.out.print('*');
                col += 1;
            }
            row += 1;
            // Reset col to 0
            col = 0;
            // Newline
            System.out.println();
        }
    }
}

