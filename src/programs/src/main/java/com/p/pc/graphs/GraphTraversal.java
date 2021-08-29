package com.p.pc.graphs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Graph Traversal algorithms, mainly focusing on Breadth First traversal and Depth First Traversal.
 */
public class GraphTraversal {
    public static void main(String[] args) {
        GraphTraversal gt = new GraphTraversal();
        // 1 .. 2 3 4 .. 5 6 7 8 9 10 11 12 13 .. 14 15  // .. represents the next level
        GraphVertex<Integer> root = gt.createGraph();

        System.out.println("Breadth first traversal");
        List<Integer> bfsTraversal = new ArrayList<>(15);
        gt.performBreadthFirstTraversal(root, bfsTraversal);
        System.out.println(bfsTraversal); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]

        System.out.println("Depth first traversal");
        List<Integer> dfsTraversal = new ArrayList<>(15);
        gt.performDepthFirstTraversal(root, dfsTraversal);
        System.out.println(dfsTraversal); // [1, 2, 5, 14, 6, 7, 3, 8, 15, 9, 10, 4, 11, 12, 13]

        System.out.println("Depth first traversal Recursive");
        List<Integer> dfsTraversalRecursive = new ArrayList<>(15);
        gt.performDepthFirstTraversalRecursive(root, dfsTraversalRecursive);
        System.out.println(dfsTraversalRecursive); // [1, 2, 5, 14, 6, 7, 3, 8, 15, 9, 10, 4, 11, 12, 13]

        System.out.println("Level order traversal with level info - Iterative");
        gt.performLevelOrderTraversalWithLevelInfoIterative(root);
        System.out.println("Level order traversal with level info - Recursive");
        gt.performLevelOrderTraversalWithLevelInfoRecursive(root);
    }

    // Follow Pre-Order traversal algo with level info to collect all nodes in map
    void performLevelOrderTraversalWithLevelInfoRecursive(GraphVertex<Integer> root) {
        Map<Integer, List<GraphVertex<Integer>>> levelMap = new HashMap<>();
        dFSWithInfoRecursive(root, 0, levelMap);
        for (int i = 0; i < levelMap.keySet().size(); i++) {
            List<Integer> nodeKeys = levelMap.get(i).stream().map(t -> t.key).collect(Collectors.toList());
            System.out.println("Level: " + i + ", Nodes: " + nodeKeys);
        }
    }

    private void dFSWithInfoRecursive(GraphVertex<Integer> node, int level, Map<Integer, List<GraphVertex<Integer>>> levelMap) {
        if (node != null) {
            addToMap(node, level, levelMap);
            if (node.children != null) {
                for (GraphVertex<Integer> child : node.children) {
                    dFSWithInfoRecursive(child, level + 1, levelMap);
                }
            }
        }
    }

    private void addToMap(GraphVertex<Integer> node, int level, Map<Integer, List<GraphVertex<Integer>>> levelMap) {
        levelMap.putIfAbsent(level, new ArrayList<>());
        levelMap.get(level).add(node);
    }

    // Use BFS algo with depth, collect all nodes in a map by level.
    // Idea here is to poll all elements from the same level in one iteration only using the queue size.
    void performLevelOrderTraversalWithLevelInfoIterative(GraphVertex<Integer> root) {
        Queue<GraphVertex<Integer>> queue = new ArrayDeque<>();
        Map<Integer, List<GraphVertex<Integer>>> levelMap = new HashMap<>();
        int level = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            while (levelSize-- > 0) {
                GraphVertex<Integer> node = queue.poll();
                addToMap(node, level, levelMap);
                if (node.children != null) {
                    node.children.stream().forEach(t -> queue.add(t));
                }
            }
            level++;
        }
        for (int i = 0; i < levelMap.keySet().size(); i++) {
            List<Integer> nodeKeys = levelMap.get(i).stream().map(t -> t.key).collect(Collectors.toList());
            System.out.println("Level: " + i + ", Nodes: " + nodeKeys);
        }
    }

    void performBreadthFirstTraversal(GraphVertex<Integer> root, List<Integer> bfsTraversal) {
        Queue<GraphVertex<Integer>> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            GraphVertex<Integer> node = queue.poll();
            bfsTraversal.add(node.key);
            if (node.children != null) {
                for (GraphVertex<Integer> child : node.children) {
                    queue.add(child);
                }
            }
        }
    }

    // Using algo of Pre-Order iterative
    private void performDepthFirstTraversal(GraphVertex<Integer> root, List<Integer> dfsTraversal) {
        Stack<GraphVertex<Integer>> stack = new Stack<>();
        GraphVertex<Integer> current = root;

        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                dfsTraversal.add(current.key);
                GraphVertex<Integer> leftMostChild = null;
                if (current.children != null) {
                    for (int i = current.children.size() - 1; i >= 0; i--) {
                        if (i == 0) {
                            leftMostChild = current.children.get(i);
                        } else {
                            stack.push(current.children.get(i));
                        }
                    }
                }
                current = leftMostChild;
            } else {
                current = stack.pop();
            }
        }
    }

    // Using Pre-order traversal's recursive approach
    private void performDepthFirstTraversalRecursive(GraphVertex<Integer> root, List<Integer> dfsTraversalRecursive) {
        if (root != null) {
            dfsTraversalRecursive.add(root.key);
            if (root.children != null) {
                for (GraphVertex<Integer> child : root.children) {
                    performDepthFirstTraversalRecursive(child, dfsTraversalRecursive);
                }
            }
        }
    }

    GraphVertex<Integer> createGraph() {
        int i = 2;
        GraphVertex<Integer> root = new GraphVertex<>(1);
        while (i <= 4) {
            root.addChild(new GraphVertex<>(i++));
        }

        for (GraphVertex<Integer> child : root.children) {
            child.addChild(new GraphVertex<>(i++));
            child.addChild(new GraphVertex<>(i++));
            child.addChild(new GraphVertex<>(i++));
        }

        GraphVertex<Integer> leafNode1 = root.children.get(0).children.get(0);
        leafNode1.addChild(new GraphVertex<>(i++));

        GraphVertex<Integer> leafNode2 = root.children.get(1).children.get(0);
        leafNode2.addChild(new GraphVertex<>(i++));
        return root;
    }

    static class GraphVertex<K> {
        K key;
        List<GraphVertex<K>> children;

        GraphVertex(K key) {
            this.key = key;
        }

        void addChild(GraphVertex<K> child) {
            if (children == null) {
                children = new LinkedList<>();
            }
            children.add(child);
        }
    }

}
