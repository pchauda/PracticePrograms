package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.*;

/**
 * <p>Given a large text file containing words separated by space and few pair of words, find the shortest distance of the
 * given pair of words in the file. If pair is not found then return -1 for shortest distance. <br/>
 * <p>
 * Example: <br/>
 * File contents = "cat is an animal and so is the dog cat fears dog and dog love its owner and owner loves cat"
 * Given pair of words = (cat, dog), (owner, dog), (cat, cute) <br/>
 * <p>
 * Result: <br/>
 * (cat, dog) => 1  <br/>
 * (owner, dog) => 3    <br/>
 * (cat, cute) => -1
 * </p>
 * Approach: <br/>
 * Iterate through the array of words and store all locations of each word in a hash map. Then for each pair of words,
 * calculate the shorted distance using all locations found.
 */
public class WordsShortestDistance {
    public static void main(String[] args) {
        String fileContent = "cat is an animal and so is the dog cat fears dog and dog love its owner and owner loves cat";
        Pair[] pairs = new Pair[]{
                new Pair("cat", "dog"), new Pair("owner", "dog"), new Pair("cat", "cute")
        };
        String[] words = fileContent.split("\\W");
        System.out.println("Given words: " + Arrays.toString(words));

        PairLocation[] pairLocations = findShortestDistance(words, pairs);
        Arrays.stream(pairLocations).forEach(t -> System.out.println("Pair: " + t.pair + " , shortest distance: " + t.distance()));
    }

    private static PairLocation[] findShortestDistance(String[] words, Pair[] pairs) {
        // create a location map for each word
        Map<String, List<Integer>> wordLocations = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (wordLocations.get(word) == null) {
                List<Integer> locations = new ArrayList<>();
                wordLocations.put(word, locations);
            }
            wordLocations.get(word).add(i);
        }
        List<PairLocation> pairLocations = new ArrayList<>();

        for (Pair pair : pairs) {
            PairLocation best = new PairLocation(pair); // initial location
            List<Integer> locations1 = wordLocations.get(pair.p1);
            List<Integer> locations2 = wordLocations.get(pair.p2);
            // calculate the shortest distance and update distance if shortest found
            if (locations1 != null && locations2 != null) {
                int i = 0, j = 0;
                while (i < locations1.size() && j < locations2.size()) {
                    int loc1 = locations1.get(i);
                    int loc2 = locations2.get(j);
                    best.updateLocationIfShortest(loc1, loc2);
                    if (loc1 < loc2) i++;
                    else j++;
                }
            }
            pairLocations.add(best);
        }
        return pairLocations.toArray(new PairLocation[0]);
    }

    static class Pair {
        String p1, p2;

        Pair(String p1, String p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return Objects.equals(p1, pair.p1) && Objects.equals(p2, pair.p2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(p1, p2);
        }

        @Override
        public String toString() {
            return "(p1= " + p1 + ", p2=" + p2 + ")";
        }
    }

    static class PairLocation {
        Pair pair;
        int loc1, loc2;

        PairLocation(Pair pair) {
            this.pair = pair;
            this.loc1 = -1;
            this.loc2 = -1;
        }

        PairLocation(Pair pair, int loc1, int loc2) {
            this.pair = pair;
            this.loc1 = loc1;
            this.loc2 = loc2;
        }

        PairLocation(int loc1, int loc2) {
            this.loc1 = loc1;
            this.loc2 = loc2;
        }

        boolean isValid() {
            return loc1 >= 0 && loc2 >= 0;
        }

        int distance() {
            if (!isValid()) return -1;
            return Math.abs(loc1 - loc2);
        }

        void updateLocationIfShortest(int loc1, int loc2) {
            if (!isValid() || Math.abs(loc1 - loc2) < this.distance()) {
                this.loc1 = loc1;
                this.loc2 = loc2;
            }
        }
    }
}
