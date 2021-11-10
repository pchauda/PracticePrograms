package com.p.pc.cracking_the_coding_interview.recursion_dp;

/**
 * Write a recursive function to multiply two positive integers without using
 * the * operator (or / operator). You can use addition, subtraction, and bit shifting, but you should
 * minimize the number of those operations.
 *
 * Approach:
 *  Find the smaller number, and add the bigger number, smaller number of times. Time complexity will be O(s).
 *  To reduce the time complexity, we can divide the smaller by 2 using right shift operation and then compute the half sum.
 *  If smaller is an even number, then add the half sum to itself, else also add bigger number. In this way, the
 *  time complexity will be reduced to O(log s) only
 */
public class RecursiveMultiply {
    private static RecursiveMultiply obj = new RecursiveMultiply();

    public static void main(String[] args) {
        int a = 23, b = 17;
        System.out.println(a * b);
        System.out.println(obj.multiply(a, b));
    }

    int multiply(int a, int b) {
        int smaller = a < b ? a : b;
        int bigger =  a < b ? b : a;
        return multiplyHelper(smaller, bigger);
    }

    int multiplyHelper(int smaller, int bigger) {
        // Base cases
        if(smaller == 0) return 0;
        if(smaller == 1) return bigger;
        // Compute half sum using recursion
        int halfSum = multiplyHelper(smaller >> 1, bigger);
        // Effectively multiply by 2
        int doubleSum = halfSum + halfSum;
        // If smaller is even then return the double sum else add bigger as well
        return smaller % 2 == 0 ? doubleSum : doubleSum + bigger;
    }
}
