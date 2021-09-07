package com.p.pc.cracking_the_coding_interview.trees_graphs;

/**
 * Check if a given binary tree is a binary search tree.
 * Consider equal elements to be on the right subtree.
 *
 * Time Complexity = O(n), Space complexity = O(log n) due to recursive call stack
 */
public class CheckIfBinaryTreeIsBST {
    public static void main(String[] args) {
        CheckIfBinaryTreeIsBST obj = new CheckIfBinaryTreeIsBST();

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.right = new TreeNode(10);
        root.right.right = new TreeNode(15);
        root.right.right.left = new TreeNode(12);
        root.right.right.left.left = new TreeNode(11);
        root.left.right = new TreeNode(9);

        TreeNode.printInOrder(root); // If a given binary tree is BST then In order will result into sorted output
        System.out.println("Is given binary tree a BST? : " + obj.checkForBST(root, null, null));
        // add another node to break BST
        root.left.right.right = new TreeNode(13);
        TreeNode.printInOrder(root);
        System.out.println("Is updated binary tree a BST? : " + obj.checkForBST(root, null, null));
    }

    // Recursive approach, for each node, its value should be within min and max range and all nodes of the tree should
    // be checked before returning true.
    private boolean checkForBST(TreeNode root, Integer min, Integer max) {
        if(root == null) return true;
        // if node value >= max or < min then return false
        if((max != null && root.val >= max) || (min != null && root.val < min))
            return false;
        // If left is not a valid BST then break, else check for right
        if(!checkForBST(root.left, min, root.val))
            return false;
        if(!checkForBST(root.right, root.val, max))
            return false;
        return true;
    }
}
