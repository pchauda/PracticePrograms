package com.p.pc.geekforgeeks.graph;

public class CompareBinaryTrees {
    public static void main(String[] args) {
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.right.right = new Node(5);

        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        root2.left.left = new Node(4);
        root2.right.left = new Node(5);
        System.out.println("Are both trees equal? " + compareTrees(root1, root2)); // Output: No

        /**
         *          2
         *         /
         *        4
         */
        Node subRoot = new Node(2);
        subRoot.left = new Node(4);
        System.out.println("Is 2 -> 4 a subtree of the given tree: " + isSubtree(root1, subRoot)); // Output: true
        System.out.println("Is 2 -> 4 a subtree of the given tree: " + containsTree(root1, subRoot)); // Output: true
    }

    private static boolean compareTrees(Node root1, Node root2) {
        // 1. Both empty
        if(root1 == null && root2 == null) return true;
        // 2. Both not empty
        if(root1 != null && root2 != null) {
            return root1.value == root2.value && compareTrees(root1.left, root2.left) && compareTrees(root1.right, root2.right);
        }
        // else return false
        return false;
    }
    // Time Complexity = 0 (n + k*m) where k = number of occurrence's of sub-tree's root in larger tree, worst case = O(n*m)
    // Space Complexity = O(log(n) + log(m))
    static boolean isSubtree(Node root, Node subRoot) {
        if(root == null && subRoot != null) return false;
        if(subRoot == null) return true; // empty sub-tree is always a sub-tree for any tree
        // Check if the trees are identical starting the root node, if no then check if either left or right subtree of the
        // larger tree is identical to other tree
        return isIdentical(root, subRoot) ||
                isSubtree(root.left, subRoot) ||
                isSubtree(root.right, subRoot);
    }

    private static boolean isIdentical(Node t1, Node t2) {
        if(t1 == null && t2 == null) return true;
        if(t1 == null || t2 == null) return false;
        return t1.value == t2.value && isIdentical(t1.left, t2.left) && isIdentical(t1.right, t2.right);
    }

    /**
     * Alternative approach is to convert the tree to a post order string with using X for the missing/null nodes.
     * Afterwards, a simple string contains check will be sufficient.
     *
     * Time Complexity: O(n + m) Space Complexity: O(n + m)
     * If memory is an issue then this alternative solution should not be used.
     */
    static boolean containsTree(Node root, Node subRoot) {
        if(root == null && subRoot != null) return false; // If root is null and sub-root is not null
        if(subRoot == null) return true; // An empty sub-tree will always be contained in any tree
        StringBuilder sb1 = new StringBuilder(); // It will take extra space of O(n)
        StringBuilder sb2 = new StringBuilder(); // It will take extra space of O(m)

        preOrderString(root, sb1);
        preOrderString(subRoot, sb2);
        return sb1.indexOf(sb2.toString()) != -1;
    }

    private static void preOrderString(Node root, StringBuilder sb) {
        if(root == null) {
            sb.append("X");
            return;
        }
        if(root != null) {
            sb.append(root.value);
            preOrderString(root.left, sb);
            preOrderString(root.right, sb);
        }
    }

    static class Node {
        int value;
        Node left;
        Node right;
        Node (int value) {
            this.value = value;
        }
    }

}
