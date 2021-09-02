package com.p.pc.cracking_the_coding_interview.trees_graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

    public static void printInOrder(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        inOrderTraversal(root, path);
        StringJoiner joiner = new StringJoiner("->", "[", "]");
        path.stream().forEach(t -> joiner.add(Integer.toString(t)));
        System.out.println(joiner);
    }

    private static void inOrderTraversal(TreeNode root, List<Integer> path) {
        if(root != null) {
            inOrderTraversal(root.left, path);
            path.add(root.val);
            inOrderTraversal(root.right, path);
        }
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
