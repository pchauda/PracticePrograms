package com.p.pc.cracking_the_coding_interview.arrays_strings;

/**
 * <p>Given a string with spaces in between, URLify the string (replace whitespace with %20) ignoring all spaces at the end.</p>
 *
 * Approach: <br/>
 *  1. Identify the true length (upto the last valid character)
 *  2. Calculate total spaces in the string
 *  3. Calculate the new length = true length + 2 * space_count --> ' ' will be replaced with '%20' that means for every space we need 2 more empty positions
 *  4. Start filling the string from the last index of the new length by replacing ' ' with '%20'
 */
public class URLify {
    public static void main(String[] args) {
        String str = "Mr. Prince Chauda        ";
        int trueLength = computeTrueLength(str);
        char[] newStr = urlify(str.toCharArray(), trueLength);
        for(int i=0; i<newStr.length && newStr[i] != '\0'; i++) {
            System.out.print(newStr[i]);
        }
    }

    private static char[] urlify(char[] str, int trueLength) {
        if(trueLength == 0) return str;
        int spaceCount = 0;
        // count the spaces in the string
        for(int i=0; i < trueLength; i++) {
            if(str[i] == ' ') spaceCount++;
        }
        int newLength = trueLength + 2 * spaceCount;
        // mark the end of the string in case original string is longer than the required
        if(str.length > newLength) str[newLength] = '\0';
        // fill the array from the end
        int index= newLength - 1;
        for(int i = trueLength-1; i >= 0; i--) {
            if(str[i] == ' ') {
                str[index--] = '0';
                str[index--] = '2';
                str[index--] = '%';
            } else {
                str[index--] = str[i];
            }
        }
        return str;
    }
    private static int computeTrueLength(String str) {
        for(int i = str.length() - 1; i >= 0; i--) {
            if(str.charAt(i) != ' ') {
                return i+1;
            }
        }
        return 0;
    }
}
