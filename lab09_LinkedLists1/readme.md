Lab 09: LinkedList and Algorithm Analysis
===
### A straightforward implementation of a LinkedList
A linked list consists of **nodes** that are chained together. Each **node** is represented as the class ```ListNode```. Each node contains an **item** called ```myItem```. Linked list can contain any type of ```Object```.

#### A ```get``` method
It takes an ```int``` position as argument and returns the list element at the given position in the list starting from zero. If the position is out of range, ```IllegalArgumentException``` should be thrown.

#### An ```isEmpty()``` method
We might attempt to write ```return (this == null)```. But we can't do anything that has a null reference because it's null. So we should ensure that even for an **empty** list, that should be a some representation of  ```ListNode``` object. One idea is to have a trailer node at the end of each list without any information and satisfy the requirement that each list contains at least one node.

#### Linked Lists and Inheritance
* Empty and NonemptyListNodes
Q. What should the trailer node's ```myItem``` contain? Since trailer node is the emptyListNode, which means that there's nothing after this, it should contain nothing. <br \>
Q. How can the ```myNext``` point to both a ```NonEmptyListNode``` and an ```EmptyListNode```? For a doubly linked list.. I think <br \>

* AbstractListNode Class
Here we created ```AbstracListNode``` class with list-processing methods(```item()```, ```next()```, ```isEmpty()```)that will be extended by both ```NonemptyListNode``` and ```EmptyListNode``` classes. 

#### The ```size``` Method

#### The ```toString``` Method
This will return the ```String``` representation of the list. e.g. "( 1 3 5 )" for the list containing the Integer objects 1, 3, and 5.

#### The ```equals``` Method
Given an ```Object``` as argument, returns ```true``` if this list and the argument list are the **same length** with **equal elements** in corresponding positions.

### Analyzing Program Efficiency

* Statement Conting 
```for (int k = 0; k < N; k++) {
        sum = sum + myValues[k]
}```
Given this code, we can count the number of statements. 

1. assignmnet of ```k``` : 1 (Literally, assignment is just throwing a number in it)
2. loop test(```k < N```) : N
3. increment ```k``` : N
4. access ```myValues``` : N
5. add sum : 1
= 3N + 2

#### Big Theta
We could say **f(n) is in T(g(n)) iff f(n) is proportional to g(n) for all large enough values of n**. In other words, f(n) in T(g(n)) iff *f(n) in O(g(n))* **AND** *g(n) in O(f(n))*

#### Big Oh (Upper-bounding)
For positive contants M and N s.t. for all values of n > N: *f(n) < M*g(n)* <br \>
Given f(n) = n^2 and g(n) = n^3, f(n) in O(g(n))

#### Big Omega (Lower-bounding)
f(n) in Om(g(n)) iff *g(n) in O(f(n))*





