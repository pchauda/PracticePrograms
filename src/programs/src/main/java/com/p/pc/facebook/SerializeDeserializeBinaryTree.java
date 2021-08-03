package com.p.pc.facebook;

import java.io.*;

/**
 * Use the Pre-order traversal approach to serialize and deserialize tree and use filler element for missing or null values.
 * If we don't want to use the filler elements then store both pre and in order in the
 */
public class SerializeDeserializeBinaryTree {

    public static void main(String[] args) throws IOException {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.right.right = new Node(5);

        printInorder(root); // Output: 4 2 1 3 5

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try(ObjectOutputStream outputStream = new ObjectOutputStream(bo)){
            serializeTree(root, outputStream);
        }

        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream inputStream = new ObjectInputStream(bi);
        Node n = deserializeTree(inputStream);
        System.out.println();
        printInorder(n);
    }

    private static void serializeTree(Node root, ObjectOutputStream outputStream) throws IOException {
        if(root == null) {
            // Filler element
            outputStream.writeInt(Integer.MIN_VALUE);
            return;
        }
        outputStream.writeInt(root.value);
        serializeTree(root.left, outputStream);
        serializeTree(root.right, outputStream);
    }

    private static Node deserializeTree(ObjectInputStream inputStream) throws IOException {
        int value = inputStream.readInt();
        // Replace filler elements with null to restore the tree
        if(value == Integer.MIN_VALUE) return null;

        Node root = new Node(value);
        root.left = deserializeTree(inputStream);
        root.right = deserializeTree(inputStream);

        return root;
    }

    private static void printInorder(Node root) {
        if(root != null) {
            printInorder(root.left);
            System.out.print(root.value + " ");
            printInorder(root.right);
        }
    }

    static class Node {
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
        }
    }
}
