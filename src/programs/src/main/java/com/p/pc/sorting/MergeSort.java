package com.p.pc.sorting;

import java.util.Arrays;
import java.util.StringJoiner;

public class MergeSort {

    public static void main(String[] args) {
        int[] array = new int[]{4, 1, 3, 5, 9, 8, 7};
        System.out.println(printArray(array));
        mergeSort(array, 0, array.length-1);
        System.out.println("Sorted Array:");
        System.out.println(printArray(array));
    }

    static void mergeSort(int[] array, int start, int end) {
        if(start < end) {
            int mid = (start + end)/2;
            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);
            merge(array, start, end, mid);
        }
    }

    static void merge(int[] array, int start, int end, int mid) {
        int l = mid - start + 1;
        int r = end - mid;

        int[] L = new int[l];
        int[] R = new int[r];
        // copy elements to left array
        for(int i=0; i < l; i++) {
            L[i] = array[start + i];
        }
        // copy elements to right array
        for(int i=0; i < r; i++) {
            R[i] = array[i + (mid + 1)];
        }
        int i=0,j=0,k=start;
        while(i < l && j < r) {
            if(L[i] <= R[j]) { // Equals condition here is important for retaining the stable-sort nature of merge sort
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }
        while(i < l) {
            array[k] = L[i];
            i++;
            k++;
        }
        while(j < r) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    static String printArray(int[] array) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        Arrays.stream(array).boxed().forEach(t -> joiner.add(t.toString()));
        return joiner.toString();
    }
}
