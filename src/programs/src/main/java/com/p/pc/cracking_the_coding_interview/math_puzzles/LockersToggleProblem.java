package com.p.pc.cracking_the_coding_interview.math_puzzles;

import java.util.Arrays;

/**
 * There are 100 closed lockers in a hallway. A man begins by opening all 100 lockers.
 * Next, he closes every second locker. Then, on his third pass, he toggles every third locker (closes it if
 * it is open or opens it if it is closed). This process continues for 100 passes, such that on each pass i,
 * the man toggles every ith locker. After his 100th pass in the hallway, in which he toggles only locker
 * #100, how many lockers are open?
 *
 * Approach:
 *  Total number of lockers that will be open at the end will be equal to number of numbers that will result into a perfect square between 1 and 100 (both inclusive)
 *  i.e. 1, 4, 9, 16, 25, 36, 49, 64, 81, 100  => 10 lockers
 */
public class LockersToggleProblem {
    public static void main(String[] args) {
        boolean[] lockers = new boolean[100];
        Arrays.fill(lockers, true); // true indicates open lockers
        toggleLockers(lockers);
        int count = 0;
        for(boolean b : lockers) {
            if(b) count++;
        }
        System.out.println("Open lockers at the end of 100th iteration are: " + count);
    }

    private static void toggleLockers(boolean[] lockers) {

        for(int i = 2; i <= 100; i++) {
            for(int j = 1; i * j < lockers.length; j++) {
                int index = (i * j) - 1;
                if(lockers[index])
                    lockers[index] = false;
                else
                    lockers[index] = true;
            }
        }
    }
}
