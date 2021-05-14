import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ThreeSumProblem {
    public static void main(String args[]) {
        int[] numbers = new int[]{3,2,4,3,5,6,7};
        int[] results = twoSum(numbers, 8);
       // System.out.println(results[0] + " : " + results[1] + " : " + results[2]); // Output=> 1 : 2
        List<Integer> alice = new ArrayList<>(); alice.add(3); alice.add(5); alice.add(4);
        List<Integer> bob = new ArrayList<>(); bob.add(2); bob.add(5); bob.add(5);
        System.out.println(compareTriplets(alice, bob));


    }
    public static int[] threeSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap();
        for(int i=0; i < nums.length; i++) {
            int remainder = target - nums[i];
            //twoSum()
        }
        return new int[0];
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap();
        for(int i=0; i < nums.length; i++) {
            int second = target - nums[i];
            if(numMap.get(second) != null) {
                return new int[]{numMap.get(second), i};
            }
            numMap.put(nums[i], i);
        }
        return null;
    }
    public static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
        int[] retVal = new int [2];
        for(int i=0; i<3; i++) {
            if(a.get(i) > b.get(i))
                retVal[0] += 1;
            else if(a.get(i) < b.get(i))
                retVal[1] += 1;
        }
        return Arrays.stream(retVal).boxed().collect(Collectors.toList());
    }
}
