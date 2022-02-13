package com.p.pc.programs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringJoiner;

public class MinimumJumpsToReachEndOfArray {

    public static void main(String[] args) {
         int[] arr = new int[] {2, 2, 0, 8, 0, 0, 0, 1, 2, 2, 1, 0, 1}; // Output should be -1 due to zero at 11th position starting from 0th position
        //int[] arr = new int[] {2, 4, 0, 8, 9, 1, 2, 7, 1, 0, 8, 0, 0, 0, 0, 1}; // Output should be 3
        // int[] arr = new int[] {3, 2, 0, 8, 0, 0, 0, 1, 2, 2, 1, 1, 1}; // Output should be 3 starting from 0th position
        System.out.println("Minimum jumps O(n): " + minJumps(arr));
        System.out.println("Minimum jumps (dynamic programming): " + minJumpsDP(arr, arr.length));
        System.out.println("Minimum jumps (with indexes): " + minJumpsWithIndexes(arr));
    }

    /**
     * This is O(n2) solution however using this solution we can also find out the jump indexes.
     */
    static int minJumpsWithIndexes(int[] arr) {
        if(arr.length == 1) return 0;
        if(arr[0] == 0) return -1;

        int[] minJumps = new int[arr.length];
        int[] jumpIndex = new int[arr.length];
        Arrays.fill(minJumps, Integer.MAX_VALUE);
        Arrays.fill(jumpIndex, -1);
        minJumps[0] = 0; // initialize the minJumps array for 0 position
        // For each index in the array, try to find out the minimum jump for each position starting from 0 index
        // if index i is reachable from the given j index
        for(int i = 1; i < arr.length; i++) {
            for(int j = 0; j < i; j++ ) {
                if( j + arr[j] >= i) {
                    int jumps = 1 + minJumps[j];
                    if(jumps < minJumps[i]) {
                        minJumps[i] = jumps;
                        jumpIndex[i] = j;
                    }
                }
            }
        }
        System.out.println("Minimum jumps to reach end of array: " + minJumps[arr.length - 1]);
        LinkedList<Integer> jumpIndexes = new LinkedList<>();
        int index = arr.length - 1;
        jumpIndexes.addLast(index);
        while(jumpIndex[index] != -1) {
            int temp = jumpIndex[index];
            jumpIndexes.addFirst(temp);
            index = temp;
        }

        System.out.println("Minimum jump indexes: " + jumpIndexes);
        return minJumps[arr.length - 1];
    }

    static int minJumpsDP(int arr[], int n) {
        if(n == 1) return 0;
        if(arr[0] == 0) return Integer.MAX_VALUE;
        int[] minJumps = new int[n];
        Arrays.fill(minJumps, Integer.MAX_VALUE);

        minJumps[0] = 0;
        // For each i index, calculate the minimum number of jumps required to reach any reachable j index
        for(int i = 0; i < n; i++) {
            int maxReach = Math.min(i + arr[i], n-1);
            for(int j = i+1; j <= maxReach; j++) {
                int jumpTillJ = 1 + minJumps[i];
                if(jumpTillJ < minJumps[j])
                    minJumps[j] = jumpTillJ;
            }
        }
        return minJumps[n-1];
    }

    // This is O(n) solution but it is not possible to know the jump indexes
    static int minJumps(int arr[]) {
        if (arr.length <= 1) return 0;
        if (arr[0] == 0) return -1;

        int maxReach = 0 + arr[0]; // current index + value at index
        int steps = arr[0];
        int jump = 1;
        for (int i = 1; i < arr.length; i++) {
            if (i == arr.length - 1) {
                return jump;
            }
            // updating maxReach
            maxReach = Math.max(maxReach, i + arr[i]);
            // we used a steps to get to the current index
            steps--;
            // If no further steps left
            if (steps == 0) {
                //  we must have used a jump
                jump++;
                // re-initialize the steps to the amount of steps to reach maxReach from position i.
                steps = maxReach - i;
                // If steps are zero or negative that means we can't reach the end of the array
                if(steps <= 0)
                    return -1;
            }
        }
        return -1;
    }
}
