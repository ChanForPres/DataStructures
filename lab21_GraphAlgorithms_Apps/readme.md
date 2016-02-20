Lab 21: Graphs Algorithms and Applications
===

### Shortest Paths

* In the last lab, we had a ```path()``` method to find out the path between two vertices. If we could have each fringe element containing not only a **vertex** but also the vertex's **predecessor** along the traversal path, we merely follow the predecessor links to find a path. 

#### Associating Distances with Edges
we could process edges with **weights**, which are numeric values associated with each edge. The weight associated with an edge will represent the **distance** between the vertices connected by the edge. A weight might represent the **cost** of an edge or the **time** needed to process the edge.

#### Dijkstra's Shortest Path Algorithm
The algorithm here finds the shortest path from starting vertex to **all** other nodes in a graph. To find the shortest path between two specified vertices ```s``` and ```t```, simply terminate the algorithm after ```t``` has been returned by a call to ```next``` (marked visited)   instead of waiting for ```hasNext``` to return false.

* **Constructor**: 
    * Add the ```startVertex``` to the fringe with distance 0. Then add all other vertices to the fringe with distance infinity. Also, for each vertex, keep track what is the predecessor(previous) for the node along the path found. All predecessors start as null.

* ```hasNext```
    * Return true if the fringe is not empty

* ```next```
    * Remove and save the vertex ```v``` in the fringe for which the distance from start is minimal.
    * Then for each neighbor ```w``` of ```v```:
        * If ```w``` is **not** in the fringe, do nothing (we've already found the shortest path from the start to ```w```)
        * Otherwise, ```w```'s distance might need updating if the path through ```v``` to ```w``` is a shorter path than what we've seen so far. In that case, replace the distance for ```w```'s fringe entry with the distance from ```start``` to ```v```plus the weight of the edge ```(v, w)```, and replace its predecessor with ```v```.
    * Return the fringe entry for ```v```.

Check out the photo I attached.

Another algorithm: <br \>

1. Assign to every node a tentative distance value: set it to zero for our initial node and to infinity for all other nodes.
2. Set the initial node as current. Mark all other nodes unvisited. Create a set of all the unvisited nodes called the unvisited set.
3. For the current node, consider all of its unvisited neighbors and calculate their tentative distances. Compare the newly calculated tentative distance to the current assigned value and assign the smaller one. For example, if the current node A is marked with a distance of 6, and the edge connecting it with a neighbor B has length 2, then the distance to B (through A) will be 6 + 2 = 8. If B was previously marked with a distance greater than 8 then change it to 8. Otherwise, keep the current value.
4. When we are done considering all of the neighbors of the current node, mark the current node as visited and remove it from the unvisited set. A visited node will never be checked again.
5. If the destination node has been marked visited (when planning a route between two specific nodes) or if the smallest tentative distance among the nodes in the unvisited set is infinity (when planning a complete traversal; occurs when there is no connection between the initial node and remaining unvisited nodes), then stop. The algorithm has finished.
6. Otherwise, select the unvisited node that is marked with the smallest tentative distance, set it as the new "current node", and go back to step 3.

! Essentially, it's choosing the shortest path of unvisited and add the total distance so far after that.

* Dijkstra's algorithm doesn't work if  a graph has negative edge weights

Q1. How can I tell what vertices were currently in the fringe for a given step? <br \>
According to the video, we know that everything that is **not** highlighted in yellow are in the fringe. <br \>

Q2. How can you look at the chart and figure out what is the shortest path from A to H? <br \>
The shortest path is found by going backwards from ```H``` to ```C``` to ```F``` to ```B``` to ```A``` (predecessor written below for every node).

#### Representation of the Fringe in Dijkstra's algorithm
In Dijkstra's algorithm, the fringe consists of one entry for each candidate vertex ```v``` for which a path from ```start``` to ```v```—not necessarily the shortest path—has been found. Every time a vertex is removed from the fringe, that vertex's shortest path has been found and it is finalized. The algorithm ends when the stop vertex is returned by ```next```.

fringe returns the one with the lowest distance. So ```priorityQueue``` would be the most appropriate for the fringe.

#### ```shortestPath(startVertex, endVertex)```
* I brought the existing ```ArrayHeap.java``` class and slightly changed. Now it's a binary min heap implementation and keeps track of the predecessor of each node.


Q. Generic Graphs? What should we do if we want our graph to have vertices that are something else other than integers - like strings or other objects?


### Application of Graphs
#### The Internet
    * Given a query, search engine presents pages from the index, with the most "interesting" ones first.
    * The index is a massive table: the keys are the contents words, values are associated URLs
    * The crawler adds pages to the table and the search engine retrives them + a second table to know what pages it has visited already

    Q. The table of already-visited pages used by crawler has to be a **hash map**, not a **hash set**. Why? <br \>
    First, let's look at the differences between hash set and hash map. HashMap stores data in form of key-value pair while hashSet only stores objects. Also, hashMap doesn't allow duplicate keys while hashSet doesn't allow duplicate objects. <br \>
    Then when we think about the table of already-visited pages, we need both key-value pairs to keep track of search information. That's why we need a hash map instead of hash set. Keys are the URLs and values would be True/False.

#### How web crawler's define "interesting"
There may be an enourmous number of indexed pages that are possibly relevant to the query, and the search engine has to order them so that the most interesting pages get presented to the user first. 

The most recent innovation by Google's inventors is to include properties of the **graph structure** in the test for interestingness. Initially they gave priority to pages with higher **in-degree**, on the assumption that the more people that link **to** your page, the better your page must be. Further, the more **interesting pages** link to you, the more interesting you must be. 

#### Improving crawler/search engine performance
* natural language understanding
* parallel computing algorithms
* user interface design and evaluation
* graph theory
* softwware engineering

#### Java's Garbage Collection Mechanism

**Java's garbage collection** is another application of graphs and graph traversals. Suppose you create an object and then essentially throw it away. 
```
LinkedList<Integer> l = new LinkedList<>();
    ...
l = null;
```
What happens to all these **inaccessible** objects? Do they just hang around and continue to clutter the program's memory? If so, doesn't that cause any problems? YES! These orphaned objects hang around in the memory cause **memory leaks** and will crash. 

One remedy is **garbage collection**. When available memory for building objects runs out, the system steps in to classify all the program's memory as "in use" and "not in use"(garbage) and collects garbage in one place.

Java, Python, aand Scheme use garbage collection while C and C++ don't. 

#### The Garbage Collection Algorithm
The goal is to find objects that have **no references** that point to them. This involves graph traversal (the graph of objects and references)

1. First, **all the marked bits are turned off**. This involves a simple sweep through memory; each object has a header that includes its **length**, so we can just hop from object to object including the orphaned objects.

2. We then mark **all the objects that are currently in use** by starting with the references on the system stack(e.g. local reference variables), going to the objects that those point to, and so on(it is a graph traversal - every object is a vertex and every reference is an edge)

3. Finally, the marked memory is compacted, leaving the unused memory all together and ready to be **reused**.

ex) ```Line myLine = new Line(new Point(1,2) , new Point(2,2));```
The marking process <br \>
1. Mark the ```Line``` object
2. The array object that's inside an instance variable in the ```Line``` class
3. Two ```Point``` references in that array
4. The ```Point``` objects themselves

