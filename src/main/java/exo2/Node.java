package exo2;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {
     
     private static int idCounter = 0;
     public int id;
     public String name;

     // Parent in the path
     public Node parent = null;

     public List<Edge> neighbors;

     // Evaluation functions
     public double f = Double.MAX_VALUE;
     public double g = Double.MAX_VALUE;
     // heuristic
     public double h;

     public Node(String name, double h) {
      this.name = name;
         this.h = h;
         this.id = idCounter++;
         this.neighbors = new ArrayList<>();
     }

     @Override
     public int compareTo(Node n) {
         return Double.compare(this.f, n.f);
     }

     public static class Edge {
         public int weight;
         public Node node;

         Edge(int weight, Node node) {
             this.weight = weight;
             this.node = node;
         }
     }

     public void addBranch(int weight, Node node) {
         Edge newEdge = new Edge(weight, node);
         neighbors.add(newEdge);
     }

     public double calculateHeuristic(Node target) {
         
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
  
}
