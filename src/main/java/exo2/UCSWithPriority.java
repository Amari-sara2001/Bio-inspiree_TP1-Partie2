package exo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class UCSWithPriority  {
 public static Node ucs(Node start, Node target) {
        PriorityQueue<Node> closedList = new PriorityQueue<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();

        openList.add(start);

        while (!openList.isEmpty()) {
            Node n = openList.poll(); 
            if (n == target) {
                return n;
            }

            for (Node.Edge edge : n.neighbors) {
                Node m = edge.node;
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

    public static void printPath(Node target) {
        Node n = target;

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

    
     Node nodeS1 = new Node("S", 0.0); 
     Node nodeA1 = new Node("A", 0.0); 
     Node nodeB1 = new Node("B", 0.0); 
     Node nodeC1 = new Node("C", 0.0);  
     Node nodeD1 = new Node("D", 0.0); 
     Node nodeG1 = new Node("G", 0.0); 
     nodeS1.addBranch(1, nodeA1); 
     nodeA1.addBranch(3, nodeB1); 
     nodeA1.addBranch(1, nodeC1);
     nodeB1.addBranch(3, nodeD1); 
     nodeC1.addBranch(1, nodeD1);
     nodeC1.addBranch(2, nodeG1); 
     nodeD1.addBranch(3, nodeG1);
     Node startNode = nodeS1; 
     Node targetNode = nodeG1; 
     Node result = UCSWithPriority.ucs(startNode, targetNode); 
     System.out.print("Le chemin UCS: ");
     UCSWithPriority.printPath(result);
}
}
