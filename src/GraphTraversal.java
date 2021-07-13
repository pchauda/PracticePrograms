import java.util.*;

/**
 * Graph Traversal algorithms, mainly focusing on Breadth First traversal and Depth First Traversal.
 */
public class GraphTraversal {
    public static void main(String[] args) {
        GraphTraversal gt = new GraphTraversal();
        // 1 .. 2 3 4 .. 5 6 7 8 9 10 11 12 13 .. 14 15  // .. represents the next level
        GraphVertex<Integer> root = gt.createGraph();

        System.out.println("Breadth first traversal");
        List<Integer> bfsTraversal = new ArrayList<>(15);
        gt.performBreadthFirstTraversal(root, bfsTraversal);
        System.out.println(bfsTraversal); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]

        System.out.println("Depth first traversal");
        List<Integer> dfsTraversal = new ArrayList<>(15);
        gt.performDepthFirstTraversalSimplified(root, dfsTraversal);
        System.out.println(dfsTraversal); // [1, 2, 5, 14, 6, 7, 3, 8, 15, 9, 10, 4, 11, 12, 13]
    }

    void performBreadthFirstTraversal(GraphVertex<Integer> root, List<Integer> bfsTraversal) {
        Queue<GraphVertex<Integer>> queue = new ArrayDeque<>();
        queue.add(root);
        bfsTraversal.add(root.key);

        while(!queue.isEmpty()) {
            GraphVertex<Integer> node = queue.poll();
            if(node.children != null) {
                for(GraphVertex<Integer> child : node.children) {
                    queue.add(child);
                    bfsTraversal.add(child.key);
                }
            }
        }
    }
    // Using algo of Pre-Order traversal
    private void performDepthFirstTraversalSimplified(GraphVertex<Integer> root, List<Integer> dfsTraversal) {
        Stack<GraphVertex<Integer>> stack = new Stack<>();
        GraphVertex<Integer> current = root;

        while(!stack.isEmpty() || current != null) {
            if(current != null) {
                dfsTraversal.add(current.key);
                GraphVertex<Integer> leftMostChild = null;
                if(current.children != null) {
                    for(int i = current.children.size() - 1; i >= 0; i--) {
                        if(i == 0 ) {
                            leftMostChild = current.children.get(i);
                        } else {
                            stack.push(current.children.get(i));
                        }
                    }
                }
                current = leftMostChild;
            } else {
                current = stack.pop();
            }
        }
    }

    GraphVertex<Integer> createGraph() {
        int i = 2;
        GraphVertex<Integer> root = new GraphVertex<>(1);
        while(i <= 4) {
            root.addChild(new GraphVertex<>(i++));
        }

        for(GraphVertex<Integer> child : root.children) {
            child.addChild(new GraphVertex<>(i++));
            child.addChild(new GraphVertex<>(i++));
            child.addChild(new GraphVertex<>(i++));
        }

        GraphVertex<Integer> leafNode1 = root.children.get(0).children.get(0);
        leafNode1.addChild(new GraphVertex<>(i++));

        GraphVertex<Integer> leafNode2 = root.children.get(1).children.get(0);
        leafNode2.addChild(new GraphVertex<>(i++));
        return root;
    }

    static class GraphVertex<K> {
        K key;
        List<GraphVertex<K>> children;

        GraphVertex(K key) {
            this.key = key;
        }

        void addChild(GraphVertex<K> child) {
            if(children == null) {
                children = new LinkedList<>();
            }
            children.add(child);
        }
    }

}
