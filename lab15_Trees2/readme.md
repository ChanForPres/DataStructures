Lab15: Trees2
===

One thing I need to keep in mind: **Trees are naturally helpful to understand recursion** <br \>
* It's often hard to figure out how recursion works on top of my head. That happened for ```height()``` method in AmoebaFamily.
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
* We will maintain a collection called *fringe* or *frontier* of all the nodes in the tree that are cnadidates for returning next. <br \>
* **next** : choose one of the elements in the fringe as the one to return -> add its children to the fringe as candidates for the next element to return
* **hasNext** : true when the fringe isn't empty
* **Depth-first iteration** = storing the fringe elements in a stack (LIFO)
