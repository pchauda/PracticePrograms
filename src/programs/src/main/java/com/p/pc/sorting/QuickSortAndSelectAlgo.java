package com.p.pc.sorting;

import java.util.Arrays;
import java.util.StringJoiner;

public class QuickSortAndSelectAlgo {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 10, 5, 9, -12, -20, 11}; // Output: -20, -12, 2, 3, 5, 9, 10, 11
        quickSort(arr, 0, arr.length-1);
        StringJoiner s = new StringJoiner(",");
        Arrays.stream(arr).forEach(i -> s.add(Integer.toString(i)));
        System.out.println(s);
        System.out.println(kthSmallest(arr, 7)); // Output: 10
    }

    static void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }

    static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int i,j;
        i = j = start;
        while(i < end) {
            if(arr[i] < pivot) {
                if(i != j)
                    swap(arr, i, j); // this swaps ensures, all elements smaller than the pivot are on the left
                j++;
            }
            i++;
        }
        swap(arr, j, end);
        return j;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    // Find Kth smallest element in the array
    static int kthSmallest(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int pIdx = partition(arr, left, right);
            if(pIdx == k - 1) return arr[pIdx];
            else if(pIdx > k - 1) right = pIdx - 1;
            else left = pIdx + 1;
        }
        return Integer.MIN_VALUE;
    }
}
