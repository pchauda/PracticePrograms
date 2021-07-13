import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumProblem {
    public static void main(String args[]) {
        int[] numbers = new int[]{3,2,4};
        int[] results = twoSum(numbers, 6);
        System.out.println(results[0] + " : " + results[1]); // Output=> 1 : 2

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
        throw new IllegalArgumentException("No results found");
    }
}
