package com.p.pc.facebook;

public class IdenticalBinaryTree {
    public static void main(String[] args) {
        Node t1 = new Node(100);
        t1.left = new Node(50);
        t1.right = new Node(200);
        t1.left.left = new Node(20);

        Node t2 = new Node(100);
        t2.left = new Node(50);
        t2.right = new Node(200);
        t2.left.left = new Node(20);
        System.out.println("Trees Identical? : " + identicalTrees(t1, t2)); // Output: true
        t2.left.right = new Node(70);
        System.out.println("Trees Identical? : " + identicalTrees(t1, t2)); // Output: false
    }

    static boolean identicalTrees(Node t1, Node t2) {
        // Empty trees are equal
        if(t1 == null && t2 == null)
            return true;
        // If trees are not empty then check for root node, left sub tree and right sub tree
        if(t1 != null && t2 != null) {
            return t1.value == t2.value && identicalTrees(t1.left, t2.left) && identicalTrees(t1.right, t2.right);
        }
        return false;
    }

    static class Node {
        int value;
        Node left;
        Node right;
        public Node(int value) {
            this.value = value;
        }
    }
}
