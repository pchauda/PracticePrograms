package com.p.pc.bloomberg;

import java.util.Stack;

/**
 * <b>Problem Statement</b><br/>
 * You are given a doubly linked list, which contains nodes that have a next pointer, a previous pointer, and an
 * additional child pointer. This child pointer may or may not point to a separate doubly linked list, also containing
 * these special nodes. These child lists may have one or more children of their own, and so on, to produce a multilevel
 * data structure as shown in the example below.
 * <p>
 * Given the head of the first level of the list, flatten the list so that all the nodes appear in a single-level,
 * doubly linked list. Let curr be a node with a child list. The nodes in the child list should appear after curr and
 * before curr.next in the flattened list.
 * <p>
 * Return the head of the flattened list. The nodes in the list must have all of their child pointers set to null.
 *
 * <b>Approach</b>
 * Use stack and perform pre-order traversal on the linked list.
 * <p>
 * <b>Companies: Bloomberg, Amazon, Oracle, Facebook</b>
 */
public class FlattenMultiLevelLinkedList {

    public static void main(String[] args) {
        Node list = createList();
        System.out.println(list); // 1,2,7,8,3,4,5,6
        Node flattenList = flattenList(list);
        System.out.println(flattenList); // Output: 1,3,5,6,4,2,7,8
    }

    private static Node flattenList(Node head) {
        if(head == null) return null;
        Stack<Node> stack = new Stack<>();
        Node curr, prev = null;
        stack.push(head);
        while(!stack.isEmpty()) {
            curr = stack.pop();
            // link current and previous
            curr.prev = prev;
            if(prev != null) prev.next = curr;
            // push next and child to stack to visit later
            if(curr.next != null)
                stack.push(curr.next);
            if(curr.child != null) {
                stack.push(curr.child);
                curr.child = null; // reset the child pointer
            }
            prev = curr; // move the prev pointer
        }
        return head;
    }

    private static Node createList() {
        Node head = new Node(1);
        head.next = new Node(2); head.next.prev = head;
        head.child = new Node(3);
        head.child.next = new Node(4); head.child.next.prev = head.child;
        head.child.child = new Node(5);
        head.child.child.next = new Node(6); head.child.child.next.prev = head.child.child;
        head.next.next = new Node(7); head.next.next.prev = head.next;
        head.next.next.next = new Node(8); head.next.next.next.prev = head.next.next;
        return head;
    }

    static class Node {
        int value;

        Node next;
        Node prev;
        Node child;

        Node(int i) {
            this.value = i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            if(this.next != null) {
                sb.append(","); sb.append(this.next);
            }
            if(this.child != null) {
                sb.append(","); sb.append(this.child);
            }
            return sb.toString();
        }
    }
}
