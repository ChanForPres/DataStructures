/**
 * Created by EunjinCho on 9/23/15.
 */

public class CheckDigit {

    public static void main (String [ ] args) {
        int id = 0;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println ("The argument has to be a sequence of digits.");
            System.exit (1);
        }

        boolean isLegal = true;
        int sum = 0;
        int[] digits = Integer.toString(id).chars().map(c->c-='0').toArray();
        for (int i = 0; i < digits.length-1; i++) {
            sum += digits[i];
        }

        if (sum %10 == digits[digits.length-1]) {
            isLegal = true;
        } else {
            isLegal = false;
        }

        if (isLegal) {
            System.out.println (id + " is legal");
        } else {
            System.out.println (id + " is not legal");
        }
    }

}
