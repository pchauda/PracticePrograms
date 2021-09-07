package com.p.pc.cracking_the_coding_interview.trees_graphs;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Graph2 {
    int vertices;
    ArrayList<Integer>[] adjLists;

    Graph2(int vertices) {
        this.vertices = vertices;
        this.adjLists = new ArrayList[vertices];
        for(int i=0; i<vertices; i++) {
            adjLists[i] = new ArrayList<>();
        }
    }
    // Add a directed edge from u to v
    void addEdge(int u, int v) {
        adjLists[u].add(v);
    }

    // Print graph
    void printGraph() {
        for (int i = 0; i < vertices; i++) {
            ArrayList<Integer> adjList = adjLists[i];
            StringJoiner joiner = new StringJoiner(" -> ", "[", "]");
            joiner.add(Integer.toString(i));
            adjList.stream().forEach(t -> joiner.add(Integer.toString(t)));
            System.out.println(joiner);
        }
    }

    static Graph2 createAndPrintGraph(int vertices) {
        Graph2 g = new Graph2(vertices);
        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        g.addEdge(5, 4);
        System.out.println("Given disconnected graph:");
        g.printGraph();
        return g;
    }
}
