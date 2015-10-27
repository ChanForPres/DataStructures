import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;
	HashMap <Integer, Integer> fibHashMap = new HashMap<Integer, Integer>();

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
		fibHashMap.put(0, 0);
		fibHashMap.put(1, 1);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			int returnValue = 0;
			if (fibHashMap.containsKey(n)) {
				return fibHashMap.get(n);
			} else {
				returnValue = fib(n-1) + fib(n-2);
				fibHashMap.put(n, returnValue);
			}
			//int returnValue = fib(n-1) + fib(n-2);
			// At first I thought this is an answer, but it wasn't complete
			//returnValue = fibHashMap.get(fib(n-1)) + fibHashMap.get(fib(n-2));
			return returnValue;
		}
	}

}
