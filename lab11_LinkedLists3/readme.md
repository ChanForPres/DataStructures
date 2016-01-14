Lab 11: Linked List Finale
===
A third way to represent a linked list, one that uses a **wrapper class**. The wrapped objects are ```ListNode```s, and the wrapper is a ```List```. A user only calls methods on the ```List```, leaving all the operations on ```ListNode```s to happen below the abstraction. <br \>
A ```List``` object will still exist even if the list is **empty** and there are no nodes. This is why we don't need a ```EmptyListNode``` class.

* A wrapper class protects list **integrity**. If we allow **any** method to call ```setNext``` to change the links will bring **disaster**. So we wrap the ```ListNode``` class inside ```List```. The ```List``` class will have ```ListNode``` instance variable, ```private ListNode myHead```, that refers to the first node in the linked list. The ```List``` class will provide most of the methods that were in ```ListNode``` class, but **access** to ```myItem``` and ```myNext``` will not be allowed outside of the ```List``` class.

#### The ```equals``` Method

#### The ```add``` and ```appendInPlace``` Methods
Destructive add method and appendInPlace method.

#### Iterator Method
```List``` implement ```Iterable``` that will return a new nested class ```ElementIterator``` that implements ```Iterator```. Since ```ElementIterator``` class is not static, you can only create ```ElementIterator``` only when ```List``` is created. Also inside of the ```ElementIterator``` class, you can access an instance variable ```myHead``` and call methods from the ```ListNode``` class.

#### Efficiency
* **If you need to access information quickly, store it somewhere**.
* ```mySize``` : store the length of the list
* ```myTail``` : store a reference to the last node in the list

#### A consistency checker ```isOK```
```isOK``` checks the list for consistency.
- the value stored in ```mySize``` is the number of nodes in the list
- all ```myItem``` objects in this list are non-null
- either both ```myHead``` and ```myTail``` are null, or ```myTail``` is a reference to the last node in the list whose first node is the node that ```myHead``` refers to.

#### A ```remove``` method
A ```List``` method that, given an ```object``` as argument, removes all elements that are equal to that object from the list. Destructive method.

### More mutation exercises

#### ```doubleInPlace``` method

#### Destructive, static ```reverse``` method
Both recursively and iteratively <br \>
Q. Invariant for reverse? For variables ```current``` and ```prev```, they should all eventually add up to the number of elements in the list. 

### Variations on linked list
#### Sentinel List Variation
Very similar to the abstract implementation, just with an **empty** node at the front rather than at the end. The reason for this null ```ListNode``` is to get rid of awkward ```if (myHead == null)``` statement. If we guarantee that there will always be at least one node in the list, we don't need to check that differently.

#### Doubly Linked Lists
Better with inserting or deleting a list element.

#### Circular Lists
Wrote ```delete``` method and the ```Iterator``` method. 


