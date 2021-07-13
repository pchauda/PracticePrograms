import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Heap is a complete binary tree.
 * For a given element i, the left child will be at (2*i + 1) and right child will be
 * at (2*i + 2) position. Similarly for a given node i, the parent node will be at (i-1)/2 position.
 *
 * Using above, an array of inputs can be considered as a complete binary tree and max/min heap can be constructed out of it.
 * For every parent node, heapify method should be invoked to construct a max/min heap.
 *
 * For an array with length n, all nodes starting from index (n/2 - 1) will be parent node and hence need to be heapified.
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] input = new int[]{4, 10, 12, 60, 7, 71, 5};
        System.out.println("Input Array: " + printArray(input));
        heapSortAscending(input);
        System.out.println("Sorted Array using HeapSort: " + printArray(input));
    }

    static void heapSortAscending(int[] array) {
        if(array.length <= 1) return;
        int length = array.length;
        // Construct a max heap starting from the last parent node
        for(int i= (length/2 - 1); i >= 0; i--) {
            heapify(array, length, i);
        }
        // Perform heap short
        for(int i=length - 1; i > 0; i--) {
            // swap the top element with the last element
            swap(array, 0, i);
            // remove the last element from array and invoke heapify on root
            heapify(array, i, 0);
        }
    }

    static void heapify(int[] array, int length, int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int maxIndex = index; // assuming current index has tbe max value

        if(leftChild < length && array[leftChild] > array[maxIndex]) {
            maxIndex = leftChild;
        }
        if(rightChild < length && array[rightChild] > array[maxIndex]) {
            maxIndex = rightChild;
        }
        if(maxIndex != index) {
            swap(array, index, maxIndex);
            // invoke heapify for the max child to ensure heap property is not broken
            heapify(array, length, maxIndex);
        }
    }

    /**
     * Deleting the root from array means reducing the size by 1. Deletion in a tree is always performed by replacing
     * the last item as the root and then heapify action should be performed to ensure heap property
     */
    static int deleteRoot(int[] arr, int currentSize) {
        int lastElement = arr[--currentSize];
        arr[0] = lastElement;
        heapify(arr, currentSize, 0);
        return currentSize;
    }
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static String printArray(int[] array) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        Arrays.stream(array).boxed().forEach(t -> joiner.add(t.toString()));
        return joiner.toString();
    }



}

