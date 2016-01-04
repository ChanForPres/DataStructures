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
* Variables can also refer to *objects*. For example,
``` public class Dog {
        public int myAge;
        public String myName;
    }
``` <br \>
First, if we create a variable ```Dog d;```, a variable ```d``` becomes a *reference* that points to ```null```. <br \>
Second, if we assign the reference to a ```Dog``` object, ```d = new Dog();```, d points to an object ```Dog``` that contains ```int myAge``` and ```String myName```. What is special about an object is that it can store primitives(```int```) and references(```String```) inside. <br \>
If and only if we see the ```new``` keyword, we create an object. <br \>

Q. Why does it make sense that objects are not stored inside variables, but are only referred to them? <br \>
    - For primitives, it's just 4 byte information that are very simple and easy to store. However, objects contain a lot more information in terms of quantity and variety. So it's easier to reference that by variable instead of storing it.
Q. Why does it make sense that objects are not drawn inside other objects? <br \>
    - Again, every object varies and contains many kinds of big and small data. For consistency and efficiency, it's makes more sense to make objects always reference.
Q. Why isn't the blue object box labeled with the name of the variable, for example ```d```? <br \>
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
}
```

Q. This doesn't work. Why not? <br \>
```otherAccount``` is newly created and simply points to another Account object. So the existing ```otherAccount``` is remained unchanged and still contains the balance.

### Debug.java
Debug some recursive code <br \>
1) Describe all pairs of arguments that correctly returns true.
    * It correctly returns true if ```myString``` is the result of inserting one character at the last index into an argument ```s```. ex) ```abc2, abc``` 
    * It also correctly returns true if the length of ```myString``` is one more than that of ```s```. ex) ```a, ```

2) Describe all pairs of arguments that correctly returns false. 
    * if ```myString.length()``` < ```s.length``` ex) ```ab, abc```
    * if ```myString.length()``` > ```s.legnth + 2``` ex) ```abcd, ab```
    * if ```myString``` doesn't match with ```s``` in terms of character. ex) ```abc, def```

3) Describe all pairs of arguments that incorrectly returns true. <br \>
4) Describe all pairs of arguments that incorrectly returns false. 
    * if ```myString``` is the result of inserting one character at the first index into an argument ```s```. ex) ```2ab, ab```
    * if ```myString``` is the result of inserting one character at the middle index into an argument ```s```. ex) ```a2b, ab```

5) Describe all pairs of arguments that crashes.
    * When either ```myString``` or ```s``` is null.
6) Determine what's wrong: I think creating another Debug class might be problematic. <br \>
When ```myString``` contains one character at an index except for the last index, it goes to ```else``` statement. ```else``` statement creates a new String of ```myString``` except for the first word. The problem is to compare two String objects, we cannot use "==" to compare the String contains. Instead, we should use ".equals" to correctly compare two String objects.


