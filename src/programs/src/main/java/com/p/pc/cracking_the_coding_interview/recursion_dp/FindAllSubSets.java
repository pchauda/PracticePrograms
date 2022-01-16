package com.p.pc.cracking_the_coding_interview.recursion_dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Find all subsets of a given set of characters. For simplicity to access the given chars in order use a list.</p>
 *
 * Approach: <br/>
 *  Find sub sets before a given element, clone all subsets and add the current element to it and perform this operation
 *  in a recursive manner.
 *  Since each element has two options, either it will be present in a set or not, for n elements, total number of subsets will be 2^n.
 *  Due to the recursive nature of the algorithm, for each item, all sub sets needs to be explored and hence if there
 *  are n items then total space and time complexity will be O (n * 2^n)
 *
 * Space Time complexity = O(n * 2^n)
 */
public class FindAllSubSets {
    private static FindAllSubSets obj = new FindAllSubSets();

    public static void main(String[] args) {
        ArrayList<Character> allChars = new ArrayList<>();
        allChars.add('a'); allChars.add('b'); allChars.add('c'); allChars.add('d');
        Set<Set<Character>> allSets = obj.getAllSubsets(allChars, 0);
        System.out.println(allSets);
    }

    private Set<Set<Character>> getAllSubsets (ArrayList<Character> allChars, int index) {
        Set<Set<Character>> allSubsets = new HashSet<>();
        // Base case, return empty set when reached the end of the given character set
        if(allChars.size() == index) {
            allSubsets.add(new HashSet<>());
            return allSubsets;
        } else {
            // Recursively find all subsets
            allSubsets = getAllSubsets(allChars, index + 1);
            // Retrieve current element
            Character currElem = allChars.get(index);

            // Clone the sub sets found so far, add the current element to it, collect it in the all sub sets
            Set<Set<Character>> tempSubSets = new HashSet<>();
            for(Set<Character> subSet : allSubsets) {
                Set<Character> newSubSet = new HashSet<>(subSet); // clone the sub set
                newSubSet.add(currElem); // add current element
                tempSubSets.add(newSubSet); // collect the sub set in a temporary sets of sets to avoid concurrent modification
            }
            allSubsets.addAll(tempSubSets);
        }
        return allSubsets;
    }
}
