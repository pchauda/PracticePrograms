package com.p.pc.facebook;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringJoiner;

/**
 * Find top K elements from a given list.
 * <p>
 * Approach: <br/>
 * Use min heap to store top K elements, any time a new element is greater than the top of the min head, remove the top
 * item and then add the new item to the min heap.
 */
public class FindTopKElementsInAList {
    public static void main(String[] args) {
        Node head = createList(1000);
        printTopKElements(head, 5);
    }

    private static void printTopKElements(Node head, int k) {
        Queue<Integer> minHeap = new PriorityQueue<>();
        if (head == null) return;

        int i=0;
        while(i++ < k && head != null) {
            minHeap.add(head.value);
            head = head.next;
        }

        while (head != null) {
            if (head.value > minHeap.peek()) {
                minHeap.poll(); // remove the current top
                minHeap.add(head.value); // add the new element
            }
            head = head.next;
        }
        System.out.println("Min heap size: " + minHeap.size());
        System.out.println(minHeap);
    }


    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private static Node createList(int n) {
        if (n == 0) return null;
        Node head = new Node(1);
        Node next = head;
        for (int i = 2; i <= n; i++) {
            Node tmp = new Node(i);
            next.next = tmp;
            next = tmp;
        }
        return head;
    }

    private static String printList(Node head) {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        while (head != null) {
            joiner.add(Integer.toString(head.value));
            head = head.next;
        }
        return joiner.toString();
    }
}
