package com.p.pc.facebook;

import java.io.*;

/**
 * <p>Write a program to serialize and deserialize a given Binary Tree. Tree after deserialization should be identical to
 * the given tree. </p>
 * Approach: <br/>
 * Use the Pre-order traversal approach to serialize and deserialize tree and use filler element for missing or null values.
 * If we don't want to use the filler elements then store both pre and in order representation in the serialized format
 * and use both of these to deserialize.
 */
public class SerializeDeserializeBinaryTree {

    public static void main(String[] args) throws IOException {
        Node root = new Node(3);
        root.left = new Node(2);
        root.right = new Node(4);
        root.left.left = new Node(1);
        root.right.right = new Node(5);

        printInorder(root); // Output: 1 2 3 4 5

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
            outputStream.writeChar('x');
            return;
        }
        outputStream.writeChar(root.value);
        serializeTree(root.left, outputStream);
        serializeTree(root.right, outputStream);
    }

    private static Node deserializeTree(ObjectInputStream inputStream) throws IOException {
        char value = inputStream.readChar();
        // Replace filler elements with null to restore the tree
        if(value == 'x') return null;

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
