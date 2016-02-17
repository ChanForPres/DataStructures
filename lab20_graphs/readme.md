Lab 20: Graphs
===

In a graph, we still have a collection of nodes, but each node in a graph can be connected to any other node without limitation. This means there isn't necessarily a hierarchical structure like you get in a tree. 

### Overview of Graphs
* An element in a graph is called a **vertex** (objects in the graph ```v0```, ```v1```, ```v2```, etc). A connection between two vertices is called an **edge** (relationship ```(v0, v1)```, ```(v2, v0)```). 
    * ```(v0, v1)``` : An edge from a vertex ```v0``` to a vertex ```v1```.

* **Undirected grpah**: If all edges in a graph are showing a relationship between two vertices that works in **either** direction (No arrows)

* **Directed grpah**: If the relationships between two vertices only go in **one** direction (Yes arrows) 
    
    * In an undirected graph, pair isn't ordered so ````(v0, v1)``` is the same as ```(v1, v0)```. However, in a directed grpah, the pair is ordered, thus even if the edge ```(v0, v1)``` exists, ```(v1, v0)``` might not.

* **Adjacent**: Two vertices are *adjacent* if there is an edge connecting two vertices

* **Incident to an edge**: A vertex and an edge are *incident* if the vertex is one of the two vertices of the edge connects.

* **Connected**: A graph is *connected* if every vertex has a path to all other vertices

* **Neighbor**: Two vertices are *neighbors* if there is an edge connecting the two vertices

* **Path**: A sequence of edges that can be followed from one vertex to another

* **Cycle**: A special *path* that ends at the same vertex where it originally started.

Q. Give another situation that can be modeled with graphs. <br \>
Grad school publications: Each researcher is a vertex. (More specifically, their work of studies) These vertices are connected when one researcher referenced another researcher's publication. It's represented as a directed graph.

#### Edge Count vs. Vertex Count
1. G is a *directed* graph with N vertices. The maximum # of edges that G can have: ```N(N-1)```

2. G is a *undirected* graph with N vertices. The maximum # of edges that G can have: ```half of N(N-1)```

3. *minimum* number of edges that a connected undirected graph with N vertices: ```N-1``` (it's a tree)

Basically we want to find quick answers for the following questions about a graph:
    * Are given vertices ```v``` and ```w``` adjacent? (Are Sam and Alice friends?)
    * Is vertex ```v``` incident to a particular edge ```e```? (Is Sam a part of Sam-Alice friendship connection?)
    * What vertices are adjacent to ```v```? (Who are the friends of Sam?)
    * What edges are incident to ```v```? (There are 3 edges that are connected to Sam)

### Data Structure to implement graph
#### Array of Adjacency
    * An array of size = # of vertices 
    * Each position in the array = one of the vertices in the graph
    * Each location in the array points to a linked list that contains its neighbors

#### Adjacency Matrix
    * An array with each position = vertex in the graph
    * Instead of the array pointing to a linked list, it points to **another array** representing possible neighbors.
    * Contains boolean values, true when there is an edge between the two given vertices, false  when no edge exists.

Q. We could represent a tree using an adjacency matrix of list. But why is it not preferred? <br \>
Trees are not cyclic and they're strictly directed to one direction (parent-to-child). If we use matrix, it means we're unnecessarily wasting many spaces because of that. It's also more inefficient because it's hard to exploit the recursive nature of the tree. If we use list or matrix, in order to retrieve the information, we need to traverse over and over which is wasteful, whereas in a regular tree structure it usually takes ```logN``` time to find/insert/delete things by recursion.

* Which one is more **space** efficient if we have a lot of edges? **Adjacency Matrix**

* Which one is more **space** efficient if we have very few edges? **Adjacency List**

* Which one is more **time** efficient for adding an edge if we have a lot of edges? **Adjacency Matrix**

* Which one is more **time** efficient for adding an edge if we have very few edges? **Adjacency Matrix**

* Which one is more **time** efficient for returning a list of edges from one node if we have very few edges? **Adjacency Lists**

* Which one is more **time** efficient for returning a list of edges from one node if we have a lot of edges? **Adjacency Lists**

* Using an adjacency matrix, how long in the worst case does it take to determine if vertex v is adjacent to vertex w? **constant time** Since matrix is a 2D array, we just index into the matrix at v and w and check if the value is true.

* Using an array of adjacency lists, how long in the worst case does it take to determine if vertex v is adjacent to vertex w? **time proportional to the number of neighbors of vertex v**

* The amount of memory required to represent the graph by adjacency list = ```N+E```

Q. Common Neighbor Timing: For a graph with N vertices and E edges impletemented with array of adjacency lists, the worst-casae time to see **if vertces v and w have a common neighbor is proportional to N^2**. It's insufficient. Explain why and give a more specific estimate.

### Processing Graphs
#### Graph Traversal
Stack = DFS, Queue = BFS <br \>
We could create a **Stack** and traverse. However a graph might contain a *cycle* of vertices and loop infinitely. The fix is to **keep track of vertices** that we've visited already. <br \>
Because graphs are usually **interconnected**, the ordering of vertices can be scrambled up. Thus using a depth-first/breadth-first traversal is not a big concern. Instead, we use a **priority queue** to implement **best-first traversal**.

### ```Graph.java```
- A graph of ```Integer```s using an adjacency list. ```myAdjLists``` is the LinkedList of ```Edge```s (not Integer).

#### ```addEdge(v1, v2, edgeInfo)```
Add to the graph a **directed edge** from vertex v1 to vertex v2. 
    
* I thought that the ```myAdjList``` contains random ```Integer``` in its list, but it seems like the index numbers are the vertex Integers. So if there's a LinkedList of size 5, it means there's a graph with numbers from 0 to 4.

#### ```addUndirectedEdge(v1, v2, edgeInfo)```
Because it's undirected, it should contain information from both v1 to v2 and v2 to v1.

#### ```isAdjacent(from, to)```
* Using LinkedLists' iterator() method, find if there's an edge from vertex "from" to vertex "to"

#### ```neighbors(vertex)```, ```inDegree(vertex)```
* I should've thought to use ```isAdjacent``` method I implemented to work with ```inDegree```.

#### ```DFSIterator```
Q. I wondered why exactly can we call ```Iterator<Edge> dfsIterator = myAdjLists[toRtn].iterator();```. Then I realized that the ```iterator``` is not from ```Graph.java``` but from ```LinkedList```. ```LinkedList``` contains ```iterator()``` method which is ```Iterator<E> iterator()``` that returns an iterator over the elements in the list. 

#### ```pathExists(startVertex, stopVertex)```
Use ```visitAll``` method to find if ```startVertex``` and ```stopVertex``` has a path between them.

#### ```path(startVertex, stopVertex)```
Actually find a path. 

1. Add code to stop calling ```next``` when you encounter the finish vertex

2. Trace back from the ```stopVertex``` to the ```startVertex``` by first finding a visted vertex ```u``` for which (u, finish) is an edge, then a vertex ```v``` vistited earlier than u for which (v, u) is an edge, etc

3. Finally find a vertex ```w``` for which (start, w) is an edge

4. Collecting all these vertices in the correct sequence produces the desired path.



