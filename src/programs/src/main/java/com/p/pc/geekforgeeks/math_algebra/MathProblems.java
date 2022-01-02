package com.p.pc.geekforgeeks.math_algebra;

/**
 * <p>Problem 1: For a given N, find missing number in an array containing elements from range 1: N-1</p>
 * <p>Problem 2: Find trailing zeros in the factorial of a given number.</p>
 */
public class MathProblems {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,8};
        System.out.println("Missing number for N=8 is: " + missingNumberInArray(arr, 8));
        System.out.println("Trailing Zeros in 100: " + trailingZeroesInFactorial(100));
        double b = 123/11.0;
        System.out.println(b);
        System.out.println(50%22);
    }
    // Calculate the (sum of n numbers - sum of array numbers)
    static int missingNumberInArray(int[] array, int n) {
        int totalSum = (n * (n+1))/2;
        int arraysum = 0;
        for(int value: array) {
            arraysum += value;
        }
        return totalSum - arraysum;
    }

    /**
     * Idea here is to calculate the number of 5's appearing in the factorial.
     * it can easily be calculated by floor(N/5). However, when number >= 25,125 then an extra 5 is added
     * so ultimately the formula is
     *      floor(N/5) + floor(N/25) + floor(N/125) .... till N/i < 1
     */
    static int trailingZeroesInFactorial(int N){
        if(N < 0) return -1;
        int count = 0;
        for(int i=5; N/i >=1; i *= 5) {
            count += N/i;
        }
        return count;
    }
}
