/*Kruskals.java*/

import java.util.*;

/**
 *  Implementation of Kruskal's algorithm for MSTs
 * @author 
 */

public class Kruskals {

  /**
   * Returns a new EdgeWeightedGraph that is an Minimum Spanning Tree of the input graph
   * @param  g the input graph
   * @return   an MST of the original graph
   */
  public static EdgeWeightedGraph mst(EdgeWeightedGraph g) {
    // make a new graph with the same vertices as G, no edges yet
    EdgeWeightedGraph T = new EdgeWeightedGraph(g.V());

    // Make a list of all of the edges in G
    HashMap<Edge, Double> mapE = new HashMap<>();
    Iterable<Edge> edges = g.edges();
    for (Edge e : edges) {
      mapE.put(e, e.weight());
    }

    // sort the edges by weight
    Map<Edge, Double> sortedMap = sortByValue(mapE);

    // Create disjointset array and assign -1 for every item
    UF uf = new UF(g.V());

    // go through the edges and for each edge (u,v)
    // if there's not already path in T
    // we add the edge (u,v) to T
    for (Map.Entry<Edge, Double> entry : sortedMap.entrySet()) {
      System.out.println("entry: " + entry);
      int u = entry.getKey().either();
      int v = entry.getKey().other(u);
      // if vertices in the same tree, skip
      if (uf.connected(u, v)) {
        continue;
      } else {
        
      }
    }

  }

  /*
  Sort the HashMap by value
   */
  private static Map<Edge,Double> sortByValue(HashMap<Edge, Double> mapE) {
    List list = new LinkedList(mapE.entrySet());
    Collections.sort(list, new Comparator() {
      @Override
      public int compare(Object o1, Object o2) {
        return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
      }
    });

    Map result = new LinkedHashMap();
    for (Iterator it = list.iterator(); it.hasNext();) {
      Map.Entry entry = (Map.Entry) it.next();
      result.put(entry.getKey(), entry.getValue());
    }
    return result;
  }
}
