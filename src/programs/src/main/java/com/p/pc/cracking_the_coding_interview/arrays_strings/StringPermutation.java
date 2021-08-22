package com.p.pc.cracking_the_coding_interview.arrays_strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Multiple types of string permutation questions
 */
public class StringPermutation {
    public static void main(String[] args) {
        System.out.println("Are strings palindrome of each other? " + checkPalindrome("Rahul", "luhaR"));
        System.out.println("Are strings permutation of each other? " + checkPermutation("Rahul", "Ruhal"));

        List<String> allPermutations = new ArrayList<>();
        printPermutations("ABCD", "", allPermutations);
        System.out.println(allPermutations);
        System.out.println("Total permutations:" + allPermutations.size());

        System.out.println("Are strings rotation of each other? " + checkRotation("Rahul", "hulRa"));

    }

    /**
     * Given two strings, write a method to decide if one is a palindrome of the
     * other.
     */
    static boolean checkPalindrome(String str1, String str2) {
        if(str1 == null || str2 == null) return false;
        if(str1.length() != str2.length()) return false;

        int ptr1 = 0;
        int ptr2 = str2.length() - 1;
        while(ptr1 < str1.length() && ptr2 >= 0) {
            if(str1.charAt(ptr1++) != str2.charAt(ptr2--)) return false;
        }
        return true;
    }

    /**
     * Given two strings, write a method to decide if one is a permutation of the
     * other.
     * Approach:
     *  Keep the characters to a map with its count for first string. Decrease the count for second string's char and
     *  if count goes to negative that means char don't match and hence string is not permutation.
     */
    static boolean checkPermutation(String str1, String str2){
        if(str1.length() != str2.length()) return false;

        HashMap<Character, Integer> charCount = new HashMap<>();
        for(char c: str1.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        for(char c: str2.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) - 1);
            if(charCount.get(c) < 0) return false;
        }
        return true;
    }


    /**
     * For each character in the string, remove the character and collect it as prefix and invoke
     * permutation method recursively using the remaining string and prev prefix + character removed
     */
    static void printPermutations(String s, String prefix, List<String> allPermutations) {
        if(s.length() == 0) {
            allPermutations.add(prefix);
        }
        for(int i=0; i < s.length(); i++) {
            String suffix = s.substring(0, i) + s.substring(i + 1);
            printPermutations(suffix, prefix + s.charAt(i), allPermutations);
        }
    }

    /**
     * Check if two strings are rotation of each other. For example: ant and tan are rotation of each other.
     *
     * Approach: If the string is appended with its own then it will contain all its rotations in it. We will use this
     * property to find if strings are rotation of each other or not.
     *  If length is not the same then return false
     *  Else append the first string to its own and perform a contains check for second string
     * @param str1
     * @param str2
     * @return
     */
    static boolean checkRotation(String str1, String str2) {
        String tmp = str1 + str1;
        return tmp.contains(str2);
    }
}
