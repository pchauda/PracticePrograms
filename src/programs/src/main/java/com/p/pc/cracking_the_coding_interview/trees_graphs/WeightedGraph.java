package com.p.pc.cracking_the_coding_interview.trees_graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class WeightedGraph {
    int vertices;
    List<List<Edge>> adjacencyList;

    static class Edge {
        int source;
        int destination;
        int weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "{d=" + destination + ",w=" + weight + '}';
        }
    }

    WeightedGraph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>(vertices);
        for(int i=0; i<vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    void addDirectedEdge(int source, int destination, int weight) {
        Edge e = new Edge(source, destination, weight);
        adjacencyList.get(source).add(e);
    }

    void addUnDirectedEdge(int source, int destination, int weight) {
        Edge e = new Edge(source, destination, weight);
        adjacencyList.get(source).add(e);
        Edge e1 = new Edge(destination, source, weight);
        adjacencyList.get(destination).add(e1);
    }

    void printGraph() {
        for (int i = 0; i < adjacencyList.size(); i++) {
            StringJoiner joiner = new StringJoiner(" -> ", "[", "]");
            joiner.add(Integer.toString(i));
            for (int j = 0; j < adjacencyList.get(i).size(); j++) {
                joiner.add(adjacencyList.get(i).get(j).toString());
            }
            System.out.println(joiner);
        }
    }
}
