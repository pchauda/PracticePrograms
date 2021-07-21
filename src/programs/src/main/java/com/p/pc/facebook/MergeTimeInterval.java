package com.p.pc.facebook;

import java.util.ArrayList;

/**
 * You are given an array (list) of interval pairs as input where each interval has a start and end timestamp.
 * The input array is sorted by starting timestamps. You are required to merge overlapping intervals and return a new output array.
 * Consider the input array below. Intervals (1, 5), (3, 7), (4, 6), (6, 8) are overlapping so they should be merged
 * to one big interval (1, 8). Similarly, intervals (10, 12) and (12, 15) are also overlapping and should be merged to (10, 15)
 *
 * Idea here is to merge the next pair to the previous pair if they are overlapping (startNext <= prevEnd) else collect
 * the prev pair in the output result and update assignment of the prev pair
 */
public class MergeTimeInterval {
    public static void main(String[] args) {
        ArrayList<Pair> intervals = new ArrayList<>();
        intervals.add(new Pair(1,5)); intervals.add(new Pair(3,7)); intervals.add(new Pair(4,6));
        intervals.add(new Pair(9,12)); intervals.add(new Pair(11,15)); intervals.add(new Pair(16,20));
        intervals.add(new Pair(21, 24));
        System.out.println(mergeIntervals(intervals));
        System.out.println(mergeIntervalsWithoutCreatingNewObject(intervals));
        // Output: [Pair{first=1, second=7}, Pair{first=9, second=15}, Pair{first=16, second=20}, Pair{first=21, second=24}]
    }
    // O(n) solution
    static ArrayList<Pair> mergeIntervals(ArrayList<Pair> v) {
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
        ArrayList<Pair> result = new ArrayList<>();
        result.add(v.get(0));

        for(int i = 1 ; i < v.size(); i++) {
            int x1 = v.get(i).first;
            int y1 = v.get(i).second;

            int y2 = result.get(result.size() - 1).second;
            if(y2 >= x1) {
                result.get(result.size() - 1).second = Math.max(y1, y2);
            } else {
                result.add(v.get(i));
            }
        }

        return result;
    }
}
class Pair{
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
}