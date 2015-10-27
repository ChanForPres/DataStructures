Lab13: Hashing2
===

# Hashing Different Objects
## Tic-Tac-Toe board
I created HashSet of TTTboard object instead of more common Integer or String object. 
I tried to insert all possible configurations of Tic-Tac-Toe boards of 9x9 size and compare the time spent. 
The problem here is finding a *good* hashcode. If I just return 0 for a particular TTTboard object, it took about 4000 milliseconds.
However, if I convert the board to a *String* and use the *String hashCode* function, it took only about 26 milliseconds.
In addition, if I interpret the board as a nine-digit base 3 numeral and convert "-" to 0, "X" to 1 and "O" to 2, and return the corresponding integer as the hash value, it took about 40 milliseconds.

## Properties of Good Hash Codes (non-exhaustive list):
* For all objects that are .equals, their hashCode method MUST return the same value
* hash code values should be spread as evenly as possible over all ints
* hash code should be relatively quick to compute
* hash code must be deterministic (not random)

# Hashing for Memoization
## Fibonacci/ The jug problem
Sometimes, infinite recursion results occur and that's not good. In this case memoization is helpful. 
Memoization allows redundant recursive calls to be eliminated by saving the result of each *new call* and then looking up the result instead of recomputing it.
*HashMap* and *HashSet* are helpful and quick methods to save these information.
