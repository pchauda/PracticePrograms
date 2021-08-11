package com.p.pc.facebook;

import java.util.ArrayList;
import java.util.List;

public class PathInATreeWithSumEqToANum {
    public static void main(String[] args) {
        TreeNode root = createTree();
        int num = 10;

        List<List<Integer>> paths = new ArrayList<>();
        findPaths(root, num, new ArrayList<>(), paths);
        System.out.println(paths);
    }

    private static void findPaths(TreeNode root, int num, List<Integer> path, List<List<Integer>> paths) {
        if(root == null) return;
        // add the current element to the path and explore child nodes
        path.add(root.value);

        int rem = num - root.value;
        // If remainder is zero than collect path so far as a valid path
        if(rem == 0) {
            // copy is importance here as path is being shared across multiple recursive calls
            List<Integer> copy = new ArrayList<>(path);
            paths.add(copy);
            // Do not return from here as there could be other paths down the line as well due to negative integers
        }
        findPaths(root.left, rem, path, paths);
        findPaths(root.right, rem, path, paths);
        // remove the last element added from the path
        path.remove(path.size() - 1);
    }


    static TreeNode createTree() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(5);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.left.left.left = new TreeNode(-1);
        root.left.left.left.right = new TreeNode(1);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.left.left = new TreeNode(1);

        return root;
    }


    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode (int value) {
            this.value = value;
        }
    }
}
