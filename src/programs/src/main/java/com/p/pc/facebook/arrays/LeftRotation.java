package com.p.pc.facebook.arrays;

import java.util.Arrays;

/**
 * Given an array and total number of left rotations, print the final array after completing all rotations.
 */
public class LeftRotation {

    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3,4};
        int rotation = 3;
        System.out.println("Original array: " + Arrays.toString(arr));
        performRotationInPlace(arr, rotation);
        System.out.println("Rotated array (in place) : " + Arrays.toString(arr));
        performRotationInPlace(arr, 1); // restore original array
        System.out.println("Original array: " + Arrays.toString(arr));
        arr = performRotation(arr, rotation);
        System.out.println("Rotated array: " + Arrays.toString(arr));
    }

    private static int[] performRotation(int[] arr, int rotation) {
        int length = arr.length;
        int actualRotation = rotation % length;
        if (actualRotation == 0) return arr;
        int[] retVal = new int[length];
        for(int i=0; i<length; i++) {
            int newIndex = ((length - rotation) + i) % length;
            retVal[newIndex] = arr[i];
        }
        return retVal;
    }

    private static void performRotationInPlace(int[] arr, int rotation) {
        int length = arr.length;
        int actualRotation = rotation % length;
        if (actualRotation == 0) return;
        // Copy the elements to be moved to left into a temp array
        int[] tmp = new int[actualRotation];
        for(int i=0; i < actualRotation; i++) {
            tmp[i] = arr[i];
        }
        // shift the array
        for(int i=actualRotation; i < length; i++) {
            arr[i - actualRotation] = arr[i];
        }
        // append the values from temp array
        for(int i=0; i < actualRotation; i++) {
            arr[i + length - actualRotation] = tmp[i];
        }
    }
}
