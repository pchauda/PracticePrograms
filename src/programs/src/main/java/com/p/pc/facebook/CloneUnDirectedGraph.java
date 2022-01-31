package com.p.pc.facebook;

import java.util.*;

/**
 * Clone a given UnDirected graph and then compare original and cloned graph to ensure they match.
 *
 * Approach:
 * Perform DFS and create a clone node for each new node encountered. Maintain a map of previously visited node to newly
 * created node to avoid creating new cloned nodes for already visited nodes.
 */
public class CloneUnDirectedGraph {

    public static void main(String[] args) {
        GraphNode root = createUndirectedGraph(5, 8);
        System.out.println("Original Graph:");
        printGraph(root);
        GraphNode clone = cloneGraph(root);
        System.out.println("Cloned Graph:");
        printGraph(clone);
        boolean isEqual = compareGraph(root, clone, new HashSet<>());
        System.out.println("Graph and cloned graph are " + (isEqual ? "equal": "not equal"));
    }

    static GraphNode cloneGraph(GraphNode root) {
        Map<GraphNode, GraphNode> visitedNodes = new HashMap<>();
        return cloneGraphRecursive(root, visitedNodes);
    }

    static GraphNode cloneGraphRecursive(GraphNode root, Map<GraphNode, GraphNode> visitedNodes) {
        if(root == null) return null;
        GraphNode rNew = new GraphNode(root.value);
        visitedNodes.put(root, rNew);

        if(root.neighbours != null) {
            for(GraphNode node : root.neighbours) {
                GraphNode clone = visitedNodes.get(node);
                if(clone == null) {
                    rNew.neighbours.add(cloneGraphRecursive(node, visitedNodes));
                } else {
                    rNew.neighbours.add(clone);
                }
            }
        }
        return rNew;
    }

    private static boolean compareGraph(GraphNode root, GraphNode clone, Set<Integer> visited) {
        if(root == null && clone == null) return true;
        if(root == null || clone == null) return false;
        if(root.value != clone.value) return false;
        if(root.neighbours.size() != clone.neighbours.size()) return false;
        visited.add(root.value);
        for(GraphNode r1 : root.neighbours) {
            boolean foundMatch = false;
            for(GraphNode c1 : clone.neighbours) {
                if(r1.value == c1.value) {
                    if(!visited.contains(r1.value)) {
                        compareGraph(r1, c1, visited);
                    }
                    foundMatch = true;
                    break;
                }
            }
            if(!foundMatch) return false;
        }
        return true;
    }

    /**
     * Undirected Graph having n nodes where all nodes are connected to other will have maximum of (n * (n - 1) ) / 2 edges.
     */
    static GraphNode createUndirectedGraph(int verticesCount, int edgeCount) {
        if(verticesCount == 0) return null;
        // all nodes
        List<GraphNode> vertices = new ArrayList<>(verticesCount);
        for(int i=0; i < verticesCount; i++) {
            vertices.add(new GraphNode(i));
        }
        // all possible edges
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        for(int i=0; i < verticesCount; i++) {
            for(int j=0; j != i && j < verticesCount; j++) {
                edges.add(new Pair<>(i,j));
            }
        }
        Collections.shuffle(edges);
        // create graph
        for(int i=0; i < edgeCount && i < edges.size(); i++) {
            Pair<Integer, Integer> edge = edges.get(i);
            vertices.get(edge.first).neighbours.add(vertices.get(edge.second));
            vertices.get(edge.second).neighbours.add(vertices.get(edge.first));
        }
        return vertices.get(0);
    }

    static void printGraphRecursive(GraphNode root, Set<GraphNode> visitedNodes) {
        if(root == null || visitedNodes.contains(root)) return;
        visitedNodes.add(root);
        StringJoiner joiner = new StringJoiner(",", "{", "}");
        for(GraphNode node: root.neighbours) {
            joiner.add(Integer.toString(node.value));
        }
        System.out.println(root.value + ": " + joiner);
        // Explore the neighbours
        for(GraphNode node: root.neighbours) {
            printGraphRecursive(node, visitedNodes);
        }
    }

    static void printGraph(GraphNode root) {
        Set<GraphNode> visitedNodes = new HashSet<>();
        printGraphRecursive(root, visitedNodes);
    }

    static class GraphNode {
        int value;
        List<GraphNode> neighbours;
        GraphNode(int value) {
            this.value = value;
            this.neighbours = new ArrayList<>();
        }
    }

    static class Pair<K, V> {
        K first;
        V second;
        Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }
    }
}
