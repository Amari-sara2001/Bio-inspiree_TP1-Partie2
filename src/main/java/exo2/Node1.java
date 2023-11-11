package exo2;

import java.util.ArrayList;
import java.util.List;

public class Node1 implements Comparable<Node1> {
     
     private static int idCounter = 0;
     public int id;
     public String name;

     // Parent in the path
     public Node1 parent = null;

     public List<Edge> neighbors;

     // Evaluation functions
     public double f = Double.MAX_VALUE;
     public double g = Double.MAX_VALUE;
     // heuristic
     public double h;

     public Node1(String name, double h) {
      this.name = name;
         this.h = h;
         this.id = idCounter++;
         this.neighbors = new ArrayList<>();
     }

     @Override
     public int compareTo(Node1 n) {
         return Double.compare(this.f, n.f);
     }

     public static class Edge {
         public int weight;
         public Node1 node;

         Edge(int weight, Node1 node) {
             this.weight = weight;
             this.node = node;
         }
     }

     public void addBranch(int weight, Node1 node) {
         Edge newEdge = new Edge(weight, node);
         neighbors.add(newEdge);
     }

     public double calculateHeuristic(Node1 target) {
         
         return this.h;
     }

  public String getName() {
   return name;
  }
  
  public int getId() {
         return id;
     }
  
  public List<Edge> getEdges() {
         return neighbors;
     }
  public boolean pathContains(String nodeName) {
      Node1 currentNode = this;
      while (currentNode != null) {
          if (currentNode.getName().equals(nodeName)) {
              return true;
          }
          currentNode = currentNode.parent;
      }
      return false;
  }
}
