Lab 6: Inheritance, Interfaces, and Abstract Classes
===
* Inheritance: allowing the reuse of most of an already-defined class that needs an extra method. Programmers can take advantage of already written correct code and avoid reinventing similar codes over and over. *Polymorphism*
* *Abstract classes* and *interfaces*: require that certain methods be included in a given class without specifying how they are implemented.

### Inheritance
*Subclass* extends the *Superclass*(parent). Methods in the superclass can be redefined in the subclass(*Overriding*). In Java, inheritance is set up in a class's header, using the keyword ```extends```. <br \>
```public class SubClass extends SuperClass {
    ...
}```

* If a class has the keyword ```final``` in its header, then it cannot have any subclasses.

* Review the Lingo: my own definition of each word
    * subclass: a class that inherits pre-defined class. It can add more methods or change methods to behave slightly differently but in general, the structure of subclass is based on the class it inherited.
    * superclass: a more general class that shares characteristics with subclass(es).
    * extends: the keyword to signify that subclass inherits superclass in Java.
    * overrides: when subclass redefines methods in the superclass, subclass overrides superclass.

### Extending the ```Counter``` class
#### ```ModNCounter``` extends ```Counter```
* ```ModNCounter``` class will borrow most of the characteristics from ```Counter``` class. The constructor and one method ```increment()``` will be replaced to fit ```ModNCounter```. 

#### Private fields and inheritance
* Subclasses *do not* have access