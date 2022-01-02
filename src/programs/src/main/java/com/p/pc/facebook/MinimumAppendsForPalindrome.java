package com.p.pc.facebook;

/**
 * <p>Find out the minimum number of appends required for a given string to make the given string as a palindrome.</p>
 *
 * <p>Approach:</p>
 *  Check if the string is a palindrome or not. If no, then remove the first char and check again and so on.
 *  The total number of chars removed will be equal to total appends.
 */
public class MinimumAppendsForPalindrome {
    public static void main(String[] args) {
        String str = "abacdedca";
        int appends = minAppendsToConvertStringToPalindrome(str.toCharArray());
        System.out.println("Minimum appends required : " + appends);
    }

    static int minAppendsToConvertStringToPalindrome(char[] str) {
        int i=0;
        while(!checkForPalindrome(str, i)) {
            i++;
        }
        return i;
    }

    static boolean checkForPalindrome(char[] str, int startIndex) {
        int length = str.length;
        if(length == 1) return true;

        int startPtr = startIndex;
        int endPtr = length - 1;
        while(startPtr <= endPtr) {
            if(str[startPtr++] != str[endPtr--]) return false;
        }
        return true;
    }
}