#### A problem with the Marking Phase
All the graph traversals have involved an auxiliary structure like a **stack** or **queue** to hold the **"fringe"**. But during the garbage collection, we already have run out of memory, so setting up a new stack or queue may not be possible. PROBLEM!

#### Graph Traversal without a Fringe
Use the pointer fields (in the object being traversed) to store the information that would have gone into the stack. When we're done, we have to restore this information. 

1. Have the pointer to go down to the end of the list, then to come back up, revisitng the nodes in reverse order (using one more auxiliary pointer and reference the pointers as we go)

#### Tree traversal without a Stack
Let's expand the idea of **reversed linked list traversal** without a stack to **tree traversal without a stack** (using only a constant amount of space + a "tag" bit & a back-pointer bit in each node) 

1. Two pointers are maintained: ```current``` and ```prev```
2. Going down the tree, ```current``` leads the way; ```prev``` points to the parent
3. Going back up the tree, ```prev``` leads the way. ```myLeft``` and ```myRight``` pointers keep track of where we've been and the back-pointer bit indicates which field in the node contains the back pointer.

#### Modern Garbage Collection Techniques
1. *generational garbage collection* : segregate objects that have been around a long time into their own section of memory. These sections get traversed less frequently

2. *Concurrent garbage collection* : Some real-time apps can't afford to be interrupted by a process as complicated as garbage collection. This garbage collection allows the garbage collector to run for a short while (even though the garbage collection hasn't finished), then the app. (Switching back and forth between the two)







