package com.p.pc.facebook;

/**
 * Implement a function that prints first n elements of the look and say sequence.
 *
 * Example: Input = 6
 * Output =
 * 1
 * 11
 * 21
 * 1211
 * 111221
 * 312211
 *
 * Approach:
 *  Use String instead of integer to handle large numbers. Read the string char by char and then count the number of times
 *  its occurring and collect it in a string builder as soon as different char appears or string ends. Print the final
 *  value and use it again to print the next number and so on.
 */
public class LookAndSaySequence {
    public static void main(String[] args) {
        int n = 10;
        printLookAndSaySequence(n);
    }

    private static void printLookAndSaySequence(int n) {
        String num = "1";
        System.out.println(num);
        int i=1;

        while(i++ < n) {
            num = printAndReturnNext(num);
            System.out.println(num);
        }
    }

    private static String printAndReturnNext(String num) {
        int count = 1;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if(i < num.length() - 1 && c == num.charAt(i+1)) {
                count++; // increase the count
            } else {
                sb.append(count).append(c); // add the count and number to the string
                count = 1; // reset count
            }
        }
        return sb.toString();
    }
}
