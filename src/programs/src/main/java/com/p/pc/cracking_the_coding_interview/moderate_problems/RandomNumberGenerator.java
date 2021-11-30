package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Implement a method rand7 () given rand5(). That is, given a method that
 * generates a random number between O and 4 (inclusive), write a method that generates a random
 * number between O and 6 (inclusive).
 */
public class RandomNumberGenerator {
    static final RandomNumberGenerator obj = new RandomNumberGenerator();

    public static void main(String[] args) {
        Map<Integer, Integer> numCount = new HashMap<>();
        int i = 1400000;
        while(i > 0) {
            int num = obj.rand7();
            numCount.compute(num, (k, v) -> v != null ? v + 1 : 1);
            i--;
        }
        System.out.println(numCount);
    }

    /**
     * This function must use rand5() function to generate a random number between 0 and 6 (inclusive).
     * All numbers should have equal distribution.
     *
     * @return random number generated between 0 and 6 (inclusive)
     */
    int rand7() {
        while(true) {
            int num = 5 * rand5() + rand5();
            if(num < 21) { // discard numbers between 21 and 24 to avoid unfair weights for 0, 1, 2, 3
                return num % 7;
            }
        }
    }

    // given random function that generated values from 0 to 4
    Random r = new Random();
    int rand5() {
        return r.nextInt(5);
    }
}
