package com.p.pc.cracking_the_coding_interview.trees_graphs;

import java.util.*;

/**
 * Given a directed graph find out the route between two nodes. Also find all simple paths from node a to node b.
 * <p>
 * Graph:
 * 0 -> 1, 2
 * 1 -> 3, 5
 * 2 -> 3
 * 3 -> 0
 * 4 -> 1
 * 5 -> 1, 2, 3
 */
public class FindRouteBetweenTwoNodesInGraph {

    public static void main(String[] args) {
        // initialize the graph with 6 vertices
        Graph g = new Graph(6);
        addEdges(g);
        g.printGraph();

        System.out.println("Does route exists from 0 to 4? " + routeExists(g, 0, 4)); // Output: false
        System.out.println("Does route exists from 4 to 0? " + routeExists(g, 4, 0)); // Output: true
        System.out.println("Find all paths between node 1 to 3: " + findAllPathsBetweenNodes(g, 1, 3)); // Output: [[1, 3], [1, 5, 2, 3], [1, 5, 3]]
        System.out.println("Find all paths between node 2 to 0: " + findAllPathsBetweenNodes(g, 2, 0)); // Output: [[2, 0], [2, 3, 0]]
    }

    private static void addEdges(Graph g) {
        g.addDirectedEdge(0, 1);
        g.addUnDirectedEdge(0, 2); // directed edge from 0 to 2 and another directed edge from 2 to 0
        g.addDirectedEdge(1, 3);
        g.addDirectedEdge(1, 5);
        g.addDirectedEdge(2, 3);
        g.addDirectedEdge(3, 0);
        g.addDirectedEdge(4, 1);
        g.addDirectedEdge(5, 1);
        g.addDirectedEdge(5, 2);
        g.addDirectedEdge(5, 3);
    }

    // Perform BFS from the starting vertex and check if its adjacency list contains the end vertex.
    // Keep track of visited vertices so that we don't visit it again
    private static boolean routeExists(Graph g, int fromVertex, int toVertex) {
        LinkedList<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(fromVertex);
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            visited.add(vertex);
            for (int adj : g.adjacencyList.get(vertex)) {
                if (adj == toVertex) return true;
                if (!visited.contains(adj)) {
                    queue.add(adj);
                }
            }
        }
        return false;
    }

    private static List<List<Integer>> findAllPathsBetweenNodes(Graph g, Integer u, Integer v) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(u); // Add the starting vertex to the path
        Set<Integer> visited = new HashSet<>();
        dfsRecursive(g, u, v, visited, path, paths);
        return paths;
    }

    // Find all routes between nodes, use recursive DFS to find all routes
    private static void dfsRecursive(Graph g, Integer u, Integer v, Set<Integer> visited, List<Integer> path, List<List<Integer>> paths) {
        // If reached the destination then collect the path and return
        if (u.equals(v)) {
            paths.add(new ArrayList<>(path));
            return;
        }
        // add the current node as visited to avoid circular paths
        visited.add(u);
        for (Integer adj : g.adjacencyList.get(u)) {
            if (!visited.contains(adj)) {
                path.add(adj); // add the current adj vertex to the path and then explore the node in DFS fashion
                dfsRecursive(g, adj, v, visited, path, paths);
                path.remove(adj); // remove the current adj vertex from the path as it is no longer the part of the path
            }
        }
        // remove the current node from visited to be able to explore other paths
        visited.remove(u);
    }
}
