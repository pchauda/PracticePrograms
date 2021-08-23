package com.p.pc.cracking_the_coding_interview.linked_lists;

import static com.p.pc.cracking_the_coding_interview.linked_lists.LinkedListNode.printList;

/**
 * Write code to partition a linked list around a value x, such that all nodes less than x come
 * before all nodes greater than or equal to x. If x is contained within the list the values of x only need
 * to be after the elements less than x (see below). The partition element x can appear anywhere in the
 * "right partition"; it does not need to appear between the left and right partitions.
 * EXAMPLE
 * Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition= 5]
 * Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
 */
public class ListPartitionAroundX {
    public static void main(String[] args) {
        LinkedListNode head = LinkedListNode.createSimpleListWithDupes();
        System.out.println("Original list");
        printList(head);
        LinkedListNode newHead = partitionListWithoutSwap(head, 3);
        System.out.println("Parition list (via returning new list)");
        printList(newHead); // Output: [2,2,2,1,3,4,7,3]
        System.out.println("Partition in-place using swap");
        LinkedListNode head2 = LinkedListNode.createSimpleListWithDupes();
        partitionListUsingSwap(head2, 3);
        printList(head2); // Output: [1,2,2,2,3,4,7,3]

    }
    // Use two pointers move the first one only if the element is smaller or if swap happened
    private static void partitionListUsingSwap(LinkedListNode head, int x) {
        LinkedListNode first = head;
        LinkedListNode second = head;
        while(second != null) {
            if(second.val < x) {
                swapNodes(first, second);
                first = first.next;
            }
            second = second.next;
        }
    }
    // In this approach, this method will return a new list head node using two extra head and tail pointers.
    // If element is smaller than x then it will be added to the head, else to the tail
    private static LinkedListNode partitionListWithoutSwap(LinkedListNode node, int x) {
        LinkedListNode head = node;
        LinkedListNode tail = node;

        while(node != null) {
            LinkedListNode next = node.next;
            if(node.val < x) {
                node.next = head;
                head = node;
            } else {
                tail.next = node;
                tail = node;
            }
            node = next;
        }
        tail.next = null;
        return head;
    }

    private static void swapNodes(LinkedListNode first, LinkedListNode second) {
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
}
