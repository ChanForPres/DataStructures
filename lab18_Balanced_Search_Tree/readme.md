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
** How bottom-up approach works**: Literally, it means that it builds a tree from leaf to root. To do that, we need to keep track of the **mid index**((start+end)/2). Recursively, we find the left and right part of the list based on the mid index until null and then set the current listnode as the root and set leftchild and right child as well. More details are following: <br \> 
For instance, let's say we have a simple sorted linked list of 3 elements, (1,2,5). <br \>
Then we go step by step: ```[result], <step>, (start, end)``` <br \>
```
(0,2) mid(1) <1> [root=2 <8>, root.myLeft=left_child1 <9>, root.myRight=right_child1 <16>]
  |__(left_child1)(0,0) mid(0) <2> [root=1 <4>, root.myLeft=NULL <5>, root.myRight=NULL <7>]                             
      |__(left_child2)(0,-1) [NULL] <3> 
      |__(right_child2)(1,0) [NULL] <6>
  |__(right_child1)(2,2) mid(2) <10> [root=5 <12>, root.myLeft=NULL <13>, root.myRight=NULL <15>]
      |__(leftChild3)(2,1) [NULL] <11>
      |__(rightChild3)(3,2) [NULL] <14>
```

### Almost-balanced Trees
#### Height-balanced Tree 
the heights of two subtrees for any node in the tree differ by **at most 1**.

#### AVL Trees 
* *height-balanced* BST, in which information about tree height is stored in each node along with the ```myItem``` information. 
* Restructuring of an AVL tree after insertion is done by **rotation**. Rotation keeps its BST status but **changes** the levels of the nodes.
* Usually, when you insert a new item into an AVL tree, nothing special happens. However, there are two special cases that must perform rotation.

    1. An insertion into left subtree of the left child of a (single right-rotate)
    2. An insertion into right subtree of the left child of a. (left-right double rotate)
    3. An insertion into left subtree of the right child of a. (right-left double rotate)
    4. An insertion into right subtree of the right child of a. (single left-rotate)

Q. How exactly do we decide what and where to apply a rotation? After inserting a new node, **check its ancestors one by one**. As soon as you find an ancestor that **isn't balanced**, then apply the appropriate rotation from there. <br \>

Q. How many operations are required to do a **single rotation**? (constant) <br \>
**how many pointers do we have to change in order to move a whole subtree**. In order to do a rotation, all we do is move the references at the top of the unbalanced subtrees. No matter how big the subtrees are, just moving the top moves the whole subtree. (```A``` for example on the above example. We just left-rotate ```A```) <br \>

Q. How many rotations are required for a single insertion? (constant) <br \>
Assuming the tree is already an AVL tree before the insertion, at most two rotations are necessary. (Because it only needs to be **height-balanced**, where the height differs by one only)

#### ```insert()``` method

1. ```add()```: One thing I should've noticed is that there's ```myParent``` instance variable that I need to keep in mind when I am inserting something. Otherwise, the process is almost the same with the ordinary insertion of an item in BST.

2. After addition, I check the ```balanceFactor```. For those 4 cases above that need rotation, I call ```rotateRight()``` and ```rotateLeft()``` appropriately.

3. One reason I spent a lot of time was that I didn't carefully check what it **returns**. ```rotateRight()``` and ```rotateLeft()``` all return ```TreeNode``` with rotated trees. Then ```add``` method returns ```TreeNode``` as well. But ```insert``` is ```void```. So it was crucial to set ```myRoot = add(this, item)```, where myRoot is the root of the AVLTree.


