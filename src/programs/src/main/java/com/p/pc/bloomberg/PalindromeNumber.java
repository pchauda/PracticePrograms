package com.p.pc.bloomberg;

/**
 * <b>Problem Statement</b> <br/>
 * Given an integer x, return true if x is palindrome integer.An integer is a palindrome when it reads the same backward as forward.
 * For example, 121 is a palindrome while 123 is not.
 * <p>
 * Example 1:
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads as 121 from left to right and from right to left.
 * <p>
 * <b>Approach</b>
 * Reverse the number till the remaining number remains grater than the reversed number. Then in case odd number of
 * digits compare the remaining number with reverse/10, for even digits just compare the remaining with reverse.
 * <p>
 * <b>Companies: Bloomberg, Facebook, Amazon, Google</b>
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        System.out.println("Is palindrome: " + checkForPalindrome(120)); // false
        System.out.println("Is palindrome: " + checkForPalindrome(121)); // true
        System.out.println("Is palindrome: " + checkForPalindrome(-121)); // false
        System.out.println("Is palindrome: " + checkForPalindrome(1221)); // true
    }

    // Time complexity = O(log10 n) as number is being divided by 10 in each iteration
    private static boolean checkForPalindrome(int num) {
        // If number is negative or has zero at least significant digit then it won't be a palindrome
        if(num < 0 || (num % 10 == 0 && num != 0)) return false;
        int reverse = 0;

        while(num > reverse) {
            reverse = reverse * 10 + num % 10;
            num /= 10;
        }
        // for odd number of digits reverse will have 1 extra digit
        return num == reverse || (num == reverse / 10);
    }
}
