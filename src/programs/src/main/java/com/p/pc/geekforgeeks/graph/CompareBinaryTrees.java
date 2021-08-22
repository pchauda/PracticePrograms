package com.p.pc.geekforgeeks.graph;

import com.p.pc.facebook.TreePathSumEqualToANumber;

public class CompareBinaryTrees {
    public static void main(String[] args) {
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.right.right = new Node(5);

        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        root2.left.left = new Node(4);
        root2.right.left = new Node(5);
        System.out.println("Are both trees equal? " + compareTrees(root1, root2)); // Output: No

        /**
         *          2
         *         /
         *        4
         */
        Node subRoot = new Node(2);
        subRoot.left = new Node(4);
        System.out.println("Is 3 -> -2 -> 2 a subtree of the given tree: " + isSubtree(root1, subRoot)); // Output: true
    }

    private static boolean compareTrees(Node root1, Node root2) {
        // 1. Both empty
        if(root1 == null && root2 == null) return true;
        // 2. Both not empty
        if(root1 != null && root2 != null) {
            return root1.value == root2.value && compareTrees(root1.left, root2.left) && compareTrees(root1.right, root2.right);
        }
        // else return false
        return false;
    }

    static boolean isSubtree(Node root, Node subRoot) {
        if(root == null) return false;

        return isIdentical(root, subRoot) ||
                isSubtree(root.left, subRoot) ||
                isSubtree(root.right, subRoot);
    }

    private static boolean isIdentical(Node t1, Node t2) {
        if(t1 == null && t2 == null) return true;
        if(t1 == null || t2 == null) return false;
        return t1.value == t2.value && isIdentical(t1.left, t2.left) && isIdentical(t1.right, t2.right);
    }

    static class Node {
        int value;
        Node left;
        Node right;
        Node (int value) {
            this.value = value;
        }
    }

}
