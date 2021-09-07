package com.p.pc.cracking_the_coding_interview.trees_graphs;

import java.util.Stack;

/**
 * Program to print topological sort for a given graph (maybe disconnected).
 *
 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every
 * directed edge u v, vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.
 *
 * Topological sort is different from DFS as in DFS the nodes are not guaranteed to appear before their adjacent nodes.
 *
 * Approach:
 *  Algorithm is similar to DFS, however, instead of printing/collecting the element being visited, we first visit all
 *  its adjacent elements in a recursive manner and then put the element on a stack at last.
 *
 *  Popping elements from the stack will give topological sort of the graph.
 */
public class TopologicalSortOfGraph {
    static TopologicalSortOfGraph obj = new TopologicalSortOfGraph();

    public static void main(String[] args) {
        Graph2 g = Graph2.createAndPrintGraph(6);
        obj.performTopologicalSort(g); // Output: 5 2 3 0 1 4
    }
    // Perform a DFS with a stack
    private void performTopologicalSort(Graph2 g) {
        boolean[] visited = new boolean[g.vertices];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < g.vertices; i++) {
            if(!visited[i]) {
                performTopologicalSortUtil(g, i, visited, stack);
            }
        }
        while(!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    private void performTopologicalSortUtil(Graph2 g, Integer v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for(Integer adj : g.adjLists[v]) {
            if(!visited[adj]) {
                performTopologicalSortUtil(g, adj, visited, stack);
            }
        }
        stack.push(v);
    }

}
