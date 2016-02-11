Lab 18: Balanced Search Trees
===
The logarithm search behavoir on a BST only happens when the tree is reasonably well balanced. More specifically, it means that O(N/2) of the nodes are in each half of the tree.  For left-heavy or right-heavy BST, the search time may take up to O(N) time. <br \>
The key to keeping a BST useful is to maintain balance. This can be done by a **global balancing algorithm** that takes any BST redistributes all of its nodes into a maximally well balanced tree. But it might be expensive. A more sensible approach is to maintain balance incrementally. More specifically,

1. **incremental balance**: at each insertion or deletion, we do a bit of work to keep the tree balanced (Literally one at a time)
2. **all-at-once balancing**: we don't do anything until it gets too lopsided, then we completely rebalance the tree.

In this lab, we will <br \>
- First, we analyze some tree balancing code.
- Second, we explore how much work is involved in maintaining complete balance.
- Third, we explore two kinds of balanced search trees: AVL trees and 2-3-4 trees.

### Making a Balanced Tree

#### The ```linkedListToTree``` method
Build a balanced binary search tree out of an already sorted ```LinkedList``` using **bottom-up approach**. <br \>
** How bottom-up approach works**: Literally, it means that it builds a tree from leaf to root. To do that, we need to keep track of the **mid index**((start+end)/2). Then recursively, we set the next item in the list as the root and the left part of the mid index as the left tree and the right part of the mid index as the right tree <br \> 
For instance, let's say we have a simple sorted linked list of 3 elements, (1,2,5). Then we go step by step:[result], <step>, (start, end) <br \>
```
(0,2) mid(1) <1>
[root=2 <8>, root.myLeft=left_child1 <9>, root.myRight=right_child1 <16>]
  |__(left_child1)(0,0) mid(0) <2> 
     [root=1 <4>, root.myLeft=NULL <5>, root.myRight=NULL <7>]                             
      |__(left_child2)(0,-1) 
         [NULL] <3> 
      |__(right_child2)(1,0) 
         [NULL] <6>
  |__(right_child1)(2,2) mid(2) <10> 
     [root=5 <12>, root.myLeft=NULL <13>, root.myRight=NULL <15>]
      |__(leftChild3)(2,1) 
         [NULL] <11>
      |__(rightChild3)(3,2) 
         [NULL] <14>
```
