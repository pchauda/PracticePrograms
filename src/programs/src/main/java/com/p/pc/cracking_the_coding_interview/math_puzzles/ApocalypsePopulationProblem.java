package com.p.pc.cracking_the_coding_interview.math_puzzles;

import java.util.Random;

/**
 * In the new post-apocalyptic world, the world queen is desperately concerned
 * about the birth rate. Therefore, she decrees that all families should ensure that they have one girl or
 * else they face massive fines. If all families abide by this policy-that is, they have continued to have
 * children until they have one girl, at which point they immediately stop-what will the gender ratio
 * of the new generation be? (Assume that the odds of someone having a boy or a girl on any given
 * pregnancy is equal.) Solve this out logically and then write a computer simulation of it.
 *
 * Approach:
 *  Since probability of each kid to be either girl oy boy is 50%. Given a large number of families the gender ratio will still be ~50%.
 */
public class ApocalypsePopulationProblem {
    static ApocalypsePopulationProblem obj = new ApocalypsePopulationProblem();
    static Random random = new Random();

    public static void main(String[] args) {
        int totalFamilies = 10000000;
        double totalGirls = 0, totalBoys = 0;
        for(int i = 0; i < totalFamilies; i++) {
            Pair pair = computeChildren();
            totalBoys += pair.boys;
            totalGirls += pair.girls;
        }
        System.out.println("Gender ratio for girls: " + (totalGirls) / (totalBoys + totalGirls));
        System.out.println("Gender ratio for boys: " + (totalBoys) / (totalBoys + totalGirls));
    }

    private static Pair computeChildren() {
        int girls = 0;
        int boys = 0;
        // Stop as soon as one girl is born
        while(girls < 1) {
            if(random.nextBoolean()) { // assuming true represents a girl being born
                girls++;
            } else {
                boys++;
            }
        }
        return new Pair(boys, girls);
    }

    static class Pair {
        int boys;
        int girls;
        Pair(int boys, int girls) {
            this.boys = boys;
            this.girls = girls;
        }
    }

}
