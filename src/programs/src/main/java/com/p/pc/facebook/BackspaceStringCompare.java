package com.p.pc.facebook;

/**
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 * Note that after backspacing an empty text, the text will continue empty.
 *
 * Examples:
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 *
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 *
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 *
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class BackspaceStringCompare {

    public static void main(String[] args) {
        System.out.println(compareStrings("ab#c", "ad#c")); // Output: true
        System.out.println(compareStrings("ab##", "c#d#")); // Output: true
        System.out.println(compareStrings("a##c", "#a#c")); // Output: true
        System.out.println(compareStrings("a##c", "b")); // Output: false
    }
    // Use two pointer approach and start from the last char. If last char is a # then maintain its count and skip those
    // many characters. Perform this operation on both strings separately and decrease the pointers accordingly. Then
    // compare the char at both pointers don't match then return false else continue.
    private static boolean compareStrings(String s, String t) {
        int p1 = s.length() - 1, p2 = t.length() - 1;

        while(p1 >=0 || p2 >= 0) {
            // Move to the left till we find a non hash character
            int hashCount1 = 0;
            while(p1 >= 0 && (s.charAt(p1) == '#' || hashCount1 > 0)) {
                if(s.charAt(p1) == '#') hashCount1++;
                else hashCount1--;
                p1--;
            }
            // Move to the left till we find a non hash character
            int hashCount2 = 0;
            while(p2 >= 0 && (t.charAt(p2) == '#' || hashCount2 > 0)) {
                if(t.charAt(p2) == '#') hashCount2++;
                else hashCount2--;
                p2--;
            }
            // Check for end of string, if both are at end of strings then return true
            if(p1 < 0 || p2 < 0) return p1 < 0 && p2 < 0;
            if(s.charAt(p1) != t.charAt(p2)) return false;

            // decrease the pointer and continue
            p1--;
            p2--;
        }
        return true;
    }
}
