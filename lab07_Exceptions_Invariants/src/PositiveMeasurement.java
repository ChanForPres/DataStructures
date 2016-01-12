/**
 * Created by EunjinCho on 1/12/16.
 */
public class PositiveMeasurement {
    private int myFeet;
    private int myInches;
    public PositiveMeasurement(int feet, int inches) {
        if (feet < 0 || inches < 0) {
            throw new IllegalArgumentException("Feet and Inches must be positive"); // 2
        }
        this.myFeet = feet;
        this.myInches = inches;
    }

    public static void main(String[] args) {
        PositiveMeasurement m = null;
        try {
            // The exception is thrown by the constructor
            // and goes immediately to the first catch
            m = new PositiveMeasurement(-1, 5);
            System.out.println("This line will never executed");
        } catch (IllegalArgumentException e) {
            System.err.println("Caught an exception"); // 1
            System.err.println(e.getMessage()); // will print "Feet and Inches must be positive"
            if (m == null) {
                System.err.println("m is null"); // 3
            }
        }
    }
}


