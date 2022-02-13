package com.p.pc.bloomberg;

import java.util.Stack;

/**
 * <b>Problem Statement</b> <br/>
 * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters
 * from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
 * We repeatedly make k duplicate removals on s until we no longer can. Return the final string after all such duplicate
 * removals have been made. It is guaranteed that the answer is unique.
 * <p>
 * Examples:
 * Input: s = "abcd", k = 2 <br/>
 * Output: "abcd" <br/>
 * Explanation: There's nothing to delete.
 * <p>
 * Input: s = "pbbcggttciiippooaais", k = 2
 * Output: "ps"
 * <p>
 * <b>Approach: </b><br/>
 * Use a stack to keep track of last visited char along with the count. If count reaches the limit then
 * pop the character and so on.
 * <p>
 * <b>Companies: Bloomberg, Facebook, Google, Amazon, Tiktok, Goldman Sachs, Oracle</b>
 */
public class RemoveDuplicatesInString {
    public static void main(String[] args) {
        System.out.println(removeDuplicates("pbbcggttciiippooaais", 2)); // Output: ps
        System.out.println(removeDuplicates("adeeedbbcccbda", 3)); // Output: aa
        System.out.println(removeDuplicates("abcd", 3)); // Output: abcd
    }

    static String removeDuplicates(String str, int k) {
        Stack<Pair> stack = new Stack<>();
        for(int i=0; i < str.length(); i++) {
            if(stack.isEmpty() || stack.peek().ch != str.charAt(i)) {
                stack.push(new Pair(str.charAt(i), 1));
            } else {
                // character is same as previous char hence increment and check the count
                if(++stack.peek().count == k) {
                    stack.pop();
                }
            }
        }
        // collect the chars and return the final string
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            Pair p = stack.pop();
            for(int i=0; i<p.count; i++) {
                sb.append(p.ch);
            }
        }
        return sb.reverse().toString();
    }

    static class Pair {
        char ch;
        int count;
        Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }
}
