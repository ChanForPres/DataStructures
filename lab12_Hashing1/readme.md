Lab12 : Maps & Hashing 1
===

### Map
It is like a table that stores collections of associations. There are two important features.

1. It's a collection of **pairs** (key, value)

2. Access to table elements is generally by key. There **can't** be any more than **one** value associated with a given key.

#### The Map Interface in Java
```Map``` is an interface in ```java.util```, not an actual class. So an actual class implement ```Map``` and provide definitions for the methods. The interesting ones are ```Object put(Object key, Object value)```, ```Object get(Object key)```. Object returned by ```put``` is the value previously associated with the given key or ```null```. (Tables contain **at most one** associated value for each key) <br \>

Q. What would be the private instance variables for a class that implements ```Map```? I think there should be an array variable that stores tuples with a key and a value? <br \>

Q. What are the runtimes of your ```get``` and ```put``` methods with respect to the total number of keys in the map? ```put``` should take a constant time because it's just adding another (key, value) pair. However, I think ```get``` should take O(n) time where n is the total number of keys in the map. <br \>

**binary search tree** and **hash tables** are good example classes in ```java.util``` that implement the ```Map``` interface.

### The Hash Map
If the keys in our map are **integers**, then we can just use normal **array** as a map with **constant time access**. <br \>
**hashing** extends this idea and enables this **constant** time access to **non-integer keys**.

### Hashing
Transforming the **key** into an **int** that can be used to index an array. If this transformation is fast and different keys get different index values, then we can approximate the direct access that an ```array``` provides. <br \>
* hash function : the transforming function
* hash value : the ```int``` the hash function returns

#### Collisions
Two common methods of dealing with collisions: <br \>

1. **Linear Probing** : Store the colliding keys **elsewhere** in the array, potentially in the next open array space.

2. **Chaining** : Store all the keys with a given hash value together in a collection of their own, such as a **linkedlist**. This collection is called a *bucket*.

#### Eutopia
Clearly we are better off if different keys have different hash codes, and **collisions** are **low**. Given the probability distribution of possible keys, we want to design **hash function** that scatters the key as much as possible. **hash** function should **muddle** whatever ordering exists in a set of keys. <br \>
Also the performace of hashing depends very much on **average case** behavior. So no matter what the hash function, we can always produce a set of keys that make it perform as badly as possible.

#### java.util.HashMap
* The class ```java.util.HashMap``` is implemented as a **hash table**, an array of collections. The ```Object put (Object key, Object value);``` method adds an entry to the ```HashMap```. This returns either the value previously associated with ```key``` or ```null``` if there was none with ```key```.

* The ```put``` method requires that keys support ```public boolean equals(Object obj)``` and ```public int hashCode()``` methods. 

    * First, ```put``` calls ```key.hashCode()``` *automatically* to find out which collection to search for ```key```. The relevant array index in the table maintained by the ```HashMap``` class is ```key.hashCode() % length of the bucket array```.

    * Then it searches the **bucket**, the collection of entries whose keys all have the same hash value. It does this first by comparing hash values, then using ```equals``` for keys with the same hash code. If there's already an entry with the given key, it replaces the associated value. Otherwise, it just adds the (key, value) entry to the collection.

* The ```get``` method works similarly. There can be an ambiguity when ```get``` returns ```null```. It is either the key is not in the table or is associated with the value ```null```. We resolve this ambiguity with the ```containsKey``` method, which returns ```true``` if the key argument is associated with something.

* The ```Constructor``` 
    * We can restrict the keys and values in the table to certain **types**. e.g. ```HashMap<String, Integer> table = new HashMap <String, Integer> ();```
    * Two optional arguments: the **initial size** of the bucket array. Normally the array is expanded when the table gets too full/ the **maximum load factor**, which is a percentage specifying how full the ```HashMap``` can be before it expands its internal array. (# of entries in the map / # of buckets)

#### ```PhoneBook.java```
Q. What happens if you modify a ```PhoneNumber``` that is already stored in the Book? The entry is reflected in the ```PhoneBook``` because they reference the same object. <br \>

Q. What happens if you modify a ```Person``` that is already stored in the Book? The entry is reflected in the Phone book because they reference the same object, but you can no longer look up the PhoneNumber <br \>

#### ```HashSet```
a ```HashSet``` works just like a ```HashMap```, except instead of storing key-value pairs, it just stores **items** with no values associated with them.

* We first create a ```HashSet<Point> points = new HashSet<Point> ();``` and ```Point p = new Point (0,5);```. Then with a for loop with k equals 0, 1, 2, we change ```p.x = k;``` and add that change by writing ```points.add(p)```. 
* Interestingly, if we check whether ```hash set``` contains (0,5), (1,5), (2,5), it only thinks the table contains (2,5). <br \>

Q. Why did the code return ```false``` when asked whether it contained the points (0,5) and (1,5)? Even though x's values have been changed within the loop, it was still referencing the same object ```p```. So it seems like it was revising the ```p```, which is the reason why the latest point, point(2,5), was set to ```true```.

#### Linear Probing
https://www.youtube.com/watch?v=8fklSTnaTRQ <br \>
Chaining contains a separate collection for the keys. However, in the linear probing strategy, keys are stored *in the array* rather than in a separate collection. 

* Search : Search table index *i*; if occupied but no match, try *i+1*, *i+2*, etc
    1. A key's hash value is found
    2. The array is searched linearly for the key
    3. The search stops either when the table is full, when an empty space is found(meaning that the key is not yet in the table), or when the key is found.
* Insert
    1. Put at table index *i* if free; if not try *i+1*, *i+2*, etc 

Q. What are the instance variables that a linear probing hash map would have in comparison with those of a bucketing hash map?
Because the linear probing hash map doesn't have a separate place for the keys, it should have a variable that stores the key in the array. <br \>
Q. What's one major pro and one major con of using linear probing haash map? <br \> 

* Pro: 
    * because the values all live in the same array, this makes copy-on-write very easy by just copying only the array. 
    * more memory efficient due to its lack of ```pointer```s
    * Insertion never fails if the table has at least one free field
* Con:
    * Compared to quadratic probing and double hashing, access becomes inefficient at a lower load factor

#### MyHashMap.java
Now, we know the following concepts pretty thoroughly:
    
    * the structure of a hash map and how items are stored
    * how items are accessed in a hash map
    * how the ```hashcode``` is used in a hash map
    * basic operations of all maps



