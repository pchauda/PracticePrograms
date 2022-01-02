package com.p.pc.geekforgeeks.math_algebra;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * <p>Given an array of elements having (N-K) elements from 1 to N, find the missing K numbers.</p>
 *
 * Approach:<br/>
 *  Maintain a Set to keep track of numbers present in the array. And then collect all number from 1 to N that are missing
 *  from the Set.
 *
 * Find 2 missing numbers from N numbers:
 *
 *  Calculate the sum of 2 missing numbers = n * (n+1)/2 - sum of array
 *  then calculate avg of missing 2 numbers = (sum of missing 2 numbers) / 2
 *  Now one of the missing number must be less than or equal to the avg.
 *  This missing number can be calculated using natural sum of numbers upto avg - sum of array elements less than avg.
 *  Then other missing number can be calculated from sum - first found number.
 */
public class FindKMissingNumbers {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 6, 7, 9};
        int n = 13;
        int k = 6; // find 6 missing numbers out of total 13 numbers
        int[] missingNumbers = findMissingNumbers(arr, n, k);
        System.out.println("Missing 6 numbers: " + Arrays.toString(missingNumbers));
        missingNumbers = findTwoMissingNumbers(arr, 9);
        System.out.println("Missing two numbers: " + Arrays.toString(missingNumbers));
    }

    private static int[] findMissingNumbers(int[] arr, int n, int k) {
        int[] result = new int[k];
        Set<Integer> numbersPresentInArray = new HashSet<>();
        for(int num : arr) {
            numbersPresentInArray.add(num);
        }
        int found = 0;
        for(int i = 1; i <= n ; i++) {
            if(!numbersPresentInArray.contains(i)) {
                result[found++] = i;
                if(found == k) break;
            }
        }
        return result;
    }
    // alternative approach using math
    private static int[] findTwoMissingNumbers(int[] arr, int n) {
        int sumOfMissingNumbers = (n * (n + 1))/2 - IntStream.of(arr).sum();
        // calculate avg of missing numbers
        int avg = sumOfMissingNumbers / 2;
        // first missing number must be less than or equal to the avg, hence calculate it using simple approach of
        // finding one missing number from an array by considering array upto avg number only
        int firstMissingNumber = (avg * (avg + 1))/2 - IntStream.of(arr).boxed().filter(t -> t <= avg).mapToInt(i -> i).sum();
        return new int[]{firstMissingNumber, sumOfMissingNumbers - firstMissingNumber};
    }
}
