package com.p.pc.cracking_the_coding_interview.trees_graphs;

/**
 * Implement a function to check if a binary tree is balanced. For the purposes of
 * this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any
 * node never differ by more than one.
 */
public class IsBalancedBinaryTree {
    public static void main(String[] args){
        IsBalancedBinaryTree obj = new IsBalancedBinaryTree();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.left.left.left.left = new TreeNode(7);
        root.left.left.left.left.right = new TreeNode(8);

        TreeNode.printInOrder(root);

        System.out.println("Is tree balanced? " + obj.isBalanced(root).isBalanced); // Output: false
        System.out.println("Is tree balanced (using error code)? " + (obj.isTreeBalanced(root) == Integer.MAX_VALUE ? false : true));
    }
    // Using a Pair class to keep track of height and isBalance flag in a bottom up fashion
    private Pair isBalanced(TreeNode root) {
        if(root == null) return new Pair(0, true);

        Pair leftTree = isBalanced(root.left);
        if(!leftTree.isBalanced) return leftTree;

        Pair rightTree = isBalanced(root.right);
        if(!rightTree.isBalanced) return rightTree;

        if(Math.abs(leftTree.height - rightTree.height) > 1) return new Pair(Math.max(leftTree.height, rightTree.height), false);
        else return new Pair(1 +  Math.max(leftTree.height, rightTree.height), true);
    }
    // Using error code (max, min value of int) to identify the fact that subtreee is not balanced.
    private int isTreeBalanced(TreeNode root) {
        if(root == null) return 0;
        int leftHeight = isTreeBalanced(root.left);
        if(leftHeight == Integer.MAX_VALUE) return Integer.MAX_VALUE;
        int rightHeight = isTreeBalanced(root.right);
        if(rightHeight == Integer.MAX_VALUE) return Integer.MAX_VALUE;

        if(Math.abs(leftHeight - rightHeight) > 1) return Integer.MAX_VALUE;
        else return 1 + Math.max(leftHeight, rightHeight);
    }

    static class Pair {
        int height;
        boolean isBalanced;
        Pair(int h, boolean b) {
            this.height = h;
            this.isBalanced = b;
        }
    }
}
