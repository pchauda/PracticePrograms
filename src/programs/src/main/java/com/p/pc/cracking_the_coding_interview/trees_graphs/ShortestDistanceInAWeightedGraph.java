package com.p.pc.cracking_the_coding_interview.trees_graphs;

import java.util.*;

/**
 * Given a weighted graph where each edge has a weight associated, find the shortest distance from any given source
 * vertex to all vertices.
 * Also print the paths from the source vertex to all vertices.
 *
 * Approach: Use Dijkstra's algorithm to compute shortest distance from a source vertex to all vertices. Use a Min-Heap
 * to maintain the set of all unvisited nodes along with their distances.
 *
 * Algo:
 *  Assign the initial distance from source vertex to all vertices as Infinity.
 *  Push the source vertex to the min heap.
 *  Pick the minimum vertex from the min heap.
 *  Visit all unvisited adjacent vertices from the source vertex and update the distance based on the weight of the edges
 *      If the new distance is less than the previously captured distance then update the distance as well the path array.
 *  Mark the current vertex as visited and add the adjacent vertices to the min heap along with their distances.
 *  Repeat the above till min heap is empty.
 */
public class ShortestDistanceInAWeightedGraph {
    public static void main(String[] args) {
        ShortestDistanceInAWeightedGraph obj = new ShortestDistanceInAWeightedGraph();

        int vertices = 7;
        int sourceVertex = 5;

        WeightedGraph graph = obj.createGraph(vertices);
        System.out.println("Shortest distance from source vertex " + sourceVertex + " for all nodes:");
        Pair<int[], int[]> distances = findShortestDistance(graph, sourceVertex, vertices);
        // distances
        for(int i=0; i < distances.first.length; i++) {
            System.out.println("Destination: " + i + " , Distance: " + distances.first[i]);
        }
        // paths
        int[] paths = distances.second;
        System.out.println("Path to all vertices from source vertex " + sourceVertex +":");
        for(int i=0; i < vertices; i++) {
            printPath(i, paths);
        }
    }

    private static void printPath(int vertex, int[] paths) {
        int prev = paths[vertex];
        ArrayList<Integer> path = new ArrayList<>();
        if(prev != -1)
            path.add(vertex);

        while(prev != -1) {
            path.add(prev);
            prev = paths[prev];
        }
        StringJoiner joiner = new StringJoiner(" -> ", "[", "]");
        for(int i=path.size() - 1; i >= 0 ;i--) {
            joiner.add(Integer.toString(path.get(i)));
        }
        System.out.println(joiner);
    }

    private static Pair<int[], int[]> findShortestDistance(WeightedGraph graph, int source, int vertices) {
        // set of visited vertices
        Set<Integer> visited = new HashSet<>();
        int[] distances = new int[vertices];
        int[] paths = new int[vertices]; // array to hold the previous vertex's index of the shortest path for a given index
        Arrays.fill(distances, Integer.MAX_VALUE); // mark all distances as infinity
        Arrays.fill(paths, -1); // mark all vertices as -1 i.e. unreachable

        // distance of source vertex from itself is equal to 0
        distances[source] = 0;

        // Min heap to store the unvisited vertices with their distance from source vertex
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(7, Comparator.comparingInt(a -> (int) a.second));
        pq.add(new Pair(source, distances[source]));

        while(!pq.isEmpty()) {
            Pair poll = pq.poll(); // always pick up vertex with minimum distance
            int currVertex = (int) poll.first;
            if(!visited.contains(currVertex)) { // Check if the vertex has already been visited
                visited.add(currVertex);

                List<WeightedGraph.Edge> edges = graph.adjacencyList.get(currVertex);
                for(WeightedGraph.Edge edge : edges) {
                    int destination = edge.destination;
                    if(!visited.contains(destination)) { // check again if the destination vertex has already been visited
                        int currentDistance = distances[destination];
                        int newDistance = distances[currVertex] + edge.weight;
                        if(newDistance < currentDistance) { // if new distance is better than update it
                            distances[destination] = newDistance;
                            paths[destination] = currVertex;
                            Pair p = new Pair(destination, newDistance);
                            pq.offer(p);
                        }
                    }
                }
            }
        }
        return new Pair<>(distances, paths);
    }

    static class Pair<K, V> {
        K first;
        K second;

        Pair(K first, K second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return first == pair.first && second == pair.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    private WeightedGraph createGraph(int vertices) {
        WeightedGraph graph = new WeightedGraph(vertices); // Graph with 7 vertices
        graph.addDirectedEdge(0, 1, 4);
        graph.addDirectedEdge(0, 2, 3);
        graph.addDirectedEdge(0, 4, 5);
        graph.addDirectedEdge(1, 5, 3);
        graph.addDirectedEdge(2, 1, 2);
        graph.addDirectedEdge(2, 3, 2);
        graph.addDirectedEdge(3, 0, 1);
        graph.addDirectedEdge(3, 4, 1);
        graph.addDirectedEdge(3, 6, 1);
        graph.addDirectedEdge(4, 5, 3);
        graph.addDirectedEdge(5, 6, 2);
        graph.addDirectedEdge(6, 5, 2);

        graph.printGraph();
        return graph;
    }
}
