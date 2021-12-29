package com.p.pc.programs;

/**
 * Write a program to detect a cycle or loop in a given linked list.
 */
public class DetectCycleInList {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = head.next.next;

        System.out.println("Does loop exists? " + detectLoop(head));
    }

    private static boolean detectLoop(Node head) {
        Node slow = head, fast = head;
        while(slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }


    static class Node {
        int value;
        Node next;
        Node(int value) {
            this.value = value;
        }
    }
}
