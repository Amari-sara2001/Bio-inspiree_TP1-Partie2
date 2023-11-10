package exo2;
import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class GraphVisualisation extends JFrame {

    private static final long serialVersionUID = -8123406571694511514L;
    private Object[] vertices; // Ajout de la variable pour stocker les vertices

    public GraphVisualisation() {
        super("Visualisation du graphe Partie 2");

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {
            Object v1 = graph.insertVertex(parent, null, "S", 20, 180, 20, 20);
            Object v2 = graph.insertVertex(parent, null, "A", 100, 70, 20, 20);
            Object v3 = graph.insertVertex(parent, null, "B", 180, 20, 20, 20);
            Object v4 = graph.insertVertex(parent, null, "C", 180, 120, 20, 20);
            Object v5 = graph.insertVertex(parent, null, "D", 250, 70, 20, 20);
            Object v6 = graph.insertVertex(parent, null, "G", 300, 180, 20, 20);

            graph.insertEdge(parent, null, "1", v1, v2);
            graph.insertEdge(parent, null, "12", v1, v6);
            graph.insertEdge(parent, null, "3", v2, v3);
            graph.insertEdge(parent, null, "1", v2, v4);
            graph.insertEdge(parent, null, "3", v3, v5);
            graph.insertEdge(parent, null, "1", v4, v5);
            graph.insertEdge(parent, null, "2", v4, v6);
            graph.insertEdge(parent, null, "3", v5, v6);
            
            // Mise à jour de la variable vertices
            vertices = new Object[]{v1, v2, v3, v4, v5, v6};
        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);

        // Appel de la méthode pour afficher la matrice d'adjacence
        printAdjacencyMatrix(graph);
    }

    private void printAdjacencyMatrix(mxGraph graph) {
        int[][] adjacencyMatrix = new int[6][6];

        // Parcourt toutes les arêtes du graphe
        for (Object edge : graph.getChildEdges(graph.getDefaultParent())) {
            // Récupère les cellules source et target de l'arête
            Object source = graph.getModel().getTerminal(edge, true);
            Object target = graph.getModel().getTerminal(edge, false);

            // Obtient les indices associés aux cellules source et target
            int sourceIndex = getIndexFromVertex(source);
            int targetIndex = getIndexFromVertex(target);

            // Met à jour la matrice d'adjacence
            adjacencyMatrix[sourceIndex][targetIndex] = 1;
            adjacencyMatrix[targetIndex][sourceIndex] = 1; // Si le graphe est non orienté
        }

        // Affiche la matrice d'adjacence dans la console
        System.out.println("Matrice d'adjacence :");
        for (int[] row : adjacencyMatrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    private int getIndexFromVertex(Object vertex) {
        // Recherche l'index de la cellule dans le tableau vertices
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
 	   GraphVisualisation frame = new GraphVisualisation();
 	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 	    frame.setSize(400, 320);
 	    frame.setVisible(true);
}
}

