Lab 26: Additional Data Structures
===
1. Splay Tree (another type of balanced tree)
2. Disjoint Sets
3. Krsukal's Algorithm (which uses Disjoint Sets)
4. Cuckoo hashing (new way to hash)

### Splay Tree
When dealing with search trees, we like to have them balanced in order to keep the runtime of the basic operations (insertion, deletion, finding in O(lg n) time) e.g. AVL Tree (kept balance by adding an extra invariant about its height)

A **splay tree** is a regular BST, except it has a little twist to its basic algorithms for insert, remove and find. All operations in a splay tree will end by **splaying** the last node accessed up to the root. Splaying is a series of tree rotations. A splay tree **maintains its balance by rotations**. 

Splaying the latest accessed node will make sure that the tree is reasonably balanced, as well as giving a key advantage over AVL tree, which is that splay trees have **fast access to things that have been accessed recently**. Things that have been accessed recently will be by the root. That means that if you keep accessing the same items a lot of times, you will be able to get them quickly!

#### Another good definition by Cornell
A splay tree is an efficient implementation of a balanced binary search tree that takes advantage of locality in the keys used in incoming lookup requests.

ex) A network router. A network router receives network packets at a high rate from incoming connections and must quickly decide on which outgoing wire to send each packet, based on the IP address in the packet. The router needs a big table (a map) that can be used to look up an IP address and find out which outgoing connection to use. **If an IP address has been used once, it is likely to be used again, perhaps many times.**

A splay tree is a binary search tree. It has one interesting difference, however: **whenever an element is looked up in the tree, the splay tree reorganizes to move that element to the root of the tree, without breaking the binary search tree invariant.** If the next lookup request is for the same element, it can be returned immediately. In general, if a small number of elements are being heavily used, they will tend to be found near the top of the tree and are thus found quickly.

### Case 1: Zig-Zag
Let's say that the node that we're splaying up (X) is the **right child of a left child** (or the left child of a right child). P is the parent of X, and G is the grandparent. 

1. We rotate X left through P (rotate right in the mirror case)
2. Rotate X right through G (rotate leeft in the mirror case)

```
        G                         G                          X
      __|__                    ___|___                   ____|____
     |     |                  |       |                 |         |
     P     D                  X       D                 P         G
   __|__                   ___|___                   ___|___   ___|___
  |     |        =>       |       |         =>      |       | |       |
  A     X                 P       C                 A       B C       D
      __|__             __|__       
     |     |           |     |        
     B     C           A     B        
```

### Case 2: Zig-Zig
Let's say X is the **left child of a left child** (or right child of a right child). 

1. Rotate right P through G
2. Rotate right X through P


```
          G                      P                        X
        __|__                ____|____                ____|____
       |     |              |         |              |         |
       P     D              X         G              A         P
     __|__                __|__     __|__                   ___|___   
    |     |        =>    |     |   |     |      =>         |       |
    A     X              A     B   C     D                 B       G 
  __|__                                                         ___|___
 |     |                                                       |       |
 B     C                                                       C       D
```


### Case 3: Zig
If X's parent is the root of the entire splay tree, then we just rotate X so that it becomes the root

```
          P                      X                        
       ___|___               ____|____                
      |       |             |         |              
      X      (C)           (A)        P              
    __|__                           __|__
   |     |         =>              |     |
  (A)   (B)                       (B)    (C)                
```

#### Implementing ```SplayTree.java``` 
* KEEP IN MIND: that ```put``` method here compares **key** not **value** of any elements inserted.











