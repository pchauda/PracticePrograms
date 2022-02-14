package com.p.pc.bloomberg;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <b>Problem Statement</b><br/>
 * Given an array of meeting time intervals where intervals[i] = [starti, endi], return the minimum number of conference
 * rooms required.
 * <p>
 * Example 1: <br/>
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 * <p>
 * Example 2:
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 * <p>
 * <b>Approach</b>
 * Sort the input data by meeting start times, then iterate over the meetings while maintaining a priority queue
 * (sorted by meeting end time) for conference room requirements.
 * <p>
 * <b>Companies: Bloomberg, Facebook, Amazon, Microsoft, Oracle</b>
 */
public class MeetingRooms {
    public static void main(String[] args) {
        MeetingRooms obj = new MeetingRooms();

        int[][] intervals = new int[][] {
                // {start time, end time}
                {0, 30}, {15, 25}, {20, 25}, {25, 30}, {5, 10}
        };

        System.out.println(obj.findMinimumRooms(intervals));
        System.out.println(obj.findMinimumRooms2(intervals));
    }

    private int findMinimumRooms(int[][] intervals) {
        if(intervals.length == 0) return 0;
        // sort the intervals using meeting start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        // maintain a priority queue using meeting end time. Size of this queue will represent total rooms required
        // having room, to get free at the earliest, at the top of the queue.
        PriorityQueue<Integer> pq = new PriorityQueue<>(intervals.length);
        pq.add(intervals[0][1]); // add the end time for first interval
        for(int i=1; i<intervals.length; i++) {
            // if the start time of the meeting starts on/after the end time of the top of the priority queue
            // then remove the room from the queue and add another room with end time of current meeting.
            if(intervals[i][0] >= pq.peek()) {
                pq.poll();
            }
            pq.add(intervals[i][1]);
        }
        return pq.size();
    }

    private int findMinimumRooms2(int[][] intervals) {
        if (intervals.length == 0) return 0;

        Integer[] start = new Integer[intervals.length];
        Integer[] end = new Integer[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        // Sort the intervals by start and end time
        Arrays.sort(start);
        Arrays.sort(end);

        // The two pointers in the algorithm: e_ptr and s_ptr.
        int startPointer = 0, endPointer = 0;
        int rooms = 0;

        // Iterate over intervals.
        while (startPointer < intervals.length) {
            // If there is a meeting that has ended by the time the meeting at `start_pointer` starts
            if (start[startPointer] >= end[endPointer]) {
                rooms--;
                endPointer++;
            }
            rooms++;
            startPointer++;
        }
        return rooms;
    }
}
