package com.p.pc.bloomberg;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <b>Problem Statement</b> <br/>
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi]
 * and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer
 * for Cj / Dj = ?.
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 * <p>
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and
 * that there is no contradiction.
 * <p>
 * Example 1:
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * <p>
 * Example 2:
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 * <p>
 * <b>Approach</b><br/>
 * Create a graph using the given input equations where variables can be considered as nodes in the graph and values can
 * be considered as weight on the edge. Once this graph is created then for any input query, we just need to find out
 * if a path exists between two given nodes and if it does then calculate the cumulative weight.
 * <p>
 * <b>Companies: Uber, Facebook, Google, Bloomberg, Goldman Sachs, Amazon, Snapchat, Blackrock</b>
 */
public class EvaluateDivision {
    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(Stream.of("a", "b").collect(Collectors.toList()));
        equations.add(Stream.of("b", "c").collect(Collectors.toList()));
        equations.add(Stream.of("bc", "cd").collect(Collectors.toList()));

        double[] values = new double[] {1.5, 2.5, 5.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Stream.of("a", "c").collect(Collectors.toList()));
        queries.add(Stream.of("c", "b").collect(Collectors.toList()));
        queries.add(Stream.of("bc", "cd").collect(Collectors.toList()));
        queries.add(Stream.of("cd", "bc").collect(Collectors.toList()));
        queries.add(Stream.of("b", "d").collect(Collectors.toList()));

        double[] result = calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(result)); // Output: [3.75, 0.4, 5.0, 0.2, -1.0]
    }

    /**
     * Construct a graph using the given equation, for simple lookup in the graph, just use Map of Map to represent the
     * graph.
     */
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Create a graph
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for(int i=0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String numerator = equation.get(0); String denominator = equation.get(1);
            double quotient = values[i];
            // add nodes to graph if it does not exist
            if(!graph.containsKey(numerator)) {
                graph.put(numerator, new HashMap<>());
            }
            if(!graph.containsKey(denominator)) {
                graph.put(denominator, new HashMap<>());
            }
            // add weighted edges in both direction
            graph.get(numerator).put(denominator, quotient);
            graph.get(denominator).put(numerator, 1/quotient);
        }

        // Perform DFS for each query and collect result
        double[] result = new double[queries.size()];
        for(int i=0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String numerator = query.get(0); String denominator = query.get(1);
            // base conditions
            if(!graph.containsKey(numerator) || !graph.containsKey(denominator)) {
                result[i] = -1;
            } else if(numerator == denominator) {
                result[i] = 1;
            } else {
                // perform DFS
                Set<String> visited = new HashSet<>();
                result[i] = performDFS(graph, numerator, denominator, 1, visited);
            }
        }
        return result;
    }

    static double performDFS(Map<String, Map<String, Double>> graph, String source, String target, double calcWeight, Set<String> visited) {
        // add the source as visited
        visited.add(source);
        double result = -1;
        Map<String, Double> neighbours = graph.get(source);
        // if the target is found as a direct neighbour then just return the weight else perform DFS
        if(neighbours.containsKey(target)) {
            result = calcWeight * neighbours.get(target);
        } else {
            for(Map.Entry<String, Double> entry : neighbours.entrySet()) {
                String node = entry.getKey(); Double weight = entry.getValue();
                if(visited.contains(node)) continue;
                result = performDFS(graph, node, target, calcWeight * weight, visited);
                if(result != -1)
                    break;
            }
        }
        // remove the source from visited to be able to explore other paths
        visited.remove(source);
        return result;
    }
}
