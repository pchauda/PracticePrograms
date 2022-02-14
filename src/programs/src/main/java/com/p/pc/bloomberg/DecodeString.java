package com.p.pc.bloomberg;

import java.util.Stack;

/**
 * <b>Problem Statement</b><br/>
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated
 * exactly k times. Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is valid; there are no extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those
 * repeat numbers, k. For example, there will not be input like 3a or 2[4].
 * <p>
 * Example 1:
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * <p>
 * Example 2:
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * <p>
 * <b>Approach</b>
 * Use two stack approach, once to keep track of K and other to keep track of decoded string so far.
 * <p>
 * <b>Companies: Bloomberg, Google, Microsoft, Amazon, Snapchat, Apple, Oracle</b>
 */
public class DecodeString {

    public static void main(String[] args) {
        System.out.println("Decoded string: " + decodeString("2[a]2[bc]")); // aabcbc
        System.out.println("Decoded string: " + decodeString("2[a2[bc]]")); // abcbcabcbc
        System.out.println("Decoded string: " + decodeString("2[ab]2[cd]ef")); // ababcdcdef

    }

    private static String decodeString(String str) {
        Stack<StringBuilder> stringStack = new Stack<>();
        Stack<Integer> kStack = new Stack<>();

        int k=0;
        StringBuilder currentString = new StringBuilder();
        for(char ch: str.toCharArray()) {
            if(Character.isDigit(ch)) {
                k = k * 10 + (ch - '0'); // collect k as k can have multiple digits
            } else if(ch == '[') { // encountered opening bracket, push k and current string to stack
                kStack.push(k);
                stringStack.push(currentString);
                // reset current string and k for next iteration
                currentString = new StringBuilder();
                k = 0 ;
            } else if(ch == ']') { // encountered closing bracket, pop from the stack and decode string
                StringBuilder decodedString = stringStack.pop();
                for(int i=kStack.pop(); i>0; i--) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                currentString.append(ch);
            }
        }
        return currentString.toString();
    }
}
