package com.p.pc.cracking_the_coding_interview.math_puzzles;

import java.util.ArrayList;
import java.util.List;

/**
 * You have 1000 bottles of soda, and exactly one is poisoned. You have 10 test strips which
 * can be used to detect poison. A single drop of poison will turn the test strip positive permanently.
 * You can put any number of drops on a test strip at once and you can reuse a test strip as many times
 * as you'd like (as long as the results are negative). However, you can only run tests once per day, and
 * it takes seven days to return a result. How would you figure out the poisoned bottle in as few days
 * as possible?
 */
public class FindPoisonousBottleProblem {

    public static void main(String[] args) {
        int poisonousBottleNumber = 827;
        List<Bottle> bottles = getBottles(1000, poisonousBottleNumber);
        List<TestStrip> testStrips = getTestStrips(10);

        int bottleId = findThePoisonousBottle(bottles, testStrips);
        System.out.println("Poisonous bottle number is: " + bottleId); // Output should be 827
    }
    // For a given bottle each test strip's result can either be positive or negative.
    // Convert the index of the bottle to its binary and add the test drop to all the strips wherever the binary has 1
    private static int findThePoisonousBottle(List<Bottle> bottles, List<TestStrip> testStrips) {
        // Add a drop from each bottle to a test strip index where ever the binary rep. of the BottleId has 1
        for(int i=0; i<bottles.size(); i++) {
            Bottle bottle = bottles.get(i);
            int id = bottle.id;
            int stripsId = 0;
            while (id > 0) {
                if((id & 1) == 1) // If the binary has 1 on the right most index then add it to that strips
                    testStrips.get(stripsId).addDrop(bottle.poisonous);
                stripsId++; // next strip
                id >>= 1; // Right shift by 1 bit to drop the right most bit and make the next left bit the right most bit
            }
        }
        // Collect the bits from the testStrips and return the Id of the Bottle
        int result = 0; int mul = 1;
        for(int i =0; i<testStrips.size(); i++) {
            if(testStrips.get(i).result)
                result += mul;
            mul *= 2;
        }
        return result;
    }

    private static List<TestStrip> getTestStrips(int count) {
        List<TestStrip> strips = new ArrayList<>(count);
        for(int i=0; i<count; i++) {
            strips.add(new TestStrip());
        }
        return strips;
    }

    private static List<Bottle> getBottles(int count, int poisonousBottleNumber) {
        List<Bottle> bottles = new ArrayList<>(count);
        for(int i=1; i<=count; i++) {
            bottles.add(new Bottle(i));
        }
        // ArrayList index starts with 0 for the first bottle
        bottles.get(poisonousBottleNumber - 1).poisonous = true;
        return bottles;
    }

    static class TestStrip {
        boolean result;
        TestStrip() {}

        public void addDrop(boolean poisonous) {
            if(poisonous)
                result = true;
        }
    }

    static class Bottle {
        int id;
        boolean poisonous;
        Bottle(int id) {this.id = id;}
    }
}

