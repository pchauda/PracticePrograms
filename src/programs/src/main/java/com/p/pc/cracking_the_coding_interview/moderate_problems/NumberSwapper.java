package com.p.pc.cracking_the_coding_interview.moderate_problems;

/**
 * Write a function to swap a number in place (that is, without temporary variables).
 */
public class NumberSwapper {
    public static void main(String[] args) {
        swapInPlace(100, 38);
        swapInPlace(25, 73);
    }

    private static void swapInPlace(int a, int b) {
        System.out.println("Current Values: A = " + a + ", B = " + b);
        if(a > b) {
            a = a - b; // set a to diff
            b = b + a; // add diff to b to make it equal to a
            a = b - a; // Since b is now equal to a and a is diff, hence b - a will given original value of a
        } else {
            b = b - a;
            a = a + b;
            b = a - b;
        }
        System.out.println("Swapped Values: A = " + a + ", B = " + b);
    }
}
