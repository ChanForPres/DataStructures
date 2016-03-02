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

    public static void main(String[] args) {
        /*long tStart = System.nanoTime();
        multiply("hello", 62900);
        long tEnd = System.nanoTime();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = (double) tDelta / 1000000000.0;
        System.out.println(elapsedSeconds);*/

        long fStart = System.nanoTime();
        fastMultiply("hello", 100000000);
        long fEnd = System.nanoTime();
        long fDelta = fEnd - fStart;
        double felapsedSeconds = (double) fDelta / 1000000000.0;
        System.out.println(felapsedSeconds);
    }

}
