package com.p.pc.programs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an undirected graph, print all connected components line by line and calculate the total number of connected
 * components.
 * A graph can be represented as vertices and adjacencyList for each vertex.
 *
 * Approach:
 *  For every node, perform DFS if the node has not been visited. Mark the node as visited as part of DFS and perform DFS
 *  recursively on all adjacent vertices. Increment the count any time you encounter an unvisited vertex outside of DFS.
 */
public class ConnectedComponentsInUndirectedGraph {
    public static void main(String[] args) {
        Graph g = new Graph(9);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(7, 8);

        System.out.println("Connected components count: " + g.connectedComponents()); // Output: 3
    }
    // class representing a graph using vertices and adjacency list
    static class Graph {
        // total
        int vertices;
        List<List<Integer>> adjacencyList;

        public Graph(int n) {
            this.vertices = n;
            adjacencyList = new ArrayList<>();
            // initialize the adjacencyList for each vertex
            for(int i =0; i < n; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }
        // Add edge from vertex src to dest
        void addEdge(int src, int dest) {
            adjacencyList.get(src).add(dest);
            // since it is undirected graph also add a reverse edge
            adjacencyList.get(dest).add(src);
        }


        int connectedComponents() {
            // Mark all the vertices as not visited
            boolean[] visited = new boolean[vertices];
            int count = 0;
            // For each vertex, perform a DFS using the adjacencyList. this way, all vertices linked to a given first
            // vertex will be marked as visited and anytime a new un-visited vertex is encountered, it will represent
            // a disconnected graph and will be treated as a new connected component.
            for(int i=0; i < vertices; i++) {
                if(!visited[i]) {
                    performDFS(i, visited);
                    count++;
                    System.out.println();
                }
            }
            return count;
        }

        void performDFS(int v, boolean[] visited) {
            // Mark the current node as visited and print it
            visited[v] = true;
            System.out.print(v + " ");
            // Recur for all the vertices adjacent to this vertex
            for (int x : adjacencyList.get(v)) {
                if (!visited[x])
                    performDFS(x, visited);
            }
        }
    }
}
