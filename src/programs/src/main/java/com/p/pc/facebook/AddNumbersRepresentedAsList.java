package com.p.pc.facebook;

import java.util.LinkedList;
import java.util.StringJoiner;

/**
 * Add numbers represented as linked list and return a linked list representing the new number.
 *
 * Approach:
 *  Addition needs to happen starting from the last element on the list. Since we are using singly linked list, it is
 *  better to push the elements of both lists onto stacks and then pop the values from stack and perform addition while
 *  maintaining the carry.
 */
public class AddNumbersRepresentedAsList {

    public static void main(String[] args) {
        Node num1 = new Node(7);
        num1.next = new Node(5);
        num1.next.next = new Node(4);
        num1.next.next.next = new Node(3);
        printList(num1); // [7,5,4,3]

        Node num2 = new Node(4);
        num2.next = new Node(6);
        num2.next.next = new Node(8);
        num2.next.next.next = new Node(6);
        printList(num2); // [4,6,8,6]

        Node sum = addListAsNumbers(num1, num2);
        printList(sum); // [1,2,2,2,9]

        // Another problem (simpler one), if lists are in reverse order for a number, then perform the sum and return the list in the reverse order
        Node num3 = new Node(3);
        num3.next = new Node(4);
        num3.next.next = new Node(5);
        num3.next.next.next = new Node(7);
        printList(num3); // [3,4,5,7] => Num = 7543

        Node num4 = new Node(6);
        num4.next = new Node(8);
        num4.next.next = new Node(6);
        num4.next.next.next = new Node(4);
        printList(num4); // [6,8,6,4] => Num = 4686

        Node sum2 = addListAsNumbersInReverse(num3, num4);
        printList(sum2); // [9,2,2,2,1] => Total = 12229
    }

    private static Node addListAsNumbersInReverse(Node num1, Node num2) {
        Node head = null, next = null;
        int carry = 0;
        while(num1 != null || num2 != null || carry != 0) {
            int n1 = num1 != null ? num1.value : 0;
            int n2 = num2 != null ? num2.value : 0;
            int sum = n1 + n2 + carry;
            carry = sum/10;
            Node tmp = new Node(sum % 10);
            if(head == null) {
                head = next = tmp;
            } else {
                next.next = tmp;
                next = tmp;
            }
            if(num1 != null)
                num1 = num1.next;
            if(num2 != null)
                num2 = num2.next;
        }
        return head;
    }

    // use stacks to perform addition from last nodes
    private static Node addListAsNumbers(Node num1, Node num2) {
        LinkedList<Node> stack1 = new LinkedList<>();
        LinkedList<Node> stack2 = new LinkedList<>();
        pushToStack(num1, stack1);
        pushToStack(num2, stack2);

        int carry = 0; Node prev = null;
        // Pop elements from stacks until those are empty
        while(!stack1.isEmpty() || !stack2.isEmpty() || carry !=0) {
            int a=0, b=0;
            if(!stack1.isEmpty()) a = stack1.pop().value;
            if(!stack2.isEmpty()) b = stack2.pop().value;
            int sum = a + b + carry;
            carry = sum/10;
            Node tmp = new Node(sum % 10);
            tmp.next = prev;
            prev = tmp;
        }
        return prev;
    }

    private static void pushToStack(Node num, LinkedList<Node> stack) {
        while(num != null) {
            stack.push(num);
            num =num.next;
        }
    }

    static class Node {
        int value;
        Node next;
        Node(int value) {
            this.value = value;
        }
    }

    static void printList(Node head) {
        StringJoiner joiner = new StringJoiner(",","[","]");
        while(head != null) {
            joiner.add(Integer.toString(head.value));
            head = head.next;
        }
        System.out.println("List: " + joiner);
    }
}
