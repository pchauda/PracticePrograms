package com.p.pc.cracking_the_coding_interview.linked_lists;

import java.util.Stack;

import static com.p.pc.cracking_the_coding_interview.linked_lists.LinkedListNode.printList;

/**
 * Given a linked list, find out if its nodes are palindrome.
 * Approach:
 *  Since the length of the list is not given and back traversal is not permitted, the best way to find out the palindrome
 *  would be to push half of the elements to a stack and then compare the next elements with the stack top.
 */
public class PalindromeCheckForList {

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(4);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(1);
        head.next.next.next = new LinkedListNode(2);
        head.next.next.next.next = new LinkedListNode(4);

        printList(head);
        System.out.println("Given list a palindrome? " + checkForPalindrome(head));
    }

    /**
     * Push half the elements on the stack, to do this use the approach of slow and fast pointers. When the fast pointer
     * (which is moving at 2x speed) reaches the end of the list then slow will be at the midpoint.
     */
    private static boolean checkForPalindrome(LinkedListNode head) {
        Stack<Integer> stack = new Stack<>();
        LinkedListNode slow = head;
        LinkedListNode fast = head;
        // Push half of the elements on the stack
        while(fast != null && fast.next != null) {
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        // In case of odd elements fast pointer will be at the last element of the list, just ignore the next element
        if(fast != null) slow = slow.next;
        while(slow != null) {
            int value  = stack.pop();
            if(value != slow.val) return false;
            slow = slow.next;
        }
        return true;
    }
}
