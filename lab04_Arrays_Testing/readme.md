Lab 4: Arrays, Objects, and JUnit Testing
===
1. Understand a simple data structure: the *array*
2. JUnit Test - *test-driven development*

### Arrays
* An array is an indexed sequence of elements, all the same type. 
* Variable Declaration : We declare an array variable by giving the type of its elemnts, a pair of square brackets and the variable name. We don't specify the length. ```int[] values``` 
* Variable Initialization : To initialize an array, we use the new operator which includes the length. ```values = new int[7]``` 
* Store : ```values[1] = 4```
* Access : Give the name of the array, then supply an index expression. ```values[3]``` If the index is negative or greater than or equal to the length of the array, it throws an exception.

#### Line3.java 
Coordinates become int array. [x1, y1, x2, y2]

#### Line4.java
Coordinates become an array of two Point objects. 

### Argument Passing
#### Copy (Java)
When we pass an argument in Java, it means the argument is **passed by value**. This means that each argument is **copied** into the corresponding paramenter in the method being called. <br \>
    ```int y = 0;
            makeThree(y);
            System.out.println(y); // prints 0```

However, when the arguments are **objects**, the argument to the method is **reference** to the object. More specifically, you copy the **reference**, but not the **object**. <br \>
    ``` int[] origVal = new int[3];
            setFirstToThree(origVal);
            System.out.println(origVal[0]); // prints 3```

### Useful Methods
#### the ```toString``` method
When we create the new class, ```System.out.println``` will output something not useful. So we could add ```toString``` method to the class. 
``` public String toString() {
        return "(" + points[0].getX()
} ```
#### the ```equals``` method
```equals``` method determines whether two objects contain the same information. For any class we write, we can define our own ```equals``` method. (Default ```==``` operator determines whether its operands reference the same object. But this might not be very useful for some cases) <br \>
``` public boolean equals (Object obc) {

}```

### Testing with JUnit
1. Write test cases that demonstrate everything you want your program to be able to do
2. Write as little code as possible so that all the tests are passed
3. Clean up the code as necessary. Recheck that all tests still pass

#### Testing Principles
1. Test values should exercise every statement in the program
2. Test various *paths* through the program

#### Glass-box Testing vs. Black-box Testing
