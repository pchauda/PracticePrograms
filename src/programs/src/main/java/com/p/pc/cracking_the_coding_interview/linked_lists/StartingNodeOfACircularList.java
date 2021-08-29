package com.p.pc.cracking_the_coding_interview.linked_lists;

/**
 * Given a list, find the first node creating the circular list.
 * For example: If the list is 1 -> 2 -> 3 -> 4 -> 5 -> 3
 * Then output should be 3.
 *
 * If the list is not circular then return null.
 *
 * Approach:
 *  Using slow and fast pointer technique, we can identify if the list is circular or not. For circular list, the node
 *  where both slow and fast pointers will meet, will be k nodes away from the start of the circle and head or
 *  both CollisionSpot and list head are k nodes from the start of the loop. So if we move
 *  any one pointer to head and start moving both slow and fast pointer by 1 node at a time then whenever these two
 *  pointers will meet again will be the starting node of the circular list
 */
public class StartingNodeOfACircularList {
    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(4);
        head.next.next.next.next = new LinkedListNode(5);
        head.next.next.next.next.next = head.next.next; // Node = 3 is the starting node of the circle

        LinkedListNode startNode = findStartingNodeOfTheCircularList(head);
        System.out.println("Starting node of the circular list is : " + (startNode != null ? startNode.val : null));
    }

    private static LinkedListNode findStartingNodeOfTheCircularList(LinkedListNode head) {
        LinkedListNode slow = head;
        LinkedListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                break; // loop found
            }
        }
        // if no loop then return null
        if(slow != fast) return null;
        // Reset the slow to head and keep the fast pointer to the collision point, move both pointers 1 node each and
        // when they meet again, it will be the starting point of the circle
        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
