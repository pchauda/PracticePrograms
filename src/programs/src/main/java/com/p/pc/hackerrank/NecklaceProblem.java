package com.p.pc.hackerrank;

/**
 * Given a set of necklaces, find a necklace with maximum beads. All beads are uniquely numbered across necklaces.
 * Given input array represents all beads. Array index represents the current bead and arr value represents the next bead.
 * int[] array = new int[] {5, 4, 0, 3, 7, 6, 2, 8, 9, 10, 1};
 *
 * Necklace 1
 * 0 -> 5 -> 6 -> 2 -> 0
 * Necklace 2
 * 1 -> 4 -> 7 -> 8 -> 9 -> 10 -> 1
 * Necklace 3
 * 3 -> 3
 * Input Array:
 * Bead Next_Bead
 * 0 ->  5,
 * 1 ->  4,
 * 2 ->  0,
 * 3 ->  3,
 * 4 ->  7,
 * 5 ->  6,
 * 6 ->  2,
 * 7 ->  8,
 * 8 ->  9,
 * 9 ->  10,
 * 10 -> 1
 */
public class NecklaceProblem {

    public static void main(String[] args) {
        int[] array = new int[] {5, 4, 0, 3, 7, 6, 2, 8, 9, 10, 1};
        System.out.println("Length: " + findMaxLength(array)); // Output: 6
        System.out.println("Length (Optimal solution): " + findMaxLengthOptimal(array)); // Output: 6
    }

    static int findMaxLengthOptimal(int[] array) {
        boolean[] visited = new boolean[array.length];
        int max = 1;
        for(int i=0; i < array.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                int value = array[i];
                int count = 1;
                while(i != value) {
                    visited[value] = true;
                    value = array[value];
                    count++;
                }
                if(count > max) max = count;
            }
        }
        return max;
    }

    static int findMaxLength(int[] array) {
        int max = 1;
        boolean[] visited = new boolean[array.length];
        for(int i=0; i<array.length; i++) {
            if(!visited[i]) {
                int tmp = findMaxLengthRecursive(array, i, array[i], visited);
                if(tmp > max) {
                    max = tmp;
                }
            }
        }
        return max;
    }

    static int findMaxLengthRecursive(int[] array, int startIndex, int value, boolean[] visited) {
        visited[startIndex] = true;
        if(startIndex == value) return 1;
        return 1 + findMaxLengthRecursive(array, startIndex, array[value], visited);
    }
}