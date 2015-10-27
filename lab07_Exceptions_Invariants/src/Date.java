import java.util.HashSet;
import java.util.Set;

/**
 * Created by EunjinCho on 10/2/15.
 */


public class Date {

    private int myMonth;        // months range from 1 (January) through 12 (December)
    private int myDateInMonth;  // dates-in-month range from 1 through the number of days in the month
    private int myYear;         // years are between 1900 and 2100 (arbitrary decision)
    Set<Integer> monthSet_31;
    Set<Integer> monthSet_30;
    Set<Integer> monthSet_28;

    public Date (int month, int dateInMonth, int year) {
        myMonth = month;
        myDateInMonth = dateInMonth;
        myYear = year;
        monthSet_31 = new HashSet<Integer>();
        monthSet_31.add(1);
        monthSet_31.add(3);
        monthSet_31.add(5);
        monthSet_31.add(7);
        monthSet_31.add(8);
        monthSet_31.add(10);
        monthSet_31.add(12);
        monthSet_30 = new HashSet<Integer>();
        monthSet_31.add(4);
        monthSet_31.add(6);
        monthSet_31.add(9);
        monthSet_31.add(11);
        monthSet_28 = new HashSet<Integer>();
        monthSet_31.add(2);

    }

    // Determine if the date information is internally consistent.
    public void isOK() {
        boolean leapDay;
        if (myYear % 4 != 0) {
            leapDay = false;
        } else if (myYear % 100 != 0) {
            leapDay = true;
        } else if (myYear % 400 != 0) {
            leapDay = false;
        } else {
            leapDay = true;
        }

        // YOUR CODE HERE
        if (myYear < 1900 || myYear > 2100) {
            throw new IllegalStateException("Invalid year format");
        } else if (myMonth < 1 || myMonth > 12) {
            throw new IllegalStateException("Invalid month format");
        } else if (monthSet_31.contains(myMonth)) {
            if(myDateInMonth < 1 || myDateInMonth > 31 || String.valueOf(myDateInMonth).length() < 31) {
                throw new IllegalStateException("Invalid date format: 31");
            }
        } else if (monthSet_30.contains(myMonth)) {
            if (myDateInMonth < 1 || myDateInMonth > 30 || String.valueOf(myDateInMonth).length() < 30) {
                throw new IllegalStateException("Invalid date format: 30");
            }
        } else if (monthSet_28.contains(myMonth)) {
            if (myDateInMonth < 1 || myDateInMonth > 29 || String.valueOf(myDateInMonth).length() < 28) {
                throw new IllegalStateException("Invalid date format: 28");
            }
        }
    }
}
