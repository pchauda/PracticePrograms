package com.p.pc.google;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Given a list of blocks having various amenities, find out the block having minimum distance considering all required
 * amenities. <br/>
 *
 * Example:
 * Required Amenities = {"School", "Gym", "Market"}
 * Blocks = {
             {"School": true, "Gym": false, "Market": false },
             {"School": false, "Gym": true, "Market": false },
             {"School": false, "Gym": false, "Market": false },
             {"School": true, "Gym": false, "Market": true }
            }
    Output: Block = 2 (School and Market on the next block, Gym one block before)
 <p/>
 * Approach: <br/>
 *  Use sliding window approach and compute the optimal block using start and end pointers.
 *
 * Time complexity: O(b * n), where b is the number of blocks and n is the average number of amenities across all blocks
 * Space complexity: O(a), where a is the number of amenities
 */
public class BlockWithMinAmenitiesDistance {

    public static void main(String[] args) {
        Set<String> requiredAmenities = Stream.of("School", "Gym", "Market").collect(Collectors.toSet());
        List<Map<String, Boolean>> blocks = getBlocks();
        System.out.println("Required amenities: " + requiredAmenities);
        System.out.println("Given blocks: " + blocks);
        int block = findOptimalBlock(blocks, requiredAmenities);
        System.out.println("Optimal block having minimum distance from all required amenities: " + block); // Output: 2
    }

    /**
     * Maintain a count of all amenities seen so far by iterating over the blocks. Keep low and high pointer such that
     * low starts from first block and high refers to current block being analyzed. As soon as all required amenities
     * are found then calculate the window size and corresponding median if window size is the minimum found so far.
     * Then increase the low pointer to the next block and check again if all amenities are still found and so on till
     * all amenities are not found. Then move on the next block using high pointer.
     */
    private static int findOptimalBlock(List<Map<String, Boolean>> blocks, Set<String> requiredAmenities) {
        int block = -1, minWindowSize = Integer.MAX_VALUE;

        Map<String, Integer> amenitiesFound = new HashMap<>();
        for(int low=0, high=0; high < blocks.size(); high++) {
            visitBlock(blocks.get(high), requiredAmenities, amenitiesFound);
            while(requiredAmenities.size() == amenitiesFound.size() && low <= high) {
                int windowSize = high - low;
                if(windowSize < minWindowSize) {
                    minWindowSize = windowSize;
                    block = (high + low) / 2;
                    if(minWindowSize == 0) return block; // break early if minWindowSize == 0 i.e. all amenities are available on the same block
                }
                // remove the block at low pointer and move the low pointer
                unvisitBlock(blocks.get(low++), requiredAmenities, amenitiesFound);
            }
        }
        return block;
    }

    private static void visitBlock(Map<String, Boolean> block, Set<String> requiredAmenities, Map<String, Integer> amenitiesFound) {
        block.entrySet().stream().filter(t -> t.getValue() && requiredAmenities.contains(t.getKey())).forEach(t -> {
            amenitiesFound.compute(t.getKey(), (k, v) -> v == null ? 1 : v + 1);
        });
    }

    private static void unvisitBlock(Map<String, Boolean> block, Set<String> requiredAmenities, Map<String, Integer> amenitiesFound) {
        block.entrySet().stream().filter(t -> t.getValue() && requiredAmenities.contains(t.getKey())).forEach(t -> {
            amenitiesFound.compute(t.getKey(), (k, v) -> v == null ? 0 : v - 1);
            amenitiesFound.remove(t.getKey(), 0); // remove the amenity if count is zero
        });
    }

    private static List<Map<String, Boolean>> getBlocks() {
        List<Map<String, Boolean>> blocks = new ArrayList<>();

        Map<String, Boolean> b1 = new HashMap<>();
        b1.put("School", true); b1.put("Gym", false); b1.put("Market", false);
        blocks.add(b1);

        Map<String, Boolean> b2 = new HashMap<>();
        b2.put("School", false); b2.put("Gym", true); b2.put("Market", false);
        blocks.add(b2);

        Map<String, Boolean> b3 = new HashMap<>();
        b3.put("School", false); b3.put("Gym", false); b3.put("Market", false);
        blocks.add(b3);

        Map<String, Boolean> b4 = new HashMap<>();
        b4.put("School", true); b4.put("Gym", false); b4.put("Market", true);
        blocks.add(b4);

        return blocks;
    }
}
