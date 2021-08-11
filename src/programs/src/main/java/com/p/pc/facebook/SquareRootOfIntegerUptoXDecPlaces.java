package com.p.pc.facebook;

/**
 * Find the square root of a given integer up to X decimal places using Binary search.
 */
public class SquareRootOfIntegerUptoXDecPlaces {
    public static void main(String[] args) {
        int num = 1234; int precision = 4;
        System.out.println("Sqrt using math : " + (float) Math.sqrt(num));
        System.out.println("Sqrt using binary search : " + calculateSqrt(num, precision));
    }

    /**
     * Use binary search to calculate the sqrt. First find the integer part
     * and then increment by 0.1 followed by increment/10.
     * @param num number
     * @param precision decimal places
     * @return sqrt
     */
    private static float calculateSqrt(int num, int precision) {
        if(num == 0 || num == 1) return num;
        // binary search
        int start = 1, end = num;
        double ans = 0.0;
        while(start <= end) {
            int mid = (start + end)/2;
            if(mid * mid == num) {
                ans = mid; break;
            } else if(mid * mid < num) {
                start = mid + 1;
                ans = mid; // Assign the mid to answer for getting the integer floor value
            } else end = mid -1;
        }
        // increment the answer by 0.1 till square of ans > num, then increment by 0.01 and so on.
        double increment = 0.1;
        for(int i=0; i < precision; i++) {
            while(ans * ans < num) {
                ans += increment;
            }
            ans -= increment; // while loop breaks when ans becomes greater than the num after last increment hence decrement ans to keep it below the number
            increment /= 10; // change the increment to 1/10th of its previous value
        }
        // for precisions up to 6 decimal places returning a float
        return (float)ans;
    }
}
