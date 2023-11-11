package exo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.JFrame;

public class UCSWithPriority  {

	 public static Node1 ucs(Node1 start, Node1 target) {

	        PriorityQueue<Node1> closedList = new PriorityQueue<>();

	        PriorityQueue<Node1> openList = new PriorityQueue<>();

	        openList.add(start);

	        while (!openList.isEmpty()) {
	            Node1 n = openList.poll();
	            if (n == target) {
	                return n;
	            }
	            for (Node1.Edge edge : n.neighbors) {
	                Node1 m = edge.node;
	                double totalWeight = n.g + edge.weight;

	                if (!openList.contains(m) && !closedList.contains(m)) {
	                    m.parent = n;
	                    m.g = totalWeight;
	                    openList.add(m);
	                } else {
	                    if (totalWeight < m.g) {
	                        m.parent = n;
	                        m.g = totalWeight;
	                        if (closedList.contains(m)) {
	                            closedList.remove(m);
	                            openList.add(m);
	                        }
	                    }
	                }
	            }
	            closedList.add(n);
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
	         nodeNames.add(n.getName());
	            n = n.parent;
	        }
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
  
	     Node1 nodeS1 = new Node1("S", 0.0);
	     Node1 nodeA1 = new Node1("A", 0.0);
	     Node1 nodeB1 = new Node1("B", 0.0);
	     Node1 nodeC1 = new Node1("C", 0.0);  
	     Node1 nodeD1 = new Node1("D", 0.0);
	     Node1 nodeG1 = new Node1("G", 0.0);

	     nodeS1.addBranch(1, nodeA1);
	     nodeA1.addBranch(3, nodeB1);
	     nodeA1.addBranch(1, nodeC1);
	     nodeB1.addBranch(3, nodeD1);
	     nodeC1.addBranch(1, nodeD1);
	     nodeC1.addBranch(2, nodeG1);
	     nodeD1.addBranch(3, nodeG1);
	     Node1 startNode = nodeS1;
	     Node1 targetNode = nodeG1;
	     Node1 result1 = UCSWithPriority.ucs(startNode, targetNode);
	     System.out.print("Le chemin UCS: ");
	     UCSWithPriority.printPath(result1);
	     GraphVisualisation frame = new GraphVisualisation(result1);

	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setSize(400, 320);
	     frame.setVisible(true);

	}

	}
