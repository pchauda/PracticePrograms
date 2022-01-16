package com.p.pc.cracking_the_coding_interview.trees_graphs;

/**
 * <p>Find distance between any two nodes of a given tree (not BST). </p>
 * Approach: <br/>
 * Find the LCA between the two nodes and then calculate the distance of the each node from the LCA and add it up.
 */
public class DistanceBetweenTwoNodesInATree {
    public static void main(String[] args) {
        DistanceBetweenTwoNodesInATree obj = new DistanceBetweenTwoNodesInATree();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        TreeNode.printInOrder(root);
        System.out.println("Distance between 4 and 6 is: " + obj.findDistanceBetweenNodes(root, 4, 6)); //Output: 4
        System.out.println("Distance between 2 and 4 is: " + obj.findDistanceBetweenNodes(root, 2, 4)); //Output: 1
        System.out.println("Distance between 2 and 7 is: " + obj.findDistanceBetweenNodes(root, 2, 7)); //Output: 3
    }

    private int findDistanceBetweenNodes(TreeNode root, int p, int q) {
        TreeNode lca = findLCA(root, p, q);
        return distance(lca, p, 0) + distance(lca, q, 0);
    }

    // assuming there are no duplicates and both p and q are present in the tree
    private TreeNode findLCA(TreeNode node, int p, int q) {
        if (node == null) return null;
        if (node.val == p || node.val == q) return node;

        TreeNode l = findLCA(node.left, p, q);
        TreeNode r = findLCA(node.right, p, q);

        if (l != null && r != null) return node;
        return l != null ? l : r;
    }

    private int distance(TreeNode node, int n, int level) {
        if (node == null) return -1;
        if (node.val == n)
            return level;
        int ld = distance(node.left, n, level + 1); // first search left then search right if not found in left
        return ld == -1 ? distance(node.right, n, level + 1) : ld;
    }

}
