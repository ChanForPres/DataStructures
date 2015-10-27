/**
 * Created by EunjinCho on 10/1/15.
 */

public class ModNCounter extends Counter {
    private int n;

    public ModNCounter(int n) {
        myCount = super.value();
        this.n = n;
    }

    public void increment() {
        myCount++;
        if (myCount >= n) {
            reset();
        }
    }
}
