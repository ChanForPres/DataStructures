/**
 * Created by EunjinCho on 9/2/15.
 */
import java.io.*;

public class DateConverter {

    // Given a day number in 2008, an integer between 1 and 366,
    // as a command-line argument, prints the date in month/day format.
    // Example:
    // java DateConverter 365
    // should print
    // 12/30

    public static void main (String [ ] args) {
        int dayOfYear = 0;
        // Catches an exception when it's not an integer
        try {
            dayOfYear = Integer.parseInt (args[0]);
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // Variables assignment
        int month, dateInMonth, daysInMonth;
        month = 1;
        daysInMonth = 31;
        while (dayOfYear > daysInMonth) {
            if (month == 2) {
                daysInMonth = 29;
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                daysInMonth = 30;
            } else {
                daysInMonth = 31;
            }
            if (daysInMonth == dayOfYear) {
                dayOfYear = dayOfYear;
                break;
            }
            dayOfYear = dayOfYear - daysInMonth;
            month++;
        }
        // Edge case (dayOfYear = 366)
        if (month > 12) {
            month = 1;
        }
        dateInMonth = dayOfYear;
        System.out.println (month + "/" + dateInMonth);
    }
}
