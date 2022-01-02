package com.p.pc.facebook;

import java.util.StringJoiner;

/**
 * <p>Perform a pairwise swap of a given linked list.<br/> For example convert 1 -> 2 -> 3 -> 4 -> 5 to 2 -> 1 -> 4 -> 3 -> 5
 *</p>
 * Approach:<br/>
 *  Simple approach will be to swap the values of current and next element.
 */
public class PairWiseSwapElementsInList {
    public static void main(String[] args) {
        Node head = createList(9);
        System.out.println("Original list: " + printList(head));
        swapListIterative(head);
        System.out.println("Swapped list using iterative : " + printList(head));
        // Reset the original list
        swapListIterative(head);
        // Swap using recursive solution
        head = swapListRecursive(head);
        System.out.println("Swapped list using recursive : " + printList(head));
        removeNode(head, 5);
        System.out.println("Swapped list after removing 5: " + printList(head));
        swapListIterative(head);
        System.out.println("Swapped list again  : " + printList(head));
    }

    static Node removeNode(Node head, int toRemove) {
        if(head == null) return null;
        if(head.value == toRemove) return head.next;

        Node prev = head, next = head.next;
        while(next != null) {
            if(next.value == toRemove) {
                prev.next = next.next;
                break;
            }
            prev = next;
            next = next.next;
        }
        return head;
    }

    private static Node swapListRecursive(Node head) {
        if(head == null || head.next == null) return head;
        Node rem = head.next.next; // remaining list
        Node tmp = head.next; // next node
        tmp.next = head; // assigned next of tmp = head
        head.next = swapListRecursive(rem); // head.next = recursively traverse the remaining list
        return tmp;
    }

    private static void swapListIterative(Node head) {
        while(head != null && head.next != null) {
            int tmp = head.value;
            head.value = head.next.value;
            head.next.value = tmp;
            head = head.next.next;
        }
    }

    private static String printList(Node head) {
        StringJoiner joiner = new StringJoiner(",","[","]");
        while(head != null) {
            joiner.add(Integer.toString(head.value));
            head = head.next;
        }
        return joiner.toString();
    }

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    static Node createList(int n) {
        if(n ==0) return null;
        Node head = new Node(1);
        Node next = head;
        for(int i = 2; i <= n; i++) {
            Node tmp = new Node(i);
            next.next = tmp;
            next = tmp;
        }
        return head;
    }
}
