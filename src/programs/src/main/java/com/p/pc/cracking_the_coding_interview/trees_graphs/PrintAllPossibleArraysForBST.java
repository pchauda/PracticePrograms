package com.p.pc.cracking_the_coding_interview.trees_graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>A binary search tree was created by traversing through an array from left to right
 * and inserting each element. Given a binary search tree with distinct elements, print all possible
 * arrays that could have led to this tree. <br/>
 * EXAMPLE <br/>
 * 2
 * / \
 * 1   3
 * Output: {2, 1, 3}, {2, 3, 1}
 * </p>
 * Approach:
 * For each node, calculate all possible array for the left and right child recursively and then weave the lists together.
 * For weaving, extract the last item from the first list, add it to prefix and recursively do this operation till
 * any one of the list is empty. In that case, just add everything to the resulting list.
 */
public class PrintAllPossibleArraysForBST {
    static PrintAllPossibleArraysForBST obj = new PrintAllPossibleArraysForBST();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(7);
        root.right = new TreeNode(14);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(2);
        root.left.left.right = new TreeNode(5);
        root.right.left = new TreeNode(12);
        /**
         *                  10
         *                 / \
         *                7  14
         *               /   /
         *              4   12
         *             / \
         *            2   5
         */

        TreeNode.printInOrder(root);
        List<LinkedList<Integer>> allArrays = obj.findAllPossibleArrays(root);
        System.out.println("Count of all arrays: " + allArrays.size());
        System.out.println("All arrays: " + allArrays);
    }

    private List<LinkedList<Integer>> findAllPossibleArrays(TreeNode root) {
        List<LinkedList<Integer>> result = new ArrayList<>();
        if (root == null) {
            // For weaving logic to work, empty list needs to be added in the result for the leaf nodes.
            result.add(new LinkedList<>());
            return result;
        }
        // Recurse on left and right subtrees
        List<LinkedList<Integer>> left = findAllPossibleArrays(root.left);
        List<LinkedList<Integer>> right = findAllPossibleArrays(root.right);
        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(root.val);
        // weave together each list from the left and right sides
        for (LinkedList<Integer> l : left) {
            for (LinkedList<Integer> r : right) {
                List<LinkedList<Integer>> weavedList = new ArrayList<>();
                weaveList(l, r, prefix, weavedList);
                result.addAll(weavedList);
            }
        }
        return result;
    }

    /**
     * Weave lists together in all possible ways. This algorithm works by removing the head from one list, recursing,
     * and then doing the same thing with the other list.
     */
    private void weaveList(LinkedList<Integer> l, LinkedList<Integer> r, LinkedList<Integer> prefix, List<LinkedList<Integer>> weavedList) {
        if (l.size() == 0 || r.size() == 0) {
            LinkedList<Integer> list = new LinkedList<>();
            Stream.of(prefix, l, r).forEach(list::addAll);
            weavedList.add(list);
            return;
        }
        // Process left list
        prefix.addLast(l.removeFirst()); // add the head to the end of the prefix to maintain the order
        weaveList(l, r, prefix, weavedList);
        // Restore left list and prefix
        l.addFirst(prefix.removeLast());

        // Process right list
        prefix.addLast(r.removeFirst()); // add the head to the end of the prefix to maintain the order
        weaveList(l, r, prefix, weavedList);
        // Restore right list and prefix
        r.addFirst(prefix.removeLast());
    }
}
