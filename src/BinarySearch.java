public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 8, 12, 13, 23, 38, 56, 72, 91};
        int x = 23; // Find the index of x in the given sorted array

        System.out.println("Index of x using recursion: " + binarySearchRecursive(arr, 0, arr.length - 1, x));
        System.out.println("Index of x using iteration: " + binarySearchIterative(arr, x));
    }

    static int binarySearchRecursive(int[] arr, int start, int end, int x) {
        if (start > end) return -1;

        int mid = (start + end) / 2;
        if (arr[mid] == x) return mid;
        if (x > arr[mid])
            return binarySearchRecursive(arr, mid + 1, end, x);
        else
            return binarySearchRecursive(arr, start, mid - 1, x);
    }

    static int binarySearchIterative(int[] arr, int x) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == x) return mid;

            if (x > arr[mid])
                start = mid + 1;
            else
                end = mid - 1;
        }
        return -1;
    }
}
