package com.p.pc.facebook;

import java.util.*;

/**
 * Goal for this program is to find a path in a tree having sum equal to a number. This problem has multiple flavors:
 * 1. Find paths starting from root node having sum equal to K
 * 2. Find paths starting from root node to leaf node only and having sum equal to K
 * 3. Find paths starting from any node going downwards only and having sum equal to K
 * 4. Count all subtrees having sum equal to K
 *
 * Tree:
 *                        4
 *                     /    \
 *                    5      3
 *                   / \    / \
 *                 1   2   2   3
 *                /   /   /     \
 *              -1   1  -2       2
 *               \
 *                1
 *
 */
public class TreePathSumEqualToANumber {
    static int count;

    public static void main(String[] args) {
        TreeNode root = createTree();
        List<List<Integer>> paths = new ArrayList<>();
        findPathsStartingFromRoot(root, 10, new ArrayList<>(), paths);
        System.out.println("All paths starting from root having sum = 10 are: " + paths); // Output: [[4, 5, 1], [4, 5, 1, -1, 1], [4, 3, 3]]
        paths = new ArrayList<>();
        findPathsStartingFromRootToLeaf(root, 10, new ArrayList<>(), paths);
        System.out.println("All paths starting from root till leaf having sum = 10 are: " + paths); // Output: [[4, 5, 1, -1, 1]]
        paths = new ArrayList<>();
        findAnyPathEqualToK(root, 8, new ArrayList<>(), paths);
        System.out.println("Any paths having sum = 8 are: " + paths); // Output: [[5, 1], [5, 1, -1, 1], [3, 3]]
        countAllSubTreesHavingSumEqualToK(root, 8);
        System.out.println("Count of all subtrees having sum = 8 is: " + count); // Output: 2
        paths = new ArrayList<>();
        findAllSubTreesHavingSumEqualToK(root, 8, paths);
        System.out.println("All subtrees having sum = 8 are: " + paths); // Output: 2
        System.out.println(Arrays.toString(mostFrequentSubTreeSum(root)));
    }

    // Find paths starting from root have sum equal to K. Use Pre-order traversal
    static void findPathsStartingFromRoot(TreeNode root, int K, List<Integer> path, List<List<Integer>> paths) {
        if(root == null) return;
        // add the current element to the path and explore child nodes
        path.add(root.value);

        int rem = K - root.value;
        // If remainder is zero than collect path so far as a valid path
        if(rem == 0) {
            // copy is importance here as path is being shared across multiple recursive calls
            List<Integer> copy = new ArrayList<>(path);
            paths.add(copy);
            // Do not return from here as there could be other paths down the line as well due to negative integers
        }
        findPathsStartingFromRoot(root.left, rem, path, paths);
        findPathsStartingFromRoot(root.right, rem, path, paths);
        // remove the last element added from the path
        path.remove(path.size() - 1);
    }

    // Find paths starting from root have sum equal to K. Use Pre-order traversal
    static void findPathsStartingFromRootToLeaf(TreeNode root, int K, List<Integer> path, List<List<Integer>> paths) {
        if(root == null) return;
        // add the current element to the path and explore child nodes
        path.add(root.value);

        int rem = K - root.value;
        // If remainder is zero than collect path so far as a valid path
        if(rem == 0 && root.left == null && root.right == null) {
            // copy is importance here as path is being shared across multiple recursive calls
            List<Integer> copy = new ArrayList<>(path);
            paths.add(copy);
            // Do not return from here as there could be other paths down the line as well due to negative integers
        }
        findPathsStartingFromRootToLeaf(root.left, rem, path, paths);
        findPathsStartingFromRootToLeaf(root.right, rem, path, paths);
        // remove the last element added from the path
        path.remove(path.size() - 1);
    }

