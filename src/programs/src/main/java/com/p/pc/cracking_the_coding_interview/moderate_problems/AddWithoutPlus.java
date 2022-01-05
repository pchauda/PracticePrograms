package com.p.pc.cracking_the_coding_interview.moderate_problems;

/**
 * <p>Write a function that adds two numbers. You should not use + or any arithmetic operators.</p> ````````
 *
 * Approach:
 *  Since any arithmetic operator is not allowed, a binary calculation needs to be done using XOR, AND and LEFT SHIFT.
 *  1. XOR of two numbers will result into a sum without carry
 *  2. AND of two numbers with LEFT SHIFT will result into carry
 *  Repeating above steps till carry is zero will result into the final sum.
 */
public class AddWithoutPlus {
    public static void main(String[] args) {
        int a = 123, b = 257;
        int result = addWithoutPlus(a, b);
        System.out.println(result); // Output: 380
    }

    private static int addWithoutPlus(int a, int b) {
        while(b != 0) {
            int tmp = a ^ b; // sum without carry
            b = (a & b) << 1; // carry
            a = tmp;
        }
        return a;
    }
}
