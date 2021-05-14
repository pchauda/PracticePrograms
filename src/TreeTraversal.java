import java.util.*;

/**
 * Given the input Tree like below:
 *                    1
 *              2           3
 *          4       5   6       7
 *
 * InOrderTraversal:    Left -> Node -> Right:                  4, 2, 5, 1, 6, 3, 7
 * PreOrderTraversal:   Node -> Left -> Right:                  1, 2, 4, 5, 3, 6, 7
 * PostOrderTraversal:  Left -> Right -> Node:                  4, 5, 2, 6, 7, 3, 1
 *
 * LevelOrderTraversal: Print all node in the same level first: 1, 2, 3, 4, 5, 6, 7
 *
 * @param <K>
 */

public class TreeTraversal<K> {
    public static void main(String[] args) {
        TreeTraversal tree = new TreeTraversal();

        Node<Integer> root = new Node<>(1, null);
        root.left = new Node<>(2, root);
        root.right = new Node<>(3, root);
        root.left.left = new Node<>(4, root.left);
        root.left.right = new Node<>(5, root.left);
        root.right.left = new Node<>(6, root.right);
        root.right.right = new Node<>(7, root.right);

        System.out.println("Printing InOrder Traversal:");
        tree.printInOrderTraversal(root);
        System.out.println("\nPrinting InOrder Traversal Iterative:");
        tree.printInOrderTraversalIterative(root);

        System.out.println("\nPrinting PreOrder Traversal:");
        tree.printPreOrderTraversal(root);
        System.out.println("\nPrinting PreOrder Traversal Iterative:");
        tree.printPreOrderTraversalIterative(root);

        System.out.println("\nPrinting PostOrder Traversal:");
        tree.printPostOrderTraversal(root);

        System.out.println("\nPrinting LevelOrder Traversal:");
        tree.printLevelOrderTraversal(root);
    }

    // Recursive
    void printInOrderTraversal(Node<K> root) {
        if(root == null) return;

        printInOrderTraversal(root.left);
        System.out.print(root.key + " ");
        printInOrderTraversal(root.right);
    }

    void printPreOrderTraversal(Node<K> root) {
        if(root == null) return;

        System.out.print(root.key + " ");
        printPreOrderTraversal(root.left);
        printPreOrderTraversal(root.right);
    }

    void printPostOrderTraversal(Node<K> root) {
        if(root == null) return;

        printPostOrderTraversal(root.left);
        printPostOrderTraversal(root.right);
        System.out.print(root.key + " ");
    }

    // Iterative using stack
    void printInOrderTraversalIterative(Node<K> root) {
        Stack<Node<K>> stack = new Stack<>();

        Node<K> node = root;
        while(!stack.empty() || node != null) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            } else {
                Node<K> pop = stack.pop();
                System.out.print(pop.key + " ");
                node = pop.right;
            }
        }
    }
    // Iterative using stack
    void printPreOrderTraversalIterative(Node<K> root) {
        Stack<Node<K>> stack = new Stack<>();

        Node<K> node = root;
        while(!stack.empty() || node != null) {
            if(node != null) {
                System.out.print(node.key + " ");
                if(node.right != null) {
                    stack.push(node.right);
                }
                node = node.left;
            } else {
                node = stack.pop();
            }
        }
    }
    // BFS using queue
    void printLevelOrderTraversal(Node<K> root) {
        Queue<Node<K>> queue = new ArrayDeque<>();

        queue.add(root);
        System.out.print(root.key + " ");
        while (!queue.isEmpty()) {
            Node<K> node = queue.poll();
            if(node.left != null) {
                System.out.print(node.left.key + " ");
                queue.add(node.left);
            }
            if(node.right != null) {
                System.out.print(node.right.key + " ");
                queue.add(node.right);
            }
        }
    }

    static class Node<K> {
        K key;
        Node<K> left;
        Node<K> right;
        Node<K> parent;

        public Node(K key, Node<K> parent) {
            this.key = key;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "key=" + key + " ";
        }

        public K getKey() {
            return key;
        }
    }
}


