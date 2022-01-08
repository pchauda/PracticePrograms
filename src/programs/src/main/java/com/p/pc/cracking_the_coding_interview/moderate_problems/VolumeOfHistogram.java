package com.p.pc.cracking_the_coding_interview.moderate_problems;

/**
 * <p>Imagine a histogram (bar graph). Design an algorithm to compute the volume of water it could hold if someone poured
 * water across the top. You can assume that each histogram bar has width 1. <br/>
 * <p>
 * EXAMPLE <br/>
 * Input: {0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 5, 0, 1, 0, 0, 0} <br/>
 * Output: 26
 * </p>
 * Approach: <br/>
 * The volume of water in a particular area is determined by the tallest bar to the left and to the right (specifically,
 * by the shorter of the two tallest bars on the left and the tallest bar on the right). For
 * example, water fills in the area between the bar with height 6 and the bar with height 8, up to a height of 6.
 * It's the second tallest, therefore, that determines the height.
 * The total volume of water is the volume of water above each histogram bar. Can we efficiently compute
 * how much water is above each histogram bar?
 * Yes. if we are able to precompute the height of the tallest bar on the left and right of each
 * index then the minimums of these will indicate the "water level" at a bar. The difference between the water level
 * and the height of this bar will be the volume of water.
 */
public class VolumeOfHistogram {
    private static VolumeOfHistogram obj = new VolumeOfHistogram();

    public static void main(String[] args) {
        int[] histogram = new int[]{0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 5, 0, 1, 0, 0, 0};
        System.out.println("Volume of histogram: " + obj.calculateVolume(histogram)); // Output: 26
    }

    /**
     * Idea here is to calculate the left max and right max for every histogram index. The volume for a given index
     * will be controlled by the minimum of left max and right max. Finally, the volume will be reduced by the histogram
     * height at that index. Follow this logic to calculate the volume.
     *
     * @param histogram
     * @return volume of the histogram
     */
    private int calculateVolume(int[] histogram) {
        // calculate left max
        int[] leftMax = new int[histogram.length];
        leftMax[0] = histogram[0];
        for (int i = 1; i < histogram.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], histogram[i]);
        }

        // calculate the right max and subsequently volume/area using Min (left max, right max) and histogram height
        int sum = 0;
        int rightMax = histogram[histogram.length - 1];
        for (int i = histogram.length - 1; i >= 0; i--) {
            rightMax = Math.max(histogram[i], rightMax);
            // Minimum of left and right max
            int minOfLeftAndRightMax = Math.min(leftMax[i], rightMax);
            // Calculate the volume/area using the minimum and histogram height at this index
            if (minOfLeftAndRightMax > histogram[i]) {
                sum += (minOfLeftAndRightMax - histogram[i]);
            }
        }
        return sum;
    }
}
