Lab 10: More LinkedLists; Destructive List Manipulation
===

First I worked on **non-destructive** operations that returns a whole new list, then I worked on **destructive** list operations, which modify the list they're called on. 

### Static list processing methods
For lists whose **items** implement ```Comparable``` interface, methods that finds the largest and smallest list item would be really handy. One way to find the smallest item in a list is to have a helper function that **keeps track of the smallest item seen so far**.

* All the list elements now are ```Comparable``` type, which means that any ```Object```s stored in ```myItem``` implements ```Comparable``` methods.

* ```public Comparable smallest()``` and ```public Comparable smallestHelper(Comparable smallestSoFar)``` along with ```public static Comparable min(Comparable c1, Comparable c2)``` will be used altogether to find the smallest element.

Q. Why is ```min``` a **static** method? Basically during the run-time, the computer finds a method in the dynamic type. However for a static method, the computer finds a method in the **static** type. I think ```min``` method is static to avoid confusion. Since ```Comparable``` class has the ```compareTo``` method, it's safer to make ```min``` method static.

* Make helper methods **static**: dealing with null objects more easily and improve the clarity of the code if it promotes symmetry of how we treat our arguments.

### Non-destructive list operations
#### ```add``` method
Given a Comparable ```c``` as argument, if list has ```n``` elements, the returned list should have ```n+1``` elements where the last element is ```c```. This method does **NOT** modify the original list, but returns a new list.

#### ```append``` method
Given an ```AbstractListNode``` as input argument, returns a **new** list which is the result of concatenating the list represented by ```this``` and the list of the argument.

#### ```reverse``` method
Returns a copy of this list with the elements in reverse order.

### Destructive list operations
Changing pointers **without** generating any new nodes.

* Mutator Methods (Setter Methods): ```setItem```, ```setNext```
Q. Should ```setItem``` and ```setNext``` be declared in the ```AbstractListNode``` class, the ```EmptyListNode``` class, the ```NonemptyListNode``` class, or combinations of these? <br \>
I think both ```setItem``` and ```setNext``` methods should be declared in the ```AbstractListNode``` class and the ```EmptyListNode``` class. Because first of all, since both of the ```NonemptyListNode``` and ```EmptyListNode``` will probably use these two setter methods, the parent class ```AbstractListNode``` that these two classes extend should declare these methods. And the reason why these setter methods should be declared in ```EmptyListNode``` class is that ```EmptyListNode``` class doesn't have any ```myItem``` or ```myNext```. So it should do something different. Instead of changing the values, it should throw an exception.

#### Destructive method ```appendInPlace(AbstractListNode list2)```
- If ```this``` is empty, ```list2``` is returned.
- If ```this``` is not empty, the ```myNext``` in its last ```NonEmptyListNode``` is replaced by ```list2```.

Q. Why can't ```appendInPlace``` be a void method? What would happen if the list were empty? <br \>
I think it cannot be void. A void method means that it doesn't return anything. Then theoretically, ```this``` should be changed. However, since ```this``` is of ```NonemptyListNode``` type and ```next()``` method returns ```AbstractListNode``` type, there is going to be type inconsistencies. (This is related to the static/dynamic and inheritance polymorphism problem we talked in the past) <br \>
But if the list were **empty**, void seems to be okay since there's no such type inconsistency problem.

#### ```remove(int index)``` method

#### ```merge(AbstractListNode a, AbstractListNode b)``` method



