package com.p.pc.facebook;

import java.util.StringJoiner;

public class MergeTwoSortedList {

    public static void main(String[] args) {
        int[] first = new int[]{4, 8, 15, 19};
        int[] second = new int[]{7, 9};

        Node firstList = createList(first);
        Node secondList = createList(second);
        printNode(firstList);
        printNode(secondList);

        Node mergedList = mergeSortedList(firstList, secondList);
        printNode(mergedList);
    }

    private static Node mergeSortedList(Node firstList, Node secondList) {
        Node head = null;
        Node current = null;
        while(firstList != null && secondList != null) {
            int first = firstList.value;
            int second = secondList.value;
            Node n;
            if(first <= second) {
                n = firstList;
                firstList = firstList.next;
            } else {
                n = secondList;
                secondList = secondList.next;
            }
            if(head == null) {
                head = current = n;
            } else {
                current.next = n;
                current = n;
            }
        }

        if(firstList != null) {
            current.next = firstList;
        }
        if(secondList != null) {
            current.next = secondList;
        }
        return head;
    }

    private static Node createList(int[] first) {
        Node head = null;
        Node current = null;
        for(int i: first) {
            if(head == null) {
                head = current = new Node(i);
            } else {
                current.next = new Node(i);
                current = current.next;
            }
        }
        return head;
    }

    static class Node {
        int value;
        Node next;
        Node(int value) {
            this.value = value;
        }
    }

    static void printNode(Node n) {
        StringJoiner join = new StringJoiner(",", "[", "]");
        while(n != null) {
            join.add(Integer.toString(n.value));
            n = n.next;
        }
        System.out.println(join.toString());
    }
}
