package com.p.pc.cracking_the_coding_interview.arrays_strings;

/**
 * Problem statement:
 * Implement an algorithm to determine if a string has all unique characters. What if you
 * cannot use additional data structures?
 */
public class UniqueCharacters {

    public static void main(String[] args) {
        String str1 = "Prince";
        String str2 = "Prince Chauda";

        System.out.println(str1 + " - unique: " + isUniqueChars(str1));
        System.out.println(str2 + " - unique: " + isUniqueChars(str2));

        System.out.println("prince" + " - unique: " + isUniqueCharsWithoutUsingTempSpace("prince"));
    }
    // assuming ascii 256 charset.
    static boolean isUniqueChars(String str) {
        // Idea here is that if there are more than 256 chars then definitely there are non-unique chars as total chars are 256 only
        if(str.length() > 256) return false;
        boolean[] chars = new boolean[256];
        for(int c: str.toCharArray()) {
            if(chars[c]) {
                return false;
            }
            chars[c] = true;
        }
        return true;
    }
    // only applicable for letters through a to z
    static boolean isUniqueCharsWithoutUsingTempSpace(String str) {
        if(str.length() > 26) return false;
        // initially checker is zero
        int checker = 0;
        for(char c: str.toCharArray()) {
            int num = c - 'a';
            // bitwise AND operation after left shifting 1 by as many positions as the num
            // if AND operation results into a non-zero number then it means there is duplicate char as checker is being
            // updated with all bits being 1 for each character encountered
            if((checker & (1 << num)) > 0) return false; // 1 << num will result into a precision loss
            // set the checker to bitwise OR of checker and num such that all bits become 1
            checker |= (1 << num);
        }
        return true;
    }
}
