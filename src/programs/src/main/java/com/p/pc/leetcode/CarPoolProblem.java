package com.p.pc.leetcode;

/**
 * There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
 * You are given the integer capacity and an array trips where trips[i] = [numPassengers, from, to] indicates that the
 * ith trip has numPassengers passengers and the locations to pick them up and drop them off are from and to respectively.
 * The locations are given as the number of kilometers due east from the car's initial location.
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 * <p>
 * Example 1:
 * * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 * Example 2:
 * <p>
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 * <p>
 * Constraints:
 * 1 <= trips.length <= 1000
 * trips[i].length == 3
 * 1 <= numPassengers <= 100
 * 0 <= from < toi <= 1000
 * 1 <= capacity <= 105
 * <p>
 * Approach:
 * Since vehicle is only driving in one direction, we can safely assume the locations are in increasing order.
 * An array representing all locations be maintained that will contain aggregate passengers at that location.
 * Hence, for each pickup location, add the number of passengers to that location, and for drop off location subtract
 * the passengers from that location.
 * At the end, just traverse the array and count the passengers from start to end, and as soon as total passengers exceed
 * the capacity then return false else true.
 * <p>
 * Time complexity = O(n) where n = total locations
 * Space complexity = O(n)
 */
public class CarPoolProblem {

    public static void main(String[] args) {
        int[][] trips = new int[][]{
                {2, 1, 5}, {3, 3, 7}, {2, 5, 8}, {1, 6, 10}
        };
        System.out.println("Is possible to pick up all passengers? " + checkCapacity(5, trips)); // Output: false
        System.out.println("Is possible to pick up all passengers? " + checkCapacity(6, trips)); // Output: true
    }

    private static boolean checkCapacity(int maxCapacity, int[][] trips) {
        int[] location = new int[1001]; // Max limit = 1000

        // update passenger counts for each location
        for (int[] trip : trips) {
            int passengers = trip[0];
            int from = trip[1];
            int to = trip[2];

            location[from] += passengers;
            location[to] -= passengers;
        }

        // check for over capacity by keeping a running sum
        int totalPassengers = 0;
        for (int passengers : location) {
            totalPassengers += passengers;
            if (totalPassengers > maxCapacity) return false;
        }
        return true;
    }

}
