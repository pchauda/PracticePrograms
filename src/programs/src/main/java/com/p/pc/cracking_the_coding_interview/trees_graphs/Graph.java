package com.p.pc.cracking_the_coding_interview.trees_graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Graph can be represented as adjacency matrix or adjacency list.
 * In case of matrix, mat[i][j] = 1 represents an edge from i to j. This approach takes V^2 space and adding a new vertex is V^2 operation.
 *  Adding or removing an edge is O(1).
 * In case of adjacency list, adding a vertex is easier, saves space.
 *
 * Below is the adjacency list representation of the graph.
 */
public class Graph {
    ArrayList<ArrayList<Integer>> adjacencyList; // Adjacency list for each vertex in the graph
    // Initialize a graph with v vertices
    public Graph(int v) {
        this.adjacencyList = new ArrayList<>(v);
        for(int i=0; i < v; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }
    // Adds an edge from u to v
    void addDirectedEdge(int u, int v) {
        this.adjacencyList.get(u).add(v);
    }
    // Adds edge from u to v and vice versa
    void addUnDirectedEdge(int u, int v) {
        this.adjacencyList.get(u).add(v);
        this.adjacencyList.get(v).add(u);
    }
    // Print graph
    void printGraph() {
        for (int i = 0; i < adjacencyList.size(); i++) {
            StringJoiner joiner = new StringJoiner(" -> ", "[", "]");
            joiner.add(Integer.toString(i));
            for (int j = 0; j < adjacencyList.get(i).size(); j++) {
                joiner.add(Integer.toString(adjacencyList.get(i).get(j)));
            }
            System.out.println(joiner);
        }
    }

}
