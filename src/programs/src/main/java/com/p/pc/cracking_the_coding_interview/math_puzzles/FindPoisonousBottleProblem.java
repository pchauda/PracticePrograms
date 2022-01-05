package com.p.pc.cracking_the_coding_interview.math_puzzles;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>You have 1000 bottles of soda, and exactly one is poisoned. You have 10 test strips which
 * can be used to detect poison. A single drop of poison will turn the test strip positive permanently.
 * You can put any number of drops on a test strip at once and you can reuse a test strip as many times
 * as you'd like (as long as the results are negative). However, you can only run tests once per day, and
 * it takes seven days to return a result. How would you figure out the poisoned bottle in as few days
 * as possible?
 * </p>
 * Approach: <br/>
 *  10 test strips can represent 1024 bottles effectively. As each test strip's result can be either positive or negative.
 *  Hence, convert the Id of the bottle to its binary and then add a test drop to all the strips wherever the binary
 *  representation of bottle Id has a 1. Later collect all the strips where result is positive and calculate the bottle
 *  Id accordingly.
 */
public class FindPoisonousBottleProblem {

    public static void main(String[] args) {
        int poisonousBottleNumber = 4;
        List<Bottle> bottles = getBottles(1000, poisonousBottleNumber);
        List<TestStrip> testStrips = getTestStrips(10);

        int bottleId = findThePoisonousBottle(bottles, testStrips);
        System.out.println("Poisonous bottle number is: " + bottleId); // Output should be 827
    }
    // For a given bottle each test strip's result can either be positive or negative.
    //
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
                result += mul; // add mul to the result
            mul <<= 1; // left shift by 1
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

