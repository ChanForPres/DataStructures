/**
 * Created by EunjinCho on 10/2/15.
 */


public class ResizeableIntSequence extends IntSequence {
    public ResizeableIntSequence(int capacity) {
        super(capacity);
    }

    public void add(int toBeAdded) {
        if (myValues.length == myCount) {
            ResizeableIntSequence newValues = new ResizeableIntSequence(myValues.length + 1);
            for (int i = 0; i < myValues.length; i++) {
                newValues.myValues[i] = this.myValues[i];
            }
            this.myValues = newValues.myValues;
            super.add(toBeAdded);

        } else {
            myValues[myCount] = toBeAdded;
            myCount++;
        }
    }


    public void insert (int pos, int newInt) {
        if (pos < 0 || pos >= myValues.length) {
            System.err.println("Invalid place to insert");
            System.exit(1);
        } if (myValues.length == myCount) {
            ResizeableIntSequence newValues = new ResizeableIntSequence(myValues.length + 1);
            for (int i = 0; i < myValues.length; i++) {
                newValues.myValues[i] = this.myValues[i];
            }
            this.myValues = newValues.myValues;
            super.insert(pos, newInt);
        } else {
            for (int i = myValues.length-1; i> pos; i--) {
                myValues[i] = myValues[i-1];
            }
            myValues[pos] = newInt;
        }
    }


    public void remove(int pos) {
        if (pos < 0 || pos >= myValues.length) {
            System.err.println("Invalid place to remove");
            System.exit(1);
        } else {
            for (int i = pos; i < myValues.length; i++) {
                if (i == myValues.length-1) {
                    myValues[myValues.length-1] = 0;
                    break;
                }
                myValues[i] = myValues[i + 1];
            }
            myCount--;

            // Decreasing Capacity
            // Where the threshold is when real integers are not even
            // 1/3 of the real capacity
            if (myCount * 3 <= myValues.length) {
                ResizeableIntSequence newValues = new ResizeableIntSequence((myValues.length-myCount)/2);
                for (int i = 0; i < myCount; i = i + 1) {
                    newValues.myValues[i] = this.myValues[i];
                }
                this.myValues = newValues.myValues;
            }
        }
    }

}
