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

#### The ```insert()``` method

* ```add()```: One thing I should've noticed is that there's ```myParent``` instance variable that I need to keep in mind when I am inserting something. Otherwise, the process is almost the same with the ordinary insertion of an item in BST.

* After addition, I check the ```balanceFactor```. For those 4 cases above that need rotation, I call ```rotateRight()``` and ```rotateLeft()``` appropriately.

* One reason I spent a lot of time was that I didn't carefully check what it **returns**. ```rotateRight()``` and ```rotateLeft()``` all return ```TreeNode``` with rotated trees. Then ```add``` method returns ```TreeNode``` as well. But ```insert``` is ```void```. So it was crucial to set ```myRoot = add(this, item)```, where myRoot is the root of the AVLTree.


#### The ```delete()``` method

1. Delete as you normally would in BST
2. Starting from the node you just deleted, **travel up** the tree and check if each node is unbalanced. If unbalanced, rotate.
3. Unlike with insertion, we may have to do this multiple nodes as we go up the tree.

### 2-3-4 Trees
* Instead of rotations, it **relaxes the constraint** that nodes can only have one item in it. Here it can stuff more into a node.
* In a 2-3-4 tree, each node can have up to 3 keys. Also any non-leaf node **must** have one more child than its keys. E.g. A node with 1 key must have 2 children, 2 keys with 3 children, and 3 keys with 4 children. 
* It follows the ordering invariants of the BST except each node can contain 1,2 or 3 items.
* Searching:
    * If there's only 1 key in a node: everything to the left of the key is less and everything to the right is greater
    * If there are 2 keys (A, B): 
        * the keys of the leftmost child <= A 
        * A < the keys of the middle child < B
        * the keys of the rightmost child >= B.

Q. What does the invariant that this tree has one more children than keys tell you about the length of the paths from the root to leaf nodes? <br \> 
Imbalance appears when one node is not divided equally. For instance, according to the past examples of imbalance, imbalance occurred when only one node is added to one node and not to the other side. However, for a 2-3-4 tree, tree has to have one more children than keys according to the invariant. This equally divided the node and eventually **keep every path from root to leaf have the same length**. Thus tree is balanced. <br \>

#### Insertion
* **Case 1: when a node already has 3 keys**
    * Do NOT add the item as a new child
    * Push the existing middle key up into the parent node and split the remaining two keys into two 1-key nodes
    * Add the new item to one of the split nodes
e.g. insert 9 into this tree

```
         20
    ______|______
   |             |
(10 11 12)       30
```
We push up the middle 11, which means we need 3 children now. Split the 10 and the 12 and put 9 in the appropriate split node.

```
     (11 20)
  ______|______
 |      |      |
(9 10)  12     30
```

* **Case 2: if the root is a 3-key node**
    * Still push up the middle key and make it the new root

Q. Suppose the keys 1,2,3,4,5 are inserted sequentially into an initially emtpy tree. <br \>
First, all 1,2,3 are inserted into the same node as 3-key node. Then 4 splits the 1,2,3 node.

### B-trees
A 2-3-4 tree is a special case of a structure called a B-tree. What variese among B-trees is the **number of keys/subtrees per node**. B-trees with **lots of keys per node** are useful for **organizing storage of disk files in an OS or Database system.** We generally try to process information in large chunks in order to minimize the number of disk accesses. The **directory structure of a file system** could be organized as a B-tree so that a single disk access would read an entire node of the tree.

### Red-Black Trees
Like an AVL Tree, a red-black tree also uses **rotation** to balance. However, there are some additional invariants related to "coloring" each node red or black. Overall it turns out similar to an AVL tree with a slightly less strict height balance invariant. There's a isometry between 2-3-4 trees and Red-Black trees. (So one can turn into the other really easily)

### Tries
* A tree structure that can be useful for storing a set of words.
* The time to find a word in the structure will be O(length of word)
* Suppose we want to store most of the 26^5 words consisting of 5 lower-case letters. Putting them in a **trie** is a lot faster than **array**.
* Tries are said to have a **fail-fast** property. If you lookup a word in a trie and it turns out that trie doesn't contain that word, the try can tell you very quickly that it doesn't.
* Disadvantage: trade space efficiency for time efficiency. If we store English words, the branching factor at each level is as much as 26.

#### Lookup in a trie
* add words and defns to the dictionary
* allow you to lookup the defns associated with words
* words and defns in a trie structure
* since trie node can have so many children, the children are stored in a map
```
    ( )
 ____|____
|    |    |
map  map  map 
|    |    |
map  map  map
|    |    |
defn map  defn
     |
    defn
```

Always keep in mind that trees are in some sort of recursion and that it should call itself. 



