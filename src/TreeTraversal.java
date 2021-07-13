import com.sun.security.jgss.GSSUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given the input Tree like below:
 *                         1
 *                2                 3
 *          4           5     6           7
 *              8                 9
 *
 * InOrder Traversal:    Left -> Node -> Right:                  4 8 2 5 1 6 9 3 7
 * PreOrder Traversal:   Node -> Left -> Right:                  1 2 4 8 5 3 6 9 7
 * PostOrder Traversal:  Left -> Right -> Node:                  8 4 5 2 9 6 7 3 1

 * LevelOrder Traversal: Print all node in the same level first: 1 2 3 4 5 6 7 8 9
 * Reverse LevelOrder Traversal: Print bottom level nodes first: 8 9 4 5 6 7 2 3 1
 *
 * Breadth First Search is equivalent to Level Order Traversal.
 * Depth First Search is equivalent to PreOrder Traversal.
 *
 * @param <K>
 */

public class TreeTraversal<K> {
    public static void main(String[] args) {
        TreeTraversal<Integer> tree = new TreeTraversal<>();

        Node<Integer> root = new Node<>(1, null);
        root.left = new Node<>(2, root);
        root.right = new Node<>(3, root);
        root.left.left = new Node<>(4, root.left);
        root.left.right = new Node<>(5, root.left);
        root.right.left = new Node<>(6, root.right);
        root.right.right = new Node<>(7, root.right);
        root.left.left.right = new Node<>(8, root.left.left);
        root.right.left.right = new Node<>(9, root.left.right);

        System.out.println("Printing InOrder Traversal:");
        tree.printInOrderTraversal(root);
        System.out.println("\nPrinting InOrder Traversal Iterative:");
        tree.printInOrderTraversalIterative(root);

        System.out.println("\nPrinting PreOrder Traversal:");
        tree.printPreOrderTraversal(root);
        System.out.println("\nPrinting PreOrder Traversal Iterative:");
        tree.printPreOrderTraversalIterative(root);

        System.out.println("\nPrinting PostOrder Traversal:");
        tree.printPostOrderTraversal(root);
        System.out.println("\nPrinting PostOrder Traversal Iterative:");
        tree.printPostOrderTraversalIterative(root);

        System.out.println("\nPrinting LevelOrder Traversal:");
        tree.printLevelOrderTraversal(root);

        System.out.println("\nPrinting Reverse LevelOrder Traversal:");
        tree.printReverseLevelOrderTraversal(root);

        System.out.println("\nPrinting Reverse LevelOrder Traversal using Breadth First Search with Depth Info:");
        tree.breadthFirstSearchWithDepthInfo(root);

        System.out.println("\nPrinting Reverse LevelOrder Traversal using Pre-Order:");
        tree.levelOrderTraversalViaPreOrderAlgo(root);
    }

    // Recursive
    void printInOrderTraversal(Node<K> root) {
        if(root == null) return;

        printInOrderTraversal(root.left);
        System.out.print(root.value + " ");
        printInOrderTraversal(root.right);
    }

    void printPreOrderTraversal(Node<K> root) {
        if(root == null) return;

        System.out.print(root.value + " ");
        printPreOrderTraversal(root.left);
        printPreOrderTraversal(root.right);
    }

    void printPostOrderTraversal(Node<K> root) {
        if(root == null) return;

        printPostOrderTraversal(root.left);
        printPostOrderTraversal(root.right);
        System.out.print(root.value + " ");
    }

