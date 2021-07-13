import java.util.Arrays;

import static java.lang.String.format;

/**
 * Find contiguous sub array with maximum sum and print the sub array and its sum.
 */
public class MaxSumSubArray {

    public static void main(String[] args) {
        int[] arr = new int[] {2, 4, -1, -6, 7, -8, 9, 5, -3, -3, 9}; // Max Sum = 17
        // int[] arr = new int[] {-91, -10, -1, -6, -8, -3, -3}; // Max Sum = -1

        int startIndex = 0, endIndex = 0;
        int tempStartIndex = 0;
        int max = arr[0];
        int prevSum = arr[0];

        for(int i=1; i < arr.length; i++) {
            int newSum = Math.max(prevSum + arr[i], arr[i]);
            if(newSum > max) {
                max = newSum;
                startIndex = tempStartIndex; // Resets the startIndex to the next max sub array
                endIndex = i; // end of the max sub array
            }
            if (newSum < 0) {
                tempStartIndex = i + 1; // Reset the temp start index to next index to find a potential max later
            }
            prevSum = newSum;
        }
        System.out.println(format("Max sum of the contiguous sub array: %d with sub array indexes: {%d, %d} " , max, startIndex, endIndex));
        for(int i : Arrays.copyOfRange(arr, startIndex, endIndex + 1)) {
            System.out.print(i + " ");
        }

        System.out.println("Max sum: " + maxSum(arr));
    }

    /**
     * Kadane's algo to get the max sum of contiguous array, O(n) solution.
     *
     * Compute the max sum for each index where maxsum{i} = max (prevMax + curr, curr)
     */
    static int maxSum(int[] arr) {
        int max = arr[0];
        int prevSum = arr[0];

        for(int i=1; i < arr.length; i++) {
            int newSum = Math.max(prevSum + arr[i], arr[i]);
            if(newSum > max) max = newSum;
            prevSum = newSum;
        }
        return max;
    }

}
