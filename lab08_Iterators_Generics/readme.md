Lab 8: Generics and Iterators
===
### Generics
Generics enable types to be parameters when defining classes and methods. **Type** parameters provide a way for you to **re-use** the same code with different inputs.

#### Writing a Generic Class
Example class Box using ```T``` as a generic type 
```public class Box<T> {
        private T myItem;
        
        public void putItem(T item) {
            myItem = item;
        }
        
        public T getItem() {
            return myItem;
        }
}```
Then when you create a box, you can pass in any types of objects to place of ```T```. To declare that a class is **generic** and that it will be using something like ```T```(or ```E```, or anything you'd like), just include the ```<T>``` in the class header.

Q. Would ```Box<Point> b5 = new Box<TracedPoint>();``` work? <br \>
No. ```Point p = new TracedPoint(3, 4);``` works but ```Box<Point>``` is not considered a superclass of ```Box<TracedPoint>```.

#### Generic Interfaces
#### Generic ```IntSequence.java``` called ```Sequence.java```
* One caveat: You *cannot* create an array with a generic **dynamic** type. Only its **static** type can be generic.

### Iterators
To **process every item** in a certain data structure other than the sequence(which is easily done by ```for``` loop), we need a more *abstract* notion, something that allows us to check every item **regardless** of how they're organized. **Iterator** is something we need. <br \>
The core idea is that we process an item and ask for the **next** one, then repeat until there is no next item.

#### Iterator methods
1. ```initIterator``` : initializes the iteration. Call this whenever a new iteration is started. 
2. ```hasNext``` : a boolean method that says whether there are any more remaining items in the data structure
3. ```next``` : successively returns items in the data structure one by one.

#### PointUtils.java
Iterate using iterators. All java collections have one including the class ```PointUtils```. Iterators don't use a ```for``` loop. Iterator starts with the following statement ```Iterator<Point> pointIterator = points.iterator();```. Then I completed methods that find largest Y value and that returns the centroid of all given points.

#### Sequence.java
Write my own Iterator. The ```hasNext``` method **doesn't change any state**. It only examines existing state by comparing the progress of the iteration to the number of sequence elements. <br \>

Q. What's the invariant relation that's true between calls to next? It should only return one value.  <br \>
Q. Why is the first version more preferrable to the second version? One difference between these two is that the ```indexToRtn``` starts either from 0 or -1. And another difference is that the first version creates a variable that will be returned and increment the index while the second version doesn't create any variable. It increments the index first and then directly return it from ```values```. I first think starting from -1 a little odd. Moreover, I'm concerned if directly returning a value from ```values``` in the ```next``` method would create any problems.

#### Set.java
Create ```public void initIterator()``` method, ```public boolean hasNext()``` method, and ```public int next()``` method.

### Using Java's ```Iterator``` and ```Iterable```
Directly adding iterator methods like ```Set.java``` is a simplistic approach. The more common approach is to use a **seperate iterator object** that has those methods.

#### A more abstract ```for``` loop
Like Python's ```for item in lst``` statement, there's similar syntax in Java, ```for (String item : lst)```. This works with the same with **arrays** and **ArrayList** and **more**.

#### Implement ```Iterable```
Java have a ```for``` loop that can be used to process items in different kinds of data structures. How? to make Java iterate over an object of a certain class, that class must implement the interface ```Iterable```. When we implement ```Iterable```, a class must define ```public Iterator iterator()``` method. ```iterator()``` method **initializes** an iteration by **returning an object type** of ```Iterator```. <br \>
So in short, the bigger class implements ```Iterable``` which has a method ```iterator()``` that returns another class that impelments ```Iterator```.  <br \>
To implement ```Iterator<E>``` interface, we need familiar methods: ```public boolean hasNext()```, ```public E next();``` and ```void remove();```. Then, you can iterate using special ```for``` loop easily.

#### Sequence.java
Sequence.java has ```Sequence<T>``` class that implements ```Iterable<T>```. Then inside ```Sequence<T>```, I created a nested private class that implements ```Iterator<T>```



