Lab13: Hashing2
===
Dealing with hashing of non-strings, particularly involving **memoization**, a technique for avoiding repeated computation.

### Hashing Different Objects

#### 2 ways of **hashing Tic-tac-toe Boards**
    * Convert the board to a ```String``` and then use the ```String hashCode``` fuctions
    * Interpret the TTT board as a nine-digit base 3 numeral and return the corresponding integer as the hash value

Q. Which hash function performs better? <br \>
* I created HashSet of TTTboard object instead of more common Integer or String object. 
* I tried to insert all possible configurations of Tic-Tac-Toe boards of 9x9 size and compare the time spent. 
The problem here is finding a *good* hashcode. If I just return 0 for a particular TTTboard object, it took about 4000 milliseconds.
However, if I convert the board to a *String* and use the *String hashCode* function, it took only about 26 milliseconds.
In addition, if I interpret the board as a nine-digit base 3 numeral and convert "-" to 0, "X" to 1 and "O" to 2, and return the corresponding integer as the hash value, it took about 40 milliseconds.

According to the properties of good hash codes: <br \>
    * For all objects that are ```.equals```, their ```hashCode()``` must return the same value
    * hash code values should be spread as evenly as possible over all ints
    * hash code should be relatively quick to compute
    * hash code must be deterministic (not random)

It seems like converting to ```String``` makes hashing spread as evenly as possible and its already implemented optimal ```hashCode()``` makes it faster.

Q. Why is random hash code not good? I think it becomes very inefficient when we try to ```get``` or ```compare``` methods when we reuse ```hashCode()``` method. <br \>

### Hashing for Memoization
#### Memoization for Fibonacci ```Fibonacci.java```
Sometimes, infinite recursion results occur and that's not good. In this case memoization is helpful. 
Memoization allows redundant recursive calls to be eliminated by saving the result of each *new call* and then looking up the result instead of recomputing it. **HashMap** and **HashSet** are helpful and quick methods to save these information.

#### The Jug Problem ```JugSovler.java```, ```JugContents.java```
2 Students have a jug filled with 8L of water that they wish to divide evenly between them. They have two empty jugs with capacities of 5L and 3L respectively. These jugs are unmarked and no other measuring device is available.  

#### Binary Search ```BinarySearch.java```
Given 31 integers, modified the code to count the number of times a comparison is made between ```values[middleIndex]``` and ```key```. <br \>
Then suppose that the 31 integers are stored in a **chained hash table with k chains**. We want to **find** every value in the hash table and count the # of comparisons.