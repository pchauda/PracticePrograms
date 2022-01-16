package com.p.pc.cracking_the_coding_interview.trees_graphs;

/**
 * <p>Find the distance between two nodes of a given BST. Equal elements will be at right-hand side.</p>
 * <p>
 * Approach: <br/>
 * Use recursive approach, since it's a BST, we can move in either direction depending on the given nodes.
 * <p>
 * Tree:
 *              10
 *             / \
 *            7   11
 *           /\    \
 *          5  8    13
 *                  / \
 *                 12  14
 */
public class DistanceBetweenNodesInBST {

    public static void main(String[] args) {
        DistanceBetweenNodesInBST obj = new DistanceBetweenNodesInBST();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(7);
        root.right = new TreeNode(11);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(8);
        root.right.right = new TreeNode(13);
        root.right.right.left = new TreeNode(12);
        root.right.right.right = new TreeNode(14);

        TreeNode.printInOrder(root);

        System.out.println("Distance between 5 and 12 is: " + obj.distanceInBST(root, 5, 12)); // Output: 5
        System.out.println("Distance between 5 and 11 is: " + obj.distanceInBST(root, 5, 11)); // Output: 3
    }

    private int distanceInBST(TreeNode root, int p, int q) {
        if (root == null) return 0;
        if (p > root.val && q > root.val)
            return distanceInBST(root.right, p, q);
        else if (p < root.val && q < root.val)
            return distanceInBST(root.left, p, q);
        else
            return calculateDistance(root, p) + calculateDistance(root, q);
    }

    int calculateDistance(TreeNode node, int n) {
        if (node.val == n) return 0;
        if (n > node.val)
            return 1 + calculateDistance(node.right, n);
        else
            return 1 + calculateDistance(node.left, n);
    }
}
