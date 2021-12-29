package com.p.pc.programs;

/**
 * Find the lowest common ancestor in a given tree having duplicate values of the node.
 * Approach:
 *  Follow post order traversal to perform bottom up search.
 *
 *
 * Tree:                   1
 *                2                 3
 *         5           5    6             7
 *     12     12
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        LowestCommonAncestor obj = new LowestCommonAncestor();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(12);
        root.left.left.right = new TreeNode(12);
        // this implementation only works for given references
        System.out.println("Lowest common ancestor between 15 and 12: " + obj.lowestCommonAncestor(root, new TreeNode(15), root.left.left.left));
        System.out.println("Lowest common ancestor between 5 and 12: " + obj.lowestCommonAncestor(root, root.left.right, root.left.left.left));
        System.out.println("Lowest common ancestor between 12 and 12: " + obj.lowestCommonAncestor(root, root.left.left.right, root.left.left.left));
        // Below is a better implementation and works well for given values
        System.out.println("LCA between 15 and 12: " + obj.findLCA(root, 15, 12));
        System.out.println("LCA between 5 and 12: " + obj.findLCA(root, 5, 12));
        System.out.println("LCA between 12 and 12: " + obj.findLCA(root, 12, 12));
    }
    // Find LCA given references to the two existing nodes in a tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ResultWithRefCheck result = lowestCommonAncestorUsingRefs(root, p, q);
        if(result.isLCA)
            return result.node;
        else return null;
    }
    private ResultWithRefCheck lowestCommonAncestorUsingRefs(TreeNode node, TreeNode p, TreeNode q) {
        if(node == null) {
            return new ResultWithRefCheck(null, false);
        }
        ResultWithRefCheck lr = lowestCommonAncestorUsingRefs(node.left, p, q); // Search in the left sub-tree
        if(lr.isLCA) return lr;
        ResultWithRefCheck rr = lowestCommonAncestorUsingRefs(node.right, p, q); // Search in the right sub-tree
        if(rr.isLCA) return rr;

        if(lr.node != null && rr.node != null) {
            // If matching nodes were found in both left and right sub-tree then the current node is the LCA
            return new ResultWithRefCheck(node, true);
        } else if(node == p || node == q) {
            // If the current node matches either p or q and there was a matching node in either left or right, then again
            // current node is the LCA else not
            boolean isLCA = (lr.node != null || rr.node != null);
            return new ResultWithRefCheck(node, isLCA);
        } else {
            // Return the result if found on either left or right search
            return lr == null ? rr : lr;
        }
    }

    // Find LCA given two values
    public TreeNode findLCA(TreeNode root, int p, int q) {
        Result result = lowestCommonAncestorHelper(root, p, q);
        if(result.foundP && result.foundQ)
            return result.node;
        else return null;
    }

    private Result lowestCommonAncestorHelper(TreeNode node, int p, int q) {
        if(node == null) return new Result();
        Result leftSearch = lowestCommonAncestorHelper(node.left, p, q);
        if(leftSearch.foundP && leftSearch.foundQ) return leftSearch;
        Result rightSearch = lowestCommonAncestorHelper(node.right, p, q);
        if(rightSearch.foundP && rightSearch.foundQ) return rightSearch;

        // Check if left search or right search or node itself resulted into finding either P or Q
        boolean foundP = (leftSearch != null ? leftSearch.foundP : false)
                || (rightSearch != null ? rightSearch.foundP : false)
                || node.val == p;

        boolean foundQ = (leftSearch != null ? leftSearch.foundQ : false)
                || (rightSearch != null ? rightSearch.foundQ : false)
                || node.val == q;
        TreeNode n = (foundP || foundQ) ? node : null;
        return new Result(n, foundP, foundQ);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + (left != null ? Integer.toString(left.val) : "null") +
                    ", right=" + (right != null ? Integer.toString(right.val) : "null") +
                    '}';
        }
    }

    static class ResultWithRefCheck {
        TreeNode node;
        boolean isLCA;

        public ResultWithRefCheck(TreeNode node, boolean isLCA) {
            this.node = node;
            this.isLCA = isLCA;
        }
    }

    static  class Result {
        TreeNode node;
        boolean foundP;
        boolean foundQ;

        Result() {}

        Result(TreeNode node, boolean foundP, boolean foundQ) {
            this.node = node;
            this.foundP = foundP;
            this.foundQ = foundQ;
        }

        public String toString() {
            return node != null ? Integer.toString(node.val) : "null";
        }
    }
}


