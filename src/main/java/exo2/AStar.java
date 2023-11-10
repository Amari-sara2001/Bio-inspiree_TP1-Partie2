package exo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;



public class AStar {
 
   public static Node aStar(Node start, Node target) {
         PriorityQueue<Node> closedList = new PriorityQueue<>();
         PriorityQueue<Node> openList = new PriorityQueue<>();

         start.f = start.g + start.calculateHeuristic(target);
         openList.add(start);

         while (!openList.isEmpty()) {
             Node n = openList.poll(); // Use poll() to retrieve and remove the head of the queue
             if (n == target) {
                 return n;
             }

             for (Node.Edge edge : n.neighbors) {
                 Node m = edge.node;
                 double totalWeight = n.g + edge.weight;

                 if (!openList.contains(m) && !closedList.contains(m)) {
                     m.parent = n;
                     m.g = totalWeight;
                     m.f = m.g + m.calculateHeuristic(target);
                     openList.add(m);
                 } else {
                     if (totalWeight < m.g) {
                         m.parent = n;
                         m.g = totalWeight;
                         m.f = m.g + m.calculateHeuristic(target);

                         if (closedList.contains(m)) {
                             closedList.remove(m);
                             openList.add(m);
                         }
                     }
                 }
             }

             closedList.add(n); // Add 'n' to closedList after examining all its neighbors
         }
         return null;
     }

     public static void printPath(Node target) {
         Node n = target;

         if (n == null)
             return;

         //List<> ids = new ArrayList<>();
         List<String> nodeNames = new ArrayList<>();

         while (n.parent != null) {
            // ids.add(n.id);
          nodeNames.add(n.getName());
             n = n.parent;
         }
        // ids.add(n.id);
         nodeNames.add(n.getName()); 
         Collections.reverse(nodeNames);

         for (String name : nodeNames) {
             System.out.print(name + " ");
         }
         System.out.println();
     }
     
     
     public List<List<Integer>> getEdges(List<Node> nodes) {
         List<List<Integer>> edges = new ArrayList<>();

         for (Node node : nodes) {
             for (Node.Edge edge : node.getEdges()) {
                 List<Integer> edgeData = new ArrayList<>();
                 edgeData.add(node.getId());
                 edgeData.add(edge.node.getId());

                 // Check for duplicate and reversed edges before adding
                 List<Integer> reversedEdge = new ArrayList<>();
                 reversedEdge.add(edge.node.getId());
                 reversedEdge.add(node.getId());

                 if (!edges.contains(edgeData) && !edges.contains(reversedEdge)) {
                     edges.add(edgeData);
                 }
             }
         }

         return edges;
     }
     public static void main(String[] args) {

    	 Node nodeS = new Node("S", 4.0); // Setting S heuristic of 3.0 for nodeA
         Node nodeA = new Node("A", 3.0); // Setting A heuristic of 3.0 for nodeA
            Node nodeB = new Node("B", 2.0); // Setting B heuristic of 1.0 for nodeB
            Node nodeC = new Node("C", 2.0); // Setting C heuristic of 0.0 for nodeC
            Node nodeD = new Node("D", 2.0); 
            Node nodeG = new Node("G", 0.0); 

            // Establishing connections
            nodeS.addBranch(1, nodeA); 
            nodeA.addBranch(3, nodeB); // nodeA connected to nodeB with a weight of 3
            nodeA.addBranch(1, nodeC);
            nodeB.addBranch(3, nodeD); // nodeA connected to nodeD with a weight of 2
            nodeC.addBranch(1, nodeD);
            nodeC.addBranch(2, nodeG); // nodeD connected to nodeC with a weight of 5
            nodeD.addBranch(3, nodeG);

           // Utilizing the A* algorithm to find a path
            Node startNode1 = nodeS; // Define the start node
            Node targetNode1 = nodeG; // Define the target node

            Node result1 = AStar.aStar(startNode1, targetNode1); // Running A* algorithm

            // Printing the found path
            System.out.print("Le chemin de A*: ");
            AStar.printPath(result1);
    }

}
