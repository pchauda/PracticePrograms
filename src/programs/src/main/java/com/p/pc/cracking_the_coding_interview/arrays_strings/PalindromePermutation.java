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

        Map<Character, Integer> charFreq = new HashMap<>();
        for(char c: str.toCharArray()) {
            charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
        }
        // Except one char every character should appear even number of times
        int count = 0;
        for(int value : charFreq.values()) {
            if(value % 2 != 0) {
                count++;
            }
        }
        return count > 1? false:true;
    }
}
