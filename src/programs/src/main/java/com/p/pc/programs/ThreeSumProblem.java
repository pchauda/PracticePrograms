package com.p.pc.programs;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ThreeSumProblem {
    public static void main(String args[]) {
        int[] numbers = new int[]{3, 2, 4, 3, 5, 6, 7};
        int[] results = threeSum(numbers, 16);
        System.out.println(results[0] + " : " + results[1] + " : " + results[2]); // Output=> 0: 5 : 6
    }

    public static int[] threeSum(int[] nums, int target) {
        for(int i=0; i < nums.length; i++) {
            int remainder = target - nums[i];
            int[] twoSums = twoSum(nums, remainder, i);
            if(twoSums != null){
                return new int[] {i, twoSums[0], twoSums[1]};
            }
        }
        return null;
    }

    public static int[] twoSum(int[] nums, int target, int currentIndex) {
        Map<Integer, Integer> numMap = new HashMap();
        for(int i=0; i < nums.length; i++) {
            if(i != currentIndex) {
                int second = target - nums[i];
                if(numMap.get(second) != null) {
                    return new int[]{numMap.get(second), i};
                }
                numMap.put(nums[i], i);
            }
        }
        return null;
    }
}
