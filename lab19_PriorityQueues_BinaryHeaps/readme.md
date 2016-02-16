Lab 19: Priority Queues and Binary Heaps
===

### Representing a Tree with an Array

* The root will be at index 1 of the array (nothing at index 0). We can define the position of every other node in the tree recursively.
* The left child of a node at position ```n``` is at index ```2n```.
* The right child of a node at position ```n``` is at index ```2n+1```.
* The parent of a node at postion ```n``` is at index ```n/2```.

### ```ArrayBST.java```

#### The ```getLeft```, ```getRight```,```getParent```,```setLeft```, ```setRight``` method
```insertInSubtree``` was a bit confusing. But it all made sense when I set ```setLeft()``` method to call ```this.setNode(index*2, n);``` and ```setRight()``` method to call ```this.setNode(index*2+1, n);```. <br \>
When you look at the ```insertInSubtree``` method carefully, if the value is smaller than the subroot(it is either real root or the parent node during the recursion), then it should go to the left. And ```getLeftOf``` method finds the appropriate index. If that place is filled, then we should look for somewhere else. That's why we recursively call ```insertInSubtree(left, value);``` with now the ```subroot``` is ```left```.  Then we find the left child of left child. <br \>
If ```left``` index is empty, then we can simply fill that place with the node. (where ```setLeft``` automatically finds the right place by multiplying 2)

#### ```contains()``` 
This new ```contains()``` method run in time proportional to the height of the tree. 

### Queues and Priority Queues
* Basically, Queues process items in a first-in-first-out system
* Sometimes, we may want to process items in order of **importance** = Priority Queue
* Both queues and priority queues have the same operations:
    * construct an empty queue
    * ```enqueue``` : add an element in the queue
    * ```dequeue``` : remove an element from the queue (normal queue: oldest element/ priority queue: one with the most priority)
    * ```isEmpty``` : check if the queue is empty
    * ```peek``` : get the item at the front of the queue without removing it

* **Priority queues!** Why is it important? Integral for **graph processing**. Invaluable in Operating Systems when they need to schedule which processes in a computer to run at what times.

* Difference Betweene Queues and Priority Queues

```
        Unordered Priority Q | Ordered Priority Q | Balanced BST Priority Q |
insert:          O(1)        |       O(n)         |         O(log N)        |
delete:          O(n)        |       O(1)         |         O(log N)        |
```

#### ```Queue``` and ```PriorityQueue``` in ```java.util```
The ```java.util``` library contains a ```Queue``` interface and a ```PriorityQueue``` class that implements ```Queue```. ```Queue``` methods include the following: <br \>

    * ```offer(element)``` : enqueue
    * ```peek()``` : returns but not remove the head of the queue
    * ```poll()``` : dequeue

java's priority queue is implemented with a data structure called a *binary min heap*.

### Binary Heaps
A priority queue with fast operations. So we want to insert objects into the heap with different priority valuese and quickly remove the most important item. **min heaps** organizes items with the smallest given the most importance and **max heaps** organizes items with the largest given the most importance.

* Binary min heaps = binary trees (not BST) with two extra invariants:
    * 1: the tree must be maximally balanced
    * 2: every node is smaller than its children. (for *max* heap, every node is bigger than its children)

* Invariant 2 guarantees that the min element will always be at the root of the tree.

* **Add an item** *binary min heap case*
    1. Put the item you're adding in the next available spot in the bottom row (leaf row)
    2. Swap the item you just added with its parent until it is larger than its parent (swap if parent is bigger), or until it is the new root (*bubbling up*)

* **Remove the min item** 
    1. Replace the item at the root with the item in the last bottom spot
    2. *Bubble down* the new root until it is smaller than both its children. If you reach a point where you can either bubble down through the left or right child, choose the smaller of the two.

#### Maximally(Completely) Balanced Trees
* Has all available positions for nodes **filled**, except for possibly the last row, which must be filled left-to-right

#### ```ArrayHeap.java```

* Implements a **binary max heap** using an ```ArrayList```. <br \> 
Check out this website: http://www.cs.usfca.edu/~galles/JavascriptVisual/Heap.html. The concept of the binary max heap is a binary tree, and this can be formulated by ```ArrayList```. So the 0th index is left empty and when we add 5, it will be stored in the 1st index. Consecutively, we add item and check that with its parent node and see if it's bigger. If bigger, swap, else, stay. 

* As a side note, for ArrayBST, if we add 5, 4, 3, 6, 7, then the array should look like the following: 

```
|x|5|4|6|3|x|x|7|
```

* Generic class 
* Heaps CAN contain duplicates

* ```insert```, ```removeMin```, ```changePriority```, ```bubbleUp```, ```bubbleDown```, ```bubbleUp``` and ```getLeft```, ```getRight``` and ```getParent```

#### Determining if a tree is maximally balanced
Q. A binary tree is maximally balanced if all the leaves in the tree come at the end of a breadth-first traversal of the tree. True? or False? 
```
          x
      ____|____
     |         |
     x         x
 ____|     ____|
|         |
x         x
```

Suppose we have a tree looks like the one above. When we do the breadth-first search, all the leaves in the tree come at the end. However, it's obviously not maximally balanced. Therefore, this approach is also not perfect.

### Heapsort
Suppose we have an array of ```N``` numbers and sort to largest-to-smallest. 

1. Put all of the numbers in a max heap ```NlogN``` (heapify makes it faster ```N```)
2. Repeatedly remove the max element from the heap, and store them in an array in that order ```log N```

Since each insertion takes ```log N``` comparisons, and each removal takes ```log N``` comparisons, the whole process takes proportional to ```N log N```.

We can make it **faster** to ```N``` comparisons using a process called **heapify**.

#### Heapify
The algorithm for taking an arbitrary array and making it into a min(max) heap in time proportional to ```N``` is called **heapify**. https://www.cs.princeton.edu/~wayne/kleinberg-tardos/pdf/DemoHeapify.pdf is a good resource.

```
def heapify(array):
    index = N/2
    while index < 0:
        bubble down item at index
        index--
```

Conceptually, this is building a heap from the bottom up. Starting from the lowest level and moving upwards, shift the root of each subtree downward as in the deletion algorithm until the heap property is restored. 

More specifically if all the subtrees starting at some height h (measured from the bottom) have already been "heapified", the trees at height h+1 can be heapified by sending their root down along the path of maximum valued children when building a max-heap

#### Finding the K Largest Elements
* Create a heap of size ```K```. 
* **min heap** would be better to use since we want quick access to the smallest of the top k elements and replace it easily if necessary.
* The run-time is proportional to ```Nlogk``` comparisons. Because we will add all ```N``` elements to our heap of size ```K```. Each insertion of an element will take ```log K``` time.

#### ```heapSort()```
Created both recursive/iterative versions. I realized that ```print``` statement should be done more carefully. 










