package com.p.pc.cracking_the_coding_interview.arrays_strings;

import java.util.HashMap;
import java.util.Map;

public class PalindromePermutation {
    public static void main(String[] args) {
        String str = "Rats evil not ton live staR";
        System.out.println("Given string: \" " + str + " \"" + " is a permutation of palindrome? " + checkIfPalindromeOfPermutation(str));
    }

    private static boolean checkIfPalindromeOfPermutation(String str) {
        if(str.length() <= 1) return true;
        int countOdd = 0;
        Map<Character, Integer> charFreq = new HashMap<>();
        for(char c: str.toCharArray()) {
            charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
            if(charFreq.get(c) % 2 == 0) {
                countOdd--;
            } else countOdd++;
        }
        return countOdd <= 1 ? true : false;
    }
}
