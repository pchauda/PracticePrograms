package com.p.pc.facebook;

/**
 * Implement a function that prints first n elements of the look and say sequence. <br/>
 * <p>
 * Example: Input = 6 <br/>
 * Output = <br/>
 * 1 <br/>
 * 11 <br/>
 * 21 <br/>
 * 1211 <br/>
 * 111221 <br/>
 * 312211 <br/>
 * <p>
 * Approach: <br/>
 * Use String instead of integer to handle large numbers. Read the string char by char and then count the number of times
 * its occurring and collect it in a string builder as soon as different char appears or string ends. Print the final
 * value and use it again to print the next number and so on.
 */
public class LookAndSaySequence {
    public static void main(String[] args) {
        int n = 10;
        printLookAndSaySequence(n);
    }

    private static void printLookAndSaySequence(int n) {
        String num = "1";
        System.out.println(num);
        int i = 1;

        while (i++ < n) {
            num = calculateNext(num);
            System.out.println(num);
        }
    }

    private static String calculateNext(String num) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if (i < num.length() - 1 && c == num.charAt(i + 1)) {
                count++; // increase the count
            } else {
                sb.append(count).append(c); // add the count and number to the string
                count = 1; // reset count
            }
        }
        return sb.toString();
    }
}
