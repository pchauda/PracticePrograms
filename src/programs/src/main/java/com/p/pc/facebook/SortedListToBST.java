package com.p.pc.facebook;

/**
 * Create a balance binary search tree from a sorted list.
 *
 * Approach:
 * Since the list is sorted, middle element of the list can be picked up as root and all elements before it will be part of
 * left subtree and after it will be part of the right sub tree.
 *
 * The number of nodes in the linked list are counted and set equal to n. First, the middle node is set as the root (always).
 * Then, the left subtree is constructed recursively, using the left n/2 nodes, and connected with the root at the end. The right subtree is similarly constructed and connected to the root.
 * While constructing the BST, we keep moving the list head pointer to the next node so that we have the appropriate pointer in each recursive call.
 */
public class SortedListToBST {
    static ListNode headNode;

    public static void main(String[] args) {
        ListNode head = createList(7);
        headNode = head;
        printList(head); System.out.println();
        System.out.println("List size: " + size(head));
        System.out.println("Value at index 3: " + findListNode(head, 3).value); // Output: 4

        TreeNode root = createBST(head);
        System.out.print("In Order Traversal: ");
        printInOrderTraversal(root); // Should print the tree nodes in sorted order

        TreeNode root1 = createBSTRecursiveOptimized(size(head));
        System.out.print("In Order Traversal: ");
        printInOrderTraversal(root1); // Should print the tree nodes in sorted order
    }
    // Idea here is to create the left subtree recursively first and then root and then right subtree.
    // Move the head pointer to the next one in each iteration
    // Complexity: O(n)
    private static TreeNode createBSTRecursiveOptimized(int size) {
        if(size <= 0) return null;
        // start from the middle element
        TreeNode left = createBSTRecursiveOptimized(size/2);
        TreeNode root = new TreeNode(headNode.value);
        root.left = left;
        headNode = headNode.next;
        root.right = createBSTRecursiveOptimized(size - size/2 - 1); // Total - Left Nodes - Root
        return root; // it will be the middle node
    }

    private static TreeNode createBST(ListNode head) {
        return createBSTRecursive(head, 0, size(head) - 1);
    }
    // Complexity: O(n log n)
    private static TreeNode createBSTRecursive(ListNode head, int start, int end) {
        if(start > end) return null;
        if(head == null) return null;
        int mid = (end + start + 1) / 2;
        if(start == end) return new TreeNode(findListNode(head, start).value);
        ListNode node = findListNode(head, mid);
        TreeNode root = new TreeNode(node.value);
        root.left = createBSTRecursive(head, start, mid - 1);
        root.right = createBSTRecursive(head, mid + 1, end);
        return root;
    }

    static int size(ListNode head) {
        int size = 0;
        while(head != null) {
            head = head.next;
            size++;
        }
        return size;
    }

    static ListNode findListNode(ListNode head, int index) {
        ListNode retVal = head;
        for(int i=0; i < index; i++) {
            retVal = retVal.next;
        }
        return retVal;
    }

    // Create a list having n nodes
    static ListNode createList(int n) {
        ListNode head = new ListNode(1);
        ListNode curr = head;
        for(int i=2; i <= n; i++) {
            curr.next = new ListNode(i);
            curr = curr.next;
        }
        return head;
    }

    static void printList(ListNode head) {
        while(head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

    static class ListNode {
        int value;
        ListNode next;
        ListNode(int value) {
            this.value = value;
        }
    }
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode(int value) {
            this.value = value;
        }
    }

    static void printInOrderTraversal(TreeNode root) {
        if(root != null) {
            printInOrderTraversal(root.left);
            System.out.print(root.value + " ");
            printInOrderTraversal(root.right);
        }
    }
}
