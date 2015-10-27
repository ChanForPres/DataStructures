import java.util.HashSet;
import java.util.LinkedList;

public class JugContents {

    public int jugs[];	// contents of the three jugs.
    
    public JugContents (int amt0, int amt1, int amt2) {
        jugs = new int [3];
        jugs[0] = amt0;
        jugs[1] = amt1;
        jugs[2] = amt2;
    }
    
    public JugContents (JugContents b) {
        jugs = new int [3];
        jugs[0] = b.jugs[0];
        jugs[1] = b.jugs[1];
        jugs[2] = b.jugs[2];
    }
    
    public String toString() {
        return "Configuration = (" + jugs[0] + "," 
            + jugs[1] + "," + jugs[2] + ")";
    }
    
    // YOUR CODE HERE
    @Override
    public boolean equals(Object obj2) {
        JugContents another = (JugContents) obj2;
        if (jugs[0] == ((JugContents) obj2).jugs[0] && jugs[1] == ((JugContents) obj2).jugs[1] && jugs[2] == ((JugContents) obj2).jugs[2]) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int c = 0; c < 3; c++) {
            hash += jugs[c];
        }
        return hash;
    }


}
