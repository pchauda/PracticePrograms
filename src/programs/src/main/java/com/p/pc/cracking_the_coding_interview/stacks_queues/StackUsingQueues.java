package com.p.pc.cracking_the_coding_interview.stacks_queues;

import java.util.LinkedList;
import java.util.Queue;

/**
 * We are given a Queue data structure that supports standard operations like enqueue() and dequeue().
 * We need to implement a Stack data structure using only instances of Queue and queue operations allowed on the instances.
 */
public class StackUsingQueues {

    public static void main(String[] args) {
        MyStack<String> stack = new StackUsingQueues().new MyStack();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println(stack.pop()); // c
        System.out.println(stack.pop()); // b
        stack.push("d");
        stack.push("e");
        System.out.println(stack.pop()); // e
        System.out.println(stack.pop()); // d
        System.out.println(stack.pop()); // a
        System.out.println(stack.pop()); // null

    }

    // This implementation makes push operation costly
    class MyStack<T> {
        Queue<T> main = new LinkedList<>();
        Queue<T> temp = new LinkedList<>();

        // first add to temp queue, then move all elements from main to temp and then swap the queues
        // O(n)
        void push(T e) {
            temp.add(e);

            while(!main.isEmpty()) {
                temp.add(main.poll());
            }

            Queue<T> temp = main;
            main = this.temp;
            this.temp = temp;
        }

        // always pop from main queue
        // O(1)
        T pop() {
            return main.poll();
        }
    }

}

