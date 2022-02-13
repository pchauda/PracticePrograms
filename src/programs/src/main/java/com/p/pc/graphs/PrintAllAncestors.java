package com.p.pc.graphs;

/**
 * Print all ancestors of a given node.
 * <p>
 * Approach: <br/>
 * For every node in the tree, print the tree node if the given node is either present on the left or right subtree.
 * Do above recursively and it will print all ancestors.
 */
public class PrintAllAncestors {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.right.left = new TreeNode(7);
        System.out.println("Tree height: " + findTreeHeight(root));

        printAllAncestors(root, 7); // Output: 6 3 1
        System.out.println();
        System.out.println("Does TreeNode(10) exists in the tree? " + findNode(root, 10));
        System.out.println("Does TreeNode(4) exists in the tree? " + findNode(root, 4));
    }

    static int findTreeHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(findTreeHeight(root.left), findTreeHeight(root.right));
    }

    static boolean findNode(TreeNode root, int value) {
        if (root == null) return false;
        if (root.value == value) return true;
        return findNode(root.left, value) || findNode(root.right, value);
    }

    static boolean printAllAncestors(TreeNode root, int value) {
        // Value not found, hence return false
        if (root == null) return false;
        // return true if the node matches the given value but don't print anything as we only need to print ancestor nodes
        if (root.value == value) return true;
        // If given value is present on either left or subtree then print the current node
        if (printAllAncestors(root.left, value) || printAllAncestors(root.right, value)) {
            System.out.print(root.value + " ");
            // Return true so that all previous ancestors can be printed
            return true;
        }
        // base case if value is not found
        return false;
    }

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }
    }
}
