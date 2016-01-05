Lab 5: Arrays & Collection Classes
===

### Variable Scope in Java
Variables can only be used within their scope.
* Local Variable: has a scope that limits within the class, method, and loop. Limited by the smallest scope that applies to the variable.

### ArrayOperations.java
#### The ```insert``` method
Insert integer element at the given position in the argument array and shift all the subsequent elements up to make room for it.

#### The ```delete``` method
Delete int element at the given position in the array and the subsequent elements are moved down one position. The value 0 is assigned to the last array element.

#### The ```zip``` method
Given two int arrays ```array1``` and ```array2``` of the same length, ```zip``` returns an array that is twice as long, in which the elements of ```array1``` and ```array2``` are interleaved.

Q. Opportunities for Errors? I think array might have problems regarding indexing. Since the array is fixed in terms of the size, it might cause problems with ArrayOutOfBounds exception.

### Collection Classes
The most commonly used collections are *sets*(unordered) and *sequences*(ordered).

#### Set.java
A set of nonnegative integers may be represented as a boolean array with element ```k``` being ```true``` if ```k``` is in the set and ```false``` otherwise.

#### IntSequence.java
Previously in array implementation, we will not be able to tell the difference betweene a value zero that we intentionally need and the default value zero when the array is initialized. One solution to this problem is using **counter** as an instance variable and keeps track of the number of values that are in use.
* The ```add``` method
* The ```toString``` method
* The ```remove``` method