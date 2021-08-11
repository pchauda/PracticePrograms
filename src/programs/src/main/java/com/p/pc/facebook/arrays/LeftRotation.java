package com.p.pc.facebook.arrays;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LeftRotation {

    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3,4};
        int rotation = 3;
        System.out.println("Original array: " + Arrays.stream(arr).boxed().map(t -> Integer.toString(t)).collect(Collectors.toList()));
        performRotationInPlace(arr, rotation);
        System.out.println("Rotated array (in place) : " + Arrays.stream(arr).boxed().map(t -> Integer.toString(t)).collect(Collectors.toList()));
        performRotationInPlace(arr, 1); // restore original array
        System.out.println("Original array: " + Arrays.stream(arr).boxed().map(t -> Integer.toString(t)).collect(Collectors.toList()));
        arr = performRotation(arr, rotation);
        System.out.println("Rotated array: " + Arrays.stream(arr).boxed().map(t -> Integer.toString(t)).collect(Collectors.toList()));
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
