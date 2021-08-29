package com.p.pc.cracking_the_coding_interview.linked_lists;

import java.util.StringJoiner;

public class LinkedListNode {
    int val;
    LinkedListNode next;
    public LinkedListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "val=" + val;
    }

    public static void printList(LinkedListNode head) {
        StringJoiner joiner = new StringJoiner(" -> " ,"[", "]");
        while(head != null) {
            joiner.add(Integer.toString(head.val));
            head = head.next;
        }
        System.out.println(joiner.toString());
    }

    public static LinkedListNode createSimpleListWithDupes() {
        LinkedListNode head = new LinkedListNode(1);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(4);
        head.next.next.next.next = new LinkedListNode(2);
        head.next.next.next.next.next = new LinkedListNode(2);
        head.next.next.next.next.next.next = new LinkedListNode(7);
        head.next.next.next.next.next.next.next = new LinkedListNode(3);
        return head;
    }
}
