import java.util.Arrays;

/**
 * Find contiguous sub array with maximum sum and print the sub array and its sum.
 */
public class MaxSumSubArray {

    public static void main(String[] args) {
        int[] arr = new int[] {2, 4, -1, -6, 7, -8, 9, 5, -3, -3, 9}; // Max Sum = 17

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
        System.out.println("Max sum of the contiguous sub array: " + max);
        for(int i : Arrays.copyOfRange(arr, startIndex, endIndex + 1)) {
            System.out.print(i + " ");
        }
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
            if(newSum > prevSum) max = newSum;
            prevSum = newSum;
        }
        return max;
    }

}
