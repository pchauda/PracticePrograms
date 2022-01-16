package com.p.pc.cracking_the_coding_interview.trees_graphs;

/**
 * <p>Program to perform a DFS on a disconnected graph. Idea is to follow the DFS algo and then perform DFS again on
 * remaining unvisited nodes.</p>
 */
public class DFSForDisconnectedGraph {

    public static void main(String[] args) {
        DFSForDisconnectedGraph obj = new DFSForDisconnectedGraph();
        Graph2 g = Graph2.createAndPrintGraph(6);
        System.out.println("Following is Depth First Traversal of the disconnected graph");
        obj.performDFS(g, 6); // Output: 0 1 4 2 3 5
    }

    // For each unvisited node, perform DFS
    private void performDFS(Graph2 g, int vertices) {
        boolean[] visited = new boolean[vertices];
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                performDFSUtil(g, i, visited);
            }
        }
    }

    // Perform a regular DFS on a graph in a recursive manner
    private void performDFSUtil(Graph2 g, Integer v, boolean[] visited) {
        System.out.print(v + " ");
        visited[v] = true;
        for (Integer adj : g.adjLists[v]) {
            if (!visited[adj]) {
                performDFSUtil(g, adj, visited);
            }
        }
    }
}
