import java.awt.*;
import java.util.ArrayList;

/**
 * Created by EunjinCho on 2016. 3. 2..
 */
public class StringExpr {
    public static String multiply(String s, int N) {
        String results = s;
        for (int i = 0; i < N; i++) {
            results += s;
        }
        return results;
    }

    public static String fastMultiply(String s, int N) {
        StringBuilder results = new StringBuilder(s); // create a StringBuilder from s
        for (int i = 0; i < N; i++) {
            results.append(s); // use StringBuilder's fast append
        }
        return results.toString(); // turn the StringBuilder back into a String
    }

    public static void printPoints(ArrayList<? extends Point> points) {
        Point p = points.get(0);
        /*Point p = new Point(0, 2);
        points.add(p);*/
        System.out.println(p);
        /*for (Point p: points) {
            System.out.println(p.getX());
        }*/
    }

    public static void main(String[] args) {
        /*long tStart = System.nanoTime();
        multiply("hello", 62900);
        long tEnd = System.nanoTime();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = (double) tDelta / 1000000000.0;
        System.out.println(elapsedSeconds);

        long fStart = System.nanoTime();
        fastMultiply("hello", 100000000);
        long fEnd = System.nanoTime();
        long fDelta = fEnd - fStart;
        double felapsedSeconds = (double) fDelta / 1000000000.0;
        System.out.println(felapsedSeconds);*/

        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 1));
        printPoints(points);
    }

}
