package com.p.pc.cracking_the_coding_interview.arrays_strings;

/**
 * There are three types of edits that can be performed on strings: insert a character,
 * remove a character, or replace a character. Given two strings, write a function to check if they are
 * one edit (or zero edits) away.
 * EXAMPLE
 * pale, ple -> true
 * pales, pale -> true
 * pale, bale -> true
 * pale, bake -> false
 *
 * Approach:
 *  1. Identify longer and smaller strings out of the given and assign one pointer to each and repeat below till end of any string
 *  2. If chars are the pointers are same then increase both pointers
 *  3. If chars are different, then record the fact that a diff has been found.
 *      a. if both strings are of same length then increase both pointers,
 *      b. else only increase the pointer for large string
 *  4. If another diff found then return false else return true
 */
public class StringCompareWithEdit {
    public static void main(String[] args) {
        System.out.println("Strings are one/zero edit away? " + compareStrings("Prince", "Printe"));
        // Output = true
        System.out.println("Strings are one/zero edit away? " + compareStrings("Prince", "Princey"));
        // Output = true
        System.out.println("Strings are one/zero edit away? " + compareStrings("Prince", "Prinb"));
        // Output = false;
    }

    private static boolean compareStrings(String str1, String str2) {
        if(Math.abs(str1.length() - str2.length()) > 1) return false;
        // find the larger string, in case of equal length strings below will ensure smaller and larger will still point to different strings.
        String smaller = str1.length() < str2.length() ? str1 : str2;
        String larger  = str1.length() < str2.length() ? str2 : str1;

        int sIdx = 0;
        int lIdx = 0;
        boolean notEqual = false;
        while(sIdx < smaller.length() && lIdx < larger.length()) {
            if(smaller.charAt(sIdx) != larger.charAt(lIdx)) {
                if(notEqual) return false;
                notEqual = true;
                if(smaller.length() == larger.length()) {
                    sIdx++;
                }
            } else {
                sIdx++;
            }
            lIdx++;
        }
        return true;
    }
}
