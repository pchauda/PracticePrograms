package com.p.pc.geekforgeeks.array_stack;

import java.util.Stack;

/**
 * <p>Given a string consisting of opening and closing parenthesis, find the length of the longest valid parenthesis
 * substring.</p>
 * <p>
 * Approach: <br/>
 * Use Stack to push open bracket's index and when a close bracket is encountered then pop the stack and calculate
 * the max length. If stack is empty then just push the index of close bracket to start counting from that index
 * effectively.
 *
 * Time Complexity: O(n)
 */
public class LongestValidParenthesisString {
    private static LongestValidParenthesisString obj = new LongestValidParenthesisString();

    public static void main(String[] args) {
        String str = "((()))()()))()()())";
        System.out.println("Length of longest substring having valid parenthesis is: " + obj.calculateLength(str));
    }

    private int calculateLength(String str) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // push -1 to the stack so that stack is not empty for "()" string
        int maxLength = Integer.MIN_VALUE;
        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if(c == '(') {
                stack.push(i);
            } else {
                // Encountered a close bracket, pop the stack top and calculate the length using the stack next top
                if(!stack.empty())
                    stack.pop(); // remove a open bracket
                // if stack is still not empty then that means an open bracket has been removed and hence calculate
                // the length using remaining stack top
                if(!stack.empty()) {
                    int tempMax = i - stack.peek();
                    if(tempMax > maxLength) maxLength = tempMax;
                } else {
                    // if stack is empty then push the current index as base for next valid substring (if any)
                    stack.push(i);
                }
            }
        }
        return maxLength;
    }
}
