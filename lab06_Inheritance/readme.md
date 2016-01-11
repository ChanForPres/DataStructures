Lab 6: Inheritance, Interfaces, and Abstract Classes
===
* Inheritance: allowing the reuse of most of an already-defined class that needs an extra method. Programmers can take advantage of already written correct code and avoid reinventing similar codes over and over. *Polymorphism*
* **Abstract classes** and **interfaces**: require that certain methods be included in a given class without specifying how they are implemented.

### Inheritance
**Subclass** extends the **Superclass**(parent). Methods in the superclass can be redefined in the subclass(**Overriding**). In Java, inheritance is set up in a class's header, using the keyword ```extends```. <br \>
```public class SubClass extends SuperClass {
    ...
}```

* If a class has the keyword ```final``` in its header, then it cannot have any subclasses.

* Review the Lingo: my own definition of each word
    * subclass: a class that inherits pre-defined class. It can add more methods or change methods to behave slightly differently but in general, the structure of subclass is based on the class it inherited.
    * superclass: a more general class that shares properties with subclass(es).
    * extends: the keyword to signify that subclass inherits superclass in Java.
    * overrides: when subclass redefines methods in the superclass, subclass overrides superclass.

### Extending the ```Counter``` class

#### ```ModNCounter``` extends ```Counter```
* ```ModNCounter``` class will borrow most of the characteristics from ```Counter``` class. The constructor and one method ```increment()``` will be replaced to fit ```ModNCounter```. 

#### Private fields and inheritance
* Subclasses **do not** have access to the **private** variables of their superclases. In this case, we can use the keyword ```protected``` instead of ```private``` if we want to allow **subclasses* to access the variables, but **not** allow any other classes.
* If a subclass overrides a method from its superclass, it can still call the **original** method by prefacing the method name with the ```super``` keyword.
* For ```ModNCounter``` class, to access private variable ```myCount```, I called ```super.value()```, which will return the private variable's value, ```myCount```. 

### Extending the ```Point``` class
We can extend classes that we haven't written ourselves (ex. Java API) as long as they are not declared as ```final```.

#### Constructor
When we write the constructor for subclass, Java automatically supplies a call to the superclass constructor with no argument. Then there are **two** possible options we can think. One is to call a constructor of the superclass by using ```super``` keyword. Another option is to call a constructor with ```this``` keyword. For example, if you want the zero-argument constructor, you can initialize to (0,0) by writing ```public TracedPoint() { this(0,0); }```.

#### Method
Similarly, we can call superclass methods using ```super``` keyword. ```super.method()``` calls the method in the parent class and ```this.method()``` calls the method in the current class.

### Static & Dynamic Type

#### Polymorphism
* Given object can be regarded either as an instance of its **own class**, as an instance of its **superclass**, as an instance of its **superclass's superclass**, and so on up the hierarchy. 
* For a method argument or return type, we can supply instead an instance of any subclass. That's because inheritance implements an **"is-a"** relationship(e.g. ```ModNCounter``` *is* a ```Counter``` with some extra properties. As an example, imagine you have a method in some class (not necessarily ```Point``` or ```TracedPoint```) <br \>
``` public static void moveTo79(Point p) {
        p.move(7,9);
}```
We can call ```moveTo79``` and pass in **either** a ```Point p``` object or a ```TracedPoint p``` object. And if we pass in a ```TracedPoint``` object, the code will use the ```move``` method that you implement in ```TracedPoint```. <br \>

Q. Would I expect the substitution mechanism to work in reverse? For example, if we have 
``` public static void anotherMoveTo79(TracedPoint p) {
        p.move(7,9);
}
Point p = new Point(3,4);
anotherMoveTo79(p);``` 

No. Because there are some extra properties in ```TracedPoint``` that are not present in ```Point``` class. So I think it might cause compile-time error.

#### Polymorphic Data Structures
The class ```Object``` is the root of the inheritance hierarchy, so every class inherits from ```Object``` at least indirectly. So ```ArrayList<Object> a = new ArrayList<Object> ();``` essentially take any classes like ```TracedPoint```, ```Point```, ```String``` and so on. <br \>
* One problem that might occur is if we create ```ArrayList<Object> a``` and add some ```Point``` and ```TracedPoint``` class elements, we cannot use ```move``` method by writing ```a.get(1).move(6,9)```. The Java compiler is looking not at the ```Point``` and ```TracedPoint``` but at the ```Object``` class. 
* Solution: ```Point p2 = a.get(k); p2.move(6,9);``` I thought this would work because it clearly signifies that p2 is of a ```Point``` class. However, the compiler still thinks that ```a.get(k)``` is of the ```Object``` class. Therefore, we need to provide extra information by casting. ```Point p2 = (Point) a.get(k); p2.move(6,9);```

