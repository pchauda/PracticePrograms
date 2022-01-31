package com.p.pc.facebook;

import java.util.LinkedList;

/**
 * Given a histogram in a form of an array where every value in the array represents the height of the histogram and
 * width being 1 for all, calculate the max area of the rectangle.
 * <p>
 * A brute approach is to calculate the area at each bar of the histogram from the first bar, it will be O(n^2) solution.
 * <p>
 * Approach: <br/>
 * Basically, we need to find out the left index and right index limit for each bar to calculate the area for that bar.
 * A O(n) solution can be achieved with the help of stacks. Push the bars to stack if the next bar is greater than or
 * equal to the previous bar. If the next bar is smaller, then pop out the top bar from stack, calculate the area and repeat.
 * Once we pop a bar out, the area can be calculated as height_of_the_popped_bar * width (right index - 1 - left index which is top index of the stack).
 * We can apply this approach as we know for sure that the left index value is smaller than the current index and bar at
 * ith index is smaller than the popped bar and in between we would have had larger bars only and hence the popped bar
 * height can be considered as smallest height.
 */
public class HistogramMaxArea {
    public static void main(String[] args) {
        int[] hist = new int[]{1, 2, 3, 2, 3, 1};
        System.out.println(String.format("Max area : %d", calculateMaxArea(hist))); // Output: 8
    }

    /**
     * Calculate the max area using stack where every new encountered bar having height more than the previous bar
     * will be pushed to stack. And for every new bar having smaller height, pop the top bar and calculate the area.
     */
    private static int calculateMaxArea(int[] hist) {
        if (hist == null) return 0;
        if (hist.length == 1) return hist[0];
        // stack to store the previous encountered bar of greater or equal heights
        LinkedList<Integer> stack = new LinkedList<>();
        int maxArea = 0, area = 0;
        int i = 0;
        // iterate over the array and either push to stack or pop from stack and calculate area
        while (i < hist.length) {
            if (stack.isEmpty() || hist[i] >= hist[stack.peek()]) {
                stack.push(i); // push the index of the element this will be used to calculate the left index
                i++;
            } else {
                int topIndex = stack.pop();
                // area = bar height * (right index - left index) after considering if stack is empty or not
                area = hist[topIndex] * (stack.peek() == null ? i : i - stack.peek() - 1);
                if (area > maxArea) maxArea = area;
            }
        }
        // if we reach the end of array and stack is not empty then pop the elements and calculate area
        // i can be considered as right most index for all values in the stack
        while (!stack.isEmpty()) {
            int topIndex = stack.pop();
            // area = bar height * (right index - left index) after considering if stack is empty or not
            area = hist[topIndex] * (stack.peek() == null ? i : i - stack.peek() - 1);
            if (area > maxArea) maxArea = area;
        }
        return maxArea;
    }
}
