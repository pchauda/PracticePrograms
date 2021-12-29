package com.p.pc.facebook;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * <p>Compute the running median of a given integer stream. Median: In case of odd number median is the middle element,
 * else mean of middle two elements</p>

 * <p>Approach:</p>
 *  Idea here is to maintain all lower elements (compared to median) on one side and all higher elements on the other side such
 *  that difference between the lower and higher size is always <= 1.
 *  Then the maximum from the lower elements (using Max heap) and minimum from the higher elements (Min heap) can be compared
 *  for the median. If both halves are equal in size then median = (max from lower + min from higher) / 2, else if
 *  lowers' size is more than higher than return lowers' max as median else highers' min.
 */
public class MedianOfIntegerStream {
    // Max Heap to maintain all smaller elements
    static Queue<Integer> smaller = new PriorityQueue<>((Integer c1, Integer c2) -> c2.compareTo(c1));
    // Min Heap to maintain all larger elements
    static Queue<Integer> greater = new PriorityQueue<>();

    public static void main(String[] args) {
        IntStream range = IntStream.range(1, 101);
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
