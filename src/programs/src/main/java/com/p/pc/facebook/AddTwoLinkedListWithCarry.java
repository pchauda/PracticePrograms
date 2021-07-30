package com.p.pc.facebook;

import java.util.StringJoiner;

public class AddTwoLinkedListWithCarry {

    public static void main(String[] args) {
        int[] first = new int[]{1, 0, 9, 9};
        int[] second = new int[]{7, 3, 2};

        Node firstList = createList(first);
        Node secondList = createList(second);
        printNode(firstList);
        printNode(secondList);

        Node finalList = addLinkedListNodesWithCarry(firstList, secondList);
        printNode(finalList); // Output: [8, 3, 1, 0, 1]
    }

    private static Node addLinkedListNodesWithCarry(Node firstList, Node secondList) {
        Node head = null;
        Node current = null;
        int carry = 0;
        while(firstList != null || secondList != null || carry > 0) {
            int first = firstList != null ? firstList.value : 0;
            int second = secondList != null? secondList.value : 0;

            int sum = first + second + carry;
            int remainder = sum % 10;
            Node n = new Node(remainder);
            if(head == null) {
                head = current = n;
            } else {
                current.next = n;
                current = n;
            }
            carry = sum / 10;
            if(firstList != null) firstList = firstList.next;
            if(secondList != null) secondList = secondList.next;
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
