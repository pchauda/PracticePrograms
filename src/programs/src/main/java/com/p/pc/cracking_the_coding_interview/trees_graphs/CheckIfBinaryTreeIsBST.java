package com.p.pc.cracking_the_coding_interview.trees_graphs;

/**
 * Check if a given binary tree is a binary search tree.
 * Consider equal elements to be on the right subtree.
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
    // Recursive approach, for each node, its value should be within min and max range
    private boolean checkForBST(TreeNode root, Integer min, Integer max) {
        if(root == null) return true;
        // Compare the left child with max OR right child with min
        // if either left or right is not a valid sub-tree then return false to break the call
        if((max != null && root.val >= max) || (min != null && root.val < min))
            return false;
        // If left is not a valid BST then break, else check for right
        if(!checkForBST(root.left, min, root.val) || !checkForBST(root.right, root.val, max))
            return false;
        return true;
    }
}
