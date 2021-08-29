package com.p.pc.cracking_the_coding_interview.stacks_queues;

import java.util.Stack;

/**
 * Implement a queue using two stacks
 */
public class QueueUsingTwoStacks {
    public static void main(String[] args) {
        QueueWithStacks<Integer> queue = new QueueWithStacks<>();
        queue.add(1); queue.add(2); queue.add(3); queue.add(4);

        System.out.println("Current queue: " + queue);
        System.out.println("First element from the queue : " + queue.remove()); // Output: 1
        queue.add(5);
        System.out.println("Current queue: " + queue);
        System.out.println("Size of the queue : " + queue.size()); // Output; 4
        System.out.println("Second element from the queue : " + queue.remove()); // Output: 2
    }
    // Queue class using two stacks
    static class QueueWithStacks<Integer> {
        Stack<Integer> forPush = new Stack<>();
        Stack<Integer> forPop = new Stack<>();

        void add(Integer element) {
            forPush.push(element);
        }

        Integer remove() {
            if(forPop.empty()) {
                shiftElements(forPush, forPop);
            }
            return forPop.pop();
        }

        Integer peek() {
            if(forPop.empty()) {
                shiftElements(forPush, forPop);
            }
            return forPop.peek();
        }

        int size() {
            return forPush.size() + forPop.size();
        }

        boolean isEmpty() {
            return forPush.empty() && forPop.empty();
        }

        @Override
        public String toString() {
            return "QueueWithStacks{" +
                    "forPush=" + forPush +
                    ", forPop=" + forPop +
                    '}';
        }

        private void shiftElements(Stack<Integer> forPush, Stack<Integer> forPop) {
            while (!forPush.empty()) {
                forPop.push(forPush.pop());
            }
        }
    }
}
