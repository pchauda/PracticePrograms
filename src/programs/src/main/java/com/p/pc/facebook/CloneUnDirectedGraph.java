package com.p.pc.facebook;

import java.util.*;

public class CloneUnDirectedGraph {
    public static void main(String[] args) {
        Node root = createUndirectedGraph(5, 8);
        System.out.println("Original Graph:");
        printGraph(root);
        Node clone = cloneGraph(root);
        System.out.println("Cloned Graph:");
        printGraph(clone);
        boolean isEqual = compareGraph(root, clone, new HashSet<>());
        System.out.println("Graph and cloned graph are " + (isEqual ? "equal": "not equal"));
    }

    private static boolean compareGraph(Node root, Node clone, Set<Node> visited) {
        if(root == null && clone == null) return true;
        if(root == null || clone == null) return false;
        if(root.value != clone.value) return false;
        if(root.neighbours.size() != clone.neighbours.size()) return false;

        for(Node r1 : root.neighbours) {
            boolean foundMatch = false;
            for(Node c1 : clone.neighbours) {
                if(r1.value == c1.value) {
                    if(!visited.contains(r1)) {
                        visited.add(r1);
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

    static Node cloneGraph(Node root) {
        Map<Node, Node> visitedNodes = new HashMap<>();
        return cloneGraphRecursive(root, visitedNodes);
    }

    static Node cloneGraphRecursive(Node root, Map<Node, Node> visitedNodes) {
        if(root == null) return null;
        Node rNew = new Node(root.value);
        visitedNodes.put(root, rNew);

        if(root.neighbours != null) {
            for(Node node : root.neighbours) {
                Node clone = visitedNodes.get(node);
                if(clone == null) {
                    rNew.neighbours.add(cloneGraphRecursive(node, visitedNodes));
                } else {
                    rNew.neighbours.add(clone);
                }
            }
        }
        return rNew;
    }

    /**
     * Undirected Graph having n nodes where all nodes are connected to other will have maximum of (n * n - n) /2 edges.
     */
    static Node createUndirectedGraph(int verticesCount, int edgeCount) {
        if(verticesCount == 0) return null;
        // all nodes
        List<Node> vertices = new ArrayList<>(verticesCount);
        for(int i=0; i < verticesCount; i++) {
            vertices.add(new Node(i));
        }
        // all possible edges
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        for(int i=0; i < verticesCount; i++) {
            for(int j=i+1; j < verticesCount; j++) {
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

    static void printGraphRecursive(Node root, Set<Node> visitedNodes) {
        if(root == null || visitedNodes.contains(root)) return;
        visitedNodes.add(root);
        StringJoiner joiner = new StringJoiner(",", "{", "}");
        for(Node node: root.neighbours) {
            joiner.add(Integer.toString(node.value));
        }
        System.out.println(root.value + ": " + joiner);
        // Explore the neighbours
        for(Node node: root.neighbours) {
            printGraphRecursive(node, visitedNodes);
        }
    }

    static void printGraph(Node root) {
        Set<Node> visitedNodes = new HashSet<>();
        printGraphRecursive(root, visitedNodes);
    }

    static class Node {
        int value;
        List<Node> neighbours;
        Node(int value) {
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
