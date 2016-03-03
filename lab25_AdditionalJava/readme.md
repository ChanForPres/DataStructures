Lab 25: Additional Java
===
1. Go over a class known as ```StringExpr```
2. Investigate *wildcard types*
3. Learn about packages, importing, running Java from the terminal
4. Learn about JAR files

### String Immutability
In Java, Strings are *immutable*, so once a String is created, it can never be changed. This matters, because every time you append to a String, you have to create an entirely new String, which takes **time** proportional to the length of the whole new String. So if there's a really long String like a length of 1000000000, and then if we were to append a single character to the end, it will take time proportional to 1000000001.

Because adding Strings together is slow, Java has a class called ```StringExpr```, which is kind of like a String, but is **mutable**, and thus support **fast appending**. The following is an example:
```
public static String fastMultiply(String s, int N) {
    StringExpr results = new StringExpr(s); // create a StringExpr from s
    for (int i = 0; i < N; i++) {
        results.append(s); // use StringExpr's fast append
    }
    return results.toString(); // turn the StringExpr back into a String
}
```
This method takes ```O(N*L)``` because append only takes time proprotional to the length of the String being appended, rather than the size of the whole resulting String.

The difference between using Strings and ```StringBuilder``` is so extreme that Java programmers say you should **never, ever** append to Strings in a loop. **Always** convert to ```StringBuilder``` and convert back to String once appends are done.

**Check out my** ```string_experiments.txt``` **file**.


### Wildcard Types

#### A problem with Generics and Inheritance
Suppose we have a class ```Point```, whih has a subclass ```TracedPoint```

1. It was okay to do ```Point p = new TracedPoint(3, 4);```. 
2. However, it was **not** okay to do ```ArrayList<Point> points = new ArrayList<TracedPoint>();```. Because ```ArrayList<Point>``` is not a superclass of ```ArrayList<TracedPoint>``` 

So suppose we have method like the following: <br \>
```
public static void printPoints(ArrayList<Point> points) {
    for (Point p: points) {
        System.out.println(p.getX());
    }
}
```
Here, we cannot pass in an ```ArrayList<TracedPoint>``` as an argument.

#### Why?
Assume we **can** do the following assignment: ```ArrayList<Point> points = new ArrayList<TracedPoint>();```. Then the compiler, which sees the **static type**, would think that any subclass of ```Point``` can be added to ```points```. 

So we'd be allowed to do, ```points.add(new WeirdPoint(4, 7));```, where ```WeirdPoint``` is a subclass of ```Point```, but not ```TracedPoint```. However, this doesn't make sense, because we know the dynamic type of ```points``` is ```ArrayList<TracedPoint>```. This cannot be allowed to contain ```WeirdPoint```. Therefore, it's **unsafe**.

#### Wildcards
To resolve this problem, Java introduces a special type knwon as a *wildcard type*. Here's the syntax: 

```ArrayList<? extends Point> points = new ArrayList<TracedPoint>();```. <br \>
This means ```points``` is an ```ArrayList``` of some subclass of ```Point```, but I'm not exactly sure which subclass.

Now, we can do the following method, where we can pass in as an argument an ```ArrayList<TracedPoint>```. This method can handle an ```ArrayList``` of any subclass of ```Point```.

```
public static void printPoints(ArrayList<? extends Point> points) {
    for (Point p: points) {
        System.out.println(p.getX());
    }
}
```

#### A Strange Assymetry
Wildcard types have a strange assymetry associated with them. Consider our ```points``` variable in the ```printPoints``` method above. We are allowed to do ```Point p = points.get(0);```, but not allowed to do ```Point p = new Point(0,2); points.add(p);```. 

In other words, we can **get** things from ```points```, but we cannot **put** things into ```points```.

**Check out my** ```wildcards.txt``` **file**.


### Packages
A package is a **grouping of related classes** (and interfaces) that provides **namespace management** and **access protection**. Sometimes packages are simply used for their organizational benefits. 

Packages correspond to the folders a class is in: If your code is in a package named ```proj1```, then it must also be in a folder named ```proj1/```. If your code is in a package named ```cs61bl.proj1```, then it must be in a nested folder ```cs61bl/proj1```

#### Namespace Management and Importing
Namespace management refers to the concept of **preventing naming collisions**. There are a lot of Java files, some of which are bound to have the same name.

Say you're writing a program and you want to use 2 different classes named ```ArrayList```, which were written by 2 different people. The packages they're in can be used to **distinguish** them. All we have to do is **use the complete pacakge name**.

For example <br \>
```
java.util.ArrayList arr1 = new java.util.ArrayList();
cs61bl.ArrayList arr2 = new cs61bl.ArrayList();
```

**Whenever you refer to a class, you must specify the complete package.**

But we haven't called ```java.util.ArrayList``` all the time. And it's because we **imported it** with a statement like ```import java.util.ArrayList;```


### Java through Terminal
#### The classpath
We can run a Java program from any location on our computer. The class file doesn't even have to be in the same folder. The way you could do this is by **adding these places to something called classpath**, which you can set with the ```-cp``` option. 

1. For example if you want to run a class "HelloWorld" in a folder "deep": ```java -cp deep HelloWorld```

2. If you want to search the current directory for a class "HelloWorld": ```java -cp . HelloWorld```

3. If you want to search multiple directories, you can add multiple locations to the classpath and separate them within colons: ```java -cp .:deep:other HelloWorld``` (This would search for the ```HelloWorld``` class in the current directory, ```deep``` directory, and the ```other``` directory.

4. We **DO NOT** use ```java deep/HelloWorld```

5. Java class in a folder is not necessarily in a package/ Java class in a **package** is necessarily in a folder. 
    * Java class just in a folder: ```java -cp deep HelloWorld```
    * Java class in a package called greetings: ```java greetings.HelloWorld``` (Java is smart to look in the ```greetings``` folder, so you don't have to include it in the classpath. This works as long as the ```greetings``` packages is in the current folder) 

Q. Consider the following directory structure:

```
home
  |__emotions
        |__deep
             |__greetings
                    |__friendly
                           |__HelloWorld.class
```
You're in the ```home/``` folder and you want to run ```HelloWorld```. It's in a package ```greetings.friendly```, but just in ```emotions/deep/```. Correct Command is ```java -cp emotions/deep greetings.friendly.HelloWorld```

#### Compiling in Folders and Packages
We mentioned that we could **add multiple different locations to the classpath**. But why?

1. Because it can be useful for compiling one class that depends on other classes in different locations. 
    * e.g. Suppose we're trying to compile the file ```A.java``` which references the classes ```B``` and ```C```. To compile ```A.java```, the classes ```B``` and ```C``` have to be on the classpath as well.

2. Because it can be useful for using packages.
    * e.g. To compile a class ```coolpackage.A``` which references the classes ```oddpackage.B``` and ```weirdpackage.C```, the locations of the packages ```oddpackage``` and ```weirdpackage``` must be on the classpath.

##### !Confusing part 
When we **run Java programs**, we always use the class name (with packages). If the class is in another folder, we have to include that folder on the classpath. <br \>
When we **compile**, however, we use the file name, not the class name. e.g. ```javac deep/HelloWorld.java```

This is different from how we would **run the class**. When running a Java program, ```-cp``` is used to indicate the **location of the class that could be found**. When **compiling a Java file**, ```-cp``` is used to **indicate the location of classes and packages that the file might depend on**.

#### Compiling Experiment
**Check out my** ```compiling.txt``` **file**.


### Using Other people's code






