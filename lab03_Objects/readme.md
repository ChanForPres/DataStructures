Lab 3: Objects
===
1. Understand the declaration and use of reference-typed variables (box-and-pointer diagrams)
2. Separate "tester" class
3. Debugging

### Box and Pointers
#### Primitives (Value)
* When we declare a *primitive* like ```int```, ```boolean```, ```float```, ```char```, we draw a box and label the box with the type of primitive and the name of the variable.
* When we assign value to the primitive, it's pass by value. 

#### Objects (Reference)
* Variables can also refer to *objects*. For example, <br \>
``` public class Dog {
        public int myAge;
        public String myName;
    }```
First, if we create a variable ```Dog d;```, a variable ```d``` becomes a *reference* that points to ```null```. <br \>
Second, if we assign the reference to a ```Dog``` object, ```d = new Dog();```, d points to an object ```Dog``` that contains ```int myAge``` and ```String myName```. What is special about an object is that it can store primitives(```int```) and references(```String```) inside. <br \>
If and only if we see the ```new``` keyword, we create an object. <br \>

Q. Why does it make sense that objects are not stored inside variables, but are only referred to them?
    - For primitives, it's just 4 byte information that are very simple and easy to store. However, objects contain a lot more information in terms of quantity and variety. So it's easier to reference that by variable instead of storing it.
Q. Why does it make sense that objects are not drawn inside other objects?
    - Again, every object varies and contains many kinds of big and small data. For consistency and efficiency, it's makes more sense to make objects always reference.
Q. Why isn't the blue object box labeled with the name of the variable, for example ```d```?
    - ```d``` is an object that points to ```new Dog()``` not ```d```. So it makes more sense that the blue object doesn't associate with the name ```d```.

### Line1.java
* This is a class representing a line segment as a pair of points (x1, y1) and (x2, y2), where all ```x1```, ```y1```, ```x2```, ```y2``` are integer instance variables.

### Line2.java
* Unlike Line1, this class represents a line segment as a pair of *points* that uses the ```Point``` class supplied in the ```java.awt``` class library.

### Account.java
* This class allows deposits and withdrawals. For more money than the account contains, the Account class disallows a withdrawal request.
* withdraw : Overdraft protection by including ```parentAccount``` instance variable and constructor.
* merge : It takes the argument account ```anotherAcct```. Even though ```myBalance``` variable is private, we can access both of the accounts balance information. (demonstrating the limits of private access)

```
public void merge (Account otherAccount) {
    this.myBalance = this.myBalance + otherAccount.myBalance;
    otherAccount = new Account (0);
}```