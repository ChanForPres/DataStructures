import java.util.*;

public class Graph implements Iterable<Integer>{

    private LinkedList<Edge>[] myAdjLists;
    private int myVertexCount;
    private int myStartVertex;


    // Initialize a graph with the given number of vertices and no edges.
    public Graph(int numVertices) {
        myAdjLists = new LinkedList[numVertices];
        myStartVertex = 0;
        for (int k = 0; k < numVertices; k++) {
            myAdjLists[k] = new LinkedList<Edge>();
        }
        myVertexCount = numVertices;
    }

    // Change the vertex the iterator will start DFS from
    public void setStartVertex(int v){
        if (v < myVertexCount && v >= 0){
            myStartVertex = v;
        } else {
            throw new IllegalArgumentException("Cannot set iteration start vertex to " + v + ".");
        }
    }


    // Add to the graph a directed edge from vertex v1 to vertex v2.
    public void addEdge(int v1, int v2) {
        addEdge(v1, v2, Double.parseDouble(null));
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2.
    public void addUndirectedEdge(int v1, int v2) {
        addUndirectedEdge(v1, v2, Double.parseDouble(null));
    }

    // Add to the graph a directed edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addEdge(int v1, int v2, double edgeInfo) {
        myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, double edgeInfo) {
        addEdge(v1, v2, edgeInfo);
        addEdge(v2, v1, edgeInfo);
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        Iterator<Edge> adjIterator = myAdjLists[from].iterator();
        while (adjIterator.hasNext()) {
            Edge curEdge = adjIterator.next();
            if (curEdge.myTo.equals(to)) {
                return true;
            }
        }
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        List toRtn = new LinkedList<>();
        Iterator<Edge> neiIterator = myAdjLists[vertex].iterator();
        while (neiIterator.hasNext()) {
            Edge curEdge = neiIterator.next();
            toRtn.add(curEdge.myTo);
        }
        return toRtn;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        for (int i = 0; i < myVertexCount; i++) {
            if (isAdjacent(i, vertex)) {
                count++;
            }
        }
        return count;
    }


    public Iterator<Integer> iterator(){
        return new TopologicalIterator();
    }

    // A class that iterates through the vertices of this graph, starting with a given vertex.
    // Does not necessarily iterate through all vertices in the graph: if the iteration starts
    // at a vertex v, and there is no path from v to a vertex w, then the iteration will not
    // include w
    private class DFSIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private HashSet<Integer> visited;

        public DFSIterator(Integer start) {
            fringe = new Stack<>();
            fringe.push(start);
            visited = new HashSet<Integer>();
            visited.add(start);
        }

