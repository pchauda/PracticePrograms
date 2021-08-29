package com.p.pc.cracking_the_coding_interview.linked_lists;

/**
 * Given two lists, find the intersection node (after which lists merge) of the two lists. Intersecting node must have
 *  * both address and value as same in both the lists.
 *
 * Approach:
 * 1. HashMap based approach: Takes O(n) extra space
 * 2. Two pointer approach: Find the length of both lists, calculate delta = (l1 - l2), move the larger list's pointer
 * by delta nodes. After that if the two lists node match anywhere then that's the intersecting node.
 */
public class IntersectingNodeOfTwoLists {

    public static void main(String[] args) {
        LinkedListNode head1 = new LinkedListNode(1);
        head1.next = new LinkedListNode(2);
        LinkedListNode intersect = new LinkedListNode(3);
        head1.next.next = intersect;

        LinkedListNode head2 = new LinkedListNode(4);
        head2.next = intersect;

        head1.next.next.next = head2.next.next = new LinkedListNode(5);
        head1.next.next.next.next = head2.next.next.next = new LinkedListNode(6);
        System.out.print("First List: ");
        LinkedListNode.printList(head1);
        System.out.print("Second List: ");
        LinkedListNode.printList(head2);

        LinkedListNode node = findIntersection(head1, head2);
        System.out.println("Intersection Node: " + (node != null ? node.val : null)); // Output = 3
    }

    private static LinkedListNode findIntersection(LinkedListNode head1, LinkedListNode head2) {
        int length1 = getLength(head1);
        int length2 = getLength(head2);

        LinkedListNode larger = length1 < length2 ? head2 : head1;
        LinkedListNode smaller = length1 < length2 ? head1 : head2;

        int delta = Math.abs(length1 - length2);

        // move the larger pointer by delta nodes
        while(delta > 0) {
            larger = larger.next;
            delta--;
        }
        // now iterate over both list and return the first matching node
        while(larger != null && smaller != null) {
            if(larger == smaller) return larger;
            larger = larger.next;
            smaller = smaller.next;
        }
        return null;
    }

    private static int getLength(LinkedListNode head) {
        int count = 0;
        while(head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
}
