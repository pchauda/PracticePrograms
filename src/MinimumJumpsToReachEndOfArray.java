public class MinimumJumpsToReachEndOfArray {

    public static void main(String[] args) {
        int[] arr = new int[] {2, 2, 0, 8, 0, 0, 0, 1, 2, 2, 1, 0, 1}; // Output should be -1 due to zero at 11th position starting from 0th position
        System.out.println("Minimum jumps O(n): " + minJumps(arr));
        System.out.println("Minimum jumps (recursive): " + minimumJumpsRecursive(arr, 0, arr.length-1));
    }

    static int minimumJumpsRecursive(int[] arr, int start, int end) {
        if(start == end) return 0;
        if(arr[start] == 0) return Integer.MAX_VALUE;

        int minJumps = Integer.MAX_VALUE;
        for(int i = start + 1; i <= end && i <= start + arr[start]; i++) {
            int jumps = minimumJumpsRecursive(arr, i, end);
            if(jumps != Integer.MAX_VALUE && jumps + 1 < minJumps) minJumps = jumps + 1;
        }
        return minJumps;
    }

    static int minJumps(int arr[]) {
        if (arr.length <= 1) return 0;
        if (arr[0] == 0) return -1;

        int maxReach = 0 + arr[0]; // current index + value at index
        int step = arr[0];
        int jump = 1;

        for (int i = 1; i < arr.length; i++) {
            if (i == arr.length - 1) return jump;
            // updating maxReach
            maxReach = Math.max(maxReach, i + arr[i]);
            // we use a step to get to the current index
            step--;
            // If no further steps left
            if (step == 0) {
                //  we must have used a jump
                jump++;
                // Since i already reached maxReach and there are no further steps left and its no the end of the array meaning we cant reach the end of array
                if(i >= maxReach)
                    return -1;
                // re-initialize the steps to the amount of steps to reach maxReach from position i.
                step = maxReach - i;
            }
        }
        return -1;
    }
}
