package com.p.pc.cracking_the_coding_interview.linked_lists;

import java.util.HashSet;
import java.util.Set;

import static com.p.pc.cracking_the_coding_interview.linked_lists.LinkedListNode.createSimpleListWithDupes;

/**
 * Write code to remove duplicates from an unsorted linked list.
 * FOLLOW UP:
 * How would you solve this problem if a temporary buffer is not allowed?
 */
public class RemoveDupsFromList {
    public static void main(String[] args) {
        LinkedListNode head = createSimpleListWithDupes();
        System.out.println("Original list with dupes:");
        LinkedListNode.printList(head);
        removeDupsUsingTempBuffer(head);
        System.out.println("List after removing dupes:");
        LinkedListNode.printList(head); // Output: [1,2,3,4,7]
    }
    // Use a temporary HashSet to store the value of the existing nodes, time complexity O(n), space complexity = O(n)
    private static void removeDupsUsingTempBuffer(LinkedListNode head) {
        Set<Integer> nodeValues = new HashSet<>();
        LinkedListNode prev = null;
        while(head != null) {
            if(nodeValues.contains(head.val)) {
                prev.next = head.next;
            } else {
                nodeValues.add(head.val);
                prev = head; // only move the prev in case the next element is not a dupe
            }
            head = head.next;
        }
    }
    // No temp space, hence the problem can only be solved in O(n^2) as we will need to iterate the entire list for each node
    private static void removeDupsWithNoBuffer(LinkedListNode head) {
        while(head != null) {
            LinkedListNode runner = head; // star the runner from the current element
            while(runner.next != null) {
                if(runner.next.val == head.val) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            head = head.next;
        }
    }
}
