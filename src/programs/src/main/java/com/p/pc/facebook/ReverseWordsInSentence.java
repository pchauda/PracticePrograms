package com.p.pc.facebook;

/**
 * <p>Reverse words in a given sentence i.e. "Prince is an engineer" to "engineer an is Prince".</p>
 *
 * Approach: <br/>
 * First reverse the given char array completely. It will result in reversed words as well.
 * Now, reverse individual words in place.
 */
public class ReverseWordsInSentence {
    public static void main(String[] args) {
        String s = "  This   is my    home";
        char[] chars = s.toCharArray();
        System.out.println(chars);
        reverseWordsInSentence(chars);
        System.out.println(chars);
    }

    private static void reverseWordsInSentence(char[] chars) {
        if(chars == null || chars.length <= 1) return;
        reverseString(chars, 0, chars.length-1);
        int start = 0, end = 0;
        for(int i=0; i < chars.length; i++) {
            if(chars[i] == ' ') {
                reverseString(chars, start, end - 1);
                start = end = i + 1; // reset both start and end
            } else {
                end++; // increment end
            }
        }
    }

    private static void reverseString(char[] word, int start, int end) {
        if(word == null || word.length <= 1) return;
        while(start < end) {
            char tmp = word[start];
            word[start++] = word[end];
            word[end--] = tmp;
        }
    }
}
