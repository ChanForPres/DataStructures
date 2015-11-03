Lab15: Trees2
===

One thing I need to keep in mind: **Trees are naturally helpful to understand recursion** <br \>
It's often hard to figure out how recursion works on top of my head. That happened for ```height()``` method in AmoebaFamily.
But I found it very helpful when I think recursion as a **tree** form. <br \>
So for a famous **Fibonacci** example, recursion actually is something like <br \>

      fibonacci(7)
      ____|_____
     |          |
fibonacci(5)  fibonacci(6)
____|____     ____|_____
|        |    |         |
fib(3)  fib(4) fib(4)   fib(5)