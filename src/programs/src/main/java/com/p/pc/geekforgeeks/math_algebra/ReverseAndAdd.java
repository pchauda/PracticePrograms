package com.p.pc.geekforgeeks.math_algebra;

/**
 * The reverse and add function starts with a number, reverses its digits, and adds the reverse to the original.
 * If the sum is not a palindrome, repeat this procedure until it does.
 *
 * Write a program that takes number and gives the resulting palindrome (if one exists).
 * If it took more than 1,000 iterations (additions) or yield a palindrome that is greater than 4,294,967,295, assume
 * that no palindrome exist for the given number.
 *
 * Examples:
 * Input : 195
 * Output : 9339
 *
 * Input: 196
 * Output: No Palindrome Exists
 */
public class ReverseAndAdd {
    public static void main(String[] args) {
        checkForPalindrome(195);
        checkForPalindrome(196);
    }

    private static void checkForPalindrome(long n) {
        int i=0; boolean found = false;
        while(i++ < 1000 && n < 4294967295L) {
            n = n + reverse(n);
            if(isPalindrome(n)) {
                found = true;
                System.out.println(n);
                break;
            }
        }
        if(!found)
            System.out.println("No Palindrome Exists");
    }

    private static long reverse(long n) {
        long reverse = 0;
        while(n > 0) {
            long leastSignificantDigit = n % 10;
            reverse = reverse * 10 + leastSignificantDigit;
            n = n/10;
        }
        return reverse;
    }

    private static boolean isPalindrome(long n) {
        return reverse(n) == n;
    }
}
