package Pekan9;

import java.util.*;

public class TugasSearchingGraph {
    private Map<String, List<String>> graph;

    public TugasSearchingGraph() {
        graph = new HashMap<>();

        // Menambahkan edge (graf tak berarah)
        addEdge("A", "B");
        addEdge("A", "C");
        addEdge("B", "D");
        addEdge("B", "E");
        addEdge("C", "F");
        addEdge("C", "E");
        addEdge("D", "H");
        addEdge("E", "F");
        addEdge("F", "G");
        addEdge("H", "G");
    }

    private void addEdge(String node1, String node2) {
        graph.putIfAbsent(node1, new ArrayList<>());
        graph.putIfAbsent(node2, new ArrayList<>());
        graph.get(node1).add(node2);
        graph.get(node2).add(node1);
    }

    public void search(String startNode, String goalNode) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        Map<String, String> parent = new HashMap<>();

        stack.push(startNode);
        visited.add(startNode);

        int langkah = 1;
        boolean found = false;

        while (!stack.isEmpty()) {
            String current = stack.pop();
            System.out.println("Langkah " + langkah + ": Kunjungi " + current);
            langkah++;

            if (current.equals(goalNode)) {
                found = true;
                break;
            }

            for (String neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    stack.push(neighbor);
                    parent.put(neighbor, current);
                }
            }
        }

        if (found) {
            System.out.println("Tujuan " + goalNode + " ditemukan");

            // Membangun rute dari goal ke start
            List<String> path = new ArrayList<>();
            String current = goalNode;
            while (current != null) {
                path.add(current);
                current = parent.get(current);
            }

            Collections.reverse(path);
            System.out.println("Rute: " + String.join(" → ", path));
        } else {
            System.out.println("Node tujuan tidak ditemukan.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Nama: [Fadil Insanus Siddik]");
        System.out.println("NIM: 2411532013");
        System.out.println("\nNode awal: A");
        System.out.println("Node tujuan: G");
        System.out.println("Algoritma: DFS\n");

        TugasSearchingGraph graf = new TugasSearchingGraph();
        graf.search("A", "G");
    }
}
