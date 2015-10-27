import junit.framework.TestCase;

/**
 * Created by EunjinCho on 10/2/15.
 */


public class TimeTest extends TestCase {


    public void testConstructor() {
        String[] timeArgs = {null, "x", "x:", ":x", "x:y", "1:", ":30",
                "4: 35", "55:00", "11:99", " 3:30", "00004:45", "4:007", "4:7",
                "4 :09", "3:30", "11:55"};
        Time[] correctTimes = {null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                new Time (3, 30), new Time (11, 55)};

        for (int k = 0; k < timeArgs.length; k++) {
            try {
                Time t = new Time(timeArgs[k]);
                assertEquals(correctTimes[k], t);
            } catch (NullPointerException e) {
                System.out.println("Invalid military time format: null");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Invalid military time format: Index out of bounds");
            } catch (NumberFormatException e) {
                System.out.println("Invalid military time format: wrong format");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid military time format: wrong numbers");
            }
        }
    }
}