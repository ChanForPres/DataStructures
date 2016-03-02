Lab 24: Additional Java
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













