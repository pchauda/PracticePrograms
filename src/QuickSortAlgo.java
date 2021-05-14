import java.util.Arrays;

public class QuickSortAlgo {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 10, 5, 9, 12, 20, 11}; // Output: 2, 3, 5, 9, 10, 11, 12, 20

        quickSort(arr, 0, arr.length-1);

        Arrays.stream(arr).forEach(i -> System.out.print(i + " "));
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

        int i = start;
        for(int j=start; j < end; j++) {
            if(arr[j] <= pivot) {
                swap(arr, i++, j);
            }
        }

        swap(arr, i, end);
        return i;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
