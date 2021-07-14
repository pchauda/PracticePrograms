package com.p.pc.sorting;

/**
 * Each iteration of the bubble sort will place the max/min at the end.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{6, -3, 1, 5, 9, 8, -10, 4, 56, 4, 56, 46, -10};
        SelectionSort.printArray(arr);
        bubbleSort(arr);
        System.out.println("Sorted Array:");
        SelectionSort.printArray(arr);
    }

    private static void bubbleSort(int[] arr) {
        for(int i=0; i < arr.length - 1; i++) {
            boolean swapHappened = false;
            // Last element will always be in its sorted position, next next loop will be from 0 to (length - 1 - i)
            for(int j=0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapHappened = true;
                }
            }
            if(!swapHappened) break;
        }
    }
}
