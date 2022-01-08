package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.Arrays;
import java.util.Random;

/**
 * <p>Write a method to shuffle a deck of cards. It must be a perfect shuffle-in other words, each
 * of the 52! permutations of the deck has to be equally likely. Assume that you are given a random
 * number generator which is perfect.</p>
 *
 * Approach: <br/>
 *  Let's start with a base case, if we have shuffled n - 1 card and nth card needs to be shuffled into the deck,
 *  then swap the nth card with any random card from the deck. Follow this process iteratively or recursively to have a
 *  shuffled deck.
 */
public class ShuffleCards {
    static Random r = new Random();

    public static void main(String[] args) {
        int[] deck = new int[52];
        for(int i=0; i < deck.length; i++) {
            deck[i] = i;
        }
        System.out.println("Initial deck: " + Arrays.toString(deck));
        // Shuffle deck 100 times
        for(int i=0; i<100; i++) {
            int[] tmp = Arrays.copyOf(deck, deck.length);
            shuffleDeck(tmp);
            System.out.println("Shuffled deck: " + Arrays.toString(tmp));
        }
    }
    // Since random number is being used for getting the index to be swapped, all cards have equal probability.
    private static void shuffleDeck(int[] deck) {
        for(int i=0; i<deck.length; i++) {
            // generate a random number in the range of 0 to i (inclusive) and swap ith element with this element
            int k = r.nextInt(i+1);
            // swap cards in the deck
            int tmp = deck[i];
            deck[i] = deck[k];
            deck[k] = tmp;
        }
    }
}
