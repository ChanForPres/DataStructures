Lab 7: Exceptions & Invariants
===

An exception can be **thrown**(passed to another part of the program to handle) or **caught**(handled on its own).

### Exceptions
1. Bugs in my programs might create inconsistent structures or erroneous method calls (e.g. division by zero/ indexing out of bounds/ dereferencing a null pointer)
2. Incorrect user input (e.g. non-numeric input/ search failures where a command or keyword is mispelled)

Q. Assuming we can detect the occurrence of an error and at least print an error message, the programmer may wish to pass information about the error back to the calling method with the hope that the caller can provide more appropriate and useful information about the root cause of the error. Compare four *error-handling approaches*. <br \>
* Don't pass back any information to the caller at all. Just print an error message and halt the program. : Not Good. This is not very useful to understand more specific reason behind the error.
* Detect the error and set some *global error indicator*? to indicate its cause.
* Require the method where the error occurs as well as all methods that call it, to *pass back an extra argument object*? that can be set to indicate the error. : I am not sure about the extra argument, but it sounds fairly complicated to handle all the related methods
* Exceptions

#### Exceptions
* Allow **signaling** of an error & selective **handling** of the error. Methods called between the signaling method and the handling method need not be aware of the possibility of the error. 
* An exception is **thrown** by the code that detects the exceptional situation, and **caught** by the code that handles the problem.
* When a method throws an exception: that exception must be dealt with by whatever method(exception method). This method can choose to either **catch** the exception and **handle** it, or choose to throw the exception **again**, passing it on to the method that called it. <br \>
= (Hot potato) When a method generates an exception, it doesn't want to deal with it so it throws the exception to the next method. That method must do something when it receives the exception. It can either handle it, or throw it to the next method. Methods keep throwing the exception until one is willing to catch. If no methods end up catching, the Java program will crash. <br \>
= Exception is thrown when the exceptional event occurs -> keep throwing -> until caught -> otherwise stops the program
* Runtime exception: Null Pointer Exception/ Buffer Overflow Exception/ Illegal Argument Exception (- Number Format Exception) + I/O Exception: File Not Found Exception + User Defined Exception 

#### Catching the exception
Catching = handling whatever exceptional condition has arisen (done with a ```try catch``` block) <br \>
``` try {
        // code that might produce the exception
    } catch {
        // code to handle the exceptional situation
    }```
Good when dealing with user input.

* Throwing exception: ```throw new exceptionName```
* User-defined exception: by extending ```Exception``` or ```RuntimeException```. Each has two constructors to override, one without any argument and the other with a string argument in which an error message is stored.

#### Time.java
1. Throw and catch ```IllegalArgumentException``` + too many leading zeroes in the hours or minutes/ values for hours and minutes that are out of range

### Consistency Checkers
The concept of using self-monitoring methods to alert when bugs appear. The concept of **invariants** with **exception throwing**, **catching**, and **handling** to check an object's internal state for **inconsistencies**.

#### Tic-Tac-Toe boards
My own thoughts on legal Tic-Tac-Toe boards <br \>
1. # of Xs = # of Os + 1 (for every odd turn)
2. # of Xs = # of Os (for every even turn)
3. Invalid when both wins

* Every opoeration that changes the board, the consistency checker ```isOK``` is called. Then if the object being checked is not internally consistent, ```isOK``` should **throw an exception** that can be **handled** by the method that called it or by the tests.

#### Dates.java
The three integers are instance variables of a ```Date``` class. ```isOK``` method throws an ```IllegalStateException``` if the instance variable's values do not represent a legal date.

#### NumberGuesser.java
This class uses the *binary search* technique to guess the user's number. From the sorted values, we find a particular value by lowering ```high``` or by rasing ```low``` at each guess. <br \>
```isOK``` method makes sure 
* that 0 <= ```low``` <= ```high``` <= 20.
* Each unsuccessful guess cannot appear again

But the number-guessing code and the ```isOK``` method are inconsistent. 
* It was ```low = guess;``` in ```NumberGuesser.java```. This is buggy because it cannot guess when the number is **20**. This can be fixed if we **add 1** like ```low = guess+1;```

#### Invariant Relationships
Formally, ```isOk``` methods are verifying **invariants**(relationships between variable)

1. **Class invariants** : relate values of **instance variables** to one another. Also known as *data invariants*, the "not varying" property is set up initially in the class constructors. This "not changing" property should hold between calls to the other methods (at least by the time the method exits)
    ex) Tic-Tac-Toe board, Date example

2. **Loop invariants** : relate values of variables in a **loop** or **recursion**. The "not varing" property is set up the first time at the start of a loop or at the initial call to a recursive method and should hold at the **end** of each loop iteration. This invariant relationship may get invalidated temporarily, it should be restored by the end of the iteration.
    ex) The buggy number guesser

#### Loop Invariants with Array Processing (XsBeforeOs.java)
ex) Moving X's to Precede O's

1. Loop through the array with an index variable ```k```, maintaining the invariant that all the X's in the first ```k``` elements precede all the O's. + keep track of the last X position ```lastXpos```

2. If ```k``` is O, the invariant is easy to extend. Just move by one.

3. If ```k``` is X, we exchange element ```k``` with the position of the first O.

* ```isOK``` method should check the first ```k``` elements of ```values``` all the Xs precede all the Os. If this consistency is not satisfied, throw an ```IllegalStatementException```. 
* ```isOK``` and ```rearrange``` methods are complete.

#### InsertionSort.java
At the start of the kth time through the loop, the leftmost ```k``` elements of the values array are **in order**. Both ```isOK``` and ```insert``` methods are completed.
* ```isOK``` throws an ```IllegalStateException``` when elements ```0``` through ```k``` of list are not sorted. It also throws an exception if ```k``` is negative or exceeds the maximum list index.

Again, the ```exception``` is **thrown** during the real programming like class or ```isOK``` method, and we can ```try/catch``` during ```main``` or ```test``` methods.

