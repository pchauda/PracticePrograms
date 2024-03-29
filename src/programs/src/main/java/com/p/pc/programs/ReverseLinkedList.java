package com.p.pc.programs;

public class ReverseLinkedList {


    public static void main(String[] args) {

        Node<Integer> head = new Node<>(10);
        head.next = new Node<>(20);
        head.next.next = new Node<>(30);
        head.next.next.next = new Node<>(40);

        System.out.println("Input LinkedList     : " + head);

        System.out.println("Reversed LinkedList  : " + reversedList(head));
    }
    /**
     * Idea here is to reverse the pointer at every node, and make the last node as head
     *
     * head -> 1 -> 2 -> 3 -> 4 -> null to
     * null <- 1 <- 2 <- 3 <- 4 <- head
     *
     * @param node input list
     * @return reversed list
     */
    private static Node<Integer> reversedList(Node<Integer> node) {
        Node<Integer> tmp = null, prev = null;
        while(node != null) {
            tmp = node.next;
            node.next = prev;
            prev = node;
            node = tmp;
        }
        return prev;
    }

    static class Node<K> {
        K value;
        Node<K> next;

        public Node(K value) {
            this.value = value;
        }
         public String toString() {
            return "Node{value:" + value + ", next:" + next + "}";
         }
    }
}
