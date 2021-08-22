package com.p.pc.cracking_the_coding_interview.arrays_strings;

/**
 * Implement a method to perform basic string compression using the counts
 * of repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the
 * "compressed" string would not become smaller than the original string, your method should return
 * the original string. You can assume the string has only uppercase and lowercase letters (a - z).
 *
 * Approach:
 *  For each character, keep increasing the counter until the next char is different. If either next char is different or it's the
 *  end of the string then record the char with count to a builder string and continue to the next char after resetting the counter.
 */
public class StringCompression {

    public static void main(String[] args) {
        System.out.println("Compressed string for aabccccddaa is : " + compressedString("aabccccddaa"));
        System.out.println("Compressed string for abc is : " + compressedString("abc"));
    }

    static String compressedString(String s) {
        if(s.length() <= 1) return s;
        StringBuilder sb = new StringBuilder();

        int counter = 0;
        for(int i = 0; i < s.length(); i++) {
            counter++;
            // trick is to check for the next char or end of string and then record the char and counter to compressed string
            if(i + 1 == s.length() || s.charAt(i + 1) != s.charAt(i)) {
                sb.append(s.charAt(i));
                sb.append(counter);
                counter = 0;
            }
        }
        return sb.length() < s.length() ? sb.toString() : s;
    }

}
