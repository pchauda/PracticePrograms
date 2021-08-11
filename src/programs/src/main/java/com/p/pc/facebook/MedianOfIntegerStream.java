package com.p.pc.facebook;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * Compute the running median of a integer stream.
 *
 * Explain median: In case of odd number median is the middle element, else mean of middle two elements
 *
 * Approach:
 * Maintain two heaps such that smaller number compared to median are in max heap and greater number are in min heap.
 * Then if size of the both the heaps is same then return sum (top of max heap + top of min heap)/2 else return the top
 * element of the heap having greater size.
 *
 */
public class MedianOfIntegerStream {
    // Max Heap to maintain all smaller elements
    static Queue<Integer> smaller = new PriorityQueue<>((Integer c1, Integer c2) -> c2.compareTo(c1));
    // Min Heap to maintain all larger elements
    static Queue<Integer> greater = new PriorityQueue<>();

    public static void main(String[] args) {
        IntStream range = IntStream.range(1, 9);
        range.forEach(i -> {
            addNumber(i);
            System.out.println("Number : " + i + ", Median:" + getMedian());
        });
        System.out.println("All smaller numbers from Max Heap: " + smaller);
        System.out.println("All greater numbers from Min Heap: " + greater);
    }

    static void addNumber(int i) {
        if(smaller.isEmpty() || i < smaller.peek()) {
            smaller.add(i);
            if(smaller.size() > greater.size() + 1) {
                greater.add(smaller.poll());
            }
        } else {
            greater.add(i);
            if(greater.size() > smaller.size() + 1) {
                smaller.add(greater.poll());
            }
        }
    }

    static double getMedian() {
        if(smaller.size() > greater.size()) return smaller.peek();
        else if(smaller.size() < greater.size()) return greater.peek();
        else {
            return (smaller.peek() + greater.peek()) / 2.0;
        }
    }
}