    // this is a slightly different problem then the above two, for every node, perform sum in the reverse order
    static void findAnyPathEqualToK(TreeNode root, int K, List<Integer> path, List<List<Integer>> paths) {
        if(root == null) return;
        path.add(root.value);

        int temp = 0;
        for(int j = path.size() - 1; j >=0 ; j--) {
            temp += path.get(j);
            if(temp == K) {
                List<Integer> copy = new ArrayList<>(path.subList(j, path.size()));
                paths.add(copy);
            }
        }
        findAnyPathEqualToK(root.left, K, path, paths);
        findAnyPathEqualToK(root.right, K, path, paths);

        path.remove(path.size() - 1);
    }
    // perform post order traversal for this problem
    static int countAllSubTreesHavingSumEqualToK(TreeNode root, int K) {
        if(root == null) return 0;
        int leftSum = countAllSubTreesHavingSumEqualToK(root.left, K);
        int rightSum = countAllSubTreesHavingSumEqualToK(root.right, K);
        int sum = root.value + leftSum + rightSum;
        if(sum == K) count++;
        return sum;
    }
    // using a temporary class to hold both sum and all nodes of the subtree so far
    static NodeSum findAllSubTreesHavingSumEqualToK(TreeNode root, int K, List<List<Integer>> paths) {
        if(root == null) return new NodeSum();
        NodeSum leftSum = findAllSubTreesHavingSumEqualToK(root.left, K, paths);
        NodeSum rightSum = findAllSubTreesHavingSumEqualToK(root.right, K, paths);
        NodeSum sum = NodeSum.sum(root, leftSum, rightSum);
        if(sum.sum == K) {
            List<Integer> copy = new ArrayList<>(sum.nodes);
            paths.add(copy);
        }
        return sum;
    }

    /**
     * Given the root of a binary tree, return the most frequent subtree sum. If there is a tie, return all the values with the highest frequency in any order.
     *
     * The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).
     * @param root
     * @return array containing most frequent tree sum if multiple then return all
     */
    static int[] mostFrequentSubTreeSum(TreeNode root) {
        HashMap<Integer, Integer> sumFreq = new HashMap<>();
        subTreeSumFrequency(root, sumFreq);

        List<Integer> maxFreqInts = new ArrayList<>();
        int maxFreq = 0;
        for(Map.Entry<Integer, Integer> entry : sumFreq.entrySet()) {
            int freq = entry.getValue();
            if(freq > maxFreq) {
                maxFreq = freq;
            }
        }
        for(Map.Entry<Integer, Integer> entry : sumFreq.entrySet()) {
            int freq = entry.getValue();
            if(freq == maxFreq) {
                maxFreqInts.add(entry.getKey());
            }
        }
        return maxFreqInts.stream().mapToInt(i -> i).toArray();
    }

    static int subTreeSumFrequency(TreeNode root, HashMap<Integer, Integer> sumFreq) {
        if(root == null) return 0;
        int letTreeSum = subTreeSumFrequency(root.left, sumFreq);
        int rightTreeSum = subTreeSumFrequency(root.right, sumFreq);
        int sum = root.value + letTreeSum + rightTreeSum;
        sumFreq.put(sum, sumFreq.getOrDefault(sum, 0) + 1);
        return sum;
    }


    static class NodeSum {
        int sum;
        List<Integer> nodes;

        NodeSum() {
            sum = 0;
            nodes = new ArrayList<>();
        }

        static NodeSum sum(TreeNode node, NodeSum n1, NodeSum n2) {
            NodeSum n = new NodeSum();
            n.sum = node.value + n1.sum + n2.sum;
            n.nodes.add(node.value);
            n.nodes.addAll(n1.nodes);
            n.nodes.addAll(n2.nodes);
            return  n;
        }
    }

    static TreeNode createTree() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(5);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(1);
        root.left.left.left = new TreeNode(-1);
        root.left.left.left.right = new TreeNode(1);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.left.left = new TreeNode(-2);
        root.right.right.right = new TreeNode(2);

        return root;
    }

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode (int value) {
            this.value = value;
        }
    }
}
