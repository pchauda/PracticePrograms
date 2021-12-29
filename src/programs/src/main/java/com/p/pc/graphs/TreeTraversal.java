package com.p.pc.graphs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given the input Tree like below (not a binary search tree):
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
 * Given a binary search tree, the inorder traversal results in a sorted array.
 *
 * @param <K>
 */

public class TreeTraversal<K> {
    public static void main(String[] args) {
        TreeTraversal<Integer> tree = new TreeTraversal<>();

        TreeNode<Integer> root = new TreeNode<>(5, null);
        root.left = new TreeNode<>(3, root);
        root.right = new TreeNode<>(8, root);
        root.left.left = new TreeNode<>(2, root.left);
        root.left.right = new TreeNode<>(4, root.left);
        root.right.left = new TreeNode<>(6, root.right);
        root.right.right = new TreeNode<>(9, root.right);
        //root.left.left.right = new TreeNode<>(8, root.left.left);
        //root.right.left.right = new TreeNode<>(9, root.left.right);

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
        System.out.println("\nPrinting LevelOrder Traversal using 2 queues:");
        tree.levelOrderTraversalUsingTwoQueue(root);

        System.out.println("\nPrinting Reverse LevelOrder Traversal:");
        tree.printReverseLevelOrderTraversal(root);

        System.out.println("\nPrinting Reverse LevelOrder Traversal using Breadth First Search with Depth Info:");
        tree.breadthFirstSearchWithDepthInfo(root);

        System.out.println("\nPrinting Reverse LevelOrder Traversal using Pre-Order:");
        tree.levelOrderTraversalViaPreOrderAlgo(root);
    }

    // Recursive
    void printInOrderTraversal(TreeNode<K> root) {
        if(root == null) return;

        printInOrderTraversal(root.left);
        System.out.print(root.value + " ");
        printInOrderTraversal(root.right);
    }

    void printPreOrderTraversal(TreeNode<K> root) {
        if(root == null) return;

        System.out.print(root.value + " ");
        printPreOrderTraversal(root.left);
        printPreOrderTraversal(root.right);
    }

    void printPostOrderTraversal(TreeNode<K> root) {
        if(root == null) return;

        printPostOrderTraversal(root.left);
        printPostOrderTraversal(root.right);
        System.out.print(root.value + " ");
    }

    // Iterative using stack
    void printInOrderTraversalIterative(TreeNode<K> root) {
        Stack<TreeNode<K>> stack = new Stack<>();
        TreeNode<K> current = root;
        while(!stack.empty() || current != null) {
            if(current != null) {
                stack.push(current);
                current = current.left;
            } else {
                TreeNode<K> pop = stack.pop();
                System.out.print(pop.value + " ");
                current = pop.right;
            }
        }
    }
    // Iterative using stack
    void printPreOrderTraversalIterative(TreeNode<K> root) {
        Stack<TreeNode<K>> stack = new Stack<>();
        TreeNode<K> current = root;
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
    // Using LinkedList as Stack
    void printPostOrderTraversalIterative(TreeNode<K> root) {
        LinkedList<TreeNode<K>> stack = new LinkedList<>();
        LinkedList<TreeNode<K>> outputStack = new LinkedList<>();
        TreeNode<K> current = root;
        while(!stack.isEmpty() || current != null) {
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
        while(!outputStack.isEmpty()) {
            System.out.print(outputStack.pop().value + " ");
        }

    }
    // level Order Traversal using two queues
    void levelOrderTraversalUsingTwoQueue(TreeNode root) {
        if(root == null) return;
        Queue<TreeNode> q1 = new ArrayDeque<>();
        Queue<TreeNode> q2 = new ArrayDeque<>();

        q1.add(root);
        while(!q1.isEmpty()) {
            TreeNode node = q1.poll();
            System.out.print(node.value + ",");
            if(node.left != null) q2.add(node.left);
            if(node.right != null) q2.add(node.right);
            if(q1.isEmpty()) {
                System.out.println();
                Queue<TreeNode> tmp = q1;
                q1 = q2;
                q2 = tmp;
            }
        }
    }

    // BFS using queue
    void printLevelOrderTraversal(TreeNode<K> root) {
        Queue<TreeNode<K>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<K> treeNode = queue.poll();
            System.out.print(treeNode.value + " ");
            if(treeNode.left != null) {
                queue.add(treeNode.left);
            }
            if(treeNode.right != null) {
                queue.add(treeNode.right);
            }
        }
    }
    // Reverse level order using queue and stack for output similar to Post Order Traversal
    void printReverseLevelOrderTraversal(TreeNode<K> root) {
        Queue<TreeNode<K>> queue = new ArrayDeque<>();
        queue.add(root);
        Stack<TreeNode<K>> output = new Stack<>();
        while(!queue.isEmpty()) {
            TreeNode<K> treeNode = queue.poll();
            output.push(treeNode);
            // Add right child first to the queue if you still need normal order for a given level
            if(treeNode.right != null) {
                queue.add(treeNode.right);
            }
            if(treeNode.left != null) {
                queue.add(treeNode.left);
            }
        }
        while(!output.empty()) {
            System.out.print(output.pop().value + " ");
        }
    }

    // BFS with depth info, this algo can be used to print the tree in reverse level order
    void breadthFirstSearchWithDepthInfo(TreeNode<K> root) {
        Queue<TreeNode<K>> queue = new ArrayDeque<>();
        Map<Integer, List<TreeNode<K>>> map = new HashMap<>();

        queue.add(root);
        map.put(0, Arrays.asList(root));
        int level = 1;
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            List<TreeNode<K>> treeNodes = new ArrayList<>();
            while(levelSize-- > 0) {
                TreeNode<K> treeNode = queue.poll();
                if(treeNode.left != null) {
                    queue.add(treeNode.left);
                    treeNodes.add(treeNode.left);
                }
                if(treeNode.right != null) {
                    queue.add(treeNode.right);
                    treeNodes.add(treeNode.right);
                }
            }
            if(treeNodes.size() > 0)
                map.put(level++, treeNodes);
        }
        for(int i = map.keySet().size() - 1; i >= 0; i--) {
            List<TreeNode<K>> treeNodes = map.get(i);
            System.out.println("Level: " + i + ", Nodes: " + treeNodes.stream().map(t -> t.value).collect(Collectors.toList()));
        }
    }

    // Level Order via Pre Order Algo with depth info using recursion
    void levelOrderTraversalViaPreOrderAlgo(TreeNode<K> root) {
        Map<Integer, List<TreeNode<K>>> map = new HashMap<>();
        printPreOrderTraversal(root, 0, map);
        for(int i = map.keySet().size() - 1; i >= 0; i--) {
            List<TreeNode<K>> treeNodes = map.get(i);
            System.out.println("Level: " + i + ", Nodes: " + treeNodes.stream().map(t -> t.value).collect(Collectors.toList()));
        }
    }
    void printPreOrderTraversal(TreeNode<K> root, Integer level, Map<Integer, List<TreeNode<K>>> map) {
        if(root == null) return;
        addToMap(map, level, root);
        printPreOrderTraversal(root.left, level + 1, map);
        printPreOrderTraversal(root.right, level + 1, map);
    }
    private void addToMap(Map<Integer, List<TreeNode<K>>> map, Integer level, TreeNode<K> treeNode) {
        map.putIfAbsent(level, new ArrayList<>());
        map.get(level).add(treeNode);
    }

    static class TreeNode<K> {
        K value;
        TreeNode<K> left;
        TreeNode<K> right;
        TreeNode<K> parent;

        public TreeNode(K value, TreeNode<K> parent) {
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


