package com.p.pc.cracking_the_coding_interview.trees_graphs;

import java.util.Random;

/**
 * <p>You are implementing a binary search tree class from scratch, which, in addition to insert, find, and delete, has a
 * method getRandomNode() which returns a random node from the tree. All nodes should be equally likely to be chosen.
 * Design and implement an algorithm for getRandomNode, and explain how you would implement the rest of the methods.</p>
 * <p>
 * Approach: <br/>
 * Maintain a size variable for each node representing the size of the sub-tree starting from that node. Increment the
 * size any time a new node is added to the tree. Decrement the size any time a node is deleted from the tree.
 */
public class RandomNodeInABST {
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert(10);
        tree.insert(12);
        tree.insert(6);
        tree.insert(8);
        tree.insert(4);

        tree.printTree(); // Output: 4 6 8 10 12
        System.out.println("Random node: " + tree.randomNode().val);
        // add more members in the tree
        tree.insert(15);
        tree.insert(3);
        tree.insert(1);
        tree.printTree(); // Output: 1 3 4 6 8 10 12 15
        System.out.println("Random node: " + tree.randomNode().val);
        System.out.println("Find node 20 : " + tree.findNode(20));
        System.out.println("Find node 12 : " + tree.findNode(12));
        System.out.println(tree.getSize());
        tree.deleteNode(1);
        System.out.println(tree.getSize());
    }

    static class Tree {
        TreeNode root;
        Random r = new Random();

        int getSize() {
            return root == null ? 0 : root.size;
        }

        void insert(int n) {
            if (root == null) root = new TreeNode(n);
            else root.insert(n);
        }

        TreeNode randomNode() {
            if (root == null) return null;
            int i = r.nextInt(getSize());
            return root.getIthNode(i);
        }

        void printTree() {
            if (root != null) {
                printInOrder(root);
                System.out.println();
            }
        }

        TreeNode findNode(int n) {
            if (root == null) return null;
            else return root.find(n);
        }

        // delete node and update the root accordingly
        void deleteNode(int n) {
            root = deleteRec(root, n);
        }

        // Recursively delete the node and adjust the size as well. Size only need to be adjusted for all parent nodes.
        private TreeNode deleteRec(TreeNode node, int n) {
            if (node == null) return null;
            if (node.val == n) {
                // If left is null then set the root to right and vice versa
                if (node.left == null) return node.right; // Effectively root = root.right
                if (node.right == null) return node.left; // Effectively root = root.left
                // If both left and right are not null then find InOrder successor and replace the node with it
                TreeNode child = findInOrderSuccessor(node.right);
                // copy the successor's value in the node being deleted and delete successor
                node.val = child.val;
                // adjust the right pointer
                node.right = deleteRec(node.right, child.val);
            } else if (n < node.val) {
                // delete the node from left sub-tree and update left pointer accordingly
                node.left = deleteRec(node.left, n);
            } else {
                // delete the node from right sub-tree and update right pointer accordingly
                node.right = deleteRec(node.right, n);
            }
            // decrease the size
            node.size--;
            return node;
        }

        private TreeNode findInOrderSuccessor(TreeNode node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        private void printInOrder(TreeNode root) {
            if (root != null) {
                printInOrder(root.left);
                System.out.print(root.val + " ");
                printInOrder(root.right);
            }
        }

        // Modified tree class to store the size at every node
        static class TreeNode {
            int val;
            TreeNode left, right;
            int size = 1;

            TreeNode(int val) {
                this.val = val;
            }

            void insert(int n) {
                TreeNode node = n < val ? left : right;
                if (node == null) {
                    if (n < val) left = new TreeNode(n);
                    else right = new TreeNode(n);
                } else {
                    node.insert(n); // recursive call will ensure that size has been increased for all intermediate nodes
                }
                size++; // increase the size after addition
            }

            TreeNode find(int n) {
                if (n == val) return this;
                else if (n < val) {
                    return left != null ? left.find(n) : null;
                } else if (n > val) {
                    return right != null ? right.find(n) : null;
                }
                return null;
            }

            /**
             * Check if "i" is smaller than the left size, then search the left side, if equal then return the current
             * node else check on the right size. For checking on the right size, adjust the i = (i - (left size + 1)).
             * Basically when searching in the right, then skip over all the elements on the left and the root.
             *
             * @param i
             * @return ith node
             */
            TreeNode getIthNode(int i) {
                int leftSize = left == null ? 0 : left.size;
                if (i < leftSize) {
                    return left.getIthNode(i);
                } else if (i == leftSize) {
                    return this;
                } else {
                    return right.getIthNode(i - leftSize - 1);
                }
            }

            @Override
            public String toString() {
                return "TreeNode{" +
                        "val=" + val +
                        ", size=" + size +
                        '}';
            }
        }
    }
}
