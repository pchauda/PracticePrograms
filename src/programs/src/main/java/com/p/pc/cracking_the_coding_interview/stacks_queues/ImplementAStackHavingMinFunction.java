package com.p.pc.cracking_the_coding_interview.stacks_queues;

import java.util.Stack;

/**
 * Implement a stack that supports min function along with push and pop. All these operations should be O(1) complexity.
 */
public class ImplementAStackHavingMinFunction {
    public static void main(String[] args) {
        StackWithMin stack = new StackWithMin();
        System.out.println("Current Stack: " + stack);
        System.out.println("Min value : " + (stack.min() != null ? stack.min() : null));
        stack.push(4); stack.push(1); stack.push(15); stack.push(5);
        stack.push(0); stack.push(-1);
        System.out.println("Current Stack: " + stack);
        System.out.println("Min value : " + (stack.min() != null ? stack.min() : null));
        stack.pop(); stack.pop();
        System.out.println("Current Stack after pop twice: " + stack);
        System.out.println("Min value : " + (stack.min() != null ? stack.min() : null));
    }


    /**
     * Class supporting a min function. Min should return null if the stack is empty
     *
     * Approach:
     *  Maintain a separate stack to keep track of minimum elements any time a new element is added or removed
     *  from the main stack.
     */
    static class StackWithMin extends Stack<Integer> {
        Stack<Integer> minStack = new Stack<>();

        public Integer push(Integer element) {
            if(super.empty() || minStack.empty() || element <= minStack.peek()) {
                minStack.push(element);
            }
            return super.push(element);
        }

        public Integer pop() {
            Integer element = super.pop();
            if(element != null) {
                if(element <= minStack.peek()) {
                    minStack.pop();
                }
            }
            return element;
        }

        public Integer min() {
            if(minStack.empty() || super.empty()) {
                return null;
            } else return minStack.peek();
        }

    }
}
