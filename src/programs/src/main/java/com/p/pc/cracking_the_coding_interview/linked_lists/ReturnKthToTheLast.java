package com.p.pc.cracking_the_coding_interview.linked_lists;

/**
 * Implement an algorithm to find the kth to last element of a singly linked list.
 * Ask question if length is given and clarify how Kth is defined.
 * For example K = 1 means the last element of one before the last.
 */
public class ReturnKthToTheLast {
    public static void main(String[] args) {
        LinkedListNode head = LinkedListNode.createSimpleListWithDupes();
        LinkedListNode.printList(head);
        // second element from the last
        System.out.println("Kth element where k = 2 is : " + findKthElement(head, 2).val);
        // 8th element from the last, since list has 8 element, it should return the first element
        System.out.println("Kth element where k = 8 is : " + findKthElement(head, 8).val);
        // 9th element from the last, since list has 8 element, it will return null
        System.out.println("Kth element where k = 9 is : " + (findKthElement(head, 9) == null ? null : findKthElement(head, 9)));
    }
    // Assuming k = 1 means the last element, we will have two pointers, one at head and other K nodes away from head
    // when the second pointer will reach the end of the list then first pointer will be at Kth position from last
    // in case, k = 0 means the last element then move the second pointer by (k-1) nodes.
    private static LinkedListNode findKthElement(LinkedListNode head, int k) {
        LinkedListNode p1 = head;
        LinkedListNode p2 = head;
        for(int i=0; i<k; i++) {
            if(p2 == null) return null;
            p2 = p2.next;
        }
        while(p2 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        return p1;
    }
}
