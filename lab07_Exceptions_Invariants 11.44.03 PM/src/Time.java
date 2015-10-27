/**
 * Created by EunjinCho on 10/2/15.
 */


public class Time {

    private int myHours;
    private int myMinutes;

    public Time (String s) {
        int colonPos = s.indexOf (":");
        myHours = Integer.parseInt (s.substring (0, colonPos));
        myMinutes = Integer.parseInt (s.substring (colonPos+1));
        String myStringRepOfmyHours = String.format("%05d", myHours);
        String myStringRepOfmyMinutes = String.format("%05d", myMinutes);
        if (myHours < 0 || myHours > 23) {
            throw new IllegalArgumentException("Hours should be between 0 and 23");
        } else if (myMinutes < 0 || myMinutes > 59) {
            throw new IllegalArgumentException("Minutes should be between 0 and 59");
        } else if (myStringRepOfmyHours.length() > 2 || myStringRepOfmyMinutes.length() > 2) {
            throw new IllegalArgumentException("Too many zeroes");
        }
    }

    public Time (int hours, int minutes) {
        myHours = hours;
        myMinutes = minutes;
    }

    public boolean equals (Object obj) {
        Time t = (Time) obj;
        return myHours == t.myHours && myMinutes == t.myMinutes;
    }

    public String toString() {
        if (myMinutes < 10) {
            return myHours + ":0" + myMinutes;
        } else {
            return myHours + ":" + myMinutes;
        }
    }
}
