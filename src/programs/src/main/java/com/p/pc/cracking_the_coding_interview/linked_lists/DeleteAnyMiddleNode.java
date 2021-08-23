package com.p.pc.cracking_the_coding_interview.linked_lists;

import static com.p.pc.cracking_the_coding_interview.linked_lists.LinkedListNode.printList;

/**
 * Give any node of the linked list, delete that node if possible
 */
public class DeleteAnyMiddleNode {
    public static void main(String[] args) {
        LinkedListNode head = LinkedListNode.createSimpleListWithDupes();
        printList(head);
        LinkedListNode node = head.next.next; // 3 - delete this node
        deleteNode(node);
        printList(head);
    }
    // since any random node the list is given, we can't really delete the node. To delete, copy the data from next node to
    // this node and simply delete the next node.
    // Using this approach the last node can never be deleted. If you are still asked to delete the last node, then
    // ask the interviewer if the List class can be modified to use a flag for dummy nodes and mark the last node as dummy in this case.
    private static void deleteNode(LinkedListNode node) {
        if(node == null || node.next == null) return;
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
