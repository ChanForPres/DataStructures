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
The concept of using self-monitoring methods to alert when bugs appear. The concept of **invariants** with **exception throwing**, **catching**, and handling
