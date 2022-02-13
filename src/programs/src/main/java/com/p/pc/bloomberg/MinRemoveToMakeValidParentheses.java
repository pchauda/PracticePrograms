package com.p.pc.bloomberg;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 * <p>
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting
 * parentheses string is valid and return any valid string.
 * <p>
 * Formally, a parentheses string is valid if and only if:
 * <p>
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 *
 * Approach:
 * Use stack to keep track of open brackets, and a separate Set to maintain the indexes to remove.
 */
public class MinRemoveToMakeValidParentheses {

    public static void main(String[] args) {
        System.out.println("Valid String: " + minRemoveToMakeValidParentheses("a)((()(b(c)d"));
        System.out.println("Valid String: " + minRemoveToMakeValidParentheses(")(("));
    }

    private static String minRemoveToMakeValidParentheses(String str) {
        Set<Integer> indexesToRemove = new HashSet<>(); // set to keep track of indexes to remove for O(1) lookup

        Stack<Integer> stack = new Stack<>(); // stack to keep track of brackets
        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if(c == '(') {
                stack.push(i);
            } else if(c == ')') {
                // If stack is empty then extra closing bracket found and hence add the index to be removed otherwise pop the stack
                if(stack.empty()) indexesToRemove.add(i);
                else stack.pop();
            }
        }
        // remove each extra bracket on the stack
        while(!stack.empty()) indexesToRemove.add(stack.pop());
        System.out.println("Total chars to remove: " + indexesToRemove.size());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<str.length(); i++) {
            if(!indexesToRemove.contains(i)) {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }
}
