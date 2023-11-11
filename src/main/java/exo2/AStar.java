package exo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.JFrame;

public class AStar {

	   public static Node1 aStar(Node1 start, Node1 target) {
	         PriorityQueue<Node1> closedList = new PriorityQueue<>();
	         PriorityQueue<Node1> openList = new PriorityQueue<>();

	         start.f = start.g + start.calculateHeuristic(target);
	         openList.add(start);

	         while (!openList.isEmpty()) {
	             Node1 n = openList.poll(); // Use poll() to retrieve and remove the head of the queue
	             if (n == target) {
	                 return n;
	             }

	             for (Node1.Edge edge : n.neighbors) {
	                 Node1 m = edge.node;
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
	     public static void printPath(Node1 target) {
	         Node1 n = target;
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

	     public List<List<Integer>> getEdges(List<Node1> nodes) {
	         List<List<Integer>> edges = new ArrayList<>();

	         for (Node1 node : nodes) {
	             for (Node1.Edge edge : node.getEdges()) {
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

	            Node1 nodeS = new Node1("S", 4.0); // Setting S heuristic of 3.0 for nodeA
	            Node1 nodeA = new Node1("A", 3.0); // Setting A heuristic of 3.0 for nodeA
	            Node1 nodeB = new Node1("B", 2.0); // Setting B heuristic of 1.0 for nodeB
	            Node1 nodeC = new Node1("C", 2.0); // Setting C heuristic of 0.0 for nodeC
	            Node1 nodeD = new Node1("D", 2.0);
	            Node1 nodeG = new Node1("G", 0.0);

	            // Establishing connections

	            nodeS.addBranch(1, nodeA);
	            nodeA.addBranch(3, nodeB); // nodeA connected to nodeB with a weight of 3
	            nodeA.addBranch(1, nodeC);
	            nodeB.addBranch(3, nodeD); // nodeA connected to nodeD with a weight of 2
	            nodeC.addBranch(1, nodeD);
	            nodeC.addBranch(2, nodeG); // nodeD connected to nodeC with a weight of 5
	            nodeD.addBranch(3, nodeG);

	           // Utilizing the A* algorithm to find a path
	            Node1 startNode1 = nodeS; // Define the start node
	            Node1 targetNode1 = nodeG; // Define the target node
	            Node1 result = AStar.aStar(startNode1, targetNode1); // Running A* algorithm

	            // Printing the found path
	            System.out.print("Le chemin de A*: ");
	            AStar.printPath(result);

	            // Display the graph with the highlighted path
	            GraphVisualisation frame = new GraphVisualisation(result);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setSize(400, 320);
	            frame.setVisible(true);
	    }
	}



