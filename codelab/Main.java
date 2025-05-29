import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

class LogisticsGraph {
    private int numWarehouses;
    private LinkedList<Integer>[] adjList;

    @SuppressWarnings("unchecked")
    LogisticsGraph(int numWarehouses) {
        this.numWarehouses = numWarehouses;
        adjList = new LinkedList[numWarehouses];
        for (int i = 0; i < numWarehouses; ++i) {
            adjList[i] = new LinkedList<>();
        }
    }

    void addRoute(int source, int destination) {
        adjList[source].add(destination);
    }

    // BFS traversal dari gudang sumber
    void BFS(int startWarehouse) {
        System.out.print("BFS Traversal dari gudang " + startWarehouse + ": ");
        boolean[] visited = new boolean[numWarehouses];
        Queue<Integer> queue = new LinkedList<>();

        visited[startWarehouse] = true;
        queue.add(startWarehouse);

        while (!queue.isEmpty()) {
            int currentWarehouse = queue.poll();
            System.out.print(currentWarehouse + " ");

            Iterator<Integer> i = adjList[currentWarehouse].listIterator();
            while (i.hasNext()) {
                int neighbor = i.next();
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    // DFS traversal (helper rekursif)
    private void DFSUtil(int warehouse, boolean[] visited, StringBuilder path) {
        visited[warehouse] = true;
        path.append(warehouse).append(" ");

        Iterator<Integer> i = adjList[warehouse].listIterator();
        while (i.hasNext()) {
            int neighbor = i.next();
            if (!visited[neighbor]) {
                DFSUtil(neighbor, visited, path);
            }
        }
    }

    // DFS traversal dari gudang sumber
    void DFS(int startWarehouse) {
        System.out.print("DFS Traversal dari gudang " + startWarehouse + ": ");
        boolean[] visited = new boolean[numWarehouses];
        StringBuilder path = new StringBuilder();
        DFSUtil(startWarehouse, visited, path);
        System.out.println(path.toString().trim());
    }

    // Menampilkan Adjacency Matrix
    void printAdjacencyMatrix() {
        System.out.println("\nAdjacency Matrix:");
        System.out.print("   "); // Spasi untuk header kolom
        for (int i = 0; i < numWarehouses; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.print("   ");
        for (int i = 0; i < numWarehouses; i++) {
            System.out.print("--");
        }
        System.out.println();

        for (int i = 0; i < numWarehouses; i++) {
            System.out.print(i + "| ");
            for (int j = 0; j < numWarehouses; j++) {
                if (adjList[i].contains(j)) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Metode main untuk Codelab
    public static void main(String[] args) {
        int numWarehouses = 5; // A=0, B=1, C=2, D=3, E=4
        LogisticsGraph logisticsGraph = new LogisticsGraph(numWarehouses);

        // 1. Buatlah representasi Directed Graph untuk 5 gudang dengan total 7 jalur pengiriman.
        System.out.println("--- CODELAB: Implementasi Graph Perusahaan Logistik ---");
        System.out.println("Gudang dinomori 0(A) hingga 4(E).");

        logisticsGraph.addRoute(0, 1);
        logisticsGraph.addRoute(0, 2);
        logisticsGraph.addRoute(1, 3);
        logisticsGraph.addRoute(2, 3); // C -> D
        logisticsGraph.addRoute(2, 4); // C -> E
        logisticsGraph.addRoute(3, 4); // D -> E
        logisticsGraph.addRoute(4, 0); // E -> A (siklus)

        // Output: Tampilkan adjacency matrix
        logisticsGraph.printAdjacencyMatrix();

        // 2. Implementasikan metode BFS dari gudang A (0)
        logisticsGraph.BFS(0);

        // 3. Implementasikan metode DFS dari gudang A (0)
        logisticsGraph.DFS(0);

        System.out.println("------------------------------------------------------");
    }
}