package com.p.pc.cracking_the_coding_interview.stacks_queues;

import java.util.Stack;

/**
 * Sort a given stack only by using just one another stack. Can't use any other data structure like arrays, queue etc.
 *
 * Approach:
 *  Pop the element from one stack and push it to another stack at its correct position. Keep another stack always sorted.
 *  To find the correct position on another stack, pop the elements from another stack and put it on the given stack till
 *  the correct position is found.
 *
 *  Time complexity: O(n^2)
 *  Space complexity: O(n) due to another stack
 */
public class SortAStackUsingOnlyAnotherStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(5); stack.push(1); stack.push(6); stack.push(9); stack.push(2); stack.push(4);
        System.out.println("Current stack: " + stack);
        sortStack(stack);
        System.out.println("Sorted stack: " + stack);
    }

    private static void sortStack(Stack<Integer> stack) {
        Stack<Integer> temp = new Stack<>();
        while(!stack.empty()) {
            Integer top = stack.pop();
            while(!temp.empty() && temp.peek() > top) {
                stack.push(temp.pop());
            }
            temp.push(top);
        }
        while(!temp.empty()) {
            stack.push(temp.pop());
        }
    }
}

