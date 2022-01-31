package com.p.pc.facebook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Convert a given binary tree to a linked list and return the head (left most element i.e. first element in in-order
 * traversal).
 *
 * Example
 * Tree:              100
 *                 /       \
 *               50         200
 *            /     \      /    \
 *           20     70    120   300
 *           \      /
 *           30    60
 *
 * ConvertedLinkedList:
 * [20, 30, 50, 60, 70, 100, 120, 200, 300]
 *
 * Approach:
 * Follow in order traversal either using iterative or recursive approach and adjust left and right pointers using
 * reference to previous node.
 */
public class BinaryTreeToDoublyLinkedList {

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(50);
        root.right = new BinaryTreeNode(200);
        root.left.left = new BinaryTreeNode(20);
        root.left.right = new BinaryTreeNode(70);
        root.left.left.right = new BinaryTreeNode(30);
        root.left.right.left = new BinaryTreeNode(60);
        root.right.left = new BinaryTreeNode(120);
        root.right.right = new BinaryTreeNode(300);
        System.out.println("Binary Tree:" + root);
        convertToDoublyLinkedListRecursive(root);
        System.out.println(get_list(head));
    }

    static BinaryTreeNode head = null, prev = null;
    static void convertToDoublyLinkedListRecursive(BinaryTreeNode root) {
        if(root == null) return;
        // convert left
        convertToDoublyLinkedListRecursive(root.left);
        // convert root
        if(head == null) {
            head = prev = root;
        } else {
            root.left = prev;
            prev.right = root;
            prev = root;
        }
        // convert right
        convertToDoublyLinkedListRecursive(root.right);
    }

    static BinaryTreeNode convertToDoublyLinkedList(BinaryTreeNode root) {
        LinkedList<BinaryTreeNode> stack = new LinkedList<>();
        BinaryTreeNode current = root;

        BinaryTreeNode head = null;
        BinaryTreeNode prev = null;
        while(current != null || !stack.isEmpty()) {
            if(current != null) {
                stack.push(current);
                current = current.left;
            } else {
                BinaryTreeNode node = stack.pop();
                if(head == null) {
                    head = prev = node;
                    head.left = null;
                } else {
                    prev.right = node;
                    node.left = prev;
                    prev = node;
                }
                current = node.right;
            }
        }
        return head;
    }

    private static List<Integer> get_list(BinaryTreeNode node) {
        List<Integer> r = new ArrayList<>();
        while(node != null) {
            r.add(node.value);
            node = node.right;
        }
        return r;
    }

    static class BinaryTreeNode {
        BinaryTreeNode left;
        BinaryTreeNode right;
        int value;
        BinaryTreeNode(int value) {
            this.value = value;
        }
    }
}
