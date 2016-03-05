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

### Disjoint Sets
* This is an entirely new data structure. The data structure represents a collections of sets that are disjoint (meaning no item is found in more than one set).

* There are only 2 operations that disjoint sets support: ```find``` and ```union```. 
    1. What ```find``` does is that it takes in an Object and it tells us which set it is in.
    2. ```union``` will take two sets and merge it into one set

* We can think of disjoint sets as representing companies. It starts with each company in its own set. Then we can call the ```union``` operation to represent a company acquiring another company. 

e.g. Given Facebook, Oculus, and Twitter in its own set, if we call ```find(Oculus)```, it will get us Oculus and so on. But if we ```union(Facebook, Oculus)``` and call ```find(Oculus)```, we get Facebook. (We have merged the two sets together)

* The best way to represent each individual set is a **tree**, where the root of each tree will be the name of the set. 

* ```union```: O(1), Take the root of one set and make it the child of another
* ```find```: slower, find what you are in and travel up to the root

Then to represent these trees, **we can do it with an array**. Let's say our Disjoint Set will only hold non-negative integers. Then we can use an array to mark what the parent of each item is. When we get to the **root of the tree**, we will **record the size** of the set and to distinguish it from a parent reference, we will make the size **negative**. The following is an example:

```
      7              1             2
     / \          /  |  \
    5   3        8   0   6    
   /
  4

Array to represent these trees: |1|-4|-1|7|5|7|1|-4|1|
                                 0  1  2 3 4 5 6  7 8
```
1. Union two trees: update the reference in the array and change the size
2. Find: Keep travleling up references in the array until you get a negative number, at which point you can return the index

#### Optimization
1. Union-by-size: when we do union on 2 sets, which we are representing as trees, we will **take the smaller tree and make it the subtree of the larger tree**

2. Path compression: Disjoint Sets only care about which set you're in, which is determined by the root of the tree that represents the set. So the patch compression moves all of the nodes we encounter as we go to the root. For example, we call ```find(4)``` with this following tree:

```
                        1
                    /   |   \
                   8    0    8
                 /   \
                7     2
              /   \
             5     3
            /
           4
```
After we **do** find(4), path compression will leave us with a new tree that looks like this (with parent nodes of 4 moved): 

```
                    1
   _________________|____________
  |     |     |     |     |     |    
  4     5     7     8     0     6
              |     |
              3     2
```
This is great because now calling find() on what were previously ancestors of 4 will now just take ```O(1)``` time. And when we call find on any decendents of the previous ancestors of 4, those operations will now be faster.

So **Union-by-size** and **Path compression** help us ```find``` very quickly (near O(1)). Officially, a series of ```u``` unions and ```f``` find operations will take ```O(u+f*alpha(f+u, u))```.

### Minimum Spanning Trees
So we have this cool Disjoint Sets data structure, but what are the applications of it? We can model companies buying each other out. But there's more than that.

Let's take a break from disjoint sets and intruduce Minimum Spanning Tree. If we have an **undirected graph G**, a spanning tree of the graph is essentially something that is **connected**, that is it reaches all of the vertices in G, **acyclic**, and **includes all of the vertices**. 

If we have weights in the graph, then we can define the minimum spanning tree as the spanning tree of G with **minimum total weight**. 

It's very useful: network design, medical imaging, electiring wiring and so on.

### Kruskal's Algorithm
How can we find these MSTs? We can use **Kruskal's Algorithm** which goes like this.

To find a minimum spanning tree, T of G, what we do first is:

1. Make a new Graph T (MST) with the same vertices as G, no edges yet though
2. Make a list of all of the edges in G
3. Sort the edges by weight, from least to greatest
4. Go through the edges in sorted order, and for each edge (u,v), if there's not already a path in T from u to v, then we addd the edge (u,v) to T.

Tricky part is step 4, determining if two vertices are already connected. How can we see if 2 things are already together? Through a **Disjoint Set**

We can have a Disjoint Set of vertices (which as you may recalled we label with integers), and as we add edges into T, we can **union** these vertices together. If we call ```find``` on 2 vertices, and it ends up that they are in the same set, then they're **connected** and we don't add any edges.

#### Runtime
If it's on adjacency list, then it takes ```O(|V|)``` time to make the initial graph. Sorting will take ```O(|E|lg|E|)``` time. Iterating all of the edges will take ```O(|E|)``` time. So the total Kruskal's runtime is ```O(|V|+|E|lg|E|)```.

#### ```Kruskals.java```
In this implementation, there's a Quick-Union Disjoint Set with path compression, as well as a weighted, undirected graph implementation















