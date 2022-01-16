package com.p.pc.cracking_the_coding_interview.trees_graphs;

/**
 * <p>Create a binary search tree from a sorted array such that the height of the BST is minimal.</p>
 * <p>
 * Approach: <br/>
 * Since the array is sorted, middle element can be marked as root and recursively create left and right trees.
 */
public class BSTFromSortedArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        TreeNode root = createBST(arr, 0, arr.length - 1);
        TreeNode.printInOrder(root);
    }

    private static TreeNode createBST(int[] arr, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = createBST(arr, start, mid - 1);
        root.right = createBST(arr, mid + 1, end);
        return root;
    }
}