        public boolean hasNext() {
            if (fringe.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }

        public Integer next() {
            if (!hasNext()) {
                throw new IllegalStateException("there's no more items to return");
            } else {
                Integer toRtn = fringe.pop();
                // Add that particular node's children to fringe
                Iterator<Edge> dfsIterator = myAdjLists[toRtn].iterator();
                while (dfsIterator.hasNext()) {
                    Edge e = dfsIterator.next();
                    if (!visited.contains(e.myTo)) {
                        fringe.push(e.myTo);
                        visited.add(e.myTo);
                    }
                }
                visited.add(toRtn);
                return toRtn;
            }
        }

        //ignore this method
        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }

    }

    // Return the collected result of iterating through this graph's
    // vertices as an ArrayList.
    public ArrayList<Integer> visitAll(int startVertex) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);

        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }

    // Returns true iff there exists a path from STARTVERTEX to
    // STOPVERTEX. Assumes both STARTVERTEX and STOPVERTEX are
    // in this graph. If STARVERTEX == STOPVERTEX, returns true.
    public boolean pathExists(int startVertex, int stopVertex) {
        if (startVertex == stopVertex) {
            return true;
        }
        ArrayList<Integer> result = visitAll(startVertex);
        for (int i = 0 ; i < result.size(); i++) {
            if (result.get(i) == stopVertex) {
                return true;
            }
        }
        return false;
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        ArrayList<Integer> toRtn = new ArrayList<Integer>();
        // if startVertex == stopVertex
        if (startVertex == stopVertex) {
            toRtn.add(startVertex);
            return toRtn;
        }
        // if no path exists
        else if (!pathExists(startVertex, stopVertex)) {
            return toRtn;
        }
        // return the path
        else {
            ArrayList<Integer> fromStart = new ArrayList<>();
            Iterator<Integer> iter = new DFSIterator(startVertex);
            int vertex;
            while (iter.hasNext()) {
                vertex = iter.next();
                if (vertex == stopVertex) {
                    break;
                }
                fromStart.add(vertex);
            }

            Integer bStartItem = stopVertex;
            int i = fromStart.size() - 1;
            Integer u = fromStart.get(i);
            while (i > 0 || u != startVertex) {
                if (isAdjacent(bStartItem, u)) {
                    toRtn.add(u);
                    bStartItem = u;
                    u = fromStart.get(i-1);
                    i--;
                } else {
                    i--;
                    u = fromStart.get(i);
                }
            }
            // [3,2] in toRtn
            toRtn.add(0, startVertex);
            toRtn.add(stopVertex);
            return toRtn;
        }
    }

    /**
     * Dijkstra's algorithm
     */
    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
        ArrayList<ArrayList<ArrayHeap<Integer>.Node>> result = new ArrayList<>();
        Iterator<ArrayList<ArrayHeap<Integer>.Node>> djkIter = new DijkstraIterator(startVertex, endVertex);
        while (djkIter.hasNext()) {
            result.add(djkIter.next());
        }
        ArrayList<ArrayHeap<Integer>.Node> newResult = result.get(0);
        // Process result to get the shortest path
        ArrayList<Integer> finalRtn = shortestPathHelper(newResult, startVertex, newResult.size()-1);
        return finalRtn;
    }

    private ArrayList<Integer> shortestPathHelper(ArrayList<ArrayHeap<Integer>.Node> newResult, int startVertex, int index) {
        ArrayList<Integer> toRtn = new ArrayList<>();
        ArrayHeap<Integer>.Node curNode = newResult.get(index);
        toRtn.add(curNode.item()); // add 3
        System.out.println(curNode.item());
        Integer pred = curNode.predecessor();
        toRtn.add(pred);
        while (pred != startVertex) {
            for (int i = 0; i < newResult.size(); i++) {
                if (newResult.get(i).item().equals(pred)) {
                    curNode = newResult.get(i);
                }
            }
            pred = curNode.predecessor();
            toRtn.add(pred);
        }
        Collections.reverse(toRtn);
        return toRtn;

    }


    public class DijkstraIterator implements Iterator<ArrayList<ArrayHeap<Integer>.Node>> {

        ArrayList<ArrayHeap<Integer>.Node> arrToRtn = new ArrayList<>();
        // fringe contains myItem(myTo), priority, myPred(myFrom)
        public ArrayHeap<Integer> fringe = new ArrayHeap<>();
        double inf = Double.POSITIVE_INFINITY;
        int myStartVertexDjk;
        int myEndVertexDjk;
        int countIdx = -1;

        public DijkstraIterator(int startVertex, int endVertex) {
            // Step 1: Assign 0 to our start node and inf to all other
            // All predecessors start as null
            myStartVertexDjk = startVertex;
            myEndVertexDjk = endVertex;
            for (int i = 0; i < myVertexCount; i++) {
                if (i == myStartVertexDjk) {
                    fringe.insert(i, 0, null);
                } else {
                    fringe.insert(i, inf, null);
                }
            }

            //System.out.println(fringe);
            ArrayHeap<Integer>.Node toRtn = fringe.removeMin(); // returns the startVertex

            // Iterate through startVertex's neighbors
            // And change their priorities from inf to distance
            // Find the shortest one

            Iterator<Edge> djkIterator1 = myAdjLists[toRtn.item()].iterator();
            while (djkIterator1.hasNext()) {
                //System.out.println("---------");
                //System.out.println(fringe);
                Edge e1 = djkIterator1.next();
                if (fringe.contains(e1.myTo)) {
                    fringe.changePriority(e1.myTo, e1.info(), e1.myFrom);
                }
            }
            //System.out.println(fringe);
        }

        @Override
        public boolean hasNext() {
            // Stops when fringe is empty
            // Or destination node has been marked visited
            if (fringe.size == 0) {
                return false;
            } //else if (!fringe.contains(myEndVertexDjk)) {
                //System.out.println("myEnd is already reached");
                //return false;
            //}
            else {
                return true;
            }
        }

        @Override
        public ArrayList<ArrayHeap<Integer>.Node> next() {
            if (!hasNext()) {
                throw new IllegalStateException("There's nothing more to return");
            } else {
                //System.out.println("-------");
                //System.out.println(fringe);

                double weightStartV;
                ArrayHeap<Integer>.Node smallest = fringe.removeMin();
                //System.out.println("SMALLEST: " + smallest);
                weightStartV = smallest.priority();
                //System.out.println("SMALEEST: " + smallest);
                arrToRtn.add(smallest);
                countIdx++;

                // For each smallest's neighbor, w
                // Replace if it's shorter than existing fringe
                Iterator<Edge> djkIterator2 = myAdjLists[smallest.item()].iterator();
                while (djkIterator2.hasNext()) {
                    Edge e2 = djkIterator2.next();
                    //System.out.println("next: " + e2.myTo);
                    if (!fringe.contains(e2.myTo)) {
                        continue;
                    } else {
                        if ((double) e2.info() < fringe.find(e2.myTo)) {
                            fringe.changePriority(e2.myTo, (double) e2.info() + weightStartV, e2.myFrom);
                        }
                    }
                }
                return arrToRtn;
            }
        }

    }

    public ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new TopologicalIterator();
        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }

    private class TopologicalIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private Integer[] currentInDegree;
        private HashSet<Integer> visited;

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            visited = new HashSet<Integer>();
            currentInDegree = new Integer[myVertexCount];
            for (int i = 0; i < myVertexCount; i++) {
                currentInDegree[i] = inDegree(i);
                if (inDegree(i) == 0) {
                    fringe.push(i);
                }
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            if (!hasNext()) {
                throw new IllegalStateException("There's nothing more to return");
            } else {
                // Pop the item with in-degree of 0
                // and decrement currentIndegree that the popped item is pointing to
                Integer toRtn = fringe.pop();
                for (Edge e : myAdjLists[toRtn]) {
                    currentInDegree[e.myTo]--;
                }
                visited.add(toRtn);
                // Update fringe with vertices shows in-degree of 0
                for (int i = 0; i < myVertexCount; i++) {
                    if (!visited.contains(i) && !fringe.contains(i) && currentInDegree[i] == 0) {
                        fringe.push(i);
                    }
                }
                return toRtn;
            }
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }

    }

    private class Edge {

        private Integer myFrom;
        private Integer myTo;
        private double myEdgeInfo;

        public Edge(int from, int to, double info) {
            myFrom = new Integer(from);
            myTo = new Integer(to);
            myEdgeInfo = info;
        }

        public Integer to() {
            return myTo;
        }

        public double info() {
            return myEdgeInfo;
        }

        public String toString() {
            return "(" + myFrom + "," + myTo + ",dist=" + myEdgeInfo + ")";
        }

    }

    public static void main(String[] args) {
        ArrayList<Integer> result;

        Graph g2 = new Graph(5);
        g2.addEdge(0, 1, 1);
        g2.addEdge(0, 2, 10);
        g2.addEdge(0, 4, 5);
        g2.addEdge(1, 2, 5);
        g2.addEdge(2, 3, 4);
        g2.addEdge(4, 3, 3);
        System.out.println(g2.shortestPath(0, 3)); //0,1,2,3
        System.out.println();
        System.out.println();
        /*System.out.println("Topological sort");
        result = g2.topologicalSort();
        Iterator<Integer> iter;
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }*/

        /*Graph g1 = new Graph(5);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(0, 4);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addEdge(2, 3);
        g1.addEdge(4, 3);*/
        //System.out.println(g1.inDegree(2));
        //System.out.println(g1.isAdjacent(0,4)); // True
        //System.out.println(g1.isAdjacent(1,4)); // False
        /*System.out.println("Traversal starting at 0");
        result = g1.visitAll(0);
        Iterator<Integer> iter;
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 2");
        result = g1.visitAll(2);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 3");
        result = g1.visitAll(3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 4");
        result = g1.visitAll(4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 0 to 3");
        result = g1.path(0, 3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 0 to 4");
        result = g1.path(0, 4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 1 to 3");
        result = g1.path(1, 3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 1 to 4");
        result = g1.path(1, 4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 4 to 0");
        result = g1.path(4, 0);
        if (result.size() != 0) {
            System.out.println("*** should be no path!");
        }*/




        /*Graph g3 = new Graph(7);
        g3.addUndirectedEdge(0,2);
        g3.addUndirectedEdge(0,3);
        g3.addUndirectedEdge(1,4);
        g3.addUndirectedEdge(1,5);
        g3.addUndirectedEdge(2,3);
        g3.addUndirectedEdge(3,6);
        g3.addUndirectedEdge(4,5);

        System.out.println(g3.pathExists(2,6)); // True
        System.out.println(g3.pathExists(0,6)); // True
        System.out.println(g3.pathExists(1,0)); // False
        System.out.println();
        System.out.println();
        System.out.println("Path from 2");
        result = g3.visitAll(2);
        Iterator<Integer> iter1;
        iter1 = result.iterator();
        while (iter1.hasNext()) {
            System.out.println(iter1.next() + " ");
        }

        System.out.println("Path from 6");
        result = g3.visitAll(6);
        Iterator<Integer> iter2;
        iter2 = result.iterator();
        while (iter2.hasNext()) {
            System.out.println(iter2.next() + " ");
        }
        System.out.println("Path from 2 to 6");
        result = g3.path(2,6);
        Iterator<Integer> iter;
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }*/
    }
}