#### Compiler & Runtime System Tasks
* Compiler: **Asks** *whether* a method with a given name can be invoked on a given object.\
    * ```p.move(7,9)```: The compiler first examines the **static type** of p. if the static type of p contains a ```move``` method with two arguments, then the statement is legal.
    * ```a.get(1).move(7,9)```: The compiler examines the type of ```a.get(1)``` then searches for a ```move``` method either in the corresponding class or its superclass. 
    * **Problem**: However, according to ```ArrayList```, the ```get``` method retruns an ```Object```, and this is what the compiler sees as the **static type**. ```Object```s don't have a ```move``` method; thus we get the error message.
    * **Solution**: if we write ```Point p = a.get(1); p.move(7,9);```, this attempts to take a reference to an ```Object``` and store it in ```Point``` reference. Since almost all ```Object```s are not ```Point```s, this is not allowed. However, since we know that there will always be ```Point``` class, we can tell the compiler by a **type cast**. A type cast *temporarily* changes the **static type** of a variable and tells the compiler to trust the programmers.
* Runtime: **Find** which of the possible versions of that method is the right one to invoke.
    * Once the compiler satisfies that a ```move``` method is available for an object, the runtime system selects the most **specialized** ```move``` method to call baased on the **dynamic type**.
* Two caveats: <br \>
    1. If you're trying to call a **static** method, **only** the **static** type is used.
    2. The method used at **runtime** must be a **direct override** of the method found at **static** type. So the name of the method and the argument list must match *exactly*.


#### Static and Dynamic Type
* Correct ones: 
    * ```Dog d = new Dog();``` (o)
    * ```Animal d = new Dog();``` (o)
    *The one on the **left** ```Animal``` is **static type** of the variable. It is the type that the reference is **declared** as. The one on the **right** ```Dog``` is **dynamic type** of the variable. It is the type of the object that is **constructed**.
    * The static type = same as the dynamic type/ superclass of the dynamic type/ superclass of the superclass of the dynamic type(and so on)/ interface that the dynamic type implements
    * The static type **cannot** be a subclass of the dynamic type.

### Abstract Classes
An abstract class **cannot** be instantiated; it can **only** be extended. It typically contains abstract methods that *must be overridden* by the extending class. In this way, JAva allows us to enforce the **provision** of a class without supplying any **details** about how the class will work. (Those details are supplied in the extending class) <br \>
``` public abstract class Animal {
        public abstract void eat();
        public abstract void sleep();
        public abstract void makeNoise();
}```
An abstract class may include non-abstract methods. Any class that has an abstract methods **must** be an abstract class.

Q. Reorganizing the Abstract Class: What are the pros and cons of eliminating the ```Date``` class entirely and copying its concrete methods into ```GregorianDate``` class and having ```FrenchRevolutionaryDate``` extend the updated ```GregorianDate``` class? <br \>
* Pros: Save memory by removing the abstract class/ 
* Cons: It can be really confusing since this is technically not an 'is-a' structure. For example, if we have Tiger class that extends Dog class, it doesn't really make sense because these are two different kinds of classes that have a lot of different methods. Eventually, it will create a lot of confusions(e.g. what if we have the Crow class?) and redundancies(due to its unclear structure) 

### Interfaces
An interface is like an abstract class except it **only** has **abstract** methods and it **cannot** have **instance variables**. Essentially, an interface is a collection of method header declarations. To call the interface is by writing ```implements```. A class can implement **multiple** interfaces (but it can only extend **one** class) and supply definitions of all its methods. For example, the Java ```Integer``` class implements both the ```Serializable``` and ```Comparable<Integer>``` interfaces. <br \>
Interfaces can ```extend``` other interfaces. (not ```implement```)
#### Sorting an array
Sorting involves comparing array elements, so if we have an array of **objects** of a certain class, that class needs to define some way to **compare** the objects in it. = Having the class implement ```Comparable``` interface and supply a method named ```compareTo``` <br \>

### A Better IntSequence, ResizableIntSequence
the ```ResizableIntSequence``` increases its capacity to accomodate for the new elements.
- Dynamically increase a ```ResizableIntSequence``` by one once it has reached its maximum capacity.
- Dynamically decrease a ```ResizableIntSequence``` once it contained less than 1/3 of its maximum capacity. This is to keep around all of its unused space. The sequence will be shrunk by half.




