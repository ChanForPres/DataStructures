import java.util.*;

public class JugSolver {
	private int desiredAmt;
	private int capacity[];
	private HashSet<JugContents> contentsHashset = new HashSet<>();

	public JugSolver (int jug0size, int jug1size, int jug2size, int desired) {
		capacity = new int [3];
		capacity[0] = jug0size;
		capacity[1] = jug1size;
		capacity[2] = jug2size;
		desiredAmt = desired;
	}
	
	// Try to solve the puzzle, starting at configuration b.
	public boolean tryPouring (JugContents jugsObject) {
		System.out.println(jugsObject.toString());

		// If reached the desired amount, tryPouring returns true
		if (jugsObject.jugs[0] == desiredAmt || jugsObject.jugs[1] == desiredAmt
		        || jugsObject.jugs[2] == desiredAmt) {
			return true;
		}

		// if the confiugration is already stored in contentHashset
		// then returns false and
		if (contentsHashset.contains(jugsObject)) {
			System.out.println("CONTAINED");
			return false;
		}
		contentsHashset.add(jugsObject);
		System.out.println("ADDED");

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				// If tryPouring is false AND i == j
				// then the j loop moves, if j moves all the way up to 2, then i moves
				// Otherwise, it's inside pour(jugsObject, i, j)'s tryPouring method (recursive)
				if (i != j && tryPouring(pour(jugsObject, i, j))) {
					System.out.println ("FINAL: Pouring from jug " + i + " to jug " + j);
					return true;
				}
			}
		}
		return false;
	}
	
	// Return the result of pouring as much as possible from jug from to jug to.
	// capacity = [jug0size, jug1size, jug2size]
	public JugContents pour (JugContents current, int from, int to) {
		JugContents afterPour = new JugContents (current);
		int amtFromCanSupply = current.jugs[from];
		System.out.println("amt jug "+ from + " can give " + amtFromCanSupply + " to " + to);
		int amtToCanGet = capacity[to] - current.jugs[to];
		System.out.println("amt jug "+ to + " can get " + amtToCanGet);
		int amtPoured = min (amtToCanGet, amtFromCanSupply);
		debugPrint ("Pouring " + amtPoured 
			+ " from jug " + from + " to jug " + to);
		System.out.println();
		afterPour.jugs[from] -= amtPoured;
		afterPour.jugs[to] += amtPoured;
		return afterPour;
	}
		
	static int min (int x, int y) {
		if (x < y){
			return x;
		}
		else {
			return y;
		}
	}

	private static boolean DEBUGGING = true;

	private static void debugPrint (String s) {
		if (DEBUGGING) {
			System.out.println (s);
		}
	}

}