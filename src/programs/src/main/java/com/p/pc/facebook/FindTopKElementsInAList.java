package com.p.pc.facebook;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringJoiner;

/**
 * Find top K elements from a given list.
 *
 * Approach:
 *  Use min heap to store top K elements, any time a new element is greater than the top of the min head, remove the top
 *  item and then add the new item to the min heap.
 */
public class FindTopKElementsInAList {
    public static void main(String[] args) {
        Node head = createList(1000);
        printTopKElements(head, 100);
    }

    private static void printTopKElements(Node head, int k) {
        Queue<Integer> minHeap = new PriorityQueue<>();
        if(head == null) return;

        for(int i=0; i < k && head != null; i++) {
            minHeap.add(head.value);
            head = head.next;
        }
        while(head != null) {
            int top = minHeap.peek();
            int val = head.value;
            if(val > top) {
                minHeap.poll();
                minHeap.add(head.value);
            }
            head = head.next;
        }
        System.out.println("Min heap size: " + minHeap.size());
        StringJoiner joiner = new StringJoiner(",","[","]");
        Integer tmp;
        while((tmp = minHeap.poll()) != null) {
            joiner.add(Integer.toString(tmp));
        }
        System.out.println(joiner);
    }


    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private static Node createList(int n) {
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

    private static String printList(Node head) {
        StringJoiner joiner = new StringJoiner(",","[","]");
        while(head != null) {
            joiner.add(Integer.toString(head.value));
            head = head.next;
        }
        return joiner.toString();
    }
}
