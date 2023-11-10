package exo2;

import java.util.*;
import java.util.ArrayList;

import java.util.Collections;

import java.util.HashMap;

import java.util.HashSet;

import java.util.Iterator;

import java.util.LinkedList;

import java.util.List;

public class DFSWithPriority {
    private Map<Character, List<Character>> graph;

    public DFSWithPriority() {
        this.graph = new HashMap<>();
    }

    public void addEdge(char from, char to) {
        graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
    }

    public List<Character> dfs(char start, char target) {
        Set<Character> visited = new HashSet<>();
        List<Character> result = new ArrayList<>();
        dfsHelper(start, target, visited, result);
        return result;
    }

    private boolean dfsHelper(char node, char target, Set<Character> visited, List<Character> result) {
        if (node == target) {
            // Ajoutez le nœud cible au résultat et retournez true pour indiquer que la cible a été atteinte
            result.add(node);
            return true;
        }

        if (!visited.contains(node)) {
            visited.add(node);
            result.add(node);

            List<Character> neighbors = graph.getOrDefault(node, Collections.emptyList());
            Collections.sort(neighbors);

            for (char neighbor : neighbors) {
                if (dfsHelper(neighbor, target, visited, result)) {
                    return true; // Si la cible est atteinte dans le sous-arbre, arrêtez la recherche
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        DFSWithPriority graph = new DFSWithPriority();

        // Ajoutez vos arêtes ici
        graph.addEdge('S', 'A');
        graph.addEdge('S', 'G');
        graph.addEdge('A', 'B');
        graph.addEdge('A', 'C');
        graph.addEdge('B', 'D');
        graph.addEdge('C', 'D');
        graph.addEdge('C', 'G');
        graph.addEdge('D', 'G');

        char startNode = 'S';
        char targetNode = 'G';

        List<Character> dfsResult = graph.dfs(startNode, targetNode);

        if (!dfsResult.isEmpty()) {
            System.out.println("Le chemin DFS de " + startNode + " à " + targetNode + ": " + dfsResult);
        } else {
            System.out.println("Target node " + targetNode + " not found in DFS traversal from " + startNode);
        }
    }
}
