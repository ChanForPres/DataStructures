/**
 * Created by EunjinCho on 9/23/15.
 */

public class CheckDigit {
    // Determines if the id in the variable id is legal
    // By storing true or false value in the variable isLegal

    public static void main (String [ ] args) {
        int id = 0;
        // try/catch to check if the argument is integer
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println ("The argument has to be a sequence of digits.");
            System.exit (1);
        }

        boolean isLegal = false;
        int sum = 0;
        // Convert input to int array
        int[] digits = Integer.toString(id).chars().map(c->c-='0').toArray();
        // Sum up all the digits up to the second last digit
        for (int i = 0; i < digits.length-1; i++) {
            sum += digits[i];
        }

        // Checking
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
