import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by EunjinCho on 10/1/15.
 */

public class SimpleDate implements Comparable<SimpleDate> {

    int curMonth;
    int curDate;

    public SimpleDate(int month, int date) {
        super();
        curMonth = month;
        curDate = date;
    }

    public int getCurMonth() {
        return curMonth;
    }

    public int getCurDate() {
        return curDate;
    }

    @Override
    public int compareTo(SimpleDate simpleDate) {
        int compareMonth = simpleDate.getCurMonth();
        int compareDate = simpleDate.getCurDate();
        if (this.curMonth > compareMonth){
            return 1;
        } else if (this.curMonth < compareMonth) {
            return -1;
        } else if (this.curMonth == compareMonth) {
            if (this.curDate > compareDate) {
                return 1;
            } else if (this.curDate < compareDate) {
                return -1;
            } else {
                return 0;
            }
        }
        return 0;
    }

    public String toString() {
        String toRtn = "";
        toRtn += curMonth;
        toRtn += "/";
        toRtn += curDate;
        return toRtn;
    }


    public static void main (String [ ] args) {
        SimpleDate [ ] dArray = new SimpleDate [4];
        dArray[0] = new SimpleDate (5, 2); // 5/2
        dArray[1] = new SimpleDate (2, 9); // 2/9
        dArray[2] = new SimpleDate (6, 3); // 6/3
        dArray[3] = new SimpleDate (1, 11); // 1/11
        Arrays.sort(dArray);
        for (int k=0; k<dArray.length; k++) {
            System.out.println(dArray[k].toString());
        }
        // should print the dates in chronological order:
        // 1/11, 2/9, 5/2, 6/3
    }
}


