package exo1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Astaralgorithme {
 
   public static Node aStar(Node start, Node target) {
         PriorityQueue<Node> closedList = new PriorityQueue<Node>();
         PriorityQueue<Node> openList = new PriorityQueue<Node>();

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
         List<String> nodeNames = new ArrayList<String>();

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
        
     }
         public static void main(String[] args) {
         Node nodeS = new Node("S", 3.0); // Setting S heuristic of 3.0 for nodeA
         Node nodeA = new Node("A", 3.0); // Setting A heuristic of 3.0 for nodeA
            Node nodeB = new Node("B", 1.0); // Setting B heuristic of 1.0 for nodeB
            Node nodeC = new Node("C", 0.0); // Setting C heuristic of 0.0 for nodeC
            Node nodeG = new Node("G", 0.0); // Setting G heuristic of 0.0 for nodeD

            // Establishing connections
            nodeS.addBranch(1, nodeA); 
            nodeA.addBranch(1, nodeC); // nodeA connected to nodeB with a weight of 3
            nodeS.addBranch(2, nodeB); // nodeB connected to nodeC with a weight of 4
            nodeB.addBranch(1, nodeC); // nodeA connected to nodeD with a weight of 2
            nodeC.addBranch(2, nodeG); // nodeD connected to nodeC with a weight of 5

            // Utilizing the A* algorithm to find a path
            Node startNode = nodeS; // Define the start node
            Node targetNode = nodeG; // Define the target node

            Node result = Astaralgorithme.aStar(startNode, targetNode); // Running A* algorithm

            // Printing the found path
            System.out.print("Le chemin: ");
            Astaralgorithme.printPath(result);
           
        
     }



    
}

