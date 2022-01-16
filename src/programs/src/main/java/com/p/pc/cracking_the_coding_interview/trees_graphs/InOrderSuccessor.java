package com.p.pc.cracking_the_coding_interview.trees_graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * <p>Find in-order successor of a given node in a binary tree (or BST).</p>
 * Approach: <br/>
 * 1. If the right child is not null then find the left most child of the right sub-tree
 * 2. If the right child is null, then move up the tree until the node is on the left side of its parent and then return its parent
 * This second case will consider both scenarios, in case parent is unvisited (node 9) or parent is visited (node 17)
 * Tree:
 *                         10
 *                     /       \
 *                   7          13
 *                  / \         / \
 *                 5   9      11   14
 *                / \               \
 *               3   6              17
 *                                  /
 *                                16
 * <p>
 * In Order: 3 -> 5 -> 6 -> 7 -> 9 -> 10 -> 11 -> 13 -> 14 -> 16 -> 17
 */
public class InOrderSuccessor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10, null);
        root.left = new TreeNode(7, root);
        root.right = new TreeNode(13, root);
        root.left.left = new TreeNode(5, root.left);
        root.left.right = new TreeNode(9, root.left);
        root.left.left.left = new TreeNode(3, root.left.left);
        root.left.left.right = new TreeNode(6, root.left.left);
        root.right.left = new TreeNode(11, root.right);
        root.right.right = new TreeNode(14, root.right);
        root.right.right.right = new TreeNode(17, root.right.right);
        root.right.right.right.left = new TreeNode(16, root.right.right.right);

        printInOrder(root);
        System.out.println("In order predecessor of node 9 is : " + findInOrderPredecessor(root, 9));
        System.out.println("In order successor of node 9 is : " + findInOrderSuccessor(root, 9));
        System.out.println("In order predecessor of node 11 is : " + findInOrderPredecessor(root, 11));
        System.out.println("In order successor of node 11 is : " + findInOrderSuccessor(root, 11));
        System.out.println("In order predecessor of node 17 is : " + findInOrderPredecessor(root, 17));
        System.out.println("In order successor of node 17 is : " + findInOrderSuccessor(root, 17));
        System.out.println("In order predecessor of node 13 is : " + findInOrderPredecessor(root, 13));
        System.out.println("In order successor of node 13 is : " + findInOrderSuccessor(root, 13));
    }

    private static TreeNode findInOrderPredecessor(TreeNode root, int key) {
        if(root == null) return null;
        if(root.val == key) return inOrderPredecessor(root);
        if(root.val < key) return findInOrderPredecessor(root.right, key);
        else return findInOrderPredecessor(root.left, key);
    }

    private static TreeNode inOrderPredecessor(TreeNode node) {
        // If node's right child is not null then return the left most child of the right child
        if (node.left != null) {
            return rightMostChild(node.left);
        } else {
            // If node's right child is null then move up the tree till the node becomes the left child and then return the parent
            while (node.parent != null && node.parent.right != node) {
                node = node.parent;
            }
            return node.parent;
        }
    }

    private static TreeNode findInOrderSuccessor(TreeNode root, int key) {
        if(root == null) return null;
        if(root.val == key) return inOrderSuccessor(root);
        if(root.val < key) return findInOrderSuccessor(root.right, key);
        else return findInOrderSuccessor(root.left, key);
    }

    private static TreeNode inOrderSuccessor(TreeNode node) {
        // If node's right child is not null then return the left most child of the right child
        if (node.right != null) {
            return leftMostChild(node.right);
        } else {
            // If node's right child is null then move up the tree till the node becomes the left child and then return the parent
            while (node.parent != null && node.parent.left != node) {
                node = node.parent;
            }
            return node.parent;
        }
    }

    private static TreeNode leftMostChild(TreeNode node) {
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }

    private static TreeNode rightMostChild(TreeNode node) {
        while (node != null && node.right != null) {
            node = node.right;
        }
        return node;
    }

    static class TreeNode {
        int val;
        TreeNode left, right, parent;

        TreeNode(int val, TreeNode parent) {
            this.val = val;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }

    public static void printInOrder(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        inOrderTraversal(root, path);
        StringJoiner joiner = new StringJoiner("->", "[", "]");
        path.stream().forEach(t -> joiner.add(Integer.toString(t)));
        System.out.println(joiner);
    }

    private static void inOrderTraversal(TreeNode root, List<Integer> path) {
        if (root != null) {
            inOrderTraversal(root.left, path);
            path.add(root.val);
            inOrderTraversal(root.right, path);
        }
    }
}
