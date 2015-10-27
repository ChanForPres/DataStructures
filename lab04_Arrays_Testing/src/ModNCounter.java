/**
 * Created by EunjinCho on 9/25/15.
 */
public class ModNCounter {

    private int myCount;
    private int n;

    public ModNCounter(int n) {
        myCount = 0;
        this.n = n;
    }

    public void increment() {
        myCount++;
        if (myCount >= n) {
            reset();
        }
    }

    public void reset() {
        myCount = 0;
    }

    public int value() {
        return myCount;
    }

}
