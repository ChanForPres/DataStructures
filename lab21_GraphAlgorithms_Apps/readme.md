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
1. The Internet
    * Given a query, search engine presents pages from the index, with the most "interesting" ones first.
    * The indes is a massive table: the keys are the contents words, values are associated URLs
    * The crawler adds pages to the table and the search engine retrives them + a second table to know what pages it has visited already










