package com.p.pc.sorting;

/**
 * Algo:
 * 1: Iterate over the array.
 * 2: Compare the current element (key) to its predecessor.
 * 3: If the key element is smaller than its predecessor, compare it to the elements before. Move the greater elements one position up to make space for the swapped element.
 *
 * Insertion sort if faster if value of n <= 8
 * If array is already almost sorted then insertion sort complexity will be O(n).
 * Worst case complexity = O(n2)
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{6, 3, 1, 5, 9, 8};
        SelectionSort.printArray(arr);
        insertionSort(arr);
        System.out.println("Sorted Array: ");
        SelectionSort.printArray(arr);
    }

    private static void insertionSort(int[] arr) {
        for(int i=0; i < arr.length - 1; i++) {
            int key = arr[i];
            int j = i-1;
            while(j>=0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

}
