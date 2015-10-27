/**
 * Created by EunjinCho on 9/23/15.
 */
public class TriangleDrawer {

    public static void main (String [ ] args) {

        int col = 0;
        int row = 0;
        int SIZE = 10;
        while (row < SIZE) {
            while (col <= row) {
                System.out.print('*');
                col += 1;
            }
            row += 1;
            col = 0;
            System.out.println();
        }
    }
}

