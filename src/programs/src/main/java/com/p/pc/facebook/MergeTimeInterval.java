package com.p.pc.facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * <p>You are given an array (list) of interval pairs as input where each interval has a start and end timestamp.
 * The input array is sorted by starting timestamps. You are required to merge overlapping intervals and return a new output array.
 * Consider the input array below. Intervals (1, 5), (3, 7), (4, 6), (6, 8) are overlapping so they should be merged
 * to one big interval (1, 8). Similarly, intervals (10, 12) and (12, 15) are also overlapping and should be merged to (10, 15)
 * </p>
 * Approach: <br/>
 * Idea here is to merge the next pair to the previous pair if they are overlapping (startNext <= prevEnd) else collect
 * the prev pair in the output result and update assignment of the prev pair.
 * If not sorted on start time then first sort on the start time and then apply the same approach
 */
public class MergeTimeInterval {
    public static void main(String[] args) {
        ArrayList<Pair> intervals = new ArrayList<>();
        intervals.add(new Pair(9,12)); intervals.add(new Pair(21, 24));
        intervals.add(new Pair(1,5)); intervals.add(new Pair(3,7)); intervals.add(new Pair(4,6));
        intervals.add(new Pair(11,15)); intervals.add(new Pair(16,20));

        System.out.println(mergeIntervals(intervals));
        System.out.println(mergeIntervalsWithoutCreatingNewObject(intervals));
        // Output: [Pair{first=1, second=7}, Pair{first=9, second=15}, Pair{first=16, second=20}, Pair{first=21, second=24}]
    }
    // O(n) solution
    static ArrayList<Pair> mergeIntervals(ArrayList<Pair> v) {
        Collections.sort(v, Comparator.comparingInt(Pair::getFirst).thenComparingInt(Pair::getSecond));
        ArrayList<Pair> result = new ArrayList<>();
        Pair prev = null;
        for(Pair p : v) {
            if(prev == null) {
                prev = p;
            } else if (p.first <= prev.second) {
                prev = new Pair(prev.first, Math.max(prev.second, p.second));
            } else {
                result.add(prev);
                prev = p;
            }
        }
        if(prev != null) result.add(prev);
        return result;
    }

    static ArrayList<Pair> mergeIntervalsWithoutCreatingNewObject(ArrayList<Pair> v) {
        if(v == null || v.size() == 0) {
            return null;
        }
        Collections.sort(v, Comparator.comparingInt(Pair::getFirst).thenComparingInt(Pair::getSecond));
        ArrayList<Pair> result = new ArrayList<>();
        result.add(v.get(0));

        for(int i = 1 ; i < v.size(); i++) {
            Pair current = v.get(i);
            Pair prev = result.get(result.size() - 1);

            if(prev.second >= current.first) {
                prev.second = Math.max(current.second, prev.second);
            } else {
                result.add(current);
            }
        }

        return result;
    }

    static class Pair{
        public int first;
        public int second;

        public Pair(int x, int y){
            this.first = x;
            this.second = y;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }
    }
}
