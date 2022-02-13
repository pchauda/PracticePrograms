package com.p.pc.cracking_the_coding_interview.trees_graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>Given a binary tree, design an algorithm which creates a linked list of all the nodes at the same depth. if
 * the tree has a depth D then there should be D linked lists created.</p>
 * <p>
 * Time complexity of both recursive and iterative approach is same and O(n). Space complexity wise, both solutions
 * need to return O(n) data. For recursive solution, the call stack will be of size log n, for the iterative solution
 * the queue size will be order of n
 */
public class DepthLevelLinkedListForATree {
    public static void main(String[] args) {
        DepthLevelLinkedListForATree obj = new DepthLevelLinkedListForATree();
        // Below tree is having 6 levels, so there should be 6 lists
        TreeNode root = new TreeNode(1); // Level 0
        root.left = new TreeNode(2); // Level 1
        root.right = new TreeNode(3); // Level 1
        root.left.left = new TreeNode(4); // Level 2
        root.right.right = new TreeNode(5); // Level 2
        root.left.left.left = new TreeNode(6); // Level 3
        root.left.left.left.left = new TreeNode(7); // Level 4
        root.left.left.left.left.right = new TreeNode(8); // Level 5
        TreeNode.printInOrder(root); // Output: [7->8->6->4->2->1->3->5]
        List<List<Integer>> lists = obj.levelOrderTraversal(root);
        System.out.println(lists);

        List<List<Integer>> listsUsingPreOrder = new ArrayList<>();
        obj.preOrderWithListInfo(root, 0, listsUsingPreOrder);
        System.out.println(listsUsingPreOrder);
    }

    private List<List<Integer>> levelOrderTraversal(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> lists = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            while (size > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }
            lists.add(list);
        }
        return lists;
    }

    private void preOrderWithListInfo(TreeNode root, int level, List<List<Integer>> lists) {
        if (root != null) {
            List<Integer> list;
            if (lists.size() == level) {
                list = new ArrayList<>();
                lists.add(list);
            } else {
                list = lists.get(level);
            }
            list.add(root.val);
            preOrderWithListInfo(root.left, level + 1, lists);
            preOrderWithListInfo(root.right, level + 1, lists);
        }
        return;
    }


}
