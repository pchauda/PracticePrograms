package com.p.pc.bloomberg;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <b>Problem Statement</b><br/>
 * A company is planning to interview 2n people. Given the array costs where costs[i] = [aCosti, bCosti], the cost of
 * flying the ith person to city a is aCosti, and the cost of flying the ith person to city b is bCosti.
 * <p>
 * Return the minimum cost to fly every person to a city such that exactly n people (half) arrive in each city.
 * <p>
 * Example 1:
 * Input: costs = [[10,20],[30,200],[400,50],[30,20]]
 * Output: 110
 * Explanation:
 * The first person goes to city A for a cost of 10.
 * The second person goes to city A for a cost of 30.
 * The third person goes to city B for a cost of 50.
 * The fourth person goes to city B for a cost of 20.
 * <p>
 * The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city. <br/>
 * Example 2:
 * Input: costs = [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
 * Output: 1859
 *
 * <b>Approach</b><br/>
 * Apply the greedy approach to solve this problem. Sort the input data based on the cost difference between moving
 * a person to A city vs B city in increasing order.
 * Then move the half persons to city A and remaining to city B. This will automatically produce the least cost to company.
 *
 * <b>Companies: Bloomberg</b>
 */
public class TwoCityScheduling {

    public static void main(String[] args) {
        System.out.println("Minimum cost: " + twoCitySchedCost(new int[][] {
                {259, 770}, {448, 54}, {926, 667}, {184, 139}, {840, 118}, {577, 469}
        }));
    }

    public static int twoCitySchedCost(int[][] costs) {
        // Sort by a gain which company has by sending a person to city A and not to city B
        Arrays.sort(costs, Comparator.comparingInt((int[] t) -> {
            int costA = t[0]; int costB = t[1];
            return costA - costB;
        }));
        // To optimize the company expenses, send the first n persons to the city A and the others to the city B
        int people = costs.length; int cost = 0;
        for(int i=1; i < people/2; i++) {
            cost += (i <= people/2) ? costs[i-1][0] : costs[i-1][1];
        }
        return cost;
    }
}