    // Iterative using stack
    void printInOrderTraversalIterative(Node<K> root) {
        Stack<Node<K>> stack = new Stack<>();

        Node<K> node = root;
        while(!stack.empty() || node != null) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            } else {
                Node<K> pop = stack.pop();
                System.out.print(pop.value + " ");
                node = pop.right;
            }
        }
    }
    // Iterative using stack
    void printPreOrderTraversalIterative(Node<K> root) {
        Stack<Node<K>> stack = new Stack<>();
        Node<K> current = root;
        while(!stack.empty() || current != null) {
            if(current != null) {
                System.out.print(current.value + " ");
                if(current.right != null) {
                    stack.push(current.right);
                }
                current = current.left;
            } else {
                current = stack.pop();
            }
        }
    }
    // Left -> Right -> Root
    void printPostOrderTraversalIterative(Node<K> root) {
        Stack<Node<K>> stack = new Stack<>();
        Stack<Node<K>> outputStack = new Stack<>();
        Node<K> current = root;
        while(!stack.empty() || current != null) {
            if(current != null) {
                outputStack.push(current);
                if(current.left != null) {
                    stack.push(current.left);
                }
                current = current.right;
            } else {
                current = stack.pop();
            }
        }
        while(!outputStack.empty()) {
            System.out.print(outputStack.pop().value + " ");
        }

    }

    // BFS using queue
    void printLevelOrderTraversal(Node<K> root) {
        Queue<Node<K>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<K> node = queue.poll();
            System.out.print(node.value + " ");
            if(node.left != null) {
                queue.add(node.left);
            }
            if(node.right != null) {
                queue.add(node.right);
            }
        }
    }

    // BFS with depth info, use this algo to print the tree in reverse level order
    void breadthFirstSearchWithDepthInfo(Node<K> root) {
        Queue<Node<K>> queue = new ArrayDeque<>();
        Map<Integer, List<Node<K>>> map = new HashMap<>();

        queue.add(root);
        map.put(0, Arrays.asList(root));
        int level = 1;
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Node<K>> nodes = new ArrayList<>();
            while(levelSize-- > 0) {
                Node<K> node = queue.poll();
                if(node.left != null) {
                    queue.add(node.left);
                    nodes.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                    nodes.add(node.right);
                }
            }
            if(nodes.size() > 0)
                map.put(level++, nodes);
        }
        for(int i = map.keySet().size() - 1; i >= 0; i--) {
            List<Node<K>> nodes = map.get(i);
            System.out.println("Level: " + i + ", Nodes: " + nodes.stream().map(t -> t.value).collect(Collectors.toList()));
        }
    }
    // Reverse level order using queue and stack for output similar to Post Order Traversal
    void printReverseLevelOrderTraversal(Node<K> root) {
        Queue<Node<K>> queue = new ArrayDeque<>();
        queue.add(root);
        Stack<Node<K>> output = new Stack<>();
        while(!queue.isEmpty()) {
            Node<K> node = queue.poll();
            output.push(node);
            if(node.right != null) {
                queue.add(node.right);
            }
            if(node.left != null) {
                queue.add(node.left);
            }
        }
        while(!output.empty()) {
            System.out.print(output.pop().value + " ");
        }
    }

    // Reverse Level Order via Pre Order Algo with depth info using recursion
    void levelOrderTraversalViaPreOrderAlgo(Node<K> root) {
        Map<Integer, List<Node<K>>> map = new HashMap<>();
        printPreOrderTraversal(root, 0, map);
        for(int i = map.keySet().size() - 1; i >= 0; i--) {
            List<Node<K>> nodes = map.get(i);
            System.out.println("Level: " + i + ", Nodes: " + nodes.stream().map(t -> t.value).collect(Collectors.toList()));
        }
    }
    void printPreOrderTraversal(Node<K> root, Integer level, Map<Integer, List<Node<K>>> map) {
        if(root == null) return;
        addToMap(map, level, root);
        printPreOrderTraversal(root.left, level + 1, map);
        printPreOrderTraversal(root.right, level + 1, map);
    }
    void addToMap(Map<Integer, List<Node<K>>> map, Integer level, Node<K> node) {
        map.putIfAbsent(level, new ArrayList<>());
        map.get(level).add(node);
    }

    static class Node<K> {
        K value;
        Node<K> left;
        Node<K> right;
        Node<K> parent;

        public Node(K value, Node<K> parent) {
            this.value = value;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "value=" + value + " ";
        }

        public K getValue() {
            return value;
        }
    }
}


