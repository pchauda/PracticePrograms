package com.p.pc.geekforgeeks.graph;

/**
 * Pre Order: Root is always the first element
 * In Order: All elements before the root element will be on its left side, all elements after root will be on its right side
 *
 * Algo:
 * 1. Pick the first element from PreOrder and mark it as root, pick next element in every recursive call
 * 2. Find the index of root element in InOrder
 * 3. build a tree for all elements before this index and assign it as left subtree of root
 * 4. build a tree for all elements after this index and assign it as right subtree of root
 */
public class ConstructABinaryTreeUsingInPreOrder {
    static int preIndex = 0;
    public static void main(String[] args) {
        char[] in = new char[] {'D', 'B', 'E', 'A', 'F', 'C'};
        char[] pre = new char[] {'A', 'B', 'D', 'E', 'C', 'F'};
        Node root = constructBinaryTree(in, pre, 0, in.length-1);
        printInOrderTraversal(root); // Output: D B E A F C
    }

    static Node constructBinaryTree(char[] in, char[] pre, int start, int end) {
        if(start > end) return null;
        Node root = new Node(pre[preIndex++]);
        if(start == end) return root; // This is just to optimize the method for leaf elements
        int index = findIndex(root, in, start, end);
        // This is only to cover scenario when InOrder and PreOrder traversals don't match
        if(index == -1) throw new IllegalArgumentException("InOrder and PreOrder traversals don't belong to the same tree");

        root.left = constructBinaryTree(in, pre, start, index - 1);
        root.right = constructBinaryTree(in, pre, index + 1, end);
        return root;
    }

    private static int findIndex(Node root, char[] in, int start, int end) {
        for(int i=start; i <= end; i++) {
            if(in[i] == root.value) return i;
        }
        return -1;
    }

    private static void printInOrderTraversal(Node root) {
        if(root != null) {
            printInOrderTraversal(root.left);
            System.out.print(root.value + " ");
            printInOrderTraversal(root.right);
        }
    }

    static class Node {
        char value;
        Node left; Node right;
        public Node(char value) {
            this.value = value;
        }
    }
}
