Lab15: Trees2
===

### Stack and Queues
2 linear data structrues that represent objects in the real world

#### Stack 
* A new item is **placed** on the **top** of the stack, and only the **top** item can be **accessed** at any time. 
    * ```push``` an item onto the top of the stack
    * ```access``` the top item of the stack
    * ```pop``` the top item off the stack
    * ```check``` if the stack is empty

#### Queue
* It is a waiting line. 
    * ```add``` an item to the **back** of the queue
    * ```access``` an item at the **front** of the queue
    * ```remove``` the **front** item
    * ```check``` if the queue is empty

One thing I need to keep in mind: **Trees are naturally helpful to understand recursion** <br \>
* It's often hard to figure out how recursion works on top of my head. That happened for ```height()``` method in AmoebaFamily.
* But a **tree** helped me a lot to understand how recursion operates. 
* So for a famous **Fibonacci** example, recursion actually is something like:

```
      fibonacci(7)
      ____|_____
     |          |
fibonacci(5)  fibonacci(6)
____|____     ____|_____
|        |    |         |
fib(3)  fib(4) fib(4)   fib(5)
```

### Tree Iterator
#### Traversing the tree
* For the tree, we not only need a single pointer what to return next, but a **whole collection** of bookmarks to nodes we've passed along the way.
* We will maintain a collection called **fringe** or **frontier** of all the nodes in the tree that are candidates for returning next. The ```next``` method will choose one of the elements of the **fringe** as the one to return, then **add its children** to the fringe as candidates for the next element to return. ```hasNext``` is true when the **fringe** is **not empty**.

* **Depth-first iteration** = storing the fringe elements in a stack (LIFO)/ ```java.util``` contains ```Stack``` class with ```push``` and ```pop``` methods
* **Breath-first iteration** = storing the fringe elements in a queue (FIFO)

#### A Depth-First Amoeba Iterator
```AmoebaFamily``` class implements interface ```Iterable```, which returns ```new AmoebaIterator()``` method. It should successively return names of Amoebas in preorder. e.g. "Amos McCoy" - "mom/dad" - ... <br \>
The constructor and ```hasNext``` run in constant time, while ```next``` runs in time proportional to the # of children of the element being returned.

#### A Breadth-First Amoeba Iterator
The name of the root is returned first, then the name of its children... Used ```java.util.Queue```.

### Build and Check a Tree ```BinaryTree.java```

#### ```private void print(int indent)```
print the tree in such a way that the output is turned 90 degree counter-clockwise

#### ```public boolean check()```
* A legal binary tree has the property that, when the tree is traversed, no node appears more than once in the traversal. 
* ```isOk(TreeNode t)``` method thorws ```IllegalStateException```, which will be caught in ```check()``` method
* Two ways to figure out validity
    * Use ```Iterator``` just implemented and traverse the tree
    * Use recursion and check validity

#### Building **Fibonacci Tree**
* ```private TreeNode fibTreeHelper (int n)```, ```private Object add(TreeNode myLeft, TreeNode myRight)```

#### Building Expression Tree
* One tricky part was how to decide the operator(+/*) that separates the given expression well. <br \>
* The solution was incrementing the variable *nesting* by 1 if we see the opening bracket and decrementing the variable *nesting* by 1 if we see the closing bracket. And when the variable *nesting* is 0 and we see any operator + or *, that's the right operator that should separate the expression.








