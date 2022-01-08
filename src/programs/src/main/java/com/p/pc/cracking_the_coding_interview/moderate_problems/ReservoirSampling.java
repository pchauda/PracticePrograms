package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.Arrays;
import java.util.Random;

/**
 * <p>Randomly choose K samples from a list of N items. This problem can also be seen as : Write a method to randomly
 * generate a set of m integers from an array of size n. Each element must have equal probability of being chosen.
 *</p>
 * Approach: <br/>
 *  Copy over first K items from the list of N items directly. Then for each i th item from the list of remaining N-K items
 *  find a random index from 0 to ith item, and if random index is less than K then replace the random index value with i th item.
 *
 *  To understand the probability here, let's consider below two cases:
 *  Case 1. Probability of any item from first K items to remain in the final list is =>
 *  -- this item not picked up for K+1 th item, this item not picked up for K+2 th item and so on which is equivalent to:
 *  (1 - 1/(K+1)) * (1 - 1/(K+2) * ..... (1 - 1/N)
 *  K/(K+1) * (K+1)/(K+2)...... * (N-1)/N => K/N -- Probability of any item from the first K item to remain in the final list
 *
 *  Case 2. Probability of any item from remaining N-K items to be in the final list is =>
 *  -- Probability of N th item to be in the final list = K/N (probability of selecting any item from first K item out of total N items)
 *  -- Probability of (N-1) th item to be in the final list =
 *      Probability of selecting any item from first K items out of total N-1 items * Probability that the index picked in iteration for N is not same as index picked for iteration N-1
 *    => (K/(N-1) * (N-1)/N => K/N
 *
 *  Hence, by considering both these cases, it is evident that all elements have equal probability to appear in the final list.
 */
public class ReservoirSampling {

    public static void main(String[] args) {
        int[] stream = new int[1000];
        for(int i =0; i<stream.length; i++) {
            stream[i] = i;
        }
        // Randomly select first 10 elements from the given 1000 elements and do this 100 times
        for(int i=0; i<100; i++) {
            int[] selectedKItems = selectFirstKElements(stream, 10);
            System.out.println(Arrays.toString(selectedKItems));
        }
    }

    static Random random = new Random();

    private static int[] selectFirstKElements(int[] stream, int k) {
        int[] result = new int[k];
        // copy first k elements from the stream
        for(int i=0; i<k; i++) {
            result[i] = stream[i];
        }
        // for each remaining element in the stream try to see if it can be swapped with existing K elements randomly
        for(int i=k; i<stream.length; i++) {
            int idx = random.nextInt(i + 1); // from 0 to i (inclusive)
            if(idx < k) {
                result[idx] = stream[i];
            }
        }
        return result;
    }
}
