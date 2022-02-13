package com.p.pc.bloomberg;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 */
public class ValidParentheses {
    private static ValidParentheses obj = new ValidParentheses();

    public static void main(String[] args) {
        System.out.println("Valid parentheses? " + isValidParentheses("()(){}[]{{()}}")); // Output: true
        System.out.println("Valid parentheses? " + isValidParentheses("()(){}[]]{{()}}")); // Output: false
    }

    // If an open bracket is encountered then push it to stack. If close bracket is encountered then pop the stack
    // and if the popped value is a machine open bracket then continue else return false. At the end if stack is still
    // not empty then return false as well
    private static boolean isValidParentheses(String str) {
        Stack<Character> stack = new Stack<>();
        // Map containing valid parenthesis pairs (close bracket -> open bracket for faster search)
        Map<Character, Character> mapping = new HashMap<>();
        mapping.put(')', '('); mapping.put('}', '{'); mapping.put(']', '[');

        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if(mapping.containsKey(c)) {
                // check the popped element is of the same type
                char poppedChar = stack.isEmpty() ? '#' : stack.pop(); // if stack empty then just use a garbage char
                if(poppedChar != mapping.get(c))
                    return false;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
