Lab15: Trees2
===

One thing I need to keep in mind: **Trees are naturally helpful to understand recursion** <br \>
* It's often hard to figure out how recursion works on top of my head. That happened for `height()` method in AmoebaFamily.
* But a **tree** helped me a lot to understand how recursion operates. <br \>
* So for a famous **Fibonacci** example, recursion actually is something like <br \>
```
      fibonacci(7)
      ____|_____
     |          |
fibonacci(5)  fibonacci(6)
____|____     ____|_____
|        |    |         |
fib(3)  fib(4) fib(4)   fib(5)
```

## Tree Iterator
### Traversing the tree
* We will maintain a collection called *fringe* or *frontier* of all the nodes in the tree that are cnadidates for returning next. <br \>
* **next** : choose one of the elements in the fringe as the one to return :arrow_forward: add its children to the fringe as candidates for the next element to return
* **hasNext** : true when the fringe isn't empty
* **Depth-first iteration** = storing the fringe elements in a stack (LIFO)
* **Breath-first iteration** = storing the fringe elements in a queue (FIFO)

### Check() method in BinaryTree
A legal binary tree has the property that, when the tree is traversed, no node appears more than once in the traversal. <br \>
In order to check that, I created **TreeIterator** class that implements **Iterator<TreeNode>**. As I exercised in the AmoebaFamily class, I use TreeIterator to check if there's any node that appears more than once.